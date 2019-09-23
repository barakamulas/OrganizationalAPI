package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getU_name() {
        User testUser = setupUser();
        assertEquals("John", testUser.getU_name());
    }

    @Test
    public void setU_name() {
        User testUser = setupUser();
        testUser.setU_name("Mark");
        assertNotEquals("John", testUser.getU_name());
    }


    @Test
    public void getRole() {
        User testUser = setupUser();
        assertEquals("Editor", testUser.getRole());
    }

    @Test
    public void setRole() {
        User testUser = setupUser();
        testUser.setRole("News Anchor");
        assertNotEquals("Editor", testUser.getRole());
    }

    @Test
    public void getDepartment_id() {
        User testUser = setupUser();
        assertEquals(1, testUser.getDepartment_id());
    }

    @Test
    public void setDepartment_id() {
        User testUser = setupUser();
        testUser.setDepartment_id(2);
        assertNotEquals(1, testUser.getDepartment_id());
    }

    @Test
    public void setId() {
        User testUser = setupUser();
        testUser.setId(5);
        assertEquals(5, testUser.getId());
    }


    public User setupUser(){
        return new User("John","Editor",1);
    }


}