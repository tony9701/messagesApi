package com.PropertyApp.messagesApi.service;

import com.PropertyApp.messagesApi.model.dto.MessageDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService {
    List<MessageDTO> getAllMessages();

    MessageDTO getMessageById(Long id);

    MessageDTO createMessage(MessageDTO messageDTO);

    void deleteMessage(Long id);

    void deleteOldMessages();

}
