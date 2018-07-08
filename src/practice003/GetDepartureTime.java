package practice003;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class GetDepartureTime {

    public static void main(String[] args) {
        /** 各地点間の数 */
        int pointNumber = GetDepartureConst.POINT_NUMBER;
        /** 出社時間 */
        String clockinTime = GetDepartureConst.CLOCK_IN_TIME;
        /** 各地点間の所要時間 */
        int[] requiredTimesArray = new int[pointNumber];
        /** 電車の出発時刻リスト */
        List<String> trainTimeList = new ArrayList<>();
        /** 電車の本数 */
        int trainNumber = 0;
        /** "HH:mm"フォーマット */
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        /** A駅を出発する基準時間 */
        Date indicateTime = null;

        Scanner sc = new Scanner(System.in);

        // 各地点間の所要時間を取得
        String tmpRequiredTimes = sc.nextLine();
        String[] tmpRequiredTimesArray = tmpRequiredTimes.split(" ");

        if (tmpRequiredTimesArray.length != pointNumber) {
            //
            sc.close();
            System.out.println(MessageUtil.getMessage(GetDepartureConst.MSG_INPUT_INVALIDATE,
                    tmpRequiredTimesArray.length, pointNumber));
            System.exit(1);
        }

        for (int i = 0; i < pointNumber; i++) {
            try {
                // intに変換
                requiredTimesArray[i] = Integer.valueOf(tmpRequiredTimesArray[i]);
            } catch (NumberFormatException e) {
                // 変換失敗
                sc.close();
                System.out.println(MessageUtil.getMessage(GetDepartureConst.MSG_NOT_NUMBER, tmpRequiredTimesArray[i]));
                System.exit(1);
            }
        }

        // 電車の本数を取得
        String tmpTrainNumber = sc.next();
        try {
            // intに変換
            trainNumber = Integer.valueOf(tmpTrainNumber);
        } catch (NumberFormatException e) {
            // 変換失敗
            sc.close();
            System.out.println(MessageUtil.getMessage(GetDepartureConst.MSG_NOT_NUMBER, tmpTrainNumber));
            System.exit(1);
        }

        // 電車の出発時刻を取得
        for (int i = 0; i < trainNumber; i++) {
            String hoursNumber = null;
            String minutesNumber = null;
            hoursNumber = sc.next();
            minutesNumber = sc.next();
            // 1桁で入力された場合
            if (hoursNumber.length() < 2) {
                hoursNumber = "0" + hoursNumber;
            }
            if (minutesNumber.length() < 2) {
                minutesNumber = "0" + minutesNumber;
            }
            // "HH:mm"形式で保存
            trainTimeList.add(hoursNumber + ":" + minutesNumber);
        }
        // スキャナクローズ
        sc.close();

        // 出社時間をDate形式に変換
        Date clockinTimeDate = null;
        try {
            clockinTimeDate = format.parse(clockinTime);
        } catch (ParseException e) {
            // 変換失敗
            System.out.println(MessageUtil.getMessage(GetDepartureConst.MSG_NOT_DATE, clockinTime));
            System.exit(1);
        }

        // 時差計算用にCalenadarインスタンス取得
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(clockinTimeDate);

        // A駅を出発する基準時間を求める
        for (int i = 0; i < pointNumber; i++) {
            if (i > 0) {
                // 所要時間を減算
                calendar.add(Calendar.MINUTE, -requiredTimesArray[i]);
            }
        }
        // 基準時間を設定
        indicateTime = calendar.getTime();
        // 使いまわすのでクリア
        calendar.clear();

        // 最も遅い出発時間を求める
        Date latestTrainTime = null;
        for (String trainTime : trainTimeList) {
            Date trainTimeDate = null;
            try {
                trainTimeDate = format.parse(trainTime);
            } catch (ParseException e) {
                // 変換失敗
                System.out.println(MessageUtil.getMessage(GetDepartureConst.MSG_NOT_DATE, trainTime));
                System.exit(1);
            }
            if (trainTimeDate.compareTo(indicateTime) > 0) {
                // 電車の出発時刻＞基準時間になったら終了
                break;
            }
            // 最も遅い出発時間を更新
            latestTrainTime = trainTimeDate;
        }

        calendar.setTime(latestTrainTime);
        // 家から駅までの所要時間を減算
        calendar.add(Calendar.MINUTE, -requiredTimesArray[0]);
        // "HH:mm"で出力
        System.out.println(format.format(calendar.getTime()));

    }

}