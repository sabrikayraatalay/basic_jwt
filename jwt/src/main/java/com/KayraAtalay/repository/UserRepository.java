package com.KayraAtalay.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.KayraAtalay.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	//@Query(value = "from user where username = :username")
	Optional<User> findByUsername(String username);

}
