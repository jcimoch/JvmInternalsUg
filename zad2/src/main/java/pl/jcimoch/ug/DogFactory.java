package pl.jcimoch.ug;

/**
 * Created by Jarek on 02.03.2016.
 */
public class DogFactory {
    public static Mammal createDog() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        CustomClassLoader customClassLoader = new CustomClassLoader();
        Mammal dog = null;
        try {
            // IMPORTANT
            // We have to cast to class or interface that is "constant between classloader"
            // in this example it is our superclass
            dog = (Mammal) customClassLoader.loadClass("pl.jcimoch.ug.Dog").newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return dog;
    }
}
