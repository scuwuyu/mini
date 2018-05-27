package com.gongsi.mini.core.utils;

import java.util.Random;

/**
 * Created by 吴宇 on 2018-05-27.
 */
public class IdGenerator {
    /** 正式环境 wemall_coupon_detail id
     * 范围：1122156845927104 -- 2162044799158656
     */
    private static IdWorker idWorker = new IdWorker();

    public static String nextId() {
        return String.valueOf(idWorker.nextId());
    }

    private static class IdWorker{
        private final static long START_STMP = 1480166465631L;

        /**
         * 每一部分占用的位数
         */
        private final static long SEQUENCE_BIT = 6; //序列号占用的位数
        private final static long MACHINE_BIT = 5;  //机器标识占用的位数
        private final static long DATACENTER_BIT = 5;//数据中心占用的位数

        /**
         * 每一部分的最大值
         */
        private final static long MAX_DATACENTER_NUM = -1L ^ (-1L << DATACENTER_BIT);//最大值31-1
        public final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);//最大值31-1
        private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);//最大值4095-1

        /**
         * 每一部分向左的位移
         */
        private final static long MACHINE_LEFT = SEQUENCE_BIT;
        private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
        private final static long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;

        private long datacenterId;  //数据中心
        private long machineId;    //机器标识
        private long sequence = 0L; //序列号
        private long lastStmp = -1L;//上一次时间戳

        public IdWorker() {
            this.datacenterId = 1L;
            this.machineId = new Random().nextInt(10);
        }

        /**
         * 下一个ID
         */
        public synchronized long nextId() {
            long currStmp = System.currentTimeMillis();
            if (currStmp < lastStmp) {
                throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
            }

            if (currStmp == lastStmp) {
                //相同毫秒内，序列号自增
                sequence = (sequence + 1) & MAX_SEQUENCE;
                //同一毫秒的序列数已经达到最大
                if (sequence == 0L) {
                    currStmp = getNextMill();
                }
            } else {
                //不同毫秒内，序列号置为0
                sequence = 0L;
            }

            lastStmp = currStmp;

            return (currStmp - START_STMP) << TIMESTMP_LEFT //时间戳部分
                    | datacenterId << DATACENTER_LEFT      //数据中心部分
                    | machineId << MACHINE_LEFT            //机器标识部分
                    | sequence;                            //序列号部分
        }

        private long getNextMill() {
            long mill = getCurrentMill();
            while (mill <= lastStmp) {
                mill = getCurrentMill();
            }
            return mill;
        }
        /** 获取当前毫秒数 */
        private long getCurrentMill(){
            return System.currentTimeMillis();
        }
    }
}
