package com.gongsi.mini.services.impl;

import com.gongsi.mini.AbstractRollbackTest;
import com.gongsi.mini.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class TestServiceImplTest extends AbstractRollbackTest {

    @Autowired
    private TestService testService;

    @Test
    public void testQueryResult() throws Exception {

    }

    @Test
    public void testFindResult() throws Exception {

    }

}