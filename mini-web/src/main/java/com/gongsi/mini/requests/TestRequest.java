package com.gongsi.mini.requests;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by lxf on 2016/4/12.
 */
public class TestRequest {

    @NotNull(message = "test_1003")
    private Integer age;

   private Long id;

    /**
     * 嵌入对象以inner方式作为入参
     */
//    @NotEmpty(message = "test_1004")
    private TestInnerRequest testInnerRequest;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TestInnerRequest getTestInnerRequest() {
        return testInnerRequest;
    }

    public void setTestInnerRequest(TestInnerRequest testInnerRequest) {
        this.testInnerRequest = testInnerRequest;
    }
}
