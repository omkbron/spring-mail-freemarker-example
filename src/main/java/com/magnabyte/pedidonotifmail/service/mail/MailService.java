package com.magnabyte.pedidonotifmail.service.mail;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.core.io.FileSystemResource;

import freemarker.template.TemplateException;

public interface MailService {
	void sendMail(String message, String subject, String... recipients);

	void sendMimeMail(String message, String messageHtml, String subject,
			String... recipients) throws MessagingException;

	void sendMailWithAttach(String message, String messageHtml, String subject,
			FileSystemResource[] attach, String... recipients)
			throws MessagingException;

	void sendMailWithEngine(String message, String subject, String template,
			Map model, String... recipients) throws MessagingException,
			IOException, TemplateException;
}
