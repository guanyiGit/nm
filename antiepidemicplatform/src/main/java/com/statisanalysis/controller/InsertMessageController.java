package com.statisanalysis.controller;

import com.statisanalysis.entity.MessageParm;
import com.statisanalysis.serivce.InsertMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.rmi.ServerException;

@Controller
@RequestMapping("/biz/msg")
public class InsertMessageController {
    @Autowired
    InsertMessage insertMessage;

    @RequestMapping("/insert")
    @ResponseBody
//    @RequiresPermissions("biz:msg:insert")
    public void insertMessage(MessageParm messageParm){
        try {
            insertMessage.insertMessage(messageParm);
        } catch (ServerException e) {
            e.printStackTrace();
        }
    }
}
