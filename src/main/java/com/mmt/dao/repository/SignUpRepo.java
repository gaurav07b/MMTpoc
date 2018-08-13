package com.mmt.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mmt.dao.entity.SignUp;

@Repository("signUpRepo")
public interface SignUpRepo extends JpaRepository<SignUp, Long>{
	
	SignUp findById(long id);
	SignUp findByUsername(String username);
	
}
