package com.kgc.house.service;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.UserCondition;
import com.kgc.house.entity.Users;

public interface UserService {

    PageInfo<Users> getUsersByPage(UserCondition condition);

//检查用户是否存在
    public int checkUname(String username);

    //注册
    int addUser(Users users);

    //登录
    Users login(String username,String password);



//    int addUsers(Users users);
//
//
//    int updateUsers(Users users);
//
//    int deleteUsers(Integer id);
//
//    int deleteMore(Integer[] ids);
}

