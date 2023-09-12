package com.care.root;

import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	@Autowired JavaMailSender mailSender;
	
	public void send(String to, String subject, String body) {
		
		MimeMessage msg = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, "utf-8");
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mailSender.send(msg);
	}
	public void send2() {
		String to = "shroad213@naver.com";
		String subject = "test 광고";
		
		StringBuffer sb = new StringBuffer();
		sb.append("<h1>test page</h1>");
		sb.append("<b>아 무 내 용</b><br>");
		sb.append("<a href='https://www.naver.com/'>네이버</a><br>");
		sb.append("<img src='https://item.kakaocdn.net/do/2441aa90eafe7f4b904618cda69baf29f43ad912ad8dd55b04db6a64cddaf76d'>");
		String body = sb.toString();
		
		MimeMessage msg = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, "utf-8");
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body, true); //true 가 있어야 html 형식으로 적용됨
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mailSender.send(msg);
	}
	public void send3(String userId, HttpSession session, String email) {
		String userKey = rand();
		session.setAttribute(userId, userKey);
		
		String body = "<h2>인증 해주세요</h2>" + 
					"<a href='http://localhost:8085/root/check?userId="
					+userId+"&userKey="+userKey+"'>인증하기</a>";
		
		MimeMessage msg = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, "utf-8");
			helper.setTo( email );
			helper.setSubject("인증 해주세요");
			helper.setText(body, true); //true 가 있어야 html 형식으로 적용됨
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		mailSender.send(msg);
	}
	private String rand() {
		Random ran = new Random();
		String str = "";
		while(str.length() <= 20) {
			int num = ran.nextInt(75)+48; //숫자, 문자 조합
			if((num >= 48 && num <= 57) || (num >=65 && num <=90) || (num >=97 && num <= 122)) {
				str += (char)num;
			}
		}
		return str;
	}
}
