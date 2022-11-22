package com.code.develop.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.code.develop.data.LoginTable;
import com.code.develop.data.SignupTable;
import com.code.develop.model.SignInData;
import com.code.develop.model.SignupData;

//import com.example.accessingdatajpa.AccessingDataJpaApplication;
//import com.example.accessingdatajpa.model.User;


/**
 * 
 * 
 *
 */
@Service
public class MailService {
	
	private static final Logger log = LoggerFactory.getLogger(MailService.class);


	@Autowired
	private JavaMailSender javaMailSender;

	/**
	 * 
	 * @param javaMailSender
	 */
	@Autowired
	public MailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	/**
	 * 
	 * @param user
	 * @throws MailException
	 */

	public void sendEmail(SignupTable user) throws MailException {

		SimpleMailMessage mail = new SimpleMailMessage();
		
		mail.setTo(user.getEmail());
		mail.setSubject("Bluebook ESD Reform Portal Random Password");
		mail.setText(" Thanks For Signing Up on Our Portal. Random password of user  :  "+ user.getPassword()+ " \n Use this password to log into your account");
		log.info(" Random password of user " +  user.getPassword());
		/*
		 * This send() contains an Object of SimpleMailMessage as an Parameter
		 */
		javaMailSender.send(mail);
	}
	
	
	
	/*
	 * 
	 * This is to send the mail on forget password option 
	 * 
	 */


	public void sendEmailForgetPassword(LoginTable user) throws MailException {

		SimpleMailMessage mail = new SimpleMailMessage();
		
		mail.setTo(user.getEmail());
		mail.setSubject("Bluebook ESD Reform Portal Forget Password");
		mail.setText(" Looks like you forgot your password \n Your password saved with us was :  "+ user.getPassword()+ " \n Use this password to log into your account");
		log.info(" Stored password of user " +  user.getPassword());
	
		javaMailSender.send(mail);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * This function is used to send mail that contains a attachment.
	 * 
	 * @param user
	 * @throws MailException
	 * @throws MessagingException
	 */
	public void sendEmailWithAttachment(SignupTable user) throws MailException, MessagingException {

		MimeMessage message = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo(user.getEmail());
		helper.setSubject("Email having an Attachment");
		helper.setText("For this email Please check the attached document .");

		
		ClassPathResource classPathResource = new ClassPathResource("esd-git.pptx");
		helper.addAttachment(classPathResource.getFilename(), classPathResource);

		javaMailSender.send(message);
	}

}