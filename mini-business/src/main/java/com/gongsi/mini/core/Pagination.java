package com.gongsi.mini.core;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Created by 吴宇 on 2018-05-27.
 */
public class Pagination<T> implements Serializable {

    public Pagination(Integer currentPage, Integer pageSize) {
        setCurrentPage(currentPage);
        setPageSize(pageSize);
    }

    /**
     * 每页最大记录数限制
     */
    public static final Integer MAX_PAGE_SIZE = Integer.MAX_VALUE;

    /**
     * 当前页码
     */
    private Integer currentPage = 1;

    /**
     * 每页记录数
     */
    private Integer pageSize = 10;

    /**
     * 总记录数
     */
    private Integer totalCount = 0;

    /**
     * 总页数
     */
    private Integer pageCount = 0;

    /**
     * 数据List
     */
    private List<T> list;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        if (Objects.isNull(currentPage)||currentPage < 1) {
            currentPage = 1;
        }
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if (Objects.isNull(pageSize)||pageSize < 1) {
            pageSize = 10;
        } else if (pageSize > MAX_PAGE_SIZE) {
            pageSize = MAX_PAGE_SIZE;
        }
        this.pageSize = pageSize;
        if (totalCount != 0) {
            setTotalCount(this.totalCount);
        }
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
        if (pageSize == 0) {
            pageCount = 0;
        } else {
            pageCount = (totalCount + pageSize - 1) / pageSize;
        }
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getOffSize(){
        int offSize = (currentPage-1)*pageSize;

        return offSize<0?0:offSize;
    }
}
