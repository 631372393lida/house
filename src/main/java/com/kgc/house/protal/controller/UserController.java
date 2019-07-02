package com.kgc.house.protal.controller;

import com.kgc.house.entity.Users;
import com.kgc.house.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/page/")
public class UserController {

    @Autowired
    private UserService userService;




    @RequestMapping("/checkUname")
    @ResponseBody
    public String checkUname(String username){
        int i = userService.checkUname(username);
        return "{\"result\":"+i+"}";
    }





    @RequestMapping("regUser")
    public String regUser(Users users){
        //调用业务
        int temp=userService.addUser(users);
        if(temp>0)
        return "login";
        else
            return "error";
    }


    @RequestMapping("login")
    public String login(String username, String password, Model model, HttpSession session){
        Users login = userService.login(username, password);
        if (login==null) {
            model.addAttribute("info", "用户名或者密码错误");
            return "login";
        }
          else
              session.setAttribute("user",login);
            //  session.setMaxInactiveInterval(30);    //30秒后信息失效
               return "guanli";


    }

}
