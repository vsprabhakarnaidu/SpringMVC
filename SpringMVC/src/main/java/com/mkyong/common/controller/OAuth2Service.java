package com.mkyong.common.controller;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class OAuth2Service {
	
	private String accessToken;

	private   String CLIENT_ID     = "CLIENT_ID";
    private   String CLIENT_SECRET = "CLIENT_SECRET";
    private   String REDIRECT_URI = "REDIRECT_URI";
    private   String USER_NAME = "USER_NAME";
    private   String PASSWORD = "PASSWORD";
    
    private   String AUTH_URL = "https://accounts.google.com/o/oauth2/v2/auth";
    private   String API_URL = "https://www.googleapis.com";
    private String redirect_url = "http://127.0.0.1:8080/SpringMVC/google/oauth2callback";
   
    
	public OAuth2Service(String cLIENT_ID, String cLIENT_SECRET, String rEDIRECT_URI, String aUTH_URL, String aPI_URL,String uName,String pwd) {
		super();
		this.CLIENT_ID = cLIENT_ID;
		this.CLIENT_SECRET = cLIENT_SECRET;
		this.REDIRECT_URI = rEDIRECT_URI;
		this.AUTH_URL = aUTH_URL;
		this.API_URL = aPI_URL;
		this.USER_NAME = uName;
		this.PASSWORD = pwd;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public String getAccessToken() throws JSONException {
		if(accessToken==null){
			getNewAccessToken();
		}
		return accessToken;
	}
	
	public void getNewAccessToken() throws JSONException{
		PostMethod post = new PostMethod(AUTH_URL);
		post.addParameter("client_id",CLIENT_ID);
		post.addParameter("client_secret",CLIENT_SECRET);
		//post.addParameter("username",USER_NAME);
		//post.addParameter("password",PASSWORD);
		HttpClient client = new HttpClient();
		try {
			client.executeMethod(post);
			JSONObject authResponse = new JSONObject(post.getResponseBodyAsString());
			String accessToken = authResponse.getString("access_token");
			String tokenType = authResponse.getString("token_type");
			setAccessToken(tokenType+" " +accessToken);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
