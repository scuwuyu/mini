package com.gongsi.mini.task;

import com.gongsi.mini.entities.StockIPO;
import com.gongsi.mini.entities.StockShangHai;
import com.gongsi.mini.services.StockIPOService;
import com.gongsi.mini.services.stock.StockServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by 吴宇 on 2018-08-18.
 */
@Slf4j
@Service
public class StockTask {

    @Autowired
    private StockServiceImpl stockService;

    @Autowired
    private StockIPOService stockIPOService;

    public void execute(){
        try {
            Thread.sleep(new Random().nextInt(10)*60*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("股票同步开始");
        List<StockShangHai> result = stockService.query();

        List<StockIPO> list = result.stream().map(item -> {
            StockIPO stockIPO = new StockIPO();
            stockIPO.setCode(item.getSecurity_code());
            stockIPO.setName(item.getSecurity_name());
            stockIPO.setTotalInitialIssue(item.getTotal_initial_issue());
            stockIPO.setOnlineIssuanceDate(item.getOnline_issuance_date());

            stockIPO.setIssuePrice(item.getIssue_price());
            stockIPO.setAnnounceSuccessRateResultDate(item.getAnnounce_success_rate_result_date());
            stockIPO.setListedDate(item.getListed_date());
            return stockIPO;
        }).collect(Collectors.toList());

        stockIPOService.syncList(list);

    }
}
