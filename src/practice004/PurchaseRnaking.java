package practice004;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class PurchaseRnaking {

    public static void main(String args[]) {

        /** 社員数 */
        int employeeNumber = 0;
        /** 購入履歴の数 */
        int purchaseHistoryNumber = 0;
        /** 社員名と購入金額のマップ */
        Map<String, Long> employeePurchaseMap = new HashMap<String, Long>();

        Scanner sc = new Scanner(System.in);
        // 社員数の取得
        String tmpEmployeeNumber = null;
        try {
            tmpEmployeeNumber = sc.next();
            employeeNumber = Integer.valueOf(tmpEmployeeNumber);
        } catch (NumberFormatException e) {
            // 数値でない
            sc.close();
            System.out.println("数値ではありません。" + tmpEmployeeNumber);
            System.exit(1);
        }

        // 社員名の取得
        String[] tmpEmployeeNamesArray = new String[employeeNumber];

        for (int i = 0; i < employeeNumber; i++) {
            tmpEmployeeNamesArray[i] = sc.next();
            // 社員名をマップに格納
            employeePurchaseMap.put(tmpEmployeeNamesArray[i], 0L);
        }

        // 購入履歴の取得
        String tmpPurchaseHistoryNumber = sc.next();
        try {
            purchaseHistoryNumber = Integer.valueOf(tmpPurchaseHistoryNumber);
        } catch (NumberFormatException e) {
            // 数値でない
            sc.close();
            System.out.println("数値ではありません。" + tmpPurchaseHistoryNumber);
            System.exit(1);
        }
        for (int i = 0; i < purchaseHistoryNumber; i++) {
            String tmpEmployeeName = null;
            String tmpPurchasePrice = null;
            Long purchasePrice = 0L;
            try {
                tmpEmployeeName = sc.next();
                tmpPurchasePrice = sc.next();
            } catch (NoSuchElementException e) {
                // 読み込み失敗
                sc.close();
                System.out.println("入力が読み込めませんでした。");
                System.exit(1);
            }
            if (!employeePurchaseMap.containsKey(tmpEmployeeName)) {
                // 社員名が不正
                sc.close();
                System.out.println("期待する社員名ではありません。" + tmpEmployeeName);
                System.exit(1);
            }
            try {
                purchasePrice = Long.valueOf(tmpPurchasePrice);
            } catch (NumberFormatException e) {
                // 数値でない
                sc.close();
                System.out.println("数値ではありません。" + tmpPurchasePrice);
                System.exit(1);
            }
            // マップエントリ－の更新
            employeePurchaseMap.put(tmpEmployeeName, employeePurchaseMap.get(tmpEmployeeName) + purchasePrice);
        }
        // スキャナクローズ
        sc.close();
        // 金額順にソートして出力
        employeePurchaseMap.entrySet().stream().sorted(Collections.reverseOrder(Entry.comparingByValue()))
                .forEach(set -> System.out.println(set.getKey()));
    }

}
