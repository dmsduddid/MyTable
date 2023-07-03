package com.spring.mytable.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.spring.mytable.domain.UserDTO;
import com.spring.mytable.persistence.JoinMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class JoinServiceImpl implements JoinService{
	
	private final JoinMapper user;

	@Override
	public int joinMember(UserDTO dto) {
		return user.insertMember(dto);
	}

	@Override
	public int idCheck(UserDTO dto) {
		return user.idCheck(dto);
	}

	@Override
	public int registerOwner(UserDTO dto) {
		return user.insertOwner(dto);
	}

	@Override
	public int registerCustomer(UserDTO dto) {
		return user.insertCustomer(dto);
	}
	
	@Override
	public String getKakaoToken(String code) {
		
		String accessToken = "";
		String reqUrl = "https://kauth.kakao.com/oauth/token";
		
		try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=d067c0b24f657bdcf9a8372ba1af258f"); // TODO REST_API_KEY 입력
            sb.append("&redirect_uri=http://localhost:9007/mytable/kakao"); // TODO 인가코드 받은 redirect_uri 입력
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();

            //결과 코드가 200이면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            //Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            accessToken = element.getAsJsonObject().get("access_token").getAsString();

            System.out.println("accessToken : " + accessToken);

            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return accessToken;
    }
	
	@Override
	public List<UserDTO> getUserInfo(String accessToken){
		
		UserDTO userDTO = new UserDTO();
		String reqURL = "https://kapi.kakao.com/v2/user/me";

	    //access_token을 이용하여 사용자 정보 조회
	    try {
	       URL url = new URL(reqURL);
	       HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	       conn.setRequestMethod("POST");
	       conn.setDoOutput(true);
	       conn.setRequestProperty("Authorization", "Bearer " + accessToken); //전송할 header 작성, access_token전송

	       //결과 코드가 200이라면 성공
	       int responseCode = conn.getResponseCode();
	       System.out.println("responseCode : " + responseCode);

	       //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
	       BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	       String line = "";
	       String result = "";

	       while ((line = br.readLine()) != null) {
	           result += line;
	       }
	       System.out.println("response body : " + result);

	       //Gson 라이브러리로 JSON파싱
	       JsonParser parser = new JsonParser();
	       JsonElement element = parser.parse(result);
	       JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();

	       //int id = element.getAsJsonObject().get("id").getAsInt();
	       boolean hasEmail = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("has_email").getAsBoolean();
	       String email = "";
	       String nickname = "";
	       System.out.println("-------------------------");
	       System.out.println("hasEmail = " + hasEmail);
	       if(hasEmail){
	           email = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
	           nickname = properties.getAsJsonObject().get("nickname").getAsString();
	           userDTO.setEmail(email);
	           userDTO.setName(nickname);
	       }

	       //System.out.println("id : " + id);
	       System.out.println("email : " + email);

	       br.close();

	       } catch (IOException e) {
	            e.printStackTrace();
	       }
	    
	    ArrayList<UserDTO> array = new ArrayList();
		
		array.add(userDTO);
		
		System.out.println("service----------------------" + userDTO);
		
		// catch 아래 코드 추가.
		UserDTO result = user.findKakaoID(userDTO);
		
		array.add(result);
		
		return array;
	 }
	
}
