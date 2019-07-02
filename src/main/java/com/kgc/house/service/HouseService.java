package com.kgc.house.service;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.House;


public interface HouseService {

    //添加出租房
    public  int addHouse(House house);


    //查询出租房
    public PageInfo<House> getUserHouseByPage(Integer page,Integer rows,Integer uid);


    //查询单条
    public House getHouse(String id);

    //修改
    public int updateHouse(House house);

    //删除
    public int deleteHouse(String id);

    //查询出租房
    public PageInfo<House> getHouseBytate(Integer page,Integer rows,Integer ispass);

}
