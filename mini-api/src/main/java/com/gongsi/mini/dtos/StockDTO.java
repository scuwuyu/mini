package com.gongsi.mini.dtos;

import java.math.BigDecimal;

/**
 * Created by wuyu on 2019/4/11.
 */
public class StockDTO {

    private String name;

    private BigDecimal yestodayPrice;

    private BigDecimal currentPrice;

    public StockDTO(String name, BigDecimal yestodayPrice, BigDecimal currentPrice) {
        this.name = name;
        this.yestodayPrice = yestodayPrice;
        this.currentPrice = currentPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getYestodayPrice() {
        return yestodayPrice;
    }

    public void setYestodayPrice(BigDecimal yestodayPrice) {
        this.yestodayPrice = yestodayPrice;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }
}
