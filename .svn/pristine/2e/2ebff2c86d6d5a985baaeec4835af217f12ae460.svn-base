package com.epmanagement.service.impl;

import com.dogmanager.dao.TDogCancelMapper;
import com.dogmanager.dao.TDogInfoMapper;
import com.dogmanager.service.DogInfoService;
import com.entities.AreaInfo;
import com.entities.CorpseDisposal;
import com.entities.OrgInfo;
import com.entities.TDeviceRefDog;
import com.entities.TDogInfo;
import com.epmanagement.dao.CorpseDisposalDao;
import com.epmanagement.service.CorpseDisposalService;
import com.epmanagement.vo.CorpseDisposalVO;
import com.netflix.discovery.converters.Auto;
import com.orgmanagement.dao.AreaInfoDao;
import com.orgmanagement.dao.OrgInfoDao;
import com.orgmanagement.domain.UserDO;
import com.utils.OrgUtils;
import com.utils.PageUtils;
import com.utils.ShiroUtils;
import com.utils.areaUtils;

import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;




@Service
public class CorpseDisposalServiceImpl implements CorpseDisposalService {
	@Autowired
	private CorpseDisposalDao corpseDisposalDao;
	
	@Autowired
	private OrgInfoDao orgDao;
	
	@Autowired
	private DogInfoService dogService;
	
	@Autowired
	private TDogInfoMapper dogMapper;
	
	@Autowired
	private TDogCancelMapper cancelMapper;
	
	
	@Autowired
	private AreaInfoDao areaInfoDao;
	@Override
	public Map<String, String> get(Integer id){
		return corpseDisposalDao.get(id);
	}
	
	@Override
	public List<CorpseDisposalVO> list(Map<String, Object> map){
        return corpseDisposalDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return corpseDisposalDao.count(map);
	}
	
	@Override
	@Transactional
	public int save(CorpseDisposal corpseDisposal){
		//登录用户相关信息
				UserDO user = ShiroUtils.getUser();
		String traceId = corpseDisposal.getTraceId().trim();
				int i = dogMapper.judgeTraceId(user.getUserId().intValue(), traceId);
				if(i==0) {
					return -1;
				}
		corpseDisposal.setTraceId(traceId);
			corpseDisposal.setAreaId(user.getAreaId().intValue());
			corpseDisposal.setOrgId(user.getDeptId().intValue());
			corpseDisposal.setOperatorId(user.getUserId().intValue());
			corpseDisposal.setDealTime(new Date());
			corpseDisposal.setCreateDate(new Date());
			//插入犬只尸体信息
			corpseDisposalDao.save(corpseDisposal);
			
			//根据traceId查找看犬只是否绑定
			TDeviceRefDog deviceRefDog = dogMapper.selectByTraceId(traceId);
				if(deviceRefDog !=null) {
					//更改设备状态
					dogMapper.updateDeviceStatus(corpseDisposal.getDeviceDealWay(), deviceRefDog.getDeviceNo());
					//根据traceId 删除关联关系
					cancelMapper.deleteDeviceRefDog(traceId);
				}
			//根据traceId 更改狗的状态为死亡1
			dogMapper.updateDogStatus(1, traceId);
			
			//返回犬只尸体主键id
			Integer id=corpseDisposal.getId();
		return id;
	}
	
	@Override
	public int update(CorpseDisposal corpseDisposal){
		corpseDisposal.setUpdateDate(new Date());
		//dogMapper.updateDeviceStatus(corpseDisposal.getDeviceDealWay(), corpseDisposal.getTraceId());
		return corpseDisposalDao.update(corpseDisposal);
	}
	
	@Override
	public int remove(Integer id){
		return corpseDisposalDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return corpseDisposalDao.batchRemove(ids);
	}

	@Override
	public Map<String, String> selectByTitle(Map<String, Object> map) {
		return corpseDisposalDao.selectByTitle(map);
	}

	/**
	 * 
	 * @param index 索引
	 * @param pageSize 页面大小
	 * @param orgId 当前登录人部门id
	 * @param userId2 前端传过来用户id
	 * @param searchKey 搜索条件
	 * @return
	 */
	@Override
	public PageUtils findCorpseDisposalList(Integer index, Integer pageSize, String searchKey,
			Integer orgId2,Integer userId2) {
		Map<String, Object>map=new HashMap<String,Object>();
			map.put("index", index);
			map.put("pageSize", pageSize);
			map.put("searchKey", searchKey);
		//获取用户相关信息
		UserDO user = ShiroUtils.getUser();
			Integer userId=user.getUserId().intValue();
			Integer orgId=user.getDeptId().intValue();
		//当前登录的角色类型
		Integer type=user.getRoles().get(0).getType();
		
		//当前登录为防疫员
		if(type==2) {
			//userId为过滤条件
			map.put("userId", userId);
		}
		//当前登录非防疫员
		else {
			 List<OrgInfo> orgList=orgDao.orgList();
	            List<Integer>res=new ArrayList<>();
	            if(orgId2==null){
	                res= OrgUtils.orgReverse(orgList, orgId, res);
	            }else {
	                res= OrgUtils.orgReverse(orgList, orgId2, res);
	            }
	            map.put("orgList",res);//组织集合
		}
		if (userId2!=null && userId2!=0){
            map.put("userId",userId2);//
        }
		//求出总记录数
		Integer total = corpseDisposalDao.count(map);
		map.put("langType",ShiroUtils.getSubjct().getSession().getAttribute("type"));
		//查出尸体列表信息
		List<CorpseDisposalVO> corpseDisposalList = corpseDisposalDao.list(map);
		for(CorpseDisposalVO c:corpseDisposalList ) {
					Integer areaId = c.getAreaId();
					List<AreaInfo> lists=new ArrayList<>();
					lists=areaUtils.areaR(areaInfoDao.getList(), areaId, lists);
					String town = "";
					for (int i = lists.size()-1;i>=0;i--){
						town += lists.get(i).getName();
					}
					c.setTown(town);
					c.setTownList(lists);
				}
		return new PageUtils(corpseDisposalList, total);
	}


	/**
	 * 查找尸体详情
	 * @param id 主键id
	 * @return
	 */
	@Override
	public CorpseDisposalVO getCorpseDetail(Integer id) {
		CorpseDisposalVO vo = corpseDisposalDao.getCorpseDetail(id);
			Integer areaId = vo.getAreaId();
			List<AreaInfo> lists=new ArrayList<>();
			lists=areaUtils.areaR(areaInfoDao.getList(), areaId, lists);
			String town = "";
			for(int i = lists.size()-1;i>=0;i--){
				town+=	lists.get(i).getName();
			}
			vo.setTown(town);
			vo.setTownList(lists);
				
		return vo;
	}

	@Override
	public CorpseDisposalVO getCorpseDetaili18n(Map<String, Object> map) {
		CorpseDisposalVO vo = corpseDisposalDao.getCorpseDetaili18n(map);
		Integer areaId = vo.getAreaId();
		List<AreaInfo> lists=new ArrayList<>();
		lists=areaUtils.areaR(areaInfoDao.getList(), areaId, lists);
		String town = "";
		for(int i = lists.size()-1;i>=0;i--){
			town+=	lists.get(i).getName();
		}
		vo.setTown(town);
		vo.setTownList(lists);

		return vo;
	}


}
