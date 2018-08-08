package com.mmt.dao.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mmt.dao.entity.SignUp;

@Repository("signUpRepo")
@Transactional
public interface SignUpRepo extends JpaRepository<SignUp, Long>{
	
	SignUp findByEmailID(String email);
	
}
