package practice009;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelProcessMain {
    public static void main (String args[]){
        // 処理対象のリスト
        List<String> list = new ArrayList<>();
        // とりあえず5個あるものとする
        for(int i = 0; i < 5; i++){
            list.add("処理対象" + (i +1));
        }
        // 処理開始の時間(ミリ秒)取得
        long startTime = System.currentTimeMillis();

        // 3つの並列スレッドを用意する
        ExecutorService es = Executors.newFixedThreadPool(3);

        // 処理結果受け取り用のリストを作成
        List<Future<String>> resultList = new ArrayList<>();

        // 処理対象を順番にサブ処理１～３に渡して処理実行
        for(String proc : list){
            resultList.add(es.submit(new ParallelProcessSub1(proc)));
            resultList.add(es.submit(new ParallelProcessSub2(proc)));
            resultList.add(es.submit(new ParallelProcessSub3(proc)));
            System.out.println(proc + "実行済み");
        }
        // 並列スレッドをシャットダウン
        es.shutdown();

        // 実行結果を取得する
        for(Future<String> result : resultList){
            try{
                System.out.println(result.get());
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        // 処理終了の時間(ミリ秒)取得
        long endTime = System.currentTimeMillis();

        // 実行時間を算出(ミリ秒)
        System.out.println("実行時間： " + (endTime -startTime));
    }

}
