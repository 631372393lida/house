package com.kgc.house.protal.controller;



import com.kgc.house.entity.Street;
import com.kgc.house.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;


@Controller("streetController2")
@RequestMapping
public class StreetController {

    @Autowired
    private StreetService streetService;


    //分页查全部
    @RequestMapping("getStreetByid2")
    @ResponseBody
    public List<Street> getStreetByid2(Integer did){
        return streetService.getStreetById(did);
    }



}
