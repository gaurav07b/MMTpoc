package com.mmt.service;

import org.springframework.http.HttpStatus;

import com.mmt.dto.SignUpDto;

public interface ISignUpLogInService {
	
	void newUserCustomSignUp(SignUpDto signUp);
//	void newUserGoogleSignUp(SignUpDto gSignUp);
//	void deleteOneUser(String email);
//	String findUsersFromName(String name);
	
}