import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * todo
 *
 * @author leiyang
 * @since 2019/6/10 17:58
 */
public class DateUtil {

    @Test
    public void testDate() {
        String dateStr = "2019-06-10 17:43:31";
        Date date = addMinutes(parseDateNewFormat(dateStr), 5);
        Date now = now();

        boolean flag = date.after(now);

        System.out.println(flag);
    }

    public static Date now() {
        return new Date();
    }

    public static Date addMinutes(Date date, long minutes) {
        return addSeconds(date, minutes * 60L);
    }

    public static Date addSeconds(Date date1, long secs) {
        return new Date(date1.getTime() + secs * 1000L);
    }

    public static Date parseDateNewFormat(String sDate) {
        Date d = null;
        if (sDate != null) {
            SimpleDateFormat dateFormat;
            if (sDate.length() == "yyyy-MM-dd HH:mm:ss".length()) {
                try {
                    dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    d = dateFormat.parse(sDate);
                } catch (ParseException var4) {
                    return null;
                }
            } else {
                try {
                    dateFormat = new SimpleDateFormat("yyyy-M-d H:m:s");
                    d = dateFormat.parse(sDate);
                } catch (ParseException var3) {
                    return null;
                }
            }
        }

        return d;
    }

}
