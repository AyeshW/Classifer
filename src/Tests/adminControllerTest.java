package Tests;

import client.clientController;
import dbUtil.dbConnection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class adminControllerTest extends clientController {

    private Connection conn;
    @BeforeAll
    public void getConnection(){
        this.conn = dbConnection.getConnection();
    }

    @Test
    void getUserData() {
    }
}