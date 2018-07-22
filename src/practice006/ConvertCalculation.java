package practice006;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author y-suwa
 *
 */
public class ConvertCalculation {
    public static void main(String[] args) {
        // 一つ目の文字列
        String firstWords = null;
        // 二つ目の文字列
        String secondWors = null;

        Scanner sc = new Scanner(System.in);
        // 入力を読み込む
        try {
            firstWords = sc.next();
            secondWors = sc.next();
        } catch (NoSuchElementException e) {
            sc.close();
            e.printStackTrace();
        }

        // それぞれの文字列を5進数をあらわす文字列に変換する
        String firstQuinary = ConvertUtil.wordsToNumbers(firstWords);
        String secondQuinary = ConvertUtil.wordsToNumbers(secondWors);

        // 10進数として計算する
        int result = ConvertUtil.convertQuinaryToDecimal(firstQuinary) + ConvertUtil.convertQuinaryToDecimal(secondQuinary);

        // 10進数⇒5進数⇒文字列で変換して出力
        System.out.println(ConvertUtil.numbersToWords(ConvertUtil.convertQuinaryString(result)));
    }

}