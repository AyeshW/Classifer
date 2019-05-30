package Tests;

import client.clientController;
import dbUtil.dbConnection;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class dbConnectionTest extends clientController {


    @Test
    void getConnection() {
        Connection conn = dbConnection.getConnection();
        assertEquals(conn != null, true);

    }
}