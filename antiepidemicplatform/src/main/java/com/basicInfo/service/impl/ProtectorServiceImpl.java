package com.basicInfo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.basicInfo.dao.TProtectorMapper;
import com.basicInfo.dao.TSysDictMapper;
import com.basicInfo.service.ProtectorService;
import com.basicInfo.vo.ProtectorVO;
import com.entities.*;
import com.entities.TProtectorExample.Criteria;
import com.orgmanagement.dao.AreaInfoDao;
import com.orgmanagement.dao.OrgInfoDao;
import com.orgmanagement.dao.UserInfoDao;
import com.orgmanagement.dao.UserRefRoleDao;
import com.orgmanagement.domain.UserDO;
import com.orgmanagement.domain.UserRoleDO;
import com.utils.*;
import com.utils.httpClient.HttpClientUtil;
import com.utils.httpClient.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.*;
@Service
public class ProtectorServiceImpl implements ProtectorService{

	
	@Autowired 
	private TProtectorMapper protectorMapper;
	@Autowired
	private OrgInfoDao orgDao;
	@Autowired 
	private TSysDictMapper dictMapper;
	@Autowired
	private UserInfoDao userInfoDao;
	
	@Autowired
	private UserRefRoleDao userRoleMapper;
	@Autowired
	private AreaInfoDao areaDao;

	@Value("${sms.SMS_BASE_URL}")
	private String SMS_BASE_URL;

	@Value("${sms.type}")
	private String type;
	//新增兽医
	@Override
	@Transactional
 	public int addProtector(TProtector protector){
		//登录获取用户相关信息
				UserDO user = ShiroUtils.getUser();
				Integer areaId = user.getAreaId().intValue();
				Integer userIdLogin=user.getUserId().intValue();
				Integer orgId = user.getDeptId().intValue();
				String pwd=(int)((Math.random()*9+1)*100000)+"";
				//新建用户对象
				UserDO u2=new UserDO();
					u2.setAreaId(user.getAreaId());
					u2.setDeptId(user.getDeptId());
					u2.setPassword(MD5Utils.encrypt(protector.getPhoneNum(), pwd));
					u2.setMobile(protector.getPhoneNum());
					u2.setUsername(protector.getPhoneNum());
					u2.setName(protector.getName());
					u2.setBirth(protector.getBirthDay());
					u2.setStatus(1);
					u2.setGmtCreate(new Date());
					//插入用户
					Integer id=userInfoDao.checkAccountRepeat(protector.getPhoneNum());
					if (id !=0){
						return -2;
					}
					
					//userService.save(user);
					userInfoDao.save(u2); 
					//返回自增主键
					Long userId = u2.getUserId();
					
					Long roleId=(long) 2;//防疫员id固定死
					
					//向关联表中插入数据
					List<UserRoleDO> list=new ArrayList<UserRoleDO>();
						UserRoleDO u=new UserRoleDO();
							u.setRoleId(roleId);
							u.setUserId(userId);
						list.add(u);
						//插入
						userRoleMapper.batchSave(list);
						
					protector.setUserId(userId.intValue());
					protector.setAreaId(areaId);
					protector.setCreateBy(userIdLogin);
					protector.setOrgId(orgId);
					protector.setCreateDate(new Date());
					protectorMapper.insertSelective(protector);

		Map<String, Object> params = new HashMap<>();
		params.put("phone", protector.getPhoneNum());
		params.put("type",type);
		Map<String, Object> template_param = new HashMap<>();
		template_param.put("name", protector.getName());
		template_param.put("username",protector.getPhoneNum());
		template_param.put("password", pwd);
		params.put("template_param", JSONObject.toJSONString(template_param));
		new Thread( () -> {
			try {
				HttpResult httpResult = HttpClientUtil.executeHttpParams(SMS_BASE_URL + "/SendSMS", "post", params);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} ).start();
		return protector.getId();
	}

	//删除防疫员
	@Override
	public int deleteProtector(Integer id) {
		return protectorMapper.deleteByPrimaryKey(id);
	}
	
	//修改防疫员
	@Override
	@Transactional
	public int updateProtector(TProtector protector) {
		//获取防疫员姓名
		String protectorName=protector.getName();
		//获取性别
		Integer sex = protector.getSex();
		//1.获取防疫员的手机号
		String phone1=protector.getPhoneNum();

		//2.获取防疫员的用户id
		Integer userId=protector.getUserId();
		//3.查看防疫员原来用户信息
		UserInfo user=protectorMapper.findUser(userId);
		String phone2=user.getPhoneNum();
			user.setAccount(phone1);
			user.setPhoneNum(phone1);
			user.setName(protectorName);
			user.setSex(sex);
		int uodateUser=0;
		//4.更改了手机号(重新发送账号和密码)
		if(!phone1.equals(phone2)){
			//判断手机号对应的账号是否重复
			int i = userInfoDao.checkAccountRepeat(phone1);
			if(i !=0){
				//账号已经存在
				return -1;
			}
			//重新生成密码
			String password = MathUtils.getRandomNumber();
			user.setPasswd(MD5Utils.encrypt(user.getAccount(), password).trim());
			//更新用户
			 uodateUser=protectorMapper.updateUser(user);
			if(uodateUser>0){
				Map<String, Object> params = new HashMap<>();
				params.put("phone", phone1);
				params.put("type",type);
				Map<String, Object> template_param = new HashMap<>();
				template_param.put("name", protector.getName());
				template_param.put("username", phone1);
				template_param.put("password", password);
				params.put("template_param", JSONObject.toJSONString(template_param));
				new Thread( () -> {
					try {
						HttpResult httpResult = HttpClientUtil.executeHttpParams(SMS_BASE_URL + "/SendSMS", "post", params);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} ).start();
			}
		}else{
			uodateUser=protectorMapper.updateUser(user);
		}
		if(uodateUser==0){
			return uodateUser;
		}

		return protectorMapper.updateByPrimaryKeySelective(protector);
	}

	//查看防疫员详情
	@Override
	public ProtectorVO findProtectorDetail(Integer id) {
		Object type = ShiroUtils.getSubjct().getSession().getAttribute("type");
		return protectorMapper.findProtectorDetail(id,type);
	}

	//防疫员列表
	@Override
	public PageUtils findProtectorList(Integer index, Integer pageSize, Integer orgId,String searchKey) throws UnsupportedEncodingException {
		orgId=ShiroUtils.getUser().getDeptId().intValue();
		//查出所有组织信息
				List<OrgInfo> list = orgDao.orgList();
				List<Integer>orgList=new ArrayList<>();
				//求出当前登录人下面所有乡级组织信息
					orgList= OrgUtils.orgReverse(list, orgId, orgList);
		Map<String, Object>map=new HashMap<>();
			//求出总记录数
		map.put("searchKey", searchKey);
		map.put("orgList", orgList);
		int total = protectorMapper.countProtector(map);
				
				map.put("index", index);
				map.put("pageSize", pageSize);
				
			List<ProtectorVO> protectorList = protectorMapper.findProtectorList(map);
				for(ProtectorVO p:protectorList) {
					List<AreaInfo>lists=new ArrayList<AreaInfo>();
					lists=areaUtils.areaR(areaDao.getList(), p.getAreaId(), lists);
					p.setTownList(lists);
				}
		return new PageUtils(protectorList, total);
	}

	@Override
	public List<Map<String,Object>> initProtectorSel(Map<String,Object> map) {
		return protectorMapper.initProtectorSel(map);
	}

	/**
	 * 查找民族
	 */
	@Override
	public List<TSysDict> findTotalNation() {
		TSysDictExample example=new TSysDictExample();
		com.entities.TSysDictExample.Criteria criteria = example.createCriteria();
		criteria.andTypeEqualTo("nation");
		return dictMapper.selectByExample(example);
	}
	/**
	 * 查找民族
	 */
	@Override
	public List<TSysDict> selectEthnic() {
		Object type = ShiroUtils.getSubjct().getSession().getAttribute("type");
		return dictMapper.selectEthnic(type);
	}
	/**
	 * 查找学历
	 */
	@Override
	public List<TSysDict> selectEducationBackground() {
		Object type = ShiroUtils.getSubjct().getSession().getAttribute("type");
		return dictMapper.selectEducationBackground(type);
	}
	/**
	 * 根据身份证或者电话号码进行唯一性判断
	 * @param cardNum:防疫员身份证号
	 * @param phoneNum:防疫员电话 号码
	 */
	@Override
	public int judugeExit(String cardNum, String phoneNum) {
		TProtectorExample example=new TProtectorExample();
			Criteria criteria = example.createCriteria();
			int count=0;
		if(cardNum !=null) {
				criteria.andCardNumEqualTo(cardNum);
			count=protectorMapper.countByExample(example);
		}else if(phoneNum !=null){
				criteria.andPhoneNumEqualTo(phoneNum);
			count=protectorMapper.countByExample(example);
		}else {
			count=0;
		}
		
		return count;
	}

	
	/**
	 * 修改时根据身份证或者电话号码进行唯一性判断
	 * @param id:主键id
	 * @param cardNum:防疫员身份证号
	 * @param phoneNum:防疫员电话 号码
	 */
	@Override
	public int judugeUpdateExit(Integer id, String cardNum, String phoneNum) {
		//根据主键id查找犬主信息
 		TProtector protector = protectorMapper.selectByPrimaryKey(id);
			
			String cardNum2 = protector.getCardNum();
			String phoneNum2 = protector.getPhoneNum();
			int i =0;
			TProtectorExample example=new TProtectorExample();
				Criteria criteria = example.createCriteria();
			//判断身份证号码是否更改
			if(cardNum !=null && !cardNum.equals(cardNum2)) {
					criteria.andCardNumEqualTo(cardNum);
				//如果更改,如果i>0,则身份证号和其他重复
				 i = protectorMapper.countByExample(example);
			}else if(phoneNum !=null &&!phoneNum.equals(phoneNum2)) {
					criteria.andPhoneNumEqualTo(phoneNum);
					//如果更改,如果i>0,则电话号和其他重复
					 i = protectorMapper.countByExample(example);	
			}else {
				i=0;
			}
		return i;
	}

	/**
	 * 根据用户id查看防疫员详情
	 * @param  userId 用户id
	 */
	@Override
	public int findProtectorDetailByUserId(Integer userId) {
		TProtectorExample example=new TProtectorExample();
			Criteria criteria = example.createCriteria();
				criteria.andUserIdEqualTo(userId);
		List<TProtector> list = protectorMapper.selectByExample(example);
		Integer id = null;
			for(TProtector p:list) {
				 id = p.getId();
			}
		return id;
	}

	@Override
	public List<TProtector> initProtectorSel2(Long deptId) {
		return protectorMapper.initProtectorSel2(deptId);
	}
}
