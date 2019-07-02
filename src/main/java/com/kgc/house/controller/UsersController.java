package com.kgc.house.controller;


import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.UserCondition;
import com.kgc.house.entity.Users;
import com.kgc.house.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping
public class UsersController {

    @Autowired
    private UserService userService;


    //分页查全部
    @RequestMapping("/getUsers")
    @ResponseBody
    public Map<String,Object> getUsers(UserCondition condition){
        PageInfo<Users> pageInfo = userService.getUsersByPage(condition);
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }



}
