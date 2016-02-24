/**
 * Created by Jarek on 18.02.2016.
 */
package pl.jcimoch.ug;

import java.util.ArrayList;
import java.util.List;

class DummyData {
    private String dummyString;

    public DummyData(String a) {
        this.dummyString = a;
    }
}

public class OutOfMemoryErrorExample {

    public static void main(String[] args) {

        List<DummyData> dummyList = new ArrayList<DummyData>();
        while (true) {
            dummyList.add(new DummyData("dummyText"));
        }

    }
}
