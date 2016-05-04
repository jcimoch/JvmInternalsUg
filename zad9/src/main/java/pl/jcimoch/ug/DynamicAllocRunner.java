package pl.jcimoch.ug;

import java.util.Random;

/**
 * Created by Jaroslaw on 04.05.2016.
 */
public class DynamicAllocRunner implements Runnable {


    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        int allocCounter = 0;
        Random random = new Random();
        while (System.currentTimeMillis() - startTime < 60000) {
            MemoryAllocator.alloc(random.nextInt(100 - 1 + 1) + 1);
            allocCounter++;
        }
        System.out.println("Current Thread: " + Thread.currentThread() + "Allocations count: " + allocCounter);

    }
}
