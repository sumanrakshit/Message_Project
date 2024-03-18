package com.message.project.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.message.project.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>  {
	
//	Message findByMessageid(String date);
	//Message findByAuthor(String date,String message,String attachment,String signature);
//	String findBymessage(Integer messageid);
//	
//	@Query( 
//	        nativeQuery = true, 
//	        value 
//	        = "SELECT * FROM message ea where ea.signature='verify' ") 
//	       Optional<Message> findByMessageid(); 
//	
	
}
