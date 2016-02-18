package com.mkyong.common.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.codehaus.jettison.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/google")
public class Google {
	
	private static String CLIENT_ID     = "1055672842849-pa4rk2sfp46jvbc4l7hk068vk1f24kfl.apps.googleusercontent.com";
    private static String CLIENT_SECRET = "rR6dmhTWGKA9YFCmpYDsmOOJ";
    private static String REDIRECT_URI = "http://prabhajanu.com/SpringMVC/oauth2callback";

    private static String AUTH_URL = "https://accounts.google.com/o/oauth2/auth";
    private static String API_URL = "https://www.googleapis.com";
    
    private static String USER_NAME = "prabhakar747@gmail.com";
    private static String PASSWORD = "RPLSP747";
    private String redirect_url = "http://localhost:8080/SpringMVC/google/oauth2callback";
    
    private String server_key = "AIzaSyDPiP4Loucfpj46nGBHP1YJ6jLRu8m8x7Y";
    private String browser_key = "AIzaSyBfSAP8CbiWx5GxRMM95Y5jWAqaEcc0bxY";
    
    @RequestMapping("/oauth")
    public String authorize() throws JSONException, UnsupportedEncodingException{
    	
    	//OAuth2Service oauthService = new OAuth2Service(CLIENT_ID, CLIENT_SECRET, REDIRECT_URI, AUTH_URL, API_URL,USER_NAME,PASSWORD);
    	
    	String google_login_url = AUTH_URL+"?response_type=code&client_id="+CLIENT_ID+"&scope=email&redirect_uri="+URLEncoder.encode(redirect_url, "UTF-8");
    	
    	
    	PostMethod post = new PostMethod(google_login_url);
    	//post.addRequestHeader("Authorization",oauthService.getAccessToken());
    	
    	HttpClient client = new HttpClient();
    	try {
			client.executeMethod(post);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:"+google_login_url;
    }
    
    @RequestMapping("/oauth2callback")
    public String callback(HttpServletRequest  request,HttpServletRequest response){
    	System.out.println("Call back came to handler");
    	
    	String drive_url = "https://www.googleapis.com/drive/v2/files/?key="+server_key;
    	
    	GetMethod get = new GetMethod(drive_url);
    	HttpClient client = new HttpClient();
    	try {
			client.executeMethod(get);
			System.out.println(get.getResponseBodyAsString());
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return "GoogleCallback";
    	
    }
    
		    
}
