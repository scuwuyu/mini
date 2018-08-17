package com.gongsi.mini.entities;

import lombok.Data;

import java.util.Date;

/**
 * Created by wuyu on 2018/8/16.
 */
@Data
public class StockShangHai {

    private String security_code;
    private String security_name;

    /** 初始发行总量（万股）*/
    private String total_initial_issue;
    /** 网上发行日*/
    private String online_issuance_date;
    /** 发行价(元)*/
    private String issue_price;
    /** 中签结果公告日*/
    private String announce_success_rate_result_date;

    /** 上市日 */
    private String listed_date;

}
