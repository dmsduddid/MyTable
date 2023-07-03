package com.spring.mytable.service.api;

import com.spring.mytable.domain.api.MsgApiDTO;

public interface MsgAPIService {

	public MsgApiDTO selectRegisterList(MsgApiDTO dto);
	
	public void sendTalk(MsgApiDTO dto);
	
	public String makeSignature(String url, String timestamp, String method,String accessKey, String secretKey, String serviceId ) throws Exception;
}
