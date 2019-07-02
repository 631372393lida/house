package com.kgc.house.service;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Type;

import java.util.List;

public interface TypeService {

    PageInfo<Type> getTypeByPage(Integer page, Integer pageSize);


    int addType(Type type);


    int updateType(Type type);

    int deleteType(Integer id);

    int deleteMore(Integer[] ids);

    List<Type> getAllType();

}

