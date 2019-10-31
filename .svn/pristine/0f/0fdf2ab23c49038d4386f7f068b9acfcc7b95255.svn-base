package com.posiition.mapper;

import com.entities.TFenceDef;
import com.entities.TFenceDetail;
import com.posiition.VO.FcnceRefFenceDetail;
import com.posiition.VO.fenceDetailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface DeviceFenceMapper {


    /**
     * 查询用户的电子围栏信息
     *
     * @param userId
     * @param orgIds
     * @return
     */
    List<TFenceDef> findFencesByUserId(@Param("userId") Integer userId, @Param("orgIds") List<Integer> orgIds);

    /**
     * 根据id删除围栏
     *
     * @param id
     */
    boolean removeById(Integer id);

    /**
     * 根据id修改围栏信息
     *
     * @param tFenceDef
     * @return
     */
    int modifyByid(TFenceDef tFenceDef);

    /**
     * 保存围栏信息
     * fence_type，create_by，status ，area_id ，org_id不能为空
     *
     * @param tFenceDefs
     * @return
     */
    int saves(List<TFenceDef> tFenceDefs);

    /**
     * 查询围栏详情信息
     *
     * @param userId 用户id
     * @param orgIds 机构id集合
     * @return
     */
    List<FcnceRefFenceDetail> findFencesInfoList(Integer userId, List<Integer> orgIds);

    /**
     * 根据id删除围栏 级联删除
     *
     * @param id
     */
    int removeDetailById(Integer id);

    /**
     * 根据围栏id查询围栏详情
     *
     * @param id
     * @return
     */
    List<TFenceDetail> findDetailsByid(Integer id);

    /**
     * 保存围栏详情信息
     * fence_id  lng lat create_date不能为空
     *
     * @param fenceDetails
     * @return
     */
    int saveDetails(List<TFenceDetail> fenceDetails);

    /**
     * 修改围栏详情信息
     *
     * @param tFenceDetail
     * @return
     */
    int modefyDetail(TFenceDetail tFenceDetail);

    /**
     * 查询我的围栏
     *
     * @param keyWord 围栏名称、编号
     * @param userId
     * @param orgIds
     * @return
     */
    List<FcnceRefFenceDetail> findFcnceRefFenceDetailByUserId(
            @Param("keyWord") String keyWord,
            @Param("userId") Integer userId,
            @Param("orgIds") List<Integer> orgIds);


    /**
     * 查询我的围栏
     *
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
