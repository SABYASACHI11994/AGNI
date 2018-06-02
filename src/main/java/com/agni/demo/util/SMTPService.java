package com.agni.demo.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class SMTPService
{
	private static String host = ConfigProperties.getPropertyByName("mail.smtp.host");
	private static String socketport = ConfigProperties.getPropertyByName("mail.smtp.socketFactory.port");
	private static String mailClass = ConfigProperties.getPropertyByName("mail.smtp.socketFactory.class");
	private static String auth = ConfigProperties.getPropertyByName("mail.smtp.auth");
	private static String port = ConfigProperties.getPropertyByName("mail.smtp.port");
	private static String from = ConfigProperties.getPropertyByName("username");
	private static String password = ConfigProperties.getPropertyByName("password");
			
	public static Integer send(String to,String sub,String msg){  
        //Get properties object    
		Integer sent=0;
        Properties props = new Properties();    
        props.put("mail.smtp.host", host);    
        props.put("mail.smtp.socketFactory.port", socketport);    
        props.put("mail.smtp.socketFactory.class",    
        		mailClass);    
        props.put("mail.smtp.auth", auth);    
        props.put("mail.smtp.port", port);    
        //get Session   
        Session session = Session.getDefaultInstance(props,    
         new javax.mail.Authenticator() {    
         protected PasswordAuthentication getPasswordAuthentication() {    
         return new PasswordAuthentication(from,password);  
         }    
        });    
        //compose message    
        try {    
         MimeMessage message = new MimeMessage(session);    
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
         message.setSubject(sub);    
         message.setText(msg);    
         //send message  
         Transport.send(message);    
         System.out.println("message sent successfully");   
         sent=1;
        } catch (MessagingException e) {throw new RuntimeException(e);}  
        return sent;
           
  }  
}

