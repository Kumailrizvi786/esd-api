package com.code.develop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.develop.data.LoginTable;
import com.code.develop.data.SignupTable;
import com.code.develop.model.SignInData;
import com.code.develop.model.changePasswordData;
import com.code.develop.model.forgetpasswordData;
import com.code.develop.service.LoginService;


/*
 * 
 * @author Kumail Rizvi
 */

@CrossOrigin("*")
@RestController
@RequestMapping("/api")

public class LoginController {
	
	private static final Logger log =LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private LoginService service;
	
	@PostMapping("/loginhelper/login")
	public ResponseEntity<String> angelLogin(@ModelAttribute SignInData signIn) {
		log.info("login sign in request " + signIn.toString());
		int result = service.validateLogin(signIn);
		String res = (result > 0) ?  "Login SuccessFull" :  "Wrong email or password"; // 
		return  new ResponseEntity<>(res ,HttpStatus.OK);
	}
	
/**
 * 
 * @param signIn
 * @return ResponseEntity<String>
 */
	
	@PutMapping("/loginhelper/changepassword")
	public  ResponseEntity<String> changePassword( @ModelAttribute changePasswordData signIn) {
		log.info("change password req" + signIn.toString());
		boolean res = service.validatePassword(signIn);
		String response = (res == true) ? "Password changed": "Incorrect email or old password";
	return  new ResponseEntity<>(response ,HttpStatus.OK);
//		return  new ResponseEntity<>("Hello" ,HttpStatus.OK);
	}
	
	@GetMapping("loginhelper/forgetpassword")
	public ResponseEntity<String> forgetpassword (@ModelAttribute forgetpasswordData user)
	{
		log.info("change password req" + user.toString());
		boolean response = service.forgetPassword(user);
		
		if(response == true ) {
			return new ResponseEntity<>("Mail send ", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Mail not send ", HttpStatus.OK);
		}
	}
}
