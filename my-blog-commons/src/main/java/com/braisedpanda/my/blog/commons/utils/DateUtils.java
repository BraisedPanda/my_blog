package com.braisedpanda.my.blog.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: my-practices
 * @description: 时间工具类
 * @author: chenzhen
 * @create: 2019-12-16 13:42
 **/
public class DateUtils {

    private static final String STANDARD_TIME = "yyyy-MM-dd HH:mm:ss";
    private static final String STANDARD_TIME_DAY = "yyyy-MM-dd";
    private static final String STANDARD_TIME_DAY_HM = "yyyy年MM月dd日 HH:mm";
    /** 
    * @Description: 获取时间 格式【2019-12-30 10:14:55】
    * @Param: 
    * @Date: 2019/12/16 0016 
    */ 
    public static String currentStandardDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtils.STANDARD_TIME);
        return simpleDateFormat.format(new Date());
    }

    /**
     * @Description: 获取时间 格式【2019-12-30】
     * @Param:
     * @Date: 2019/12/16 0016
     */
    public static String currentStandardDate2(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtils.STANDARD_TIME_DAY);
        return simpleDateFormat.format(new Date());
    }
    /**
     * @Description: 获取时间 格式【2019-12-30 12:00】
     * @Param:
     * @Date: 2019/12/16 0016
     */
    public static String currentDate_YMDHM(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtils.STANDARD_TIME_DAY_HM);
        return simpleDateFormat.format(new Date());
    }

}
