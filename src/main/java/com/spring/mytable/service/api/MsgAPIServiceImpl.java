package com.spring.mytable.service.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.spring.mytable.domain.api.MsgApiDTO;
import com.spring.mytable.persistence.JoinMapper;
import com.spring.mytable.persistence.MsgAPIMapper;
import com.spring.mytable.service.JoinServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class MsgAPIServiceImpl implements MsgAPIService {
	

	private final MsgAPIMapper mapper;
	private final String accessKey = "ufTkXtu3lMquLMsHPycn";
	private final String secretKey = "SglTeFfNDfHpsLvdWvlml5nOB2RMyP5CTEChX6zf";
	private final String serviceId ="ncp:kkobizmsg:kr:3038527:tablingproject";
	
	@Override
	public MsgApiDTO selectRegisterList(MsgApiDTO dto) {
		
		return mapper.selectMsgAPIInfo(dto);
	}

	@Override
	public void sendTalk(MsgApiDTO dto) {
		
		String method = "POST";
		String timestamp = Long.toString(System.currentTimeMillis());
		String hostNameUrl = "https://sens.apigw.ntruss.com"; // 호스트 URL
		String requestUrl = "/alimtalk/v2/services/" + serviceId + "/messages";// 요청 URL
		String apiUrl = hostNameUrl + requestUrl;
		String username = dto.getUsername();
		String storename = dto.getStorename();
		int phone = dto.getPhone();
		
		System.out.println(serviceId);

		// JSON body data 생성
		JSONObject bodyJson = new JSONObject();
		JSONObject msgJson = new JSONObject();
		JSONArray msgArr = new JSONArray();
		//JSONObject btnJson = new JSONObject();
		//JSONArray btnArr = new JSONArray();
			
		bodyJson.put("plusFriendId", "@rb_songjong"); 
		bodyJson.put("templateCode", "type2"); //템플릿 등록 후 검수완료된 코드값 입력
		
		msgJson.put("countryCode","82");
		msgJson.put("to", phone);//알림톡 받을 유저 전화번호
		msgJson.put("content", "안녕하세요.\n" + username +"님.\n" + storename +" 지금 바로 입장해주세요!\n" + "문자 수신 후 5분이 지나면 자동 입장 취소됩니다."); 
        //등록한 템플릿과 똑같이 입력해야함
		msgArr.add(msgJson);
		bodyJson.put("messages", msgArr);

		String body = bodyJson.toString();
		System.out.println(body);

		
		try {
			URL url = new URL(apiUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setUseCaches(false);
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setRequestProperty("content-type", "application/json");
			con.setRequestProperty("x-ncp-apigw-timestamp", timestamp);
			con.setRequestProperty("x-ncp-iam-access-key", accessKey);
			con.setRequestProperty("x-ncp-apigw-signature-v2",makeSignature(requestUrl, timestamp, method, accessKey, secretKey, serviceId));
			con.setRequestMethod(method);
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			
			wr.write(body.getBytes());
			wr.flush();
			wr.close();
			
			int responseCode = con.getResponseCode();
			BufferedReader br;
			System.out.println("responseCode" + " " + responseCode);
			if (responseCode == 202) { // 정상호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			
			System.out.println(response.toString());
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	@Override
	public String makeSignature(String url, String timestamp, String method, String accessKey, String secretKey,
			String serviceId) throws Exception {
		String space = " "; // one space
		String newLine = "\n"; // new line
		
		String message = new StringBuilder()
				.append(method)
				.append(space)
				.append(url)
				.append(newLine)
				.append(timestamp)
				.append(newLine)
				.append(accessKey)
				.toString();
		
		SecretKeySpec signingKey;
	    String encodeBase64String;

		try {
			signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(signingKey);
			byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
			encodeBase64String = Base64.getEncoder().encodeToString(rawHmac);
		} catch (UnsupportedEncodingException e) {

			encodeBase64String = e.toString();
		}
		return encodeBase64String;
	}
}
