package com.kgc.house.mapper;

import com.kgc.house.entity.House;
import com.kgc.house.entity.HouseExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HouseMapper {
    int deleteByPrimaryKey(String id);

    int insert(House record);

    int insertSelective(House record);

    List<House> selectByExample(HouseExample example);

    House selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);

    //查询用户发布的出租房
    List<House> selectHouseByUserId( Integer uid);

    //查询出租房 带区域ID
    House getHouseByDId( String uid);

    //查询审核出租房
    List<House> getHouseBytate(Integer state);
}