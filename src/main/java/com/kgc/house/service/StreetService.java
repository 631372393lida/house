package com.kgc.house.service;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Street;

import java.util.List;


public interface StreetService {

    PageInfo<Street> getStreetById(Integer page,Integer pageSize,Integer districtId);

    List<Street> getStreetById(Integer districtId);



//    int addUsers(Users users);
//
//
//    int updateUsers(Users users);
//
//    int deleteUsers(Integer id);
//
//    int deleteMore(Integer[] ids);
}

