package com.epmanagement.service.impl;

import com.basicInfo.dao.TMediaInfoMapper;
import com.basicInfo.dao.TMediaRefMapper;
import com.entities.*;
import com.entities.TMediaRefExample.Criteria;
import com.epmanagement.dao.ManureDisposalDao;
import com.epmanagement.service.ManureDisposalService;
import com.epmanagement.vo.ManureDisposalVO;
import com.epmanagement.vo.MediaUrl;
import com.epmanagement.vo.SelectVO;
import com.orgmanagement.dao.AreaInfoDao;
import com.statisanalysis.dao.MediaInfoDao;
import com.utils.ShiroUtils;
import com.utils.UploadResult;
import com.utils.areaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class ManureDisposalServiceImpl implements ManureDisposalService {
	@Autowired
	private ManureDisposalDao manureDisposalDao;
	@Autowired
	private MediaInfoDao mediaInfoDao;
	@Autowired
	private TMediaRefMapper mediaRefMapper;
	@Autowired
	private TMediaInfoMapper mediaInfoMapper;
	@Autowired
	private AreaInfoDao areaInfoDao;

	@Override
	public ManureDisposalVO  get(Integer id){
		Object type = ShiroUtils.getSubjct().getSession().getAttribute("type");
		Map<String,Object> map = new HashMap<>();
		map.put("langType",type);
		map.put("id",id);
//		ManureDisposalVO manureDisposalVO = manureDisposalDao.get(id);
		ManureDisposalVO manureDisposalVO = manureDisposalDao.getI18N(map);
        List<AreaInfo> areaList = areaInfoDao.getList();
        Integer areaId = manureDisposalVO.getAreaId();
        String areaName = "";
        if(areaId != null) {
            List<AreaInfo> areaInfos = areaUtils.areaReverse(areaList, areaId, new ArrayList<>());
            //组装州县乡
            StringBuilder sb = new StringBuilder();
            if(areaInfos != null && areaInfos.size() > 0) {
                for (int i = 1; i< areaInfos.size(); i++) {
                    sb.append(areaInfos.get(i).getName());
                }
            }
            areaName = sb.toString();
        }
        manureDisposalVO.setAreaName(areaName);
        return manureDisposalVO;
    }

    @Override
    public List<MediaUrl> getMediaUrl(Integer id) {
	    return manureDisposalDao.getMediaUrlById(id);
    }

    @Override
	public List<Map<String, Object>> list(Map<String, Object> map){
		Object type = ShiroUtils.getSubjct().getSession().getAttribute("type");
		map.put("langType",type);
		List<Map<String, Object>> res = manureDisposalDao.list(map);
		if(res != null && res.size() > 0) {
            List<AreaInfo> areaList = areaInfoDao.getList();
            for (Map<String,Object> m:
                 res) {
                Integer areaId = null;
                if(m.get("areaId") != null) {
                	areaId = Integer.parseInt(m.get("areaId").toString());
				}
                String areaName = "";
                if(areaId != null) {
                    List<AreaInfo> areaInfos = areaUtils.areaReverse(areaList, areaId, new ArrayList<>());
                    //组装州县乡
                    StringBuilder sb = new StringBuilder();
                    if(areaInfos != null && areaInfos.size() > 0) {
                        for (int i = 1; i< areaInfos.size(); i++) {
                            sb.append(areaInfos.get(i).getName());
                        }
                    }
                    areaName = sb.toString();
                }
                m.put("areaName",areaName);
            }
        }
        return res;
    }
	
	@Override
	public int count(Map<String, Object> map){
		return manureDisposalDao.count(map);
	}
	
	@Override
	public int save(ManureDisposal manureDisposal){
		return manureDisposalDao.save(manureDisposal);
	}
	
	@Override
	public int update(ManureDisposal manureDisposal){
		return manureDisposalDao.update(manureDisposal);
	}
	
	@Override
	public int remove(Integer id){
		return manureDisposalDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return manureDisposalDao.batchRemove(ids);
	}

	@Override
	public Map<String, String> selectByTitle(String title) {
		return manureDisposalDao.selectByTitle(title);
	}

	@Transactional
	@Override
	public int saveMediaAndRef(List<UploadResult> list, Map<String,Object> map) {
		//1.已知条件
		Integer id = Integer.parseInt(map.get("id").toString());
        Integer type = Integer.parseInt(map.get("type").toString());
        Integer isVideo = Integer.parseInt(map.get("isVideo").toString());
		
		TMediaRefExample example=new TMediaRefExample();
			Criteria criteria = example.createCriteria();
				criteria.andIsVideoEqualTo(isVideo);
				criteria.andTypeEqualTo(type);
				criteria.andRefIdEqualTo(type);
				
		//2.根据ref_id,type,is_video查询t_media_ref表，判断是否为空
		int i = mediaRefMapper.countByExample(example);
			//3. 非空,说明以前已经存有照片,删除以前数据库表中的多余数据
			if(i>0) {
				//3.1 求出media表中的主键id
				List<TMediaRef> mediaRefList = mediaRefMapper.selectByExample(example);
					for(TMediaRef m:mediaRefList) {
						//3.2 t_media_info的主键id
						Integer id2 = m.getId();
						
						//3.3 t_media_ref的主键id
						Integer id3 = m.getMediaId();
						
						//3.4 删除t_media_info表中的数据
						mediaInfoMapper.deleteByPrimaryKey(id2);
						
						//3.5 删除t_media_ref表中的数据
						mediaRefMapper.deleteByPrimaryKey(id3);
					}
			}
		
		List<TMediaInfo> mediaList =new ArrayList<>();
		for (UploadResult result:
			 list) {
			TMediaInfo mediaInfo = new TMediaInfo();
			mediaInfo.setUrl(result.getUrl());
			mediaInfo.setThumbnailUrl(result.getThumbnailUrl());
			mediaInfo.setCreateDate(new Date());
			mediaList.add(mediaInfo);
		}
		//插入媒体表
        int rows1 = mediaInfoDao.insertMediaBatch(mediaList);
        int rows2 = 0;
		if(rows1 > 0) {
            //插入媒体关联表
            List<TMediaRef>  refList =new ArrayList<>();
            for (TMediaInfo media :
                    mediaList) {
                TMediaRef ref =new TMediaRef();
                ref.setMediaId(media.getId());
                ref.setRefId(id);
                ref.setCreateDate(new Date());
                ref.setIsVideo(isVideo);
                ref.setType(type);
                ref.setCreateDate(new Date());
                refList.add(ref);
            }
            rows2 = mediaInfoDao.insertMediaRefBatch(refList);
        }
        return rows2;
	}

	/**
	 * 保存视频及关联
	 *
	 * @param videoUrlList
	 * @param map
	 * @return
	 */
	@Override
	public int saveVideoMediaAndRef(List<String> videoUrlList, Map<String, Object> map) {
				//1.已知条件
				Integer id = Integer.parseInt(map.get("id").toString());
		        Integer type = Integer.parseInt(map.get("type").toString());
		        Integer isVideo = Integer.parseInt(map.get("isVideo").toString());
				
				TMediaRefExample example=new TMediaRefExample();
					Criteria criteria = example.createCriteria();
						criteria.andIsVideoEqualTo(isVideo);
						criteria.andTypeEqualTo(type);
						criteria.andRefIdEqualTo(type);
						
				//2.根据ref_id,type,is_video查询t_media_ref表，判断是否为空
				int i = mediaRefMapper.countByExample(example);
					//3. 非空,说明以前已经存有照片,删除以前数据库表中的多余数据
					if(i>0) {
						//3.1 求出media表中的主键id
						List<TMediaRef> mediaRefList = mediaRefMapper.selectByExample(example);
							for(TMediaRef m:mediaRefList) {
								//3.2 t_media_info的主键id
								Integer id2 = m.getId();
								
								//3.3 t_media_ref的主键id
								Integer id3 = m.getMediaId();
								
								//3.4 删除t_media_info表中的数据
								mediaInfoMapper.deleteByPrimaryKey(id2);
								
								//3.5 删除t_media_ref表中的数据
								mediaRefMapper.deleteByPrimaryKey(id3);
							}
					}
		
		List<TMediaInfo> mediaList =new ArrayList<>();
        for (String videoUrl:
             videoUrlList) {
		    TMediaInfo mediaInfo = new TMediaInfo();
            mediaInfo.setUrl(videoUrl);
            mediaInfo.setCreateDate(new Date());
            mediaList.add(mediaInfo);
        }
        int row1 = mediaInfoDao.insertMediaBatch(mediaList);
        int row2 = 0;
        if(row1 > 0) {
            List<TMediaRef>  refList =new ArrayList<>();
            for (TMediaInfo mediaInfo: mediaList) {
                TMediaRef mediaRef = new TMediaRef();
                mediaRef.setIsVideo(isVideo);
                mediaRef.setType(type);
                mediaRef.setRefId(id);
                mediaRef.setMediaId(mediaInfo.getId());
                mediaRef.setCreateDate(new Date());
                refList.add(mediaRef);
            }
            row2 = mediaInfoDao.insertMediaRefBatch(refList);
        }
        return row2;
	}

	/**
	 * 初始化下拉列表（通用）
	 *
	 * @param map
	 * @return
	 */
	@Override
	public List<SelectVO> initSelData(Map<String, Object> map) {
		Object type = ShiroUtils.getSubjct().getSession().getAttribute("type");
		map.put("langType",type);
		return manureDisposalDao.getDictData(map);
	}
}
