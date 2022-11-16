package com.code.develop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.develop.data.LoginTable;
import com.code.develop.data.SignupTable;
import com.code.develop.model.SignupData;
import com.code.develop.repository.LoginRepository;
import com.code.develop.repository.SignupRepository;

@Service
public class signupService {
	
	@Autowired
	SignupRepository repo;
	
	@Autowired
	LoginRepository repository;
	
public boolean saveUser(SignupData signup) {
		
		SignupTable obj = new SignupTable();
		obj.setEmail(signup.getEmail());
		obj.setContact_no(signup.getContact_no());
		obj.setFirstName(signup.getFirstName());
		obj.setLastName(signup.getLastName());
		obj.setPassword(signup.getPassword());
		repo.save(obj);
		return true;
	}
	
	
	
	public boolean saveEmailPassword(SignupData signup) {
		
		LoginTable obj1 = new LoginTable();
		obj1.setEmail(signup.getEmail());
		obj1.setPassword(signup.getPassword());
		repository.save(obj1);
		return true;
	}
}
