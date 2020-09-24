package practice009;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelMainSample {
    public static void main(String args[]) {

        long startTime = System.currentTimeMillis();

        ExecutorService es = Executors.newFixedThreadPool(3);

        // 同時実行する処理のリスト化
        List<CompletableFuture<String>> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            String proc = ("処理対象" + (i + 1));
            list.add(CompletableFuture.supplyAsync(() -> new ParallelProcessSub1(proc).process(),es));
            list.add(CompletableFuture.supplyAsync(() -> new ParallelProcessSub2(proc).process(),es));
            list.add(CompletableFuture.supplyAsync(() -> new ParallelProcessSub3(proc).process(),es));
            System.out.println(proc + "実行済み");
        }

        es.shutdown();

        for(CompletableFuture<String> result: list){
            try {
                System.out.println(result.get());
            } catch (InterruptedException | ExecutionException e) {
                // TODO 自動生成された catch ブロック
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("実行時間" + (endTime - startTime));
    }
}