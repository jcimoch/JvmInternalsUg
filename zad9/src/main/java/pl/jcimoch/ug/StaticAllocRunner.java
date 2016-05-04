package pl.jcimoch.ug;

/**
 * Created by Jaroslaw on 04.05.2016.
 */
public class StaticAllocRunner implements Runnable {
    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        int allocCounter = 0;
        while (System.currentTimeMillis() - startTime < 60000) {
            MemoryAllocator.alloc(1);
            allocCounter++;
        }
        System.out.println("Current Thread: " + Thread.currentThread() + "Allocations count: " + allocCounter);

    }
}
