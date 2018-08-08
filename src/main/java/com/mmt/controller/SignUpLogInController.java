package com.mmt.controller;

import static com.mmt.model.security.Constants.*;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mmt.config.security.TokenProvider;
import com.mmt.dto.SignUpDto;
import com.mmt.model.security.AuthToken;
import com.mmt.model.security.Constants;
import com.mmt.model.security.LoginUser;
import com.mmt.service.ISignUpLogInService;

@RestController("controlSignUp")
@RequestMapping("/mmt/authentication")
public class SignUpLogInController{
	
	@Autowired
	private ISignUpLogInService signUpServ;
	
	@Autowired
    private RedisTemplate<String,String> redisTemplate;
	
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;
    
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public void saveUser(@RequestBody SignUpDto signingUp) {

		signUpServ.newUserCustomSignUp(signingUp);
	}
	
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody LoginUser loginUser) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        redisTemplate.opsForValue().set(loginUser.getUsername(),token);
        redisTemplate.expire(loginUser.getUsername(), Constants.ACCESS_TOKEN_VALIDITY_SECONDS, TimeUnit.SECONDS);
        return ResponseEntity.ok(new AuthToken(token));
    }
    
    @DeleteMapping("/logout")
    public void logOutUser(HttpServletRequest req) {
    	String token = req.getHeader(HEADER_STRING).replace(TOKEN_PREFIX,"");
    	redisTemplate.opsForValue().getOperations().delete(jwtTokenUtil.getUsernameFromToken(token));
    	
    }
}
