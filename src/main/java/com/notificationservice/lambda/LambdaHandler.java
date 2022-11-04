package com.notificationservice.lambda;


import com.notificationservice.model.InputModel;
import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;


public class LambdaHandler extends SpringBootRequestHandler<InputModel,Object> {
}