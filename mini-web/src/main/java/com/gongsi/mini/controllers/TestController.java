package com.gongsi.mini.controllers;

import com.gongsi.mini.apis.UserDubboService;
import com.gongsi.mini.dtos.QueryUserDto;
import com.gongsi.mini.services.TestService;
import com.gongsi.mini.requests.TestRequest;
import com.gongsi.mini.responses.TestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 *
 * requestMapping 统一以'/'开头
 * 建议使用restful的接口，比如/operator/{id}。
 * 方法名称使用add/save, update/modify, delete, get/list/view对应RequestMethod里的post, put, delete, get;其他的随意
 *
 */
@Controller
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private UserDubboService testDubboService;

    /**
     * 如果需要校验参数，@Valid注解一定需要加，否则校验不会生效
     *
     * @param request
     * @return 返回值的问题，框架里默认会包装一个Result对象，会包含code， descrition 与业务对象，请不要自己封装code，description，只需要返回业务对象（XXXResponse）
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public TestResponse test(@Valid TestRequest request) {
        //调用local服务，此时入参可以是po对象，也可以为dto对象，但是禁止出现vo对象
        QueryUserDto dto = new QueryUserDto();


        return new TestResponse();
    }
}
