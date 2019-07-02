package com.kgc.house.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.House;
import com.kgc.house.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller("HouseController2")
@RequestMapping
   public class HouseController {
    @Autowired
    private HouseService houseService;

    //查询未审核
    @RequestMapping("/getHouseNopass")
    @ResponseBody
   public Map<String,Object> getHouseNopass(Integer page,Integer rows){
        PageInfo<House> houseBytate = houseService.getHouseBytate(page, rows, 0);
        Map<String,Object> map=new HashMap<>();
        map.put("total",houseBytate.getTotal());
        map.put("rows",houseBytate.getList());

        return map;

    }

    //查询已审核
    @RequestMapping("/getHouseYespass")
    @ResponseBody
    public Map<String,Object> getHouseYespass(Integer page,Integer rows){
        PageInfo<House> houseBytate = houseService.getHouseBytate(page, rows, 1);
        Map<String,Object> map=new HashMap<>();
        map.put("total",houseBytate.getTotal());
        map.put("rows",houseBytate.getList());
        return map;

    }


    //通过审核
    @RequestMapping("/passHouse")
    public Map<String,Object> passHouse(String id){
        int temp = houseService.passHoues(id);
        Map<String,Object> map=new HashMap<>();
        map.put("result",temp);
        return map;

    }


    }
