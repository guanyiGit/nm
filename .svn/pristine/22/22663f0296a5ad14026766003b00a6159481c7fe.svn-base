package com.msgmanagement.service.impl;

import com.entities.Msg;
import com.msgmanagement.dao.MsgDao;
import com.msgmanagement.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class MsgServiceImpl implements MsgService {
	@Autowired
	private MsgDao msgDao;
	
	@Override
	public Msg get(Integer id){
		return msgDao.get(id);
	}
	
	@Override
	public List<Msg> list(Map<String, Object> map){
		return msgDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return msgDao.count(map);
	}
	
	@Override
	public int save(Msg msg){
		return msgDao.save(msg);
	}
	
	@Override
	public int update(Msg msg){
		return msgDao.update(msg);
	}
	
	@Override
	public int remove(Integer id){
		return msgDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return msgDao.batchRemove(ids);
	}
	
}
