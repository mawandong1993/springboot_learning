package utils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;


/**
 * @author mawandong
 * @date 2018/3/28 1:28
 */
public class DateUtils {

    private final static Logger Log = LoggerFactory.getLogger(DateUtils.class);

    private final static String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";

    private static String format(){
        return DateFormatUtils.format(new Date(),DateUtils.DATEFORMAT);
    }
    private static String format(Date date,String pattern){
        return DateFormatUtils.format(date,pattern);
    }

}
