package com.mmt.dao.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mmt.dao.entity.Role;


@Repository("roleRepo")
@Transactional
public interface RoleRepo extends JpaRepository<Role, Long>{

}
