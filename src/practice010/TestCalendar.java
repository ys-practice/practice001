package practice010;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TestCalendar {
    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        // インスタンスを取得⇒1900/01/01を設定
        Calendar cal1 = Calendar.getInstance();
        cal1.set(1900, Calendar.JANUARY, 1, 0, 0, 0);

        // インスタンスを取得⇒2000/12/31
        Calendar cal2 = Calendar.getInstance();
        cal2.set(2000, Calendar.DECEMBER, 31, 0, 0, 0);

        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 100;i++){
                // Thread1 : 1900/01/01　を100回出力
                System.out.println("Thread1 : " + dateFormat.format(cal1.getTime()));
            }
        });

        Thread t2 = new Thread(() ->{
            for(int i = 0; i < 100;i++){
                // Thread2 : 2000/12/31　を100回出力
                System.out.println("Thread2 : " +dateFormat.format(cal2.getTime()));
            }
        });

        // マルチスレッドで実行
        t1.start();//期待する出力　Thread1 : 1900/01/01
        t2.start();//期待する出力　Thread2 : 2000/12/31

    }
}
