package com.posiition.service.impl;

import com.posiition.VO.DogRefDeviceRecord;
import com.posiition.mapper.TDeviceRecordMapper;
import com.posiition.service.LocusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LocusServiceImpl implements LocusService {

    @Autowired
    private TDeviceRecordMapper tDeviceRecordMapper;

    @Override
    public List<DogRefDeviceRecord> getLocusByDogId(Integer dogId, Integer day) {
        return tDeviceRecordMapper.getLocusByDogId(dogId, day);
    }

}
