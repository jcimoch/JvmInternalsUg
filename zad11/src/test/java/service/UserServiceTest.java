package service;

import entities.User;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserServiceTest {

    private UserService us;
    private List<User> results;

    @Before
    public void testBefore() {
        us = new UserService(DataProducer.getUsersTestData(10));
    }

    @Test
    public void findUsersWithAddressesCountMoreThanTest() {
        results = us.findUsersWithAddressesCountMoreThan(1);
        assertNotNull("Result can't be null", results);
        assertEquals(10, results.size());
    }

    @Test
    public void findOldestPersonTest() {
        assertEquals(90, us.findOldestPerson().getAge());
    }

    @Test
    public void findUserWithLongestUsernameTest() {
        assertEquals("abcdefghijklmnoprst", us.findUserWithLongestUsername().getName());
    }

    @Test
    public void getNamesAndSurnamesCommaSeparatedOfAllUsersAbove18Test() {
        assertEquals(8, us.getNamesAndSurnamesCommaSeparatedOfAllUsersAbove18().split(",").length);
    }

    @Test
    public void getSortedPermissionsOfUsersWithNameStartingWithTest() {
        assertEquals(10, us.getSortedPermissionsOfUsersWithNameStartingWith("0abc").size());
    }

    @Test
    public void printCapitalizedPermissionNamesOfUsersWithSurnameEndingWithTest() {
        /* testing actual output from console */
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        us.printCapitalizedPermissionNamesOfUsersWithSurnameEndingWith("cba0");
        assertEquals(outContent.toString().trim(), "ADMIN\nMANAGER");
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }

    @Test
    public void getUsersAverageAgeTest() {
        assertEquals(45, us.getUsersAverageAge(), 0.01);
    }

    @Test
    public void groupUsersByRoleTest() {
        assertEquals(10, us.groupUsersByRole().get(us.users.get(0).getPersonDetails().getRole()).size());
    }

    @Test
    public void partitionUserByUnderAndOver18Test() {
        assertEquals(2, us.partitionUserByUnderAndOver18().get(false).size());
    }
}
