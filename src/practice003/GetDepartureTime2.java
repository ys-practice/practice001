package practice003;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author y-suwa
 *
 */
public class GetDepartureTime2 {

    public static void main(String[] args) {
         /** 出社時刻 */
        LocalTime indicateTime = LocalTime.of(GetDepartureConst.CLOCK_IN_TIME_HOUR, GetDepartureConst.CLOCK_IN_TIME_MINUTE);
        /** 各地点間の数 */
        int pointNumber = GetDepartureConst.POINT_NUMBER;
        /** 各地点間の所要時間 */
        int[] requiredTimesArray = new int[pointNumber];
        /** 電車の出発時刻リスト */
        List<LocalTime> trainTimeList = new ArrayList<>();
        /** 電車の本数 */
        int trainNumber = 0;

        Scanner sc = new Scanner(System.in);

        // 各地点間の所要時間を取得
        String tmpRequiredTimes = sc.nextLine();
        String[] tmpRequiredTimesArray = tmpRequiredTimes.split(" ");

        if (tmpRequiredTimesArray.length != pointNumber) {
            // 入力数エラー
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
        String tmpTrainNumber = sc.nextLine();
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
            String hoursAndMinutes = sc.nextLine();
            String[] hoursAndMinutesArray = hoursAndMinutes.split(" ");
            if (hoursAndMinutesArray.length != 2) {
                // 入力数エラー
                sc.close();
                System.out.println(
                        MessageUtil.getMessage(GetDepartureConst.MSG_INPUT_INVALIDATE, hoursAndMinutesArray.length, 2));
                System.exit(1);
            }
            Integer hoursNumber = null;
            Integer minutesNumber = null;
            try {
                hoursNumber = Integer.valueOf(hoursAndMinutesArray[0]);
                minutesNumber = Integer.valueOf(hoursAndMinutesArray[1]);
            } catch (NumberFormatException e) {
                // 変換失敗
                sc.close();
                System.out.println(MessageUtil.getMessage(GetDepartureConst.MSG_NOT_NUMBER, hoursAndMinutes));
                System.exit(1);
            }
            // "HH:mm"形式で保存
            trainTimeList.add(LocalTime.of(hoursNumber, minutesNumber));
        }
        // スキャナクローズ
        sc.close();

        // A駅を出発する基準時間を求める
        for (int i = 0; i < pointNumber; i++) {
            if (i > 0) {
                // 所要時間を減算
                indicateTime = indicateTime.minusMinutes(requiredTimesArray[i]);
            }
        }

        // 最も遅い出発時間を求める
        LocalTime latestTrainTime = null;
        for (LocalTime trainTime : trainTimeList) {
            if (trainTime.isAfter(indicateTime)) {
                // 電車の出発時刻＞基準時間になったら終了
                break;
            }
            // 最も遅い出発時間を更新
            latestTrainTime = trainTime;
        }
        LocalTime departueTime = latestTrainTime.minusMinutes(requiredTimesArray[0]);
        // "HH:mm"先頭0埋めで出力
        System.out.printf("%02d:%02d\n", departueTime.getHour(),departueTime.getMinute());
    }

}