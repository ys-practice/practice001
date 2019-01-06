package practice008.bean;

/** 出力用クラス*/
public class OutputInformation {

    /** 商品名*/
    private String itemName;
    /** 商品の個数*/
    private int itemCounts = 0;

    /******************************************************
     * コンストラクタ　商品名を設定し、商品の個数を1個加算する。
     * @param itemId 商品ID
     * @param itemName 商品名
     ******************************************************/
    public OutputInformation(String itemName){
        this.itemName = itemName;
        itemCounts++;
    }

    /******************************************************
     * 商品名を取得する
     * @return 商品名
     ******************************************************/
    public String getItemName() {
        return itemName;
    }

    /******************************************************
     * 商品名を設定する
     * @param itemName 商品名
     ******************************************************/
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /******************************************************
     * 商品の個数を取得する
     * @return 商品の個数
     ******************************************************/
    public int getItemCounts() {
        return itemCounts;
    }

    /******************************************************
     * 商品の個数を加算する
     ******************************************************/
    public void increaseItemCounts(){
        this.itemCounts++;
    }

}
