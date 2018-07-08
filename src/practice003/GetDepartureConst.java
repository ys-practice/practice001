package practice003;

public class GetDepartureConst {
    // 定数定義
    /** 各地点間の数 */
    public static final int POINT_NUMBER = 3;
    /** 出社時間 */
    public static final String CLOCK_IN_TIME = "8:59";

    /** 出社時間(時) */
    public static final int CLOCK_IN_TIME_HOUR = 8;
    /** 出社時間(分) */
    public static final int CLOCK_IN_TIME_MINUTE = 59;

    // エラーメッセージ
    /** 数値変換エラー */
    public static final String MSG_NOT_NUMBER = "数値以外が入力されました。入力値：{0}";
    /** 日付フォーマットエラー */
    public static final String MSG_NOT_DATE = "期待する日付フォーマットではありません。入力値：{0}";
    /** 入力エラー */
    public static final String MSG_INPUT_INVALIDATE = "入力数が期待値と異なります。入力数：{0}、期待値：{1}";

}
