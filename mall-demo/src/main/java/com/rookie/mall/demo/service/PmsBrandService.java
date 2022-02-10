package com.rookie.mall.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rookie.mall.demo.config.PageResult;
import com.rookie.mall.demo.config.Result;
import com.rookie.mall.demo.dao.PmsBrandMapper;
import com.rookie.mall.demo.entity.PmsBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author: chen
 * @date: 2021/12/30
 * @description: 品牌管理：二级缓存
 **/
@Service
@CacheConfig(cacheNames = "PmsBrand")
public class PmsBrandService {
    @Autowired
    
    private PmsBrandMapper pmsBrandMapper;

    public void addPmsBrand(PmsBrand pmsBrand) {
        pmsBrandMapper.insert(pmsBrand);
    }

    @CachePut(key = "'id_'+#pmsBrand.id")
    public PmsBrand updatePmsBrandById(PmsBrand pmsBrand) {
        pmsBrandMapper.updateById(pmsBrand);
        return pmsBrandMapper.selectById(pmsBrand.getId());
    }

    @CacheEvict(key = "'id_'+#pmsBrand.id")
    public void updatePmsBrand(PmsBrand pmsBrand, Map<String,Object> whereMap) {
        UpdateWrapper<PmsBrand> wrapper = new UpdateWrapper<>();
        //TODO
        pmsBrandMapper.update(pmsBrand, wrapper);
    }


    @Cacheable(key = "'id_'+#id",unless = "#result.data == null ")
    public Result<PmsBrand> getPmsBrand(Long id) {
        PmsBrand pmsBrand = pmsBrandMapper.selectById(id);
        return Result.success(pmsBrand);
    }

    public void getPmsBrand(Map<String,Object> whereMap) {
        pmsBrandMapper.selectByMap(whereMap);
    }

    /*
     * @param pmsBrandPageResult
     * @description: 通用查询接口
     * @return:
     * @author: chen
     * @date:
     */
    public Result getList(PageResult<PmsBrand> pmsBrandPageResult) {
        QueryWrapper<PmsBrand> wrapper = new QueryWrapper();
        //模糊查询
        if (pmsBrandPageResult.getMap() != null) {
            pmsBrandPageResult.getMap().forEach((k,v) -> wrapper.like(k,v));
        }

        Page<PmsBrand> page = new Page<>(pmsBrandPageResult.getPageNum(),pmsBrandPageResult.getPageSize());
        IPage<PmsBrand> iPage = pmsBrandMapper.selectPage(page, wrapper);
        return Result.success(PageResult.restPage(iPage));
    }

}
