package pl.jcimoch.ug;

import java.text.ParseException;

/**
 * Created by Jarek on 06.04.2016.
 */
public class SimpleDateThread implements Runnable {

    private int year;

    public void run() {
        String date = "06/04/" + year;
        System.out.println("Expected to be " + date + " with current thread " + Thread.currentThread());
        try {
            System.out.println("Got: " + SharedSimpleDate.sdf.parse(date) + " with current thread " + Thread.currentThread());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public SimpleDateThread(int year) {
        this.year = year;
    }

}
