package com.example.PipiShrimp.entity;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import java.util.Properties;

import javax.mail.PasswordAuthentication;

public class Mail {
	// gmail金鑰
	private static String pwd = "tcog rtbx cbqx jyrm";

	// 取得使用者ip
//	private static String getClientIP(HttpServletRequest request) {
//		String ipAddress = request.getHeader("X-Forwarded-For");
//		if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
//			ipAddress = request.getHeader("Proxy-Client-IP");
//		}
//		if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
//			ipAddress = request.getHeader("WL-Proxy-Client-IP");
//		}
//		if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
//			ipAddress = request.getRemoteAddr();
//		}
//		return ipAddress;
//	}

	// 註冊成功發送通知信件
	public static void sentSignUpMail(String email) {

		// 寄件人
		String senderName = "皮皮蝦負責人沒料彥茗";
		String senderEmail = "ian20000217@gmail.com";
		String senderPassword = pwd;

		// 收件人
		String recipientEmail = email;

		// 設定SMTP
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com"); // 以 Gmail 为例
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2"); // 指定協議

		// 創建 Session
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmail, senderPassword);
			}
		});

		try {
			// 設定 MimeMessage
			Message message = new MimeMessage(session);

			// 設定寄件人
			message.setFrom(new InternetAddress(senderEmail, senderName));

			// 設定收件人
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));

			// 設定信件主題
			message.setSubject("註冊成功");

			// 設定信件內容
			message.setText("你成為了皮皮蝦會員，負責人沒料彥茗感謝你的加入");

			// 發送信件
			Transport.send(message);

			System.out.println("發送成功!!!");

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("發送失敗!!!");
		}
	}

	// 登入成功通知使用者信件
	// TODO 取得登入者IP
	public static void sentLoginMail(String email) {

		// 寄件人
		String senderName = "皮皮蝦負責人沒料彥茗";
		String senderEmail = "ian20000217@gmail.com";
		String senderPassword = "tcog rtbx cbqx jyrm";

		// 收件人
		String recipientEmail = email;

		// 設定SMTP
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com"); // 以 Gmail 为例
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2"); // 指定協議

		// 創建 Session
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmail, senderPassword);
			}
		});

		try {
			// 設定 MimeMessage
			Message message = new MimeMessage(session);

			// 設定寄件人
			message.setFrom(new InternetAddress(senderEmail, senderName));

			// 設定收件人
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));

			// 設定信件主題
			message.setSubject("登入通知");

			// 設定信件內容
			message.setText("登入成功!!!");

			// 發送信件
			Transport.send(message);

			System.out.println("發送成功!!!");

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("發送失敗!!!");
		}
	}

}
