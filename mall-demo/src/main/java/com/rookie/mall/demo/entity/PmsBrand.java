package com.rookie.mall.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class PmsBrand implements Serializable {
    private static final long serialVersionUID = 3318929062133160067L;
    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    /**
     * 首字母
     *
     * @mbggenerated
     */
    private String firstLetter;

    private Integer sort;

    /**
     * 是否为品牌制造商：0->不是；1->是
     *
     * @mbggenerated
     */
    private Integer factoryStatus;

    private Integer showStatus;

    /**
     * 产品数量
     *
     * @mbggenerated
     */
    private Integer productCount;

    /**
     * 产品评论数量
     *
     * @mbggenerated
     */
    private Integer productCommentCount;

    /**
     * 品牌logo
     *
     * @mbggenerated
     */
    private String logo;

    /**
     * 专区大图
     *
     * @mbggenerated
     */
    private String bigPic;

    /**
     * 品牌故事
     *
     * @mbggenerated
     */
    private String brandStory;


}