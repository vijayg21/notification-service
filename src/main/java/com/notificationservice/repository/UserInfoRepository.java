package com.notificationservice.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.notificationservice.config.DynamoDbConfig;
import com.notificationservice.entity.UserInfoEntity;

public class UserInfoRepository {
   private DynamoDBMapper dynamoDBMapper;

    public UserInfoRepository() {
        DynamoDbConfig dynamoDbConfig = new DynamoDbConfig();
        this.dynamoDBMapper = dynamoDbConfig.dynamoDBMapper();
    }

    public void saveUserInfo(UserInfoEntity userInfoEntity){
        dynamoDBMapper.save(userInfoEntity);
    }
}
