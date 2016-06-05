package service;

import entities.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Jaroslaw on 05.06.2016.
 */
public class DataProducer {


    public static List<User> getUsersTestData(int count) {
        List<User> users = new ArrayList<>();
        Role r = new Role();
        Permission pm1 = new Permission();
        pm1.setName("admin");

        Permission pm2 = new Permission();
        pm2.setName("manager");

        r.setName("role1");
        r.setPermissions(Arrays.asList(pm1, pm2));
        for (int i = 0; i < count; i++) {
            User u = new User();
            Person p = new Person();
            Address a1 = new Address();
            a1.setCity("Gdansk");
            a1.setCountry("Poland");
            a1.setFlatNumber(11);
            a1.setPostCode("80-299");
            a1.setStreetName("abc" + i);

            Address a2 = new Address();
            a2.setCity("Poznan");
            a2.setCountry("Poland");
            a2.setFlatNumber(11);
            a2.setPostCode("80-299");
            a2.setStreetName("abc" + i);
            ArrayList<Address> adresses = new ArrayList<>(Arrays.asList(a1, a2));
            if (i == 9) {
                u.setName("abcdefghijklmnoprst");
            } else {
                u.setName(i%2 +"abc" + i);
            }
            u.setPassword("abc" + i);
            p.setName(i%2 + "abc" + i);
            p.setSurname(i%2 + "cba" + i);
            p.setAddresses(adresses);
            p.setAge(i * 10);
            p.setPhoneNumbers(Arrays.asList("5545698" + i, "558565" + i));
            p.setRole(r);

            u.setPersonDetails(p);
            users.add(u);
        }

        return users;
    }

}
