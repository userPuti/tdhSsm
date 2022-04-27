package com.tdh.log4j;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Puti
 * @date 2022/4/26 14:53
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestLog4j {

    @Test
    public void test1() {
        Logger logger = Logger.getLogger(TestLog4j.class);

        logger.info("info信息");
    }


    @Test
    public void test2() {
        Logger logger = Logger.getLogger(TestLog4j.class);
        System.out.println(System.getProperty("ssmPrac.root"));
//        for (int i = 0; i < 10000; i++) {
//            logger.error("error");
//            logger.warn("warn");
//            logger.debug("debug");
//            logger.info("info信息");
//        }
    }

}
