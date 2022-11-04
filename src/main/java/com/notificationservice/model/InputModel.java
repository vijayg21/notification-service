package com.notificationservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputModel {

    private String fromEmail;

    private String toEmail;

    private String message;

    private String subject;

    private String messageId;
}
