package com.statisanalysis.serivce.impl;

import com.statisanalysis.dao.OuterDao;
import com.statisanalysis.entity.DogVO;
import com.statisanalysis.serivce.IOuterService;
import com.statisanalysis.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author: linchong
 * @Date: 2018/10/12 17:59
 * @Version 1.0
 */
@Service
public class OuterSereviceImpl implements IOuterService {
    @Autowired
    private OuterDao outerDao;
    @Override
    public Integer getDogLiveNum(Map<String, Object> map) throws Exception {
        return outerDao.getLiveDog(map);
    }

    @Override
    public Integer getDogNewNum(Map<String, Object> map) {
        return outerDao.getNewDogNum(map);
    }

    @Override
    public Integer getCancelDogNum(Map<String, Object> map) {
        return outerDao.getCancelDogNum(map);
    }

    @Override
    public Integer getOwnerNewNum(Map<String, Object> map) {
        return outerDao.getOwnerNewNum(map);
    }

    @Override
    public Integer getAntiedemicTimes(Map<String, Object> map) {
        return outerDao.getAntiedemicTimes(map);
    }

    @Override
    public Integer getDogCorpseNum(Map<String, Object> map) {
        return outerDao.getDogCorpseNum(map);
    }

    @Override
    public Integer getManureDealTimes(Map<String, Object> map) {
        return outerDao.getManureDealTimes(map);
    }

    @Override
    public Integer getStrayDogTimes(Map<String, Object> map) {
        return outerDao.getStrayDogTimes(map);
    }

    @Override
    public Integer getNeckTimes(Map<String, Object> map) {
        return outerDao.getNectletTimes(map);
    }
}
