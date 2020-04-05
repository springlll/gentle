package gentle.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author silence
 * @date 2018/8/1 16:31
 */
public class DateUtil {

    public static SimpleDateFormat dateFormat() {
        return new SimpleDateFormat("HH:mm:ss");
    }

    /**
     * Date 类型格式化为 String
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

}
