package com.statisanalysis.dao;

import com.statisanalysis.entity.Message;
import com.statisanalysis.entity.UserRefMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface InsertMessageDao {
    /**
     * 插入消息
     * @param message
     */
    void insertMessage(@Param("message") Message message);

    /**
     * 插入消息和接收人关联关系
     * @param userRefMessages
     */
    void insertMsgRef(List<UserRefMessage> userRefMessages);
}
