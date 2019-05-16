package com.basicInfo.service;

import java.io.UnsupportedEncodingException;

import com.basicInfo.vo.VeterinarianVO;
import com.entities.TVeterinarian;
import com.utils.PageUtils;

public interface VeterinarianService {

	/**
	 * 新增兽医
	 * @param veterinarian
	 * @return
	 */
	int addVeterinarian(TVeterinarian veterinarian);

	/**
	 * 删除兽医
	 * @param id
	 * @return
	 */
	int deleteVeterinarian(Integer id);

	/**
	 * 修改兽医
	 * @param veterinarian
	 * @return
	 */
	int updateVeterinarian(TVeterinarian veterinarian);

	/**
	 * 兽医列表
	 * @param pageNumber
	 * @param pageSize
	 * @param orgId
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	PageUtils findVeterinarianList(Integer index, Integer pageSize, Integer orgId,String searchKey) throws UnsupportedEncodingException;

	/**
	 * 查看兽医详情
	 * @param id
	 * @return
	 */
	VeterinarianVO findVeterinarianDetail(Integer id);

}
