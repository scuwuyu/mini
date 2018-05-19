package com.gongsi.mini;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 吴宇 on 2018-05-13.
 */
@ContextConfiguration(
        locations = {"classpath:spring-datasource-dbunit.xml", "classpath*:spring-services.xml"}
)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, TransactionDbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@Transactional
public class AbstractRollbackTest {
    public AbstractRollbackTest() {
    }
}
