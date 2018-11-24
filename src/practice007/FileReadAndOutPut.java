package practice007;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.TreeMap;

public class FileReadAndOutPut {
    public static void main(String args[]) {

        /** 合計金額 */
        long amounts = 0;
        /** 購入日時 */
        LocalDateTime purchaseDateTime = null;

        // XMLをオブジェクトに変換
        ReceiptInfomation recieptInfo = ReadFile.readXmlWithPath("※ファイルパス※", ReceiptInfomation.class);

        try {
            purchaseDateTime = LocalDateTime.parse(recieptInfo.getTime(),
                    DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        } catch (DateTimeParseException e) {
            // 想定する日付フォーマットではない
            e.printStackTrace();
            System.exit(1);
        }

        // 出力処理用のMap ※自動ソートを利用する
        Map<String, OutputInformation> outputMap = new TreeMap<>();

        for (ItemInformation itemInfo : recieptInfo.getItems()) {
            String id = itemInfo.getId();
            if (!outputMap.containsKey(id)) {
                outputMap.put(id, new OutputInformation(itemInfo.getName()));
            } else {
                outputMap.get(id).increaseItemCounts();
            }
            amounts += itemInfo.getPrice();
        }

        try {
            System.out.println("購入時間：" + purchaseDateTime.format(DateTimeFormatter.ofPattern("yyyy年M月d日 HH時mm分ss秒")));
        } catch (DateTimeException e) {
            // 日付の出力でエラー
            e.printStackTrace();
            System.exit(1);
        }

        for (String key : outputMap.keySet()) {
            System.out
                    .println("商品名：" + outputMap.get(key).getItemName() + "　購入個数：" + outputMap.get(key).getItemCounts());
        }

        System.out.println("合計金額：" + amounts + "円");
    }
}
