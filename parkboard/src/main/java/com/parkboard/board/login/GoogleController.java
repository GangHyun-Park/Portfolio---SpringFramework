package com.parkboard.board.login;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class GoogleController {
	private final static String Google_CLIENT_ID = "1006017538645-bu6sf380q78em1vtb3th07tugs5sjeh7.apps.googleusercontent.com"; 
	private final static String Google_REDIRECT_URI = "http://localhost:8080/login/googleLogin"; 
	public static String getAuthorizationUrl(HttpSession session) {
		
		String googleUrl = "https://accounts.google.com/o/oauth2/v2/auth?" 
						+ "client_id=" + Google_CLIENT_ID 
						+ "&redirect_uri=" + Google_REDIRECT_URI 
						+ "&response_type=code" 
						+ "&scope=email%20profile%20openid"
						+ "&access_type=offline"; 
		return googleUrl; 
	}
	
	public static String getAccessToken(String authorize_code) { 
		
		String access_Token = ""; 
		String reqURL = "https://www.googleapis.com/oauth2/v4/token"; 
		
		try { 
			URL url = new URL(reqURL); 
			HttpURLConnection conn = (HttpURLConnection) url.openConnection(); 

			conn.setRequestMethod("POST"); 
			conn.setDoOutput(true); 
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream())); 
			StringBuilder sb = new StringBuilder(); 
			sb.append("grant_type=authorization_code"); 
			sb.append("&client_id=1006017538645-bu6sf380q78em1vtb3th07tugs5sjeh7.apps.googleusercontent.com"); 
			sb.append("&client_secret=jOmy_qvKInCEEDkccgSOFGZd"); 
			sb.append("&redirect_uri=http://localhost:8080/login/googleLogin"); 
			sb.append("&code="+authorize_code); 
			sb.append("&state=url_parameter"); 
			bw.write(sb.toString()); 
			bw.flush();
			int responseCode = conn.getResponseCode(); 
			
			if(responseCode==200){ 
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
				String line = ""; 
				String result = ""; 
				while ((line = br.readLine()) != null) { 
					result += line; 
				}
				JsonParser parser = new JsonParser(); 
				JsonElement element = parser.parse(result); 
				System.out.println("result : "+result); 
				access_Token = element.getAsJsonObject().get("access_token").getAsString(); 
				br.close(); 
				bw.close(); 
				} 
			} catch (IOException e) { 
				e.printStackTrace(); 
			} 
		return access_Token;
	}
	
	public static Map<String,Object> getGoogleUserInfo(String access_Token)  { 
		String name = "";
		String email = "";
		
		Map<String, Object> profile = new HashMap<>();
		try {
			String reqURL = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token="+access_Token;
			URL url = new URL(reqURL);
			String line = "";
            String result = "";
			BufferedReader br;
            br = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
            while ((line = br.readLine()) != null) {
                result = result.concat(line);
            }            

            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject)parser.parse(result);
            name = (String)obj.get("name");
            email = (String)obj.get("email");
            
            profile.put("name", name);
            profile.put("email", email);
		} catch(Exception e){
			e.printStackTrace();
		} 
				
		return profile;
	}

}
	

