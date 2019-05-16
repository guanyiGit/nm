package com.epmanagement.service.impl;

import com.dogmanager.dao.TDogInfoMapper;
import com.entities.Antiepidemic;
import com.entities.AreaInfo;
import com.entities.TDogInfo;
import com.epmanagement.dao.AntiepidemicDao;
import com.epmanagement.service.AntiepidemicService;
import com.epmanagement.vo.AntiepidemicVO;
import com.orgmanagement.dao.AreaInfoDao;
import com.orgmanagement.domain.UserDO;
import com.orgmanagement.service.AreaInfoService;
import com.utils.ShiroUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class AntiepidemicServiceImpl implements AntiepidemicService {
    private Logger logger = LoggerFactory.getLogger(AntiepidemicServiceImpl.class);
	@Autowired
	private AntiepidemicDao antiepidemicDao;
	@Autowired
	private TDogInfoMapper dogInfoMapper;
	@Autowired
    private AreaInfoService areaInfoService;
	@Autowired
	private AreaInfoDao areaInfoDao;
	
	@Override
	public AntiepidemicVO get(Integer id){
        AntiepidemicVO antiepidemicVO = antiepidemicDao.get(id);
		if(!StringUtils.isEmpty(antiepidemicVO.getTown())){
			List<AreaInfo> AreaInfoList = areaInfoDao.getList();
			antiepidemicVO.setTown(areaInfoService.findAreas(Integer.parseInt(antiepidemicVO.getTown()),AreaInfoList));
		}
        return antiepidemicVO;
	}

	@Override
	public List<Map<String,Object>> list(Map<String, Object> map) {
		return antiepidemicDao.list(map);
	}

	@Override
	public List<AntiepidemicVO> pagelist(Map<String, Object> map){
		return antiepidemicDao.pagelist(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return antiepidemicDao.count(map);
	}
	
	@Override
	public int save(Antiepidemic antiepidemic){
		UserDO user = ShiroUtils.getUser();
		String traceId=null;
		if(StringUtils.isEmpty(antiepidemic.getTraceId())){
			logger.error("【新增防疫】 溯源号为空；traceId:",antiepidemic.getTraceId());
			throw new RuntimeException();
		}
		traceId=antiepidemic.getTraceId().trim();
		Integer proId = dogInfoMapper.findProId(user.getUserId().intValue());
        TDogInfo dogInfo = dogInfoMapper.selectByPrimaryKey(traceId, proId);
        if(dogInfo==null){
            logger.error("【新增防疫】 犬只不存在");
            throw new RuntimeException();
        }
        antiepidemic.setCreateDate(new Date());
        antiepidemic.setUpdateDate(new Date());
		antiepidemic.setOrgId(user.getDeptId().intValue());
		antiepidemic.setAreaId(user.getAreaId().intValue());
        antiepidemic.setAntiepidemicDate(new Date());
		antiepidemic.setTraceId(traceId);
        antiepidemic.setProtector(proId);
		return antiepidemicDao.save(antiepidemic);
	}
	
	@Override
	public int update(Antiepidemic antiepidemic){
		return antiepidemicDao.update(antiepidemic);
	}
	
	@Override
	public int remove(Integer id){
		return antiepidemicDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return antiepidemicDao.batchRemove(ids);
	}

	@Override
	public Map<String, String> selectBySidOrDrugName(String str) {
		return antiepidemicDao.selectBySidOrDrugName(str);
	}

	@Override
	public List<HashMap<String, Object>> findDrug(Integer type) {
		return antiepidemicDao.findDrug(type);
	}

}
