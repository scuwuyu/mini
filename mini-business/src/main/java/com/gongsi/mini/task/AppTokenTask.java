package com.gongsi.mini.task;

import com.gongsi.mini.services.AppTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 吴宇 on 2018-10-26.
 */
@Slf4j
@Service
public class AppTokenTask {

    @Autowired
    private AppTokenService appTokenService;

    public void execute(){
        appTokenService.updateAllTokens();
    }
}
