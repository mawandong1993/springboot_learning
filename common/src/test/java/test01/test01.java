package test01;

import com.google.gson.Gson;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;

/**
 * @author mawandong
 * @date 2018/7/10 0:00
 */
public class test01 {

    Gson gson =new Gson();

    private static Logger logger = LoggerFactory.getLogger(test01.class);

    @Test
    public void test01(){

        Date date1=new Date();
        Calendar c =Calendar.getInstance();

        logger.info(date1.toString());
        logger.info(c.toString());

    }
}
