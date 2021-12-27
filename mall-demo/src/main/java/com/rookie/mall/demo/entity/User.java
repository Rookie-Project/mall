package com.rookie.mall.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ganxinming
 * @createDate 2021/12/27
 * @description
 */
@Data
public class User implements Serializable {
    @TableId(type = IdType.ID_WORKER)
    private Integer id;
    private String userName;
    private Integer userAge;
}
