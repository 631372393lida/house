package com.kgc.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.District;
import com.kgc.house.entity.DistrictExample;
import com.kgc.house.mapper.DistrictMapper;
import com.kgc.house.mapper.StreetMapper;
import com.kgc.house.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DistrictServiceImpl  implements DistrictService {
    @Autowired
    private DistrictMapper districtMapper;

    @Autowired
    private StreetMapper streetMapper;


    @Override
    public PageInfo<District> getDistrictByPage(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        DistrictExample example=new DistrictExample();
//  条件查询      DistrictExample.Criteria criteria = example.createCriteria();
//        criteria.andNameNotLike("");


        List<District> districts = districtMapper.selectByExample(example);
        return new PageInfo<>(districts);


    }


    @Override
    public int addDistrict(District district) {
       return   districtMapper.insertSelective(district);
    }


    @Override
    public int updateDistrict(District district) {
        return districtMapper.updateByPrimaryKeySelective(district);
    }


    @Transactional  //事务
    public int deleteDistrict(Integer id) {


        try {
            streetMapper.deleteByid(id);   //删街道
            districtMapper.deleteByPrimaryKey(id);
            return 1;
        } catch (Exception e) {
            return 0;
        }

    }

    @Override
    public int deleteMore(Integer[] ids) {
        return districtMapper.deleteMore(ids);
    }


    @Override
    public List<District> getAllDistrict() {
        return districtMapper.selectByExample(new DistrictExample());
    }
}
