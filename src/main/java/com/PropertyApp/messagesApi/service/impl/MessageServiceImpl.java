package com.PropertyApp.messagesApi.service.impl;

import com.PropertyApp.messagesApi.model.dto.MessageDTO;
import com.PropertyApp.messagesApi.model.entity.Message;
import com.PropertyApp.messagesApi.repository.MessageRepository;
import com.PropertyApp.messagesApi.service.MessageService;
import com.PropertyApp.messagesApi.service.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<MessageDTO> getAllMessages() {
        return messageRepository.findAll().stream().map(MessageServiceImpl::mapToDTO).toList();
    }

    @Override
    public MessageDTO getMessageById(Long id) {
        return messageRepository.findById(id).
                map(MessageServiceImpl::mapToDTO).
                orElseThrow(ObjectNotFoundException::new);
    }

    @Override
    public MessageDTO createMessage(MessageDTO messageDTO) {
        Message message = messageRepository.save(mapToMessage(messageDTO));
        return mapToDTO(message);
    }

    @Override
    public void deleteMessage(Long id) {
        messageRepository.delete(
                messageRepository.findById(id)
                        .orElseThrow(ObjectNotFoundException::new)
        );
    }

    @Override
    public void deleteOldMessages() {
        LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(30);
        messageRepository.deleteByTimestampBefore(thirtyDaysAgo);
    }


    private static MessageDTO mapToDTO(Message message) {
        return new MessageDTO(
                message.getId(),
                message.getSenderName(),
                message.getSenderEmail(),
                message.getMessage(),
                message.getTimestamp());
    }

    private static Message mapToMessage(MessageDTO messageDTO) {
        return new Message()
                .setSenderName(messageDTO.getSenderName())
                .setSenderEmail(messageDTO.getSenderEmail())
                .setMessage(messageDTO.getMessage())
                .setTimestamp();
    }
}
