package pl.jcimoch.ug;

/**
 * Created by Jarek on 01.03.2016.
 */
public class Main {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        Dog d1 = new Dog();
        System.out.println("Our Dog: " + d1.getName());

        Mammal d2 = DogFactory.createDog();
        System.out.println("Dog from shelter: " + d2.getName());

    }
}
