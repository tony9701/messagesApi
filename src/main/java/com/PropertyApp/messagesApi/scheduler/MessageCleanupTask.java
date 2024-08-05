package com.PropertyApp.messagesApi.scheduler;

import com.PropertyApp.messagesApi.service.MessageService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MessageCleanupTask {
    private final MessageService messageService;

    public MessageCleanupTask(MessageService messageService) {
        this.messageService = messageService;
    }

    //once a day delete messages older than 1 month
    @Scheduled(cron = "0 0 0 * * *")
    public void deleteOldMessages() {
        messageService.deleteOldMessages();
    }
}
