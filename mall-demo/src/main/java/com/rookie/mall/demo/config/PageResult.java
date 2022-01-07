package com.rookie.mall.demo.config;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rookie.mall.demo.entity.PmsBrand;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.List;
import java.util.Map;

/**
 * @author: chen
 * @date: 2022/1/4
 * @description: TODO 类描述
 **/
@Data
public class PageResult<T> {
    //当前页
    private Long pageNum = 1L;
    //每页记录数
    private Long pageSize = 10L;
    //总页数
    private Long totalPage;
    //总数
    private Long total;
    private List<T> list;
    //查询条件，模糊查询
    private Map<String,Object> map;

    public static <T> PageResult<T> restPage(IPage<T> page) {
        PageResult<T> result = new PageResult<T>();
        result.setTotalPage(page.getPages());
        result.setPageNum(page.getCurrent());
        result.setPageSize(page.getSize());
        result.setTotal(page.getTotal());
        result.setList(page.getRecords());
        return result;
    }
}
