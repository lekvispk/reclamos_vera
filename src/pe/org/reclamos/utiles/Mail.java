package pe.org.reclamos.utiles;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
 
public class Mail {

	private JavaMailSender mailSender;

	public void setMailSender(JavaMailSender mailSender) {
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