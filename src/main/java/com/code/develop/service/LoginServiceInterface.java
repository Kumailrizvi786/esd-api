package com.code.develop.service;

import com.code.develop.data.LoginTable;
import com.code.develop.model.SignInData;
import com.code.develop.model.changePasswordData;

public interface LoginServiceInterface {
	
	public int validateLogin(SignInData signIn);
	
	public  LoginTable validatePassword(changePasswordData signIn);

}
