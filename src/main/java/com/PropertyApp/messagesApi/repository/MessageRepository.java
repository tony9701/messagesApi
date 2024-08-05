package com.PropertyApp.messagesApi.repository;

import com.PropertyApp.messagesApi.model.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    void deleteByTimestampBefore(LocalDateTime timestamp);

}
