package com.kgc.house.protal.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.District;
import com.kgc.house.entity.House;
import com.kgc.house.entity.Type;
import com.kgc.house.entity.Users;
import com.kgc.house.service.DistrictService;
import com.kgc.house.service.HouseService;
import com.kgc.house.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class HouseController {
    @Autowired
 private TypeService typeService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private HouseService houseService;


@RequestMapping("gofabu")
    public String gofabu(Model model){
    List<Type> allType = typeService.getAllType();

    List<District> allDistrict = districtService.getAllDistrict();

    //使用model 将数据传递到页面
    model.addAttribute("allType",allType);
    model.addAttribute("allDistrict",allDistrict);
    return "fabu";

}

    @RequestMapping("addHouse")
    public String addHouse(House house, @RequestParam(value="pfile",required = false) CommonsMultipartFile pfile, HttpSession session) throws IOException {

        String filename = pfile.getOriginalFilename();
        String expName=filename.substring(filename.lastIndexOf("."));
       //保存文件名
        String saveName = System.currentTimeMillis()+expName;
        File file=new File("D:\\images\\"+saveName);
        pfile.transferTo(file);  //保存


        //保存数据库的记录  house已经接收部分表单数据
        //设置编号
        house.setId(System.currentTimeMillis()+"");
        //设置用户编号
        Users user=(Users)session.getAttribute("user");
        house.setUserId(user.getId());
        //设置审核状态 0  如果表中有默认值 可不设
        house.setIspass(0);
        //设置是否删除  0
        house.setIsdel(0);
        //设置图片路径
        house.setPath(saveName);
        if(houseService.addHouse(house)>0){ //保存数据
            //调用业务
            //houseService.addHouse(house); //添加信息到数据库
            return "redirect:/page/guanli.jsp";  //跳转页面
        }
        else{
            //成功上传的图片删除
     //       file.delete();
            return "redirect:/page/guanli.jsp";  //跳转页面
        }

    }




     //查询出租房
    @RequestMapping("getUserHouse")
    public String getUserHouse(Integer page,HttpSession session,Model model){
    Users user = (Users) session.getAttribute("user");
    PageInfo<House> pageInfo = houseService.getUserHouseByPage(page==null?1:page, 3, user.getId());
    model.addAttribute("pageInfo",pageInfo);

    return "guanli";

    }




   //修改出租房
     @RequestMapping("getHouse")
    public String getHouse(String id,Model model){
         List<Type> allType = typeService.getAllType();
         List<District> allDistrict = districtService.getAllDistrict();
         House house = houseService.getHouse(id);
         model.addAttribute("allType",allType);
         model.addAttribute("allDistrict",allDistrict);
         model.addAttribute("house",house);

         return "upfabu";


     }



    @RequestMapping("upHouse")
    public String upHouse(String oldPic, House house, @RequestParam(value="pfile",required = false) CommonsMultipartFile pfile, HttpSession session) throws IOException {
          File file=null;
          if(pfile.getOriginalFilename().equals("")){
              //System.out.println("不修改图片");
          }else {
             // System.out.println("修改图片");

              //保存文件名
              file=new File("D:\\images\\"+oldPic);
              pfile.transferTo(file);  //保存
              //设置图片名称
              house.setPath(oldPic);
          }


        if(houseService.updateHouse(house)<=0){
            //成功上传的图片删除
            if(file!=null) file.delete();
        }
            return "redirect:getUserHouse";  //跳转页面
    }

    @RequestMapping("delHouse")
    @ResponseBody
    public String delHouse(String id){
        int i = houseService.deleteHouse(id);
       // return "redirect:getUserHouse";
      return "{\"result\":"+i+"}";

    }

    }
