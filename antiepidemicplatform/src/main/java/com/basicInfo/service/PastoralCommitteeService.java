package com.basicInfo.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.basicInfo.vo.PastoralVO;
import com.entities.TPastoralCommittee;
import com.utils.PageUtils;

public interface PastoralCommitteeService {

	/**
	 * 新增牧委会
	 * @param committee
	 * @return
	 */
	int addPastoralCommittee(TPastoralCommittee committee);

	/**
	 * 删除牧委会
	 * @param id
	 * @return
	 */
	int deletePastoralCommittee(Integer id);

	/**
	 * 修改牧委会
	 * @param cooperation
	 * @return
	 */
	int updatePastoralCommittee(TPastoralCommittee committee);

	/**
	 * 查看牧委会详情
	 * @param id
	 * @return
	 */
	TPastoralCommittee findPastoralCommitteeDetail(Integer id);

	
	/**
	 * 查看牧委会列表
	 * @param pageNumber
	 * @param pageSize
	 * @param orgId
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	PageUtils findPastoralCommitteeList(Integer index, Integer pageSize, String searchKey) throws UnsupportedEncodingException;

	/**
	 * 根据区域id查找下属牧委会
	 */
	List<TPastoralCommittee> findPastoralByAreaId();

}
