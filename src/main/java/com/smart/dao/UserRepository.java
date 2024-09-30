package com.smart.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;


import com.smart.entities.User;
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query("select u from User u where u.email = :email")
	public User getUserByUserName(@Param("email") String email);
	
	
	@Query("update User u SET u.enabled=true where u.id = ?1")
	@Modifying
	public void enable(Integer id);
	
	@Query("select u from User u where u.verificationcode = :code")
	public User findByVerificationCode(@Param("code") String code);

}
