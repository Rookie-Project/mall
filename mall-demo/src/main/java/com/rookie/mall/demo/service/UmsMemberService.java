package com.rookie.mall.demo.service;

import com.rookie.mall.demo.config.Result;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author: chen
 * @date: 2022/1/5
 * @description: TODO 类描述
 **/
@Service
public class UmsMemberService {


    @Cacheable()
    public Result generateAuthCode(String telephone) {
        return null;
    }

    public Result verifyAuthCode(String telephone, String authCode) {
        return null;
    }
}
