package com.PropertyApp.messagesApi.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 50)
    private String senderName;

    @NotBlank
    @Email
    private String senderEmail;

    @NotBlank
    @Size(min = 2, max = 1500)
    private String message;

    @NotNull
    private LocalDateTime timestamp;

    public Message setMessage(String message) {
        this.message = message;
        return this;
    }

    public Message setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
        return this;
    }

    public Message setSenderName(String senderName) {
        this.senderName = senderName;
        return this;
    }

    public Message setTimestamp() {
        this.timestamp = LocalDateTime.now();
        return this;
    }
}
