package practice006;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author y-suwa
 *
 */
class ConvertUtil {

    /** 文字列と数字の変換マップ*/
    private static final Map<String, String> map;

    static{
        map = new HashMap<>();
        map.put("A", "0");
        map.put("B", "1");
        map.put("C", "2");
        map.put("D", "3");
        map.put("E", "4");
    }

    /**
     * 数字⇒文字列に変換する
     * @param arg 変換対象の数字(String)
     * @return 変換結果の文字列
     * */
    public static String numbersToWords(String arg){
        StringBuffer sb = new StringBuffer();
        String tmp = null;
        for(char c : arg.toCharArray()){
            tmp = String.valueOf(c);
            for (Entry<String, String> entry : map.entrySet()) {
                if (entry.getValue().equals(tmp)) {
                    sb.append(entry.getKey());
                    break;
                }
            }
        }
        return sb.toString();
    }

    /**
     * 文字列⇒数字に変換する
     * @param arg 変換対象の文字列
     * @return 変換結果の数字(String)
     * */
    public static String wordsToNumbers(String arg){
        StringBuffer sb = new StringBuffer();
        String tmp = null;
        for(char c : arg.toCharArray()){
            tmp = String.valueOf(c);
            sb.append(map.get(tmp));
        }
        return sb.toString();
    }

    /**
     * 5進数を10進数に変換する
     * @param arg 変換対象の5進数(String)
     * @return 10進数(Integer)
     * */
    public static Integer convertQuinaryToDecimal(String arg){
        return convertDecimal(arg, 5);
    }

    /**
     * N進数を10進数に変換する
     * @param arg 変換対象のN進数(String)
     * @param base 変換対象の底Nの値
     * @return 10進数(Integer)
     * */
    public static Integer convertDecimal(String arg, int base){
        char [] charArrayOfArg = arg.toCharArray();
        int result = 0;
        // 計算のため逆順に並べ替え
        reversArray(charArrayOfArg);
        for(int i = 0; i < charArrayOfArg.length; i++){
            result += Character.getNumericValue(charArrayOfArg[i]) * getWeight(i, base);
        }
        return result;
    }

    /**
     * 10進数から5進数に変換する
     * @param dec 変換対象の10進数
     * @return 変換後のN進数(String)
     * */
    public static String convertQuinaryString(int dec){
        return convertNumeralString(dec, 5);
    }


    /**
     * char配列を逆順に並べ替える
     * @param array char配列
     * */
    private static void reversArray(char[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            char tmp = array[i];
            array[i] = array[(array.length - 1) - i];
            array[(array.length - 1) - i] = tmp;
        }
    }

    /**
     * N進法の桁数に対応する重みを算出する
     * @param digit 桁数(最初の桁数を0桁とする)
     * @param base 底Nの値
     * @return 底Nにおける桁数に応じた重み
     * */
    private static int getWeight(int digit, int base) {
        int result = 1;
        if (digit == 0) {
            return result;
        }
        for (int i = 0; i < digit; i++) {
            result *= base;
        }
        return result;
    }

    /**
     * 10進数からN進数に変換する(※11進数以上にはつかえない)
     * @param dec 変換対象の10進数
     * @param base 底Nの値
     * @return 変換後のN進数(String)
     * */
    private static String convertNumeralString(int dec, int base) {
        StringBuffer sb = new StringBuffer();
        int tmp = dec;
        while (true) {
            // 剰余を格納
            sb.append(tmp % base);
            tmp /= base;
            if (tmp <= 0) {
                break;
            }
        }
        return sb.reverse().toString();
    }


}