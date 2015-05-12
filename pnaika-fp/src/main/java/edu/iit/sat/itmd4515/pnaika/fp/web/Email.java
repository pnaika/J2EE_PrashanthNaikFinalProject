/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.pnaika.fp.web;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Prashanth
 */
public class Email {

    private String userEmail;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    
    
    public Email() {    
   
    }

    public Email(String userEmail) {
        this.userEmail = userEmail;
        
        final String username = "prashanthpnaika@gmail.com";
        final String password = "kesavraj";
        
	Properties props = new Properties();
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.port", "587");
 
	Session session = Session.getInstance(props,
	new javax.mail.Authenticator() {
	protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
            }
	});
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("username"));
            message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(userEmail));
            message.setSubject("Global Parking System");
            message.setText("Dear New User!,"
				+ "\n\n your registration is complete, Thank you!");
 
            Transport.send(message);
            System.out.println("Done");
 
	} catch (MessagingException e) {
			throw new RuntimeException(e);
            }
    }
   }
