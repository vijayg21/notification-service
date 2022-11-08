package com.notificationservice.function;

import com.notificationservice.model.InputModel;
import com.notificationservice.service.DynamoDbService;
import com.notificationservice.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@Slf4j
public class FunctionHandler implements Function<InputModel, String> {
    @Autowired
    private EmailService emailService;
    @Autowired
    private DynamoDbService dynamoDbService;
    @Override
    public String apply(InputModel inputModel) {
        log.info("Received:  "+inputModel.toString());
       // EmailService emailService = new EmailService();
        //DynamoDbService dynamoDbService = new DynamoDbService();
        String messageId = emailService.sendEmail(inputModel);
        inputModel.setMessageId(messageId);
        if (messageId != null) {
             dynamoDbService.saveData(inputModel);
        }
        return "success";
    }
}
