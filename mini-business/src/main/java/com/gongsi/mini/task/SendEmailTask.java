package com.gongsi.mini.task;

import com.gongsi.mini.services.stock.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

/**
 * Created by wuyu on 2019/4/11.
 */
@Slf4j
@Service
public class SendEmailTask {

    @Autowired
    private EmailService emailService;

    public void execute(){
        log.info("SendEmailTask start");
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            emailService.send("");
            log.info("time={}",stopWatch.getTotalTimeMillis());
        } catch (Exception e) {
            log.error("发送邮件失败",e);
        }
    }

}
