package service;

import entities.Person;
import entities.Role;
import entities.User;

import java.util.List;
import java.util.Map;

public interface UserServiceInterface {

    /**
     * Finds customers who have more than number of addresses given.
     * @param numberOfAddresses number of addresses
     * @return list of users
     */
    List<User> findUsersWithAddressesCountMoreThan(int numberOfAddresses);

    /**
     * Return the oldest user's personal details.
     * @return person
     */
    Person findOldestPerson();

    /**
     * Finds user with the longest username
     * @return user
     */
    User findUserWithLongestUsername();

    /**
     * Returns a string with names and surnames of users above 18 years old
     * separated by a comma
     * @return string
     */
    String getNamesAndSurnamesCommaSeparatedOfAllUsersAbove18();

    /**
     * Returns a sorted list of permission names of users with name
     * starting with a prefix given
     * @return
     */
    List<String> getSortedPermissionsOfUsersWithNameStartingWith(String prefix);

    /**
     * Returns all users average age.
     * @return user's average age
     */
    double getUsersAverageAge();

    /**
     * Prints capitalized permission names of users with surname ending with suffix given.
     */
    void printCapitalizedPermissionNamesOfUsersWithSurnameEndingWith(String suffix);

    /**
     * Groups users by role.
     * @return map
     */
    Map<Role, List<User>> groupUsersByRole();

    /**
     * Partitions users on two groups - below and over 18 years old.
     * @return map
     */
    Map<Boolean, List<User>> partitionUserByUnderAndOver18();
}
