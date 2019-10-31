package com.statisanalysis.serivce.impl;

import com.statisanalysis.dao.InsertMessageDao;
import com.statisanalysis.entity.Message;
import com.statisanalysis.entity.MessageParm;
import com.statisanalysis.entity.UserRefMessage;
import com.statisanalysis.serivce.InsertMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.List;

@Service
public class InsertMessageImpl implements InsertMessage {
    @Autowired
    InsertMessageDao insertMessageDao;
    @Override
    public void insertMessage(MessageParm messageParm) throws ServerException {
        Message message = messageParm.getMessage();
        List<UserRefMessage> lists = new ArrayList<UserRefMessage>();
        UserRefMessage userRefMessage = new UserRefMessage();
        List<Integer> list = messageParm.getList();
        try {
            insertMessageDao.insertMessage(message);
            for (int i=0;i<list.size();i++){
                userRefMessage.setMsgId(message.getId());
                userRefMessage.setReceiver(list.get(i));
                lists.add(userRefMessage);
            }
            insertMessageDao.insertMsgRef(lists);
        } catch (Exception e) {
            throw new ServerException("插入消息失败！",e);
        }
    }
}
