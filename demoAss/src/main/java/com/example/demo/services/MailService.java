 package com.example.demo.services;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EmailDTO;

import java.nio.charset.StandardCharsets;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {
	
    final static String username = "chieudantan@gmail.com";
    
    private final JavaMailSender javaMailSender ;
    
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public boolean sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml, String path) {
//    	isHtml: noi dung content co phai la html k
//    	inmultipart : bat tat che do mutilpart
//    	String to : gui den dia chi
//    	String subject : tieu de mail
//    	String content : noi dungf mailk
//    	path link dan file 
        // Prepare message using a Spring helper
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        if(path!=null||path.equals("")) {
//        FileSystemResource file = new FileSystemResource(path);
//        	}
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, StandardCharsets.UTF_8.name());
            message.setTo(to);
            message.setFrom(username);
            message.setSubject(subject);
            System.out.println("subject: "+subject);
            message.setText(content, isHtml);
            System.out.println("content: "+content);
//            doctor send pdf to patients
            if(path!=null) {
                FileSystemResource file = new FileSystemResource(path);
                message.addAttachment("pdf", file);
            }
            javaMailSender.send(mimeMessage);
//            log.debug("Sent email to User '{}'", to);
            return true;
        } catch (MailException | MessagingException e) {
        	e.printStackTrace();
        	return false;
        }
    }
    
    public boolean sendEmail(EmailDTO emailDTO) {
    	String to = emailDTO.getTo();
    	String subject = emailDTO.getSubject();
    	String content = emailDTO.getContent();
    	boolean isMultipart = true;
    	boolean isHtml = emailDTO.isHtml;
    	String path = emailDTO.getPath();
//    	isHtml: noi dung content co phai la html k
//    	inmultipart : bat tat che do mutilpart / o day la true de gui file
//    	String to : gui den dia chi
//    	String subject : tieu de mail
//    	String content : noi dungf mailk
//    	path link dan file 
        // Prepare message using a Spring helper
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        if(path!=null||path.equals("")) {
//        FileSystemResource file = new FileSystemResource(path);
//        	}
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
            message.setTo(to);
            message.setFrom(username);
            message.setSubject(subject);
            System.out.println("subject: "+subject);
            message.setText(content, false);
            System.out.println("content: "+content);
//            doctor send pdf to patients
            if(path!=null) {
                FileSystemResource file = new FileSystemResource(path);
                message.addAttachment("pdf", file);
            }
            javaMailSender.send(mimeMessage);
//            log.debug("Sent email to User '{}'", to);
            return true;
        } catch (MailException | MessagingException e) {
        	e.printStackTrace();
        	return false;
//            log.warn("Email could not be sent to user '{}'", to, e);

        }
    }

}
