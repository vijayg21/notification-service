package com.notificationservice.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
import com.notificationservice.model.InputModel;

public class EmailService {

    private static String ACCESS_KEY = "AKIA4QBN6MKLGDKTOSPI";
    private static String SECRET_KEY = "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";

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
