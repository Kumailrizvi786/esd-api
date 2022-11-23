package com.code.develop.service;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import com.code.develop.data.AngelTable;
import com.code.develop.data.LoginTable;
import com.code.develop.data.SignupTable;
import com.code.develop.model.SignupData;
import com.code.develop.repository.AngelRepository;
import com.code.develop.repository.LoginRepository;
import com.code.develop.repository.SignupRepository;
import com.code.develop.service.MailService;
import com.code.develop.util.*;


@Service
public class signupService {
	
	private static final Logger log = LoggerFactory.getLogger(signupService.class);
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private RandomPassword randomPassword;

	
	
	
	@Autowired
	SignupRepository repo;
	
	@Autowired
	LoginRepository repository;
	
	
	@Autowired
	AngelRepository re;
	
	String hashcpy;
	public boolean saveUser(SignupData signup) {
	
		String hash = randomPassword.apacheCommonRandomPassword();
		hashcpy = hash;
		SignupTable obj = new SignupTable();
		obj.setEmail(signup.getEmail());
		obj.setContact_no(signup.getContact_no());
		obj.setFirstName(signup.getFirstName());
		obj.setLastName(signup.getLastName());
		obj.setPassword(hash);
		boolean res;
		if (repo.save(obj) != null )
		{
			Long id = obj.getId();
			res = saveAngelUserinTable(signup);
			if (res == true)
				mailService.sendEmail(obj);
			return true;
		}
		return false;
	}
	
	
	
	public boolean saveEmailPassword(SignupData signup) {
		log.info(" Generated Hash value and stored " + hashcpy.toString());
		LoginTable obj1 = new LoginTable();
		obj1.setEmail(signup.getEmail());
		obj1.setPassword(hashcpy);
		repository.save(obj1);
		return true;
	}





	public boolean saveAngelUserinTable(SignupData signUp) {
		// TODO Auto-generated method stub
		AngelTable obj = new AngelTable();
		
		String con =  signUp.getFirstName() + signUp.getLastName();
		obj.setAhName(con);
		obj.setAhContactNumber(signUp.getContact_no());
		obj.setAhEmail(signUp.getEmail());
		//obj.setAhId(id);
		 if ( re.save(obj) != null ) {
			return true; 
		 }
		 	return false;
		 
	}
	
	
}
