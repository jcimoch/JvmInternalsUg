package pl.jcimoch.ug;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Jarek on 06.04.2016.
 */
public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(8);
        for (int i = 0; i < 10; i++) {
            Runnable worker = new SimpleDateThread(2016 - i);
            executor.execute(worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {

        }
        System.out.println("Finished all threads!");
    }
}
