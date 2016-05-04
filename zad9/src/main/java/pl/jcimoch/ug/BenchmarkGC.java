package pl.jcimoch.ug;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Jaroslaw on 04.05.2016.
 */
public class BenchmarkGC {

    public static void beginBenchmark(BenchmarkTypes type) {
        ExecutorService executor;
        switch (type) {
            case EightThreadsStatic:
                executor = Executors.newFixedThreadPool(8);
                for (int i = 0; i < 8; i++) {
                    Runnable worker = new StaticAllocRunner();
                    executor.execute(worker);
                }
                break;
            case EightThreadsDynamic:
                executor = Executors.newFixedThreadPool(8);
                for (int i = 0; i < 8; i++) {
                    Runnable worker = new DynamicAllocRunner();
                    executor.execute(worker);
                }
                break;
            case OneThreadStatic:
                executor = Executors.newFixedThreadPool(1);
                StaticAllocRunner r1 = new StaticAllocRunner();
                executor.execute(r1);
                break;
            case OneThreadDynamic:
                executor = Executors.newFixedThreadPool(1);
                DynamicAllocRunner r2 = new DynamicAllocRunner();
                executor.execute(r2);
                break;
            default:
                executor = Executors.newFixedThreadPool(8);
                break;
        }

        executor.shutdown();
        while (!executor.isTerminated()) {

        }
        System.out.println("Finished all threads!");
    }
}
