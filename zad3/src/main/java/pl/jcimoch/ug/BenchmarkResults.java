package pl.jcimoch.ug;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

/**
 * Created by Jarek on 09.03.2016.
 */
public class BenchmarkResults {


    public static HashMap<MethodType, ArrayList<Long>> results = new HashMap<MethodType, ArrayList<Long>>();
    public static HashMap<MethodType, Long> resultsAverage = new HashMap<MethodType, Long>();

    static {
        for (MethodType t : MethodType.values()) {
            results.put(t, new ArrayList<Long>());
        }
    }

    /**
     * @implNote I'm using Java 8 lambda and streams for calculating average
     */
    public static void calculateAverage() {
        Stream<Long> simpleReadTimesStream = results.get(MethodType.SimpleRead).stream();
        simpleReadTimesStream.mapToLong(a -> a).average();

        for (MethodType t : MethodType.values()) {
            Stream<Long> stream = results.get(t).stream();
            resultsAverage.put(t, (long) stream.mapToLong(a -> a).average().getAsDouble());
        }

    }


}
