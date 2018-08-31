package com.gongsi.mini.vo;

import com.gongsi.mini.core.ensure.Ensure;
import lombok.Data;

/**
 * Created by 吴宇 on 2018-08-31.
 */
@Data
public class AdviseVO extends BaseVO {

    private String advise;

    public void check(){
        Ensure.that(advise).isNotEmpty("建议不能为空");
    }
}
