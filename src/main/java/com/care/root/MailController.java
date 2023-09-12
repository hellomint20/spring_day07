package com.care.root;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.care.root.config.TestClass;

@Controller
public class MailController {
	@Autowired MailService ms;
	@Autowired TestClass testclass;
	
	@GetMapping("send_mail")
	public void sendMail(HttpServletResponse res) throws IOException {
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		
		ms.send("shroad213@naver.com", "테스트 메일(제목)", "안녕하세요");
		
		out.print("메일을 전송했습니다 : "+testclass.getName());
	}
	@GetMapping("send_mail2")
	public void sendMai2l(HttpServletResponse res) throws IOException {
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		
		ms.send2();
		
		out.print("메일을 전송했습니다 : "+testclass.getName());
	}
	@GetMapping("auth")
	public String authForm() {
		return "auth";
	}
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:auth";
	}
	@PostMapping("auth_check")
	public String authCheck(HttpSession session, @RequestParam String email) {
		
		String[] e = email.split("@");
		System.out.println(e[0]);
		System.out.println(e[1]);
		
		ms.send3(e[0], session, email);
		
		return "redirect:https://www."+e[1];
	}
	@GetMapping("check")
	public String check(@RequestParam String userId, @RequestParam String userKey, HttpSession session) {
	
		String sessionKey = (String)session.getAttribute(userId);
		if(sessionKey.equals(userKey)) {
			session.setAttribute("userId", userId);
		}
		return "redirect:auth";
	}
}
