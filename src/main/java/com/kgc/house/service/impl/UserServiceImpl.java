package com.kgc.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.UserCondition;
import com.kgc.house.entity.Users;
import com.kgc.house.entity.UsersExample;
import com.kgc.house.mapper.UsersMapper;
import com.kgc.house.service.UserService;
import com.kgc.house.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public PageInfo<Users> getUsersByPage(UserCondition condition) {
        PageHelper.startPage(condition.getPage(),condition.getRows());
        UsersExample usersExample=new UsersExample();
//添加条件
        UsersExample.Criteria criteria=usersExample.createCriteria();
        criteria.andIsadminEqualTo(new Integer(1));//表示管理员

        if(condition.getTelephone()!=null){
            criteria.andTelephoneLike("%"+condition.getTelephone()+"%");
        }
        if(condition.getStartAge()!=null){
            criteria.andAgeGreaterThan(condition.getStartAge());
        }
        if(condition.getEndAge()!=null){
            criteria.andAgeLessThan(condition.getEndAge());
        }

        List<Users> users = usersMapper.selectByExample(usersExample);
        PageInfo<Users> pageInfo=new PageInfo<>(users);
        return pageInfo;
    }

    @Override
    public int checkUname(String username) {
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();

        criteria.andNameEqualTo(username);
        criteria.andIsadminEqualTo(0);  //注册用户
        List<Users> users = usersMapper.selectByExample(usersExample);
        return users.size()==0?0:1;
    }

    @Override
    public int addUser(Users users) {
        users.setIsadmin(0);   //设置为前台用户
        //使用MD5加密
        users.setPassword(MD5Utils.md5Encrypt(users.getPassword()));

        return usersMapper.insertSelective(users);
    }

    @Override
    public Users login(String username, String password) {
        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        criteria.andIsadminEqualTo(0);
        criteria.andNameEqualTo(username);
        criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password));
        List<Users> users = usersMapper.selectByExample(usersExample);
        if (users.size() == 1) {
            return users.get(0);
        } return null;
    }

    //    @Override
//    public int addUsers(Users users) {
//        return 0;
//    }
//
//    @Override
//    public int updateUsers(Users users) {
//        return 0;
//    }
//
//    @Override
//    public int deleteUsers(Integer id) {
//        return 0;
//    }
//
//    @Override
//    public int deleteMore(Integer[] ids) {
//        return 0;
//    }
}

