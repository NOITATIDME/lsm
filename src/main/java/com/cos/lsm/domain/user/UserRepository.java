package com.cos.lsm.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	@Query(value = "insert into user (username, password, email) values(:username, :password, :email)", nativeQuery = true)
	void join(String username, String password, String email);
	
	@Query(value = "select * from user where username = :username and password = :password", nativeQuery = true)
	User mLogin(String username, String password);
	
}
