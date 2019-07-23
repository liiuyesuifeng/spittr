package com.mvc.test.controller;

import com.mvc.utils.PrintUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CalaTest {
    @Test
    public void testCanla() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
        Date parse = dateFormat.parse("2019-02-01");
        Calendar cale_lst = Calendar.getInstance();
        cale_lst.setTime(new Date(parse.getTime()));
        cale_lst.set(Calendar.DATE, 1);//把日期设置为当月第一天
        cale_lst.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天

        PrintUtils.println(dateFormat.format(cale_lst.getTime()) + ":" + cale_lst.get(Calendar.DATE));
    }
}
