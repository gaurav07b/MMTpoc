package com.mmt.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jayway.jsonpath.internal.function.text.Length;
import com.mmt.dao.entity.Role;
import com.mmt.dao.entity.SignUp;
import com.mmt.dao.repository.RoleRepo;
import com.mmt.dao.repository.SignUpRepo;
import com.mmt.dto.SignUpDto;

@Service("serviceSignUp")
public class SignUpLoginServiceImpl implements ISignUpLogInService {

	@Autowired
	private SignUpRepo signRepo;
	@Autowired
	private RoleRepo roleRepo;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	@Override
	public void newUserCustomSignUp(SignUpDto signUpDto) {


			Role adminRole = new Role();
			adminRole.setName("ADMIN");
			Set<Role> forAdmin = new HashSet<>();
			forAdmin.add(adminRole);

			Role userRole = new Role();
			userRole.setName("USER");
			Set<Role> forUser = new HashSet<>();
			forUser.add(userRole);

			roleRepo.save(adminRole);
			roleRepo.save(userRole);

			SignUp signUpEnt = new SignUp();
			signUpEnt.setfName(signUpDto.getfName());
			signUpEnt.setlName(signUpDto.getlName());
			signUpEnt.setEmailID(signUpDto.getEmailID());
			signUpEnt.setPwd(bcryptEncoder.encode(signUpDto.getPwd()));
			List<SignUp> allSignedUp = signRepo.findAll();
			int totalPwds = 0;
			for (SignUp signUp : allSignedUp) {
				String pwds = signUp.getPwd();
				if (pwds != null)
					totalPwds += 1;
			}
			if (totalPwds == 0) {
				signUpEnt.setRoles(forAdmin);
			} else {
				signUpEnt.setRoles(forUser);
			}

			signRepo.save(signUpEnt);
		}

		

	}

	// @Override
	// public void newUserGoogleSignUp(SignUpDto gSignUp) {
	// return ;
	// }


