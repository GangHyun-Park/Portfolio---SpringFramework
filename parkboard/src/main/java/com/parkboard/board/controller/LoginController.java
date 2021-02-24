package com.parkboard.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.parkboard.board.login.GoogleController;
import com.parkboard.board.login.KakaoController;
import com.parkboard.board.login.NaverLoginBO;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
	
	//���̹�
	private NaverLoginBO naverLoginBO;
	private String apiResult = null;

	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;	
	}
	
	// �α���
	@RequestMapping(value="/loginForm", method = {RequestMethod.GET, RequestMethod.POST })
	public String loginForm(Model model,HttpSession session) throws Exception{
			
			//login���������� �� �Ҽȷα����� ���� �� �α��� url�� String���� ��Ƽ� return �����ش�
			String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
			String kakaoUrl = KakaoController.getAuthorizationUrl(session);
			String googleUrl = GoogleController.getAuthorizationUrl(session);
			
			model.addAttribute("url", naverAuthUrl);
			model.addAttribute("kakao_url", kakaoUrl);
			model.addAttribute("google_url", googleUrl);
			
			return "login/loginForm";
	}
	
	//���̹� �α��� �ݹ�
	@RequestMapping(value = "/callback", method = { RequestMethod.GET, RequestMethod.POST })
	public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session)
	throws IOException, ParseException{
		
		OAuth2AccessToken oauthToken; //��ū�� ���� ��ü ����

		oauthToken = naverLoginBO.getAccessToken(session, code, state); //naverLoginBO�� session,code,state ���� �����ϸ� ��ū�� ��û�Ѵ�.
		apiResult = naverLoginBO.getUserProfile(oauthToken); // �α����� ������ ������ ��û�Ѵ�. �̶� ��ū���� �Ѱ��ش�.
		
		JSONParser parser = new JSONParser(); // �α��� ������ json���·� �Ǿ��ֱ� ������ �Ľ����ش�.
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj = (JSONObject) obj;
		
		JSONObject response_obj = (JSONObject)jsonObj.get("response");
		String nickname = (String)response_obj.get("nickname");
		String email = (String)response_obj.get("email");
				
		session.setAttribute("sessionId", nickname);
		session.setAttribute("sessionMail", email);
        model.addAttribute("result", apiResult);
		
		return "etc/skill";
	}
	//īī�� �α��� �ݹ�
	@RequestMapping(value = "/kakaoLogin", produces = "application/json", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView kakaoLogin(@RequestParam("code") String code, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		JsonNode node = KakaoController.getAccessToken(code);
		JsonNode accessToken = node.get("access_token");
		// ������� ����
		JsonNode userInfo = KakaoController.getKakaoUserInfo(accessToken);
		String kname = null;
		String kmail = null;
		JsonNode properties = userInfo.path("properties");
		JsonNode account = userInfo.path("kakao_account");
		kname = properties.path("nickname").asText();
		kmail = account.path("email").asText();
		
		session.setAttribute("sessionId", kname);
		session.setAttribute("sessionMail", kmail);
		mav.setViewName("etc/skill");
		return mav;
	}
	
	//���� �α��� �ݹ�
	@RequestMapping(value = "/googleLogin" , method = {RequestMethod.GET, RequestMethod.POST}) 
	public ModelAndView googleLogin( @RequestParam("code") String code, HttpServletResponse response, HttpSession session ) throws IOException { 
		
		Map<String,Object> profile = new HashMap<>();
		ModelAndView mav = new ModelAndView();
		String gname = null;
		String gmail = null;
		
		String access_Token = GoogleController.getAccessToken(code); 
		profile =  GoogleController.getGoogleUserInfo(access_Token);
		
		gname = (String)profile.get("name");
		gmail = (String)profile.get("email");
		
		session.setAttribute("sessionId", gname);
		session.setAttribute("sessionMail", gmail);
		mav.setViewName("etc/skill");
		return mav;
	}
	

	
	//�α׾ƿ�
	@RequestMapping(value="/logoutForm", method = {RequestMethod.GET, RequestMethod.POST })
	public String logoutForm(Model model,HttpSession session) throws Exception{
		
			//���ǰ� apiresult���� �ʱ�ȭ ��Ų��
			session.invalidate();
			apiResult = null;
			return "etc/skill";
		
	}
}
