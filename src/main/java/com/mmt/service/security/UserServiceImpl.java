package com.mmt.service.security;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mmt.dao.entity.SignUp;
import com.mmt.dao.repository.SignUpRepo;

import java.util.*;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	private SignUpRepo signRepo;

	Logger logger;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SignUp user = signRepo.findByEmailID(username);
		try {
			if (user == null) {
				throw new UsernameNotFoundException("Invalid email or password.");
			}

		} catch (UsernameNotFoundException e) {
			logger.error(e.getLocalizedMessage());
			return null;
		}
		return new org.springframework.security.core.userdetails.User(user.getEmailID(), user.getPwd(),
				getAuthority(user));
	}

	private Set<SimpleGrantedAuthority> getAuthority(SignUp user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));
		return authorities;
	}

}
