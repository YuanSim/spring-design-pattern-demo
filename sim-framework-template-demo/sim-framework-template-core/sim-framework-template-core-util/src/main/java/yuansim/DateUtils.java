package yuansim;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/5/9
 */
public class DateUtils {

    public static void main(String[] args) {


        LocalDateTime today_start = LocalDateTime.of(LocalDate.now(),LocalTime.MAX);

        System.out.println(today_start);
    }
}
