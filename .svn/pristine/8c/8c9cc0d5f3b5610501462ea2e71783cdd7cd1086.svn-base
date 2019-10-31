package com.publicitytraining.service.impl;

import com.entities.ActivityInfoDO;
import com.epmanagement.vo.MediaUrl;
import com.publicitytraining.dao.ActivityInfoDao;
import com.publicitytraining.service.ActivityInfoService;
import com.publicitytraining.vo.ActivityInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Service
public class ActivityInfoServiceImpl implements ActivityInfoService {
	@Autowired
	private ActivityInfoDao activityInfoDao;
	
	@Override
	public ActivityInfoVO get(Integer id){
		return activityInfoDao.get(id);
	}

	@Override
	public List<MediaUrl> getMediaUrlById(Map<String, Object> map) {
		return activityInfoDao.getMediaUrlById(map);
	}

	@Override
	public List<ActivityInfoVO> list(Map<String, Object> map){
		return activityInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return activityInfoDao.count(map);
	}
	
	@Override
	public int save(ActivityInfoDO activityInfo){
		return activityInfoDao.save(activityInfo);
	}
	
	@Override
	public int update(ActivityInfoDO activityInfo){
		return activityInfoDao.update(activityInfo);
	}
	
	@Override
	public int remove(Integer id){
		return activityInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return activityInfoDao.batchRemove(ids);
	}

	@Override
	public ActivityInfoVO getActivityDetail(Integer id) {
		ActivityInfoVO activityInfoVO = get(id);
		List<MediaUrl> picUrlList = new ArrayList<>();
		List<MediaUrl> videoUrlList = new ArrayList<>();
		Map<String,Object> map = new HashMap<>();
		map.put("id",id);
		map.put("type",13);
		List<MediaUrl> mediaUrlById = getMediaUrlById(map);
		if(mediaUrlById != null &&  mediaUrlById.size() > 0) {
			for (MediaUrl mediaUrl:
					mediaUrlById) {
				MediaUrl media = new MediaUrl();
				if(mediaUrl.getIsVideo() == 0) {    //图片
					media.setUrl(mediaUrl.getUrl());
					media.setThumbnailUrl(mediaUrl.getThumbnailUrl());
					picUrlList.add(media);
				}else if(mediaUrl.getIsVideo() == 1){
					media.setUrl(mediaUrl.getUrl());
					media.setThumbnailUrl(mediaUrl.getThumbnailUrl());
					videoUrlList.add(media);
				}
			}
		}
		activityInfoVO.setPicUrlList(picUrlList);
		activityInfoVO.setVideoUrlList(videoUrlList);
		return activityInfoVO;
	}
}
