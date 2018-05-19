package com.gongsi.mini.controllers;

import com.gongsi.mini.dtos.QueryUserDto;
import com.gongsi.mini.requests.TestRequest;
import com.gongsi.mini.responses.TestResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * Created by 吴宇 on 2017-02-12.
 */
@Controller
public class RedirectTestController {
    @RequestMapping(value = "/test123", method = RequestMethod.GET)
    @ResponseBody
    public TestResponse test(TestRequest request) {
        //调用local服务，此时入参可以是po对象，也可以为dto对象，但是禁止出现vo对象
        QueryUserDto dto = new QueryUserDto();
        dto.setName("123");

        TestResponse response = new TestResponse();
        response.setName("111");

        return response;
    }
}
