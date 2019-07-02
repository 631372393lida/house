package com.kgc.house.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Street;
import com.kgc.house.entity.StreetExample;
import com.kgc.house.mapper.StreetMapper;
import com.kgc.house.service.StreetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StreetServiceImpl implements StreetService {
    @Autowired
    private StreetMapper streetMapper;

    @Override
    public PageInfo<Street> getStreetById(Integer page, Integer pageSize, Integer districtId) {

        PageHelper.startPage(page,pageSize);
        StreetExample streetExample=new StreetExample();
        StreetExample.Criteria criteria = streetExample.createCriteria();
        criteria.andDistrictIdEqualTo(districtId);

        List<Street> streets = streetMapper.selectByExample(streetExample);

        return new PageInfo<>(streets);
    }

    @Override
    public List<Street> getStreetById(Integer districtId) {
        StreetExample streetExample=new StreetExample();
        StreetExample.Criteria criteria=streetExample.createCriteria();
        criteria.andDistrictIdEqualTo(districtId);
        List<Street> streets = streetMapper.selectByExample(streetExample);

        return streets;
    }
}
