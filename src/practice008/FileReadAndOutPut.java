package practice008;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import practice008.bean.ItemInformation;
import practice008.bean.OutputInformation;
import practice008.bean.ReceiptInfomation;
import practice008.util.FileUtils;

public class FileReadAndOutPut {
    public static void main(String args[]) {
        /** 合計金額 */
        long amounts = 0;
        // 出力処理用のMap ※自動ソートを利用する
        Map<String, OutputInformation> outputMap = new TreeMap<>();
        // XMLをオブジェクトに変換
        List <ReceiptInfomation> recieptInfos = FileUtils.readDirectory("※ディレクトリを表すパス", ReceiptInfomation.class);

        if(recieptInfos == null){
            System.out.println("購入情報が取得できませんでした。");
            System.exit(1);
        }

        for (ReceiptInfomation recieptInfo : recieptInfos) {
            for (ItemInformation itemInfo : recieptInfo.getItems()) {
                String id = itemInfo.getId();
                if (!outputMap.containsKey(id)) {
                    outputMap.put(id, new OutputInformation(itemInfo.getName()));
                } else {
                    outputMap.get(id).increaseItemCounts();
                }
                amounts += itemInfo.getPrice();
            }
        }

        for (String key : outputMap.keySet()) {
            System.out.println(
                    "商品名：" + outputMap.get(key).getItemName() + "　購入個数：" + outputMap.get(key).getItemCounts());
        }

        System.out.println("合計金額：" + amounts + "円");
    }
}
