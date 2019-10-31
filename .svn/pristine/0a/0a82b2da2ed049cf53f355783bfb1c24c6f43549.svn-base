package com.posiition.service;

import com.entities.TFenceDef;
import com.entities.TFenceDetail;
import com.github.pagehelper.PageInfo;
import com.posiition.VO.FcnceRefFenceDetail;
import com.posiition.VO.fenceDetailVO;
import com.utils.ReqPage;

import java.util.HashMap;
import java.util.List;

/**
 * 围栏相关
 */
public interface DeviceFenceService {


    /**
     * 查询电子围栏
     *
     * @param userId
     * @param page
     * @return
     */
    PageInfo<TFenceDef> list(Integer userId, ReqPage page);

    /**
     * 根据id删除围栏
     *
     * @param id
     */
    boolean delete(Integer id);

    /**
     * 根据id修改围栏信息
     *
     * @param tFenceDef
     * @return
     */
    boolean modifyByid(TFenceDef tFenceDef);

    /**
     * 保存区域信息
     *
     * @param tFenceDefs
     */
    boolean saves(List<TFenceDef> tFenceDefs);

    /**
     * 根据用户id 查询围栏详情集
     *
     * @param userId
     * @return
     */
    List<FcnceRefFenceDetail> listDetail(Integer userId);

    /**
     * 删除围栏详情
     *
     * @param id
     */
    boolean deleteDetail(Integer id);

    /**
     * 保存围栏信息  级联
     *
     * @param tFenceDetails
     */
    boolean saveDetails(List<FcnceRefFenceDetail> tFenceDetails);

    /**
     * 修改围栏详情信息
     *
     * @param tFenceDetail
     * @return
     */
    boolean modefyDetail(TFenceDetail tFenceDetail);

    /**
     * 搜索我的围栏列表
     *
     * @param keyWord
     * @param userId
     * @return
     */
    PageInfo<FcnceRefFenceDetail> searchFenceList(String keyWord, Integer userId, ReqPage reqPage);

    /**
     * 查询我的围栏
     * @param fenceId
     * @return
     */
    FcnceRefFenceDetail findfenRefDetailsByid(Integer fenceId);

    //电子围栏列表
    List<fenceDetailVO> findFenceList(HashMap<String,Object> map);
    
    
    /**
     * 根据犬id查找电子围栏
     */
    fenceDetailVO findFenceByDogId(Integer id);
}
