package practice005;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author y-suwa
 *
 */
public class HateNumbers {

    /** 嫌いな数字の許容最小値 */
    public static final int MIN_HATE_NUMBER = 0;
    /** 嫌いな数字の許容最大値 */
    public static final int MAX_HATE_NUMBER = 9;
    /** 部屋の数の許容最小値 */
    public static final int MIN_ROOM_COUNT = 1;
    /** 部屋の数の許容最大値 */
    public static final int MAX_ROOM_COUNT = 100;
    /** 部屋番号の許容最小値 */
    public static final int MIN_ROOM_NUMBER = 1;
    /** 部屋番号の許容最大値 */
    public static final int MAX_ROOM_NUMBER = 1000;
    /** 出力可能な部屋番号がない場合の出力 */
    public static final String NO_ROOMS_IN_LIST = "none";

    public static void main(String args[]) {

        /** 入力される部屋番号のリスト */
        List<String> inputRoomNumberList = new ArrayList<>();
        /** 嫌いな数字 */
        int hateNumber = 0;
        /** 部屋の数 */
        int roomCount = 0;

        Scanner sc = new Scanner(System.in);

        // 嫌いな番号を取得する
        try {
            hateNumber = sc.nextInt();
        } catch (InputMismatchException e) {
            // 数値でない
            sc.close();
            System.out.println(MessageUtil.getMessage(HateNumberMessage.MSG_NOT_NUMBER_HATE_NUM));
            System.exit(1);
        }
        // 入力値のチェック
        if (hateNumber < MIN_HATE_NUMBER || hateNumber > MAX_HATE_NUMBER) {
            sc.close();
            System.out.println(MessageUtil.getMessage(HateNumberMessage.MSG_OUT_OF_RANGE_HATE_NUM, MIN_HATE_NUMBER,
                    MAX_HATE_NUMBER));
            System.exit(1);
        }

        // 部屋の数を取得する
        try {
            roomCount = sc.nextInt();
        } catch (InputMismatchException e) {
            // 数値でない
            sc.close();
            System.out.println(MessageUtil.getMessage(HateNumberMessage.MSG_NOT_NUMBER_ROOM_CNT));
            System.exit(1);
        }
        // 入力値のチェック
        if (roomCount < MIN_ROOM_COUNT || roomCount > MAX_ROOM_COUNT) {
            sc.close();
            System.out.println(MessageUtil.getMessage(HateNumberMessage.MSG_OUT_OF_RANGE_ROOM_CNT, MIN_ROOM_COUNT,
                    MAX_ROOM_COUNT));
            System.exit(1);
        }

        // 部屋番号を取得する
        for (int i = 0; i < roomCount; i++) {
            try {
                // 部屋番号
                int roomNumber = sc.nextInt();
                // 入力値のチェック
                if (roomNumber < MIN_ROOM_NUMBER || roomNumber > MAX_ROOM_NUMBER) {
                    sc.close();
                    System.out.println(MessageUtil.getMessage(HateNumberMessage.MSG_OUT_OF_RANGE_ROOM_NUM,
                            MIN_ROOM_NUMBER, MAX_ROOM_NUMBER));
                    System.exit(1);
                }
                // リストに追加
                inputRoomNumberList.add(String.valueOf(roomNumber));
            } catch (InputMismatchException e) {
                // 数値でない
                sc.close();
                System.out.println(MessageUtil.getMessage(HateNumberMessage.MSG_NOT_NUMBER_ROOM_NUM));
                System.exit(1);
            }
        }
        // スキャナクローズ
        sc.close();
        // 嫌いな数字⇒String
        String hateNumberString = String.valueOf(hateNumber);
        // 嫌いな数字を含まない要素でリストを生成
        List<String> outputRoomNumberList = inputRoomNumberList.stream().filter(r -> !r.contains(hateNumberString))
                .collect(Collectors.toList());
        // 出力処理開始
        if (!outputRoomNumberList.isEmpty()) {
            // 全ての要素に対してprintln
            outputRoomNumberList.stream().forEach(System.out::println);
        } else {
            // 要素が空の場合は"none"を出力
            System.out.println(NO_ROOMS_IN_LIST);
        }

    }

}
