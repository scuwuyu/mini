package com.gongsi.mini.vo;

import com.gongsi.mini.core.ensure.Ensure;
import lombok.Data;

import java.util.Objects;

/**
 * 获取小程序码
 *
 * @see https://developers.weixin.qq.com/miniprogram/dev/api/open-api/qr-code/getWXACodeUnlimit.html
 * Created by 吴宇 on 2018-10-26.
 */
@Data
public class MiniCodeVO extends BaseVO{

    private String access_token;
    /** 最大32个可见字符*/
    private String scene;

    private String page;

    private Integer width;

    private Boolean is_hyaline;


    public void check(){
        Ensure.that(Objects.isNull(scene)||scene.length()<=32).isTrue("scene长度超长");
    }
}
