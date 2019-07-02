package com.kgc.house.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Type;
import com.kgc.house.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller         //@RestController =@Controller+@ResponseBody

public class TypeController {
    @Autowired
    private TypeService typeService;

    //分页查全部
    @RequestMapping("/getType")
    @ResponseBody
    public Map<String,Object> getType(Integer page,Integer rows){
        PageInfo<Type> pageInfo = typeService.getTypeByPage(page, rows);
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

//添加
    @RequestMapping("/addType")
    @ResponseBody
    public String addType(Type type){
        int i = typeService.addType(type);
        return "{\"result\":"+i+"}";
    }

//修改
    @RequestMapping("/upType")
    @ResponseBody
    public String upType(Type type){
        int i = typeService.updateType(type);
        return "{\"result\":"+i+"}";
    }


    //删除单条
    @RequestMapping("/deType")
    @ResponseBody
    public String deType(Integer id){
        int i = typeService.deleteType(id);
        return "{\"result\":"+i+"}";
    }


    //删除多条
    @RequestMapping("/demoreType")
    @ResponseBody
    public String demoreType(String ids){
        //将字符串转换为数组

        String [] arys =ids.split(",");
        Integer [] id=new Integer[arys.length];
        for (int i=0; i<arys.length;i++){
            id[i]=Integer.parseInt(arys[i]);
        }

        int i = typeService.deleteMore(id);
        return "{\"result\":"+i+"}";
    }



}
