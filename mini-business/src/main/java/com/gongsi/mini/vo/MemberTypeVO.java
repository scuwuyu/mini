package com.gongsi.mini.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by 吴宇 on 2018-06-03.
 */
@Data
public class MemberTypeVO extends BaseVO {
    private Long id;

    private String name;

    private Integer months;

    private BigDecimal price;

    private Boolean condition;
}
