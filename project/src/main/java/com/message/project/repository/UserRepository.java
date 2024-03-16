package com.message.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.message.project.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByPublickey(String username);

}
