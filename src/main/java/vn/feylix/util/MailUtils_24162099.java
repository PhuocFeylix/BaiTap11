package vn.feylix.util;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.io.InputStream;
import java.util.*;

public final class MailUtils_24162099 {
	private MailUtils_24162099() {
	}

	public static void sendOtp(String to, String otp, String purpose) throws Exception {
		Properties p = new Properties();
		try (InputStream in = MailUtils_24162099.class.getClassLoader().getResourceAsStream("mail.properties")) {
			if (in == null)
				throw new IllegalStateException("Thiếu mail.properties");
			p.load(in);
		}
		String user = p.getProperty("mail.username"), pass = p.getProperty("mail.password"),
				from = p.getProperty("mail.from", user);
		if (user == null || user.startsWith("YOUR_"))
			throw new IllegalStateException("Chưa cấu hình Gmail App Password trong mail.properties");
		Session s = Session.getInstance(p, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, pass);
			}
		});
		MimeMessage m = new MimeMessage(s);
		m.setFrom(new InternetAddress(from));
		m.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		m.setSubject("Mã OTP - " + purpose, "UTF-8");
		m.setText("Mã OTP của bạn là: " + otp + "\nMã có hiệu lực trong 5 phút.", "UTF-8");
		Transport.send(m);
	}
}
