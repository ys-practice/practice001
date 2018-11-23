package practice007;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class FileReadAndOutPut {
    public static void main(String args[]) {

        // XMLをオブジェクトに変換
        ReceiptInfomation recieptInfo = ReadFile.readXmlWithPath("※ファイルパス※", ReceiptInfomation.class);

        /** 合計金額 */
        long amounts = 0;

        LocalDateTime date = LocalDateTime.parse(recieptInfo.getTime(), DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        System.out.println("購入時間：" + date.format(DateTimeFormatter.ofPattern("yyyy年M月d日 HH時mm分ss秒")));

        // 購入情報から商品情報を取り出して商品IDの昇順に並べ替え
        List<ItemInformation> itemInfos = recieptInfo.getItems().stream()
                .sorted(Comparator.comparing(ItemInformation::getId)).collect(Collectors.toList());

        // 出力処理用のMap
        Map<String, Integer> map = new HashMap<>();

        for (ItemInformation itemInfo : itemInfos) {
            if (!map.containsKey(itemInfo.getName())) {
                map.put(itemInfo.getName(), 1);
            } else {
                map.put(itemInfo.getName(), map.get(itemInfo.getName()) + 1);
            }
            amounts += itemInfo.getPrice();
        }

        for (Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("商品名：" + entry.getKey() + "　購入個数：" + entry.getValue());
        }

        System.out.println("合計金額：" + amounts + "円");
    }
}
