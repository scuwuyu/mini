package com.gongsi.mini.vo.page;

import com.gongsi.mini.vo.BaseVO;
import lombok.Data;

/**
 * Created by 吴宇 on 2018-05-27.
 */
@Data
public class GoodsPageVO extends BaseVO {

    private String name;

    /**
     * 当前页码
     */
    private Integer currentPage;
    /**
     * 每页记录数
     */
    private Integer pageSize;
}
