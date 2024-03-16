package com.message.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.message.project.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>  {

}
