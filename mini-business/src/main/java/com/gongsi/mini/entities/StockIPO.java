package com.gongsi.mini.entities;

import java.util.Date;

public class StockIPO {
    private Long id;

    private String code;

    private String name;

    private String totalInitialIssue;

    private String onlineIssuanceDate;

    private String issuePrice;

    private String announceSuccessRateResultDate;

    private String listedDate;

    private Date createTime;

    private Date modifyTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockIPO)) return false;

        StockIPO stockIPO = (StockIPO) o;

        if (code != null ? !code.equals(stockIPO.code) : stockIPO.code != null) return false;
        if (name != null ? !name.equals(stockIPO.name) : stockIPO.name != null) return false;
        if (totalInitialIssue != null ? !totalInitialIssue.equals(stockIPO.totalInitialIssue) : stockIPO.totalInitialIssue != null)
            return false;
        if (onlineIssuanceDate != null ? !onlineIssuanceDate.equals(stockIPO.onlineIssuanceDate) : stockIPO.onlineIssuanceDate != null)
            return false;
        if (issuePrice != null ? !issuePrice.equals(stockIPO.issuePrice) : stockIPO.issuePrice != null) return false;
        if (announceSuccessRateResultDate != null ? !announceSuccessRateResultDate.equals(stockIPO.announceSuccessRateResultDate) : stockIPO.announceSuccessRateResultDate != null)
            return false;
        return listedDate != null ? listedDate.equals(stockIPO.listedDate) : stockIPO.listedDate == null;

    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (totalInitialIssue != null ? totalInitialIssue.hashCode() : 0);
        result = 31 * result + (onlineIssuanceDate != null ? onlineIssuanceDate.hashCode() : 0);
        result = 31 * result + (issuePrice != null ? issuePrice.hashCode() : 0);
        result = 31 * result + (announceSuccessRateResultDate != null ? announceSuccessRateResultDate.hashCode() : 0);
        result = 31 * result + (listedDate != null ? listedDate.hashCode() : 0);
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTotalInitialIssue() {
        return totalInitialIssue;
    }

    public void setTotalInitialIssue(String totalInitialIssue) {
        this.totalInitialIssue = totalInitialIssue == null ? null : totalInitialIssue.trim();
    }

    public String getOnlineIssuanceDate() {
        return onlineIssuanceDate;
    }

    public void setOnlineIssuanceDate(String onlineIssuanceDate) {
        this.onlineIssuanceDate = onlineIssuanceDate == null ? null : onlineIssuanceDate.trim();
    }

    public String getIssuePrice() {
        return issuePrice;
    }

    public void setIssuePrice(String issuePrice) {
        this.issuePrice = issuePrice == null ? null : issuePrice.trim();
    }

    public String getAnnounceSuccessRateResultDate() {
        return announceSuccessRateResultDate;
    }

    public void setAnnounceSuccessRateResultDate(String announceSuccessRateResultDate) {
        this.announceSuccessRateResultDate = announceSuccessRateResultDate == null ? null : announceSuccessRateResultDate.trim();
    }

    public String getListedDate() {
        return listedDate;
    }

    public void setListedDate(String listedDate) {
        this.listedDate = listedDate == null ? null : listedDate.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}