package Tests;

import admin.userData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class userDataTest {

    private userData userdata = new userData("10","Sanath","Jayasena","Sanath@gmail.com");

    @Test
    void getID() {
        assertEquals("10",userdata.getID());
    }


    @Test
    void getFirstname() {
        assertEquals("Sanath",userdata.getFirstname());
    }

    @Test
    void getLastname() {
        assertEquals("Jayasena",userdata.getLastname());
    }

    @Test
    void getEmail() {
        assertEquals("Sanath@gmail.com",userdata.getEmail());
    }
}