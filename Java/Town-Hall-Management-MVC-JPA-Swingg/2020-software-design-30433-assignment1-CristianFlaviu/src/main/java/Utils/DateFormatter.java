package Utils;





import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

    public static int getYear(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String date1 = sdf.format(new Date());

        return Integer.valueOf(date1);
    }
    public static int getMonth(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        String date1 = sdf.format(new Date());

        return Integer.valueOf(date1);
    }




}
