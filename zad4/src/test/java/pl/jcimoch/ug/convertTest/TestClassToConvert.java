package pl.jcimoch.ug.convertTest;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Jarek on 13.03.2016.
 */

enum Level {
    Low, High
}

public class TestClassToConvert {
    //private final String testString = "hello world";
    public ArrayList<Boolean> logicList = new ArrayList<Boolean>(Arrays.asList(true, false, true, false));
    public ArrayList<Person> personList = new ArrayList<Person>(Arrays.asList(new Person(), new Person()));
    public int[][][] simpleArray = new int[2][2][2];
    public String[][][] simpleStringArray = new String[2][2][2];
    public Person[] persons = new Person[2];
    public Object empty = new Object();
    public Level e = Level.Low;
    public String h = "Hello World";

    public TestClassToConvert() {
        simpleArray[0][1][0] = 22;
        simpleStringArray[0][1][0] = "hello world";
        persons[0] = new Person();

    }
}
