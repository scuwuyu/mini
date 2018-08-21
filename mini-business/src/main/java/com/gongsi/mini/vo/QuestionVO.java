package com.gongsi.mini.vo;

import com.gongsi.mini.core.ensure.Ensure;
import lombok.Data;

/**
 * Created by 吴宇 on 2018-08-21.
 */
@Data
public class QuestionVO extends BaseVO{

    private Integer type;

    public void check(){
        Ensure.that(type).isNotNull("问题类型不能为空");
    }
}
