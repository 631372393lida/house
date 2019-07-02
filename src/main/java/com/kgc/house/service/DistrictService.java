package com.kgc.house.service;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.District;

import java.util.List;

public interface DistrictService {

    PageInfo<District> getDistrictByPage(Integer page,Integer pageSize);


    int addDistrict(District district);


    int updateDistrict(District district);

    int deleteDistrict(Integer id);

    int deleteMore(Integer[] ids);

    List<District> getAllDistrict();
}

