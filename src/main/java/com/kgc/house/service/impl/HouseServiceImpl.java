package com.kgc.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.House;
import com.kgc.house.mapper.HouseMapper;
import com.kgc.house.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    private HouseMapper houseMapper;

    @Override
    public int addHouse(House house) {
        return houseMapper.insertSelective(house);
    }

    @Override
    public PageInfo<House> getUserHouseByPage(Integer page, Integer rows, Integer uid) {
        PageHelper.startPage(page,rows);
        List<House> houses = houseMapper.selectHouseByUserId(uid);
         return new PageInfo<House>(houses);
    }


    @Override
    public House getHouse(String id) {
        return houseMapper.getHouseByDId(id);

    }

    @Override
    public int updateHouse(House house) {
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public int deleteHouse(String id) {
        House house=new House();
        //设置主键值
        house.setId(id);
        //设置出租房状态
        house.setIsdel(new Integer(1));
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public PageInfo<House> getHouseBytate(Integer page, Integer rows, Integer ispass) {
        PageHelper.startPage(page,rows);
        List<House> houseBytate = houseMapper.getHouseBytate(ispass);

        return new  PageInfo<House>(houseBytate);
    }
}
