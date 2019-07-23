package com.ximuyi.demo.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Controller
@RequestMapping("/mail")
public class MailController {

	@Autowired
	private JavaMailSenderImpl mailSender;

	@GetMapping("")
	public String mailForm(Model model){
		model.addAttribute("mail", new Mail());
		return "/mail/mail";
	}

	@PostMapping("")
	@ResponseBody
	public ResponseEntity<String> postTxtForm(@ModelAttribute Mail mail){
		try {
			MultipartFile file = mail.getFile();
			if (file != null && !file.isEmpty()) {
				sendAttachment(mail);
			}
			else {
				sendText(mail);
			}
			return ResponseEntity.ok().body("Success!");
		}
		catch (Throwable throwable){
			return ResponseEntity.ok().body(throwable.getMessage());
		}
	}

	private void sendText(Mail mail){
		SimpleMailMessage message = new SimpleMailMessage();
		/***
		 * com.sun.mail.smtp.SMTPSendFailedException: 501 mail from address must be same as authorization user
		 * 增加如下代码，疑问：配置上不是写了用户名字了嘛？为什么还需要设置？
		 */
		message.setTo(new String[] {mail.getAddress()});
		message.setFrom(mailSender.getUsername());
		message.setSubject(mail.getSubject());
		message.setText(mail.getMessage());
		mailSender.send(message);
	}

	private void sendAttachment(Mail mail) throws MessagingException, IOException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(new String[] {mail.getAddress()});
		helper.setFrom(mailSender.getUsername());
		helper.setSubject(mail.getSubject());
		helper.setText(mail.getMessage());
		helper.addAttachment("Invoice", mail.getFile());
		mailSender.send(message);
	}
}
