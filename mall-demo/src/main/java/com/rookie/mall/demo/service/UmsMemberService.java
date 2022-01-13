package com.rookie.mall.demo.service;


import com.rookie.mall.demo.config.CodeEnum;
import com.rookie.mall.demo.config.Result;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author: chen
 * @date: 2022/1/5
 * @description: TODO 类描述
 **/
@Service
public class UmsMemberService {


    /*
     * @param telephone
     * @description: 获取验证码，每次请求都会重新生成，时效一分钟
     * @return:
     * @author: chen
     * @date:
     */
    @CachePut(cacheNames = "authCode",key = "#telephone")
    public Result generateAuthCode(String telephone) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        return Result.success(sb.toString());
    }

    /*
     * @param telephone
     * @description: 获取验证码
     * @return:
     * @author: chen
     * @date:
     */
    @Cacheable(cacheNames = "authCode",key = "#telephone",unless = "result.data == null ")
    public Result verifyAuthCode(String telephone) {
        return Result.code(CodeEnum.AUTH_CODE_EXPIRED.getCode(),CodeEnum.AUTH_CODE_EXPIRED.getCode());
    }
}
