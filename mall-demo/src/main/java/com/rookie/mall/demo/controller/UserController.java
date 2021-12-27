package com.rookie.mall.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rookie.mall.demo.dao.UserMapper;
import com.rookie.mall.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ganxinming
 * @createDate 2021/12/27
 * @description
 */
@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/getUser")
    public void getUser(){
        User user = userMapper.selectById(1);
        System.out.println(user);
    }}
