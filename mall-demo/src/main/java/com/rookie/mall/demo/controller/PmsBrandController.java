package com.rookie.mall.demo.controller;

import com.rookie.mall.demo.entity.PmsBrand;
import com.rookie.mall.demo.service.PmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author: chen
 * @date: 2021/12/30
 * @description: TODO 类描述
 **/
@RestController
@RequestMapping("/pmsBrand")
public class PmsBrandController {

    @Autowired
    private PmsBrandService service;

    @RequestMapping("/add")
    public void add(@RequestBody PmsBrand pmsBrand) {
        service.addPmsBrand(pmsBrand);
    }

    @RequestMapping("/upt")
    public void update(@RequestBody PmsBrand pmsBrand) {
        service.updatePmsBrandById(pmsBrand);
    }

    @RequestMapping("/upt")
    public void updateByMap(@RequestBody PmsBrand pmsBrand, Map<String,Object> map) {
        service.updatePmsBrand(pmsBrand,map);
    }

    @RequestMapping("/get")
    public void get(Long id) {
        service.getPmsBrand(id);
    }

    @RequestMapping("/get")
    public void get(Map<String,Object> map) {
        service.getPmsBrand(map);
    }
}
