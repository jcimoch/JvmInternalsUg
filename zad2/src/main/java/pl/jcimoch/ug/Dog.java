package pl.jcimoch.ug;

/**
 * Created by Jarek on 02.03.2016.
 */
public class Dog extends Mammal {
    private String name;

    @Override
    public String getName() {
        return name;
    }

    public Dog() {
        this.name = "Sponge";
    }
}
