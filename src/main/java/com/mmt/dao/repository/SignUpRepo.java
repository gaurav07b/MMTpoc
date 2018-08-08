package com.mmt.dao.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mmt.dao.entity.SignUp;

@Repository("signUpRepo")
public interface SignUpRepo extends JpaRepository<SignUp, Long>{
	
	SignUp findById(Integer id);
	SignUp findByEmailID(String username);

	
}
