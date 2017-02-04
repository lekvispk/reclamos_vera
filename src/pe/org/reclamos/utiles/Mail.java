package pe.org.reclamos.utiles;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class Mail {

	private static final Logger logger = Logger.getLogger(Mail.class );
	
	JavaMailSender mailSender;

	public JavaMailSender getMailSender() {
		System.out.println("get mail sender ");
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		System.out.println("SET mail sender "+mailSender);
		this.mailSender = mailSender;
	}

	/**
	 * 
	 * 
	 * @param from quien envia el email
	 * @param to para quien va dirigido el email 
	 * @param cc con copia a 
	 * @param subject asunto
	 * @param msg mensaje
	 */
	public void sendMail(String from, String to, String cc, String subject,	String msg) {
		try {
			logger.debug( "mailSender=" + mailSender );
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			if( !Utiles.nullToBlank( cc ).equals(""))
				helper.setCc(cc);
			helper.setSubject(subject);
			helper.setText(msg, true);
			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendMail(String from, String to[], String cc[], String bcc[], String subject,	String msg) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setCc(cc);
			if(bcc!=null)helper.setBcc(bcc);
			helper.setSubject(subject);
			helper.setText(msg, true);
			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
			
}