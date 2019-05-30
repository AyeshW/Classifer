package dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {

    private static final String conn = "jdbc:sqlite:Classifer.sqlite";

    public static Connection getConnection(){
        try{
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(conn);
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
