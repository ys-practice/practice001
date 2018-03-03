package practice001;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 * @author y-suwa
 *
 */
public class WorldTimeDiff {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // 総都市数
        int cityNumber = 0;
        // 都市と時差のマップ
        Map<String, Integer> cityDiffMap = new LinkedHashMap<String, Integer>();

        try {
            cityNumber = scanner.nextInt();
        } catch (InputMismatchException e) {
            // 数値でない
            System.out.println("入力エラー：1行目の値が数値以外の値で渡されました");
            System.exit(0);
        }

        // 総都数の数だけ繰り返す
        for (int roop = 0; roop < cityNumber; roop++) {
            String cityNameOther = scanner.next();
            int diff = 0;
            try {
                diff = scanner.nextInt();
            } catch (InputMismatchException e) {
                // 数値でない
                System.out.println("入力エラー：" + (roop + 2) + "行目の値が数値以外の値で渡されました");
                System.exit(0);
            }
            cityDiffMap.put(cityNameOther, diff);
        }

        // 投稿した都市と時刻
        String cityName = scanner.next();
        String sendTimeStr = scanner.next();

        // 読み取り終了
        scanner.close();

        // 投稿した都市の基準時との時差
        int tmpStndardTime = cityDiffMap.get(cityName);

        // "HH:mm"の入出力用フォーマット
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        // 基準となる時刻の文字列をDate型に変換する
        Date time = null;
        try {
            time = format.parse(sendTimeStr);
        } catch (ParseException e) {
            // 入力が"HH:mm"以外の形式
            System.out.println("入力エラー：" + (cityNumber + 2) + "行目に期待する値は\"都市名 HH:mm\"です");
            System.exit(0);
        }

        // 時差計算用にCalenadarインスタンス取得
        Calendar calendar = Calendar.getInstance();

        // 出力1行空ける
        System.out.println();

        for (Entry<String, Integer> entry : cityDiffMap.entrySet()) {
            // リセットして使いまわす
            calendar.clear();
            // 基準時刻を設定
            calendar.setTime(time);
            if (!cityName.equals(entry.getKey())) {
                // 時差を計算する
                calendar.add(Calendar.HOUR_OF_DAY, entry.getValue() - tmpStndardTime);
            }
            // "都市名 HH:mm"を出力する
            System.out.println(entry.getKey() + " " + format.format(calendar.getTime()));

        }

    }

}
