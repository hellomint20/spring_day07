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
		String subject = "test ����";
		
		StringBuffer sb = new StringBuffer();
		sb.append("<h1>test page</h1>");
		sb.append("<b>�� �� �� ��</b><br>");
		sb.append("<a href='https://www.naver.com/'>���̹�</a><br>");
		sb.append("<img src='https://item.kakaocdn.net/do/2441aa90eafe7f4b904618cda69baf29f43ad912ad8dd55b04db6a64cddaf76d'>");
		String body = sb.toString();
		
		MimeMessage msg = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, "utf-8");
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body, true); //true �� �־�� html �������� �����
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mailSender.send(msg);
	}
	public void send3(String userId, HttpSession session, String email) {
		String userKey = rand();
		session.setAttribute(userId, userKey);
		
		String body = "<h2>���� ���ּ���</h2>" + 
					"<a href='http://localhost:8085/root/check?userId="
					+userId+"&userKey="+userKey+"'>�����ϱ�</a>";
		
		MimeMessage msg = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, "utf-8");
			helper.setTo( email );
			helper.setSubject("���� ���ּ���");
			helper.setText(body, true); //true �� �־�� html �������� �����
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		mailSender.send(msg);
	}
	private String rand() {
		Random ran = new Random();
		String str = "";
		while(str.length() <= 20) {
			int num = ran.nextInt(75)+48; //����, ���� ����
			if((num >= 48 && num <= 57) || (num >=65 && num <=90) || (num >=97 && num <= 122)) {
				str += (char)num;
			}
		}
		return str;
	}
}
