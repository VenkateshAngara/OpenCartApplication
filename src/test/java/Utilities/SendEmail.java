package Utilities;

import java.util.Properties;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail {
	public static void sendMail(String filePath)  {
		String fromEmail="venkateshangara666@gmail.com";
		String fromPassword="gkih pcvu mclt bjyq";
		String toEmail="venkateshangara769@gmail.com";
		String subject="Test Reports of the OpenCart Application";
		String Body="Hello, This is a Email regarding the Reports of the OpenCart Application";
		
		Properties prop= new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		
		Session session=Session.getInstance(prop,new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail,fromPassword);
			}
		});
		try {
			Message message= new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
			message.setSubject(subject);
			
			BodyPart mesaageBodyPart= new MimeBodyPart();
			mesaageBodyPart.setText(Body);
			Multipart multipart=new MimeMultipart();
			multipart.addBodyPart(mesaageBodyPart);
			mesaageBodyPart=new MimeBodyPart();
			DataSource source= new FileDataSource(filePath);
			mesaageBodyPart.setDataHandler(new DataHandler(source));
			mesaageBodyPart.setFileName("Report.html");
			multipart.addBodyPart(mesaageBodyPart);
			message.setContent(multipart);
			Transport.send(message);
			System.out.println("Email sent Successfully !");
		}catch(MessagingException e) {
			e.printStackTrace();
		}	
	}

}
