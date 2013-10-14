package com.magnabyte.pedidonotifmail.service.mail.impl;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.magnabyte.pedidonotifmail.service.mail.MailService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class MailServiceImpl implements MailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	@Qualifier("freemarkerConfiguration")
	private Configuration configuration;
	
	@Value("${email.from}")
	private String from;

	@Override
	public void sendMail(String message, String subject, String... recipients) {
		// TODO Auto-generated method stub
	}

	@Override
	public void sendMimeMail(String message, String messageHtml,
			String subject, String... recipients) throws MessagingException {
		// TODO Auto-generated method stub
	}

	@Override
	public void sendMailWithAttach(String message, String messageHtml,
			String subject, FileSystemResource[] attach, String... recipients)
			throws MessagingException {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void sendMailWithEngine(String message, String subject,
			String template, Map model, String... recipients)
			throws MessagingException, IOException, TemplateException {
		MimeMessage msg = javaMailSender.createMimeMessage();
		Template templateFreemarker = configuration.getTemplate(template);
		MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");
		helper.setTo(recipients);
		helper.setSubject(subject);
		helper.setFrom(from);
		String text = FreeMarkerTemplateUtils.processTemplateIntoString(templateFreemarker, model);
		helper.setText(message, text);
		javaMailSender.send(msg);
	}
}
