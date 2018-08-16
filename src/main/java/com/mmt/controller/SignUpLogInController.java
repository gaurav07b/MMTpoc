package com.mmt.controller;

import static com.mmt.model.security.Constants.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mmt.config.security.TokenProvider;
import com.mmt.dto.SignUpDto;
import com.mmt.model.security.AuthToken;
import com.mmt.model.security.Constants;
import com.mmt.model.security.LoginUser;
import com.mmt.response.ResponseData;
import com.mmt.service.ISignUpLogInService;

@RestController("controlSignUp")
@RequestMapping("/mmt/authentication")
public class SignUpLogInController {

	@Autowired
	private ISignUpLogInService signUpServ;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenProvider jwtTokenUtil;

	@Autowired
	private JavaMailSender sender;

	String msg = "Message";

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public Map<String, String> saveUser(@Valid @RequestBody SignUpDto signingUp) {
		Map<String, String> map = new HashMap<>();
		signUpServ.newUserCustomSignUp(signingUp);

		map.put(msg, "SignUp Successful !!");
		map.put("Code", HttpStatus.OK.toString());
		MimeMessage message = sender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(signingUp.getUsername());
			helper.setText(
					"<html><body>Hi User,</br>Welcome to MakeMyTrip<b>(duplicate)</b>!</br> We hope to make each of your journey memorable... </br>Kindly check the attachment for <b>Terms and Conditions<b></br></br>See you around</br>Team Duplicate<img src='cid:id101'/><body></html>",
					true);
			helper.setSubject("Welcome to MakeMyTrip");
			ClassPathResource file1 = new ClassPathResource("mmt.jpg");
			helper.addInline("id101", file1);
			ClassPathResource file = new ClassPathResource("Terms and Conditions.pdf");
			helper.addAttachment("Terms and Conditions.pdf", file);
			sender.send(message);
		} catch (MailException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return map;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	// public ResponseEntity<?> register(@Valid @RequestBody LoginUser loginUser)
	// throws AuthenticationException {
	public ResponseData register(@Valid @RequestBody LoginUser loginUser) throws AuthenticationException {

		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final String token = jwtTokenUtil.generateToken(authentication);
		redisTemplate.opsForValue().set(loginUser.getUsername(), token);
		redisTemplate.expire(loginUser.getUsername(), Constants.ACCESS_TOKEN_VALIDITY_SECONDS, TimeUnit.SECONDS);
		AuthToken auth = new AuthToken(token);
		ResponseEntity.ok(auth);
		return new ResponseData(HttpStatus.CREATED.toString(), "Login Successful!!", null, auth.getToken());
	}

	// @RequestMapping(value = "/changePwd", method = RequestMethod.PUT)
	// public ResponseData

	@DeleteMapping("/logout")
	public void logOutUser(HttpServletRequest req) {
		String token = req.getHeader(HEADER_STRING).replace(TOKEN_PREFIX, "");
		redisTemplate.opsForValue().getOperations().delete(jwtTokenUtil.getUsernameFromToken(token));
	}
}
