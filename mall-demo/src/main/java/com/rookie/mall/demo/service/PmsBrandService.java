package com.rookie.mall.demo.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.rookie.mall.demo.dao.PmsBrandMapper;
import com.rookie.mall.demo.entity.PmsBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author: chen
 * @date: 2021/12/30
 * @description: TODO 类描述
 **/
@Service
public class PmsBrandService {
    @Autowired
    private PmsBrandMapper pmsBrandMapper;

    public void addPmsBrand(PmsBrand pmsBrand) {
        pmsBrandMapper.insert(pmsBrand);
    }

    public void updatePmsBrandById(PmsBrand pmsBrand) {
        pmsBrandMapper.updateById(pmsBrand);
    }

    public void updatePmsBrand(PmsBrand pmsBrand, Map<String,Object> whereMap) {
        UpdateWrapper<PmsBrand> wrapper = new UpdateWrapper<>();
        //TODO
        pmsBrandMapper.update(pmsBrand, wrapper);
    }

    public void getPmsBrand(Long id) {
        pmsBrandMapper.selectById(id);
    }

    public void getPmsBrand(Map<String,Object> whereMap) {
        pmsBrandMapper.selectByMap(whereMap);
    }


}
