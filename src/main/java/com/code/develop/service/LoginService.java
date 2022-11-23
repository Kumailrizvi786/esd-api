package com.code.develop.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.code.develop.data.LoginTable;
import com.code.develop.model.SignInData;
import com.code.develop.model.changePasswordData;
import com.code.develop.model.forgetpasswordData;
import com.code.develop.repository.LoginRepository;

@Service
public class LoginService{
	
	@Autowired
	private MailService mailService;

	@Autowired
	LoginRepository repository;
	
	
	public Long validateLogin(SignInData signIn) {
		
	      return repository.findByEmailAndPassword(signIn.getEmail(), signIn.getPassword());
	    
		//query  " select count (*) from LoginTable where email ='singIn.email and passowrd ='singin.password';
	}
	
	/*
	 * 
	 * 
	 * 
	 */

	public  boolean validatePassword(changePasswordData signIn) {
		LoginTable obj = new LoginTable();
		Long res =repository.findByEmailAndOldPassword(signIn.getEmail(),signIn.getOld_password());
		if(res>0) {
			repository.deleteById(res);
			obj.setId(res);
			obj.setEmail(signIn.getEmail());
			obj.setPassword(signIn.getNew_password());
			repository.save(obj);
			return true;
		}
		else {
			return false;
		}
}
	
	/*
	 * 
	 * 
	 */
	public boolean forgetPassword(forgetpasswordData user) {
		LoginTable obj = new LoginTable();
		obj.setEmail(user.getEmail());
		String result =  repository.findPasswordByEmail(obj.getEmail());
		
		if(obj.getEmail() != null) {
			obj.setPassword(result);
			mailService.sendEmailForgetPassword(obj);
			return true;
		}
		else 
			return false ;
	}
}