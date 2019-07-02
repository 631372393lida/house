package com.kgc.house.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.District;
import com.kgc.house.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller         //@RestController =@Controller+@ResponseBody

public class DistrictController {
    @Autowired
    private DistrictService districtService;

    //分页查全部
    @RequestMapping("/getDistrict")
    @ResponseBody
    public Map<String,Object> getDistrict(Integer page,Integer rows){
        PageInfo<District> pageInfo = districtService.getDistrictByPage(page, rows);
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

//添加
    @RequestMapping("/addDistrict")
    @ResponseBody
    public String addDistrict(District district){
        int i = districtService.addDistrict(district);
        return "{\"result\":"+i+"}";
    }

//修改
    @RequestMapping("/upDistrict")
    @ResponseBody
    public String upDistrict(District district){
        int i = districtService.updateDistrict(district);
        return "{\"result\":"+i+"}";
    }


    //删除单条
    @RequestMapping("/deDistrict")
    @ResponseBody
    public String deDistrict(Integer id){
        int i = districtService.deleteDistrict(id);
        return "{\"result\":"+i+"}";
    }


    //删除多条
    @RequestMapping("/demoreDistrict")
    @ResponseBody
    public String demoreDistrict(String ids){
        //将字符串转换为数组

        String [] arys =ids.split(",");
        Integer [] id=new Integer[arys.length];
        for (int i=0; i<arys.length;i++){
            id[i]=Integer.parseInt(arys[i]);
        }

        int i = districtService.deleteMore(id);
        return "{\"result\":"+i+"}";
    }



}
