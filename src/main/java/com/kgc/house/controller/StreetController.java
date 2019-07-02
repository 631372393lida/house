package com.kgc.house.controller;


import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Street;
import com.kgc.house.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping
public class StreetController {

    @Autowired
    private StreetService streetService;


    //分页查全部
    @RequestMapping("/getStreetByid")
    @ResponseBody
    public Map<String,Object> getStreetByid(Integer page, Integer rows,Integer did){
        PageInfo<Street> pageInfo = streetService.getStreetById(page,rows,did);
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }



}
