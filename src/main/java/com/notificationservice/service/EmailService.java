package com.notificationservice.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
import com.notificationservice.model.InputModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {


   // @Value("${aws.access.key}")
    private String ACCESS_KEY = "AKIA4QBN6MKLHKXGP7GQ";

   // @Value("${aws.secret.key}")
    private String SECRET_KEY = "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";

    public String sendEmail(InputModel inputModel){
       // LambdaLogger logger = context.getLogger();
        try {
            BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(ACCESS_KEY,SECRET_KEY);
            Content subject = new Content().withData(inputModel.getSubject());
            Content textbody = new Content().withData(inputModel.getMessage());
            Body body = new Body().withText(textbody);
            Message message = new Message().withSubject(subject).withBody(body);
            SendEmailRequest emailRequest = new SendEmailRequest()
                    .withDestination(new Destination().withToAddresses(inputModel.getToEmail())).withMessage(message)
                    .withSource(inputModel.getFromEmail());
            AmazonSimpleEmailService amazonSimpleEmailService = AmazonSimpleEmailServiceClientBuilder.standard()
                    .withRegion(Regions.AP_NORTHEAST_1)
                    .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                    .build();
           SendEmailResult sendEmailResult = amazonSimpleEmailService.sendEmail(emailRequest);
           System.out.println("Email sent successfully" + sendEmailResult.toString());
          //logger.log("Email sent successfully!"+ sendEmailResult.toString());
            return sendEmailResult.getMessageId();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
