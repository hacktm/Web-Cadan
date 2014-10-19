package com.acc.home.server;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.logging.Logger;

public class TestStringSplit {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(TestStringSplit.class);

    @DataProvider
    public Object[][] dp() {
        return new Object[][] { {"1#2#3"},{"1"},{"1#2"},{"1#2#3&4&5"} };
    }

    @Test(dataProvider = "dp")
    public void testStringSplit(String value) {
        int findPosition = 0;
        int start = 0;
        String msg;
        do {
            findPosition = value.indexOf("#", start);
            if (findPosition != -1) {
                msg = value.substring(start, findPosition);
                start = findPosition+1;
            } else {
                msg = value.substring(start, value.length());
            }
            LOGGER.info(msg);

        } while(findPosition>0);
        LOGGER.info("done "+value);
    }
}
