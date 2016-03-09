package pl.jcimoch.ug;

/**
 * Created by Jarek on 09.03.2016.
 */
public class Person {
    public int age;
    public Double height;

    public Person() {
        this.age = 18;
        this.height = 182.0;
    }

    public double grow(int howMuch) {
        return this.height + howMuch;
    }
}
