package pl.jcimoch.ug;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jarek on 09.03.2016.
 */
public class Main {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        Benchmark b = new Benchmark();
        BenchmarkResults.calculateAverage();

        for (MethodType t : MethodType.values()) {
            System.out.print(TimeUnit.NANOSECONDS.toMillis(BenchmarkResults.results.get(t).get(0)) + "\t");
            //System.out.print(TimeUnit.NANOSECONDS.toMillis(BenchmarkResults.resultsAverage.get(t)) + "\t");
        }
        System.out.println();

    }

}
