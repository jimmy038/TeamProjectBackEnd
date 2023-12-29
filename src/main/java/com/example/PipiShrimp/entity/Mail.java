package com.example.PipiShrimp.entity;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import java.util.Properties;

import javax.mail.PasswordAuthentication;

public class Mail {
	// gmail金鑰
	private static String pwd = "ucpp jysp ivwx lkjy";

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
		String senderName = "賴皮購物";
		String senderEmail = "zz0257800000@gmail.com";
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
			message.setText("你成為了賴皮購物會員，賴皮購物你的加入");

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
		String senderName = "賴皮購物負責人";
		String senderEmail = "zz0257800000@gmail.com";
		String senderPassword = "ucpp jysp ivwx lkjy";

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
	// 忘記密碼 通知使用者信件
		public static void sentForgotPwdMail(String email,String cachedRandomPwd) {

			// 寄件人
			String senderName = "賴皮購物負責人";
			String senderEmail = "zz0257800000@gmail.com";
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
				message.setSubject("請重新設定您的密碼");

				// 設定信件內容
		        String emailContent = "這是您的一次性密碼 : " + cachedRandomPwd + "請先使用一次性密碼登入後再更改密碼，謝謝。"
		                + "在更改密碼頁面時驗證碼欄位即為您收到的一次性密碼，" + "皮皮蝦娛樂股份有限公司祝您身體健康。";
		        message.setText(emailContent);

				// 發送信件
				Transport.send(message);

				System.out.println("發送成功!!!");

			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("發送失敗!!!");
			}
		}

		
		// 更改密碼 通知使用者信件
		public static void sentChangePwdMail(String email) {

			// 寄件人
			String senderName = "賴皮購物負責人";
			String senderEmail = "zz0257800000@gmail.com";
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
				message.setSubject("更改密碼成功。");

				// 設定信件內容
				message.setText("謝謝您，皮皮蝦股份有限公司歡迎您的回歸。");

				// 發送信件
				Transport.send(message);

				System.out.println("發送成功!!!");

			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("發送失敗!!!");
			}
		}

}
