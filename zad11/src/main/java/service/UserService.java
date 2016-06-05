package service;

import entities.Person;
import entities.Role;
import entities.User;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class UserService implements UserServiceInterface {

    public List<User> users;

    public UserService(List<User> users) {
        this.users = users;
    }

    public List<User> findUsersWithAddressesCountMoreThan(final int numberOfAddresses) {
        return users.stream().filter(user -> user.getPersonDetails().getAddresses().size() > numberOfAddresses)
                .collect(Collectors.toList());
    }

    public Person findOldestPerson() {
        final Comparator<User> comp = (u1, u2) -> Integer.compare(u1.getPersonDetails().getAge(), u2.getPersonDetails().getAge());
        return users.stream()
                .max(comp)
                .get()
                .getPersonDetails();
    }

    public User findUserWithLongestUsername() {
        final Comparator<User> comp = (u1, u2) -> Integer.compare(u1.getName().length(), u2.getName().length());
        return users.stream()
                .max(comp)
                .get();
    }

    public String getNamesAndSurnamesCommaSeparatedOfAllUsersAbove18() {
        return users.stream()
                .filter(user -> user.getPersonDetails().getAge() > 18)
                .map(user -> user.getPersonDetails().getName() + " " + user.getPersonDetails().getSurname())
                .collect(Collectors.joining(", "));
    }

    public List<String> getSortedPermissionsOfUsersWithNameStartingWith(String prefix) {
        return users.stream()
                .filter(user -> user.getName().startsWith(prefix))
                .map(user -> user.getPersonDetails().getRole().getPermissions())
                .flatMap(permissions -> permissions.stream())
                .map(permission -> permission.getName())
                .sorted()
                .collect(Collectors.toList());
    }

    public double getUsersAverageAge() {
        return users.stream()
                .mapToDouble(user -> user.getPersonDetails().getAge())
                .average()
                .getAsDouble();
    }

    public void printCapitalizedPermissionNamesOfUsersWithSurnameEndingWith(String suffix) {
        users.stream()
                .filter(user -> user.getPersonDetails().getSurname().endsWith(suffix))
                .map(user -> user.getPersonDetails().getRole().getPermissions())
                .flatMap(permissions -> permissions.stream())
                .map(permission -> permission.getName().toUpperCase()+'\n')
                .forEach(System.out::print);
    }

    public Map<Role, List<User>> groupUsersByRole() {
        return users.stream()
                .collect(Collectors
                        .groupingBy(user -> user.getPersonDetails().getRole()));
    }

    public Map<Boolean, List<User>> partitionUserByUnderAndOver18() {
        return users.stream()
                .collect(Collectors.groupingBy(user -> user.getPersonDetails().getAge() >= 18));
    }
}
