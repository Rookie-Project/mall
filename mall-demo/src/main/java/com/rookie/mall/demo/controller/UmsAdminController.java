package com.rookie.mall.demo.controller;

import com.rookie.mall.demo.entity.UmsAdmin;
import com.rookie.mall.demo.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: chen
 * @date: 2022/1/17
 * @description: TODO 类描述
 **/
@RestController
public class UmsAdminController {

    @Autowired
    private UmsAdminService service;

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/register")
    public String register(@RequestBody UmsAdmin admin) {
        service.register(admin);
        return "hello";
    }
}
