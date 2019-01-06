package practice008.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/** 商品情報*/
@XmlRootElement(name="item")
public class ItemInformation {

    /** 商品ID*/
    @XmlElement(name="itemid")
    private String id;

    /** 商品名*/
    @XmlElement(name="itemname")
    private String name;

    /** 金額*/
    @XmlElement(name="price")
    private long price;

    /******************************************************
     * 商品IDを取得する
     * @return 商品ID
     ******************************************************/
    public String getId() {
        return id;
    }

    /******************************************************
     * 商品名を取得する
     * @return 商品名
     ******************************************************/
    public String getName() {
        return name;
    }

    /******************************************************
     * 金額を取得する
     * @return 金額
     ******************************************************/
    public long getPrice() {
        return price;
    }

}
