package practice004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author y-suwa
 *
 */
public class PurchaseRnaking2 {

    /** 入力＞社員数のインデックス */
    public static final int INDEX_E_NUMBER = 0;
    /** 入力＞社員名のインデックス */
    public static final int INDEX_E_NAMES = 1;
    /** 入力＞購入履歴数のインデックス */
    public static final int INDEX_P_NUMBER = 2;
    /** 入力＞購入履歴詳細のインデックス */
    public static final int INDEX_P_DETAILS = 3;
    /** 購入履歴詳細＞社員名のインデックス */
    public static final int INDEX_PD_NAME = 0;
    /** 購入履歴詳細＞購入費のインデックス */
    public static final int INDEX_PD_AMOUNT = 1;
    /** 区切り文字 */
    public static final String DELIMITER = " ";

    public static void main(String args[]) {

        /** 社員数 */
        int employeeNumber = 0;
        /** 購入履歴の数 */
        int purchaseHistoryNumber = 0;
        /** 社員名と購入費のマップ */
        Map<String, Long> employeePurchaseMap = new HashMap<String, Long>();

        // 標準入力を取得
        List<String> inputStringList = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                String inStr = br.readLine();
                if (inStr.isEmpty()) {
                    // 空文字でループ終了
                    break;
                }
                // 入力リストに追加
                inputStringList.add(inStr);
            }
            // 入力終了
            br.close();
        } catch (IOException e) {
            // 読み込み中にエラー
            System.out.println("読み込みエラーです。");
            System.exit(1);
        }

        // 入力数が要件を満たすかチェック
        if (inputStringList.size() < 1 + INDEX_P_DETAILS) {
            // 入力行数が4行未満
            System.out.println("入力が不正です。最低4行の入力が必要です。入力ライン数：" + inputStringList.size());
            System.exit(1);
        }

        // 社員数の取得
        try {
            employeeNumber = Integer.valueOf(inputStringList.get(INDEX_E_NUMBER));
        } catch (NumberFormatException e) {
            // 数値でない
            System.out.println("数値ではありません。社員数：" + inputStringList.get(INDEX_E_NUMBER));
            System.exit(1);
        }

        // 社員名の取得
        String[] tmpEmployeeNamesArray = inputStringList.get(INDEX_E_NAMES).split(DELIMITER);

        // 社員数の整合性チェック
        if (tmpEmployeeNamesArray.length != employeeNumber) {
            System.out.println("社員名の入力数が不整合です。期待値：" + employeeNumber + "　入力：" + tmpEmployeeNamesArray.length);
            System.exit(1);
        }

        // 社員名をマップに格納
        for (String names : tmpEmployeeNamesArray) {
            // 金額は0円としておく
            employeePurchaseMap.put(names, 0L);
        }

        // 購入履歴数の取得
        String tmpPurchaseHistoryNumber = inputStringList.get(INDEX_P_NUMBER);
        try {
            purchaseHistoryNumber = Integer.valueOf(tmpPurchaseHistoryNumber);
        } catch (NumberFormatException e) {
            // 数値でない
            System.out.println("数値ではありません。購入履歴数：" + tmpPurchaseHistoryNumber);
            System.exit(1);
        }

        // 入力ライン総数の整合性チェック
        int expect = INDEX_P_DETAILS + purchaseHistoryNumber;
        if (inputStringList.size() != expect) {
            // 不一致
            System.out.println("入力ライン数が不整合です。期待値：" + expect + "　入力：" + inputStringList.size());
            System.exit(1);
        }

        // 購入履歴詳細の取得
        String[] tmpPurchaseHistoryArray = new String[purchaseHistoryNumber];

        for (int i = 0; i < purchaseHistoryNumber; i++) {
            Long purchaseAmount = 0L;

            // 購入履歴詳細
            tmpPurchaseHistoryArray = inputStringList.get(INDEX_P_DETAILS + i).split(DELIMITER);

            // 購入履歴詳細のフォーマットチェック
            if (tmpPurchaseHistoryArray.length != 2) {
                System.out.println("購入履歴の入力が不正です。購入履歴：" + inputStringList.get(INDEX_P_DETAILS + i));
                System.exit(1);
            }

            // 社員名が含まれているかチェック
            if (!employeePurchaseMap.containsKey(tmpPurchaseHistoryArray[INDEX_PD_NAME])) {
                // 社員名が不正
                System.out.println("期待する社員名ではありません。社員名：" + tmpPurchaseHistoryArray[INDEX_PD_NAME]);
                System.exit(1);
            }

            try {
                purchaseAmount = Long.valueOf(tmpPurchaseHistoryArray[INDEX_PD_AMOUNT]);
            } catch (NumberFormatException e) {
                // 数値でない
                System.out.println("数値ではありません。購入費：" + tmpPurchaseHistoryArray[INDEX_PD_AMOUNT]);
                System.exit(1);
            }

            // マップの更新
            employeePurchaseMap.put(tmpPurchaseHistoryArray[INDEX_PD_NAME],
                    employeePurchaseMap.get(tmpPurchaseHistoryArray[INDEX_PD_NAME]) + purchaseAmount);
        }

        // 金額順にソートして出力
        employeePurchaseMap.entrySet().stream().sorted(Collections.reverseOrder(Entry.comparingByValue()))
                .forEach(set -> System.out.println(set.getKey()));
    }

}
