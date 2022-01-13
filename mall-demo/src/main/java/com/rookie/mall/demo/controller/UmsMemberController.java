package com.rookie.mall.demo.controller;

import com.rookie.mall.demo.config.CodeEnum;
import com.rookie.mall.demo.config.Result;
import com.rookie.mall.demo.service.UmsMemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: chen
 * @date: 2022/1/12
 * @description: TODO 类描述
 **/
@RestController
@RequestMapping("/member")
public class UmsMemberController {

    @Autowired
    private UmsMemberService service;

    @RequestMapping("/getcode")
    public Result getCode(String phone) {
        return service.generateAuthCode(phone);
    }

    @RequestMapping("/verifyAuthCode")
    public Result verifyAuthCode(String phone,String authCode) {
        Result<String> result = service.verifyAuthCode(phone);
        if (StringUtils.isBlank(result.getData())) {
            return result;
        }
        if (result.getData().equals(authCode)) {
            return Result.success();
        }
        return Result.code(CodeEnum.AUTH_CODE_ERROR.getCode(),CodeEnum.AUTH_CODE_ERROR.getMessage());
    }

}
