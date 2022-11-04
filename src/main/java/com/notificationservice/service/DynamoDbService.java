package com.notificationservice.service;

import com.notificationservice.entity.UserInfoEntity;
import com.notificationservice.model.InputModel;
import com.notificationservice.repository.UserInfoRepository;

import java.time.ZonedDateTime;

public class DynamoDbService {

    UserInfoRepository userInfoRepository = new UserInfoRepository();

    public void saveData(InputModel inputModel) {
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        setUserInfoEntity(userInfoEntity, inputModel);
        userInfoRepository.saveUserInfo(userInfoEntity);
    }

    private void setUserInfoEntity(UserInfoEntity userInfoEntity, InputModel inputModel) {
        userInfoEntity.setMessage(inputModel.getMessage());
        userInfoEntity.setToMailId(inputModel.getToEmail());
        userInfoEntity.setSubject(inputModel.getSubject());
        userInfoEntity.setMessageId(inputModel.getMessageId());
        userInfoEntity.setMessageSentTime(ZonedDateTime.now().toString());
    }
}
