package logginApp;

import dbUtil.dbConnection;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class loginModel {
    private Connection connection;

    public loginModel(){
        try{
            this.connection = dbConnection.getConnection();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        if (this.connection == null){
            System.out.println("Database Connection Error");
            System.exit(1);
        }
    }

    public boolean isDBConnected(){
        return this.connection != null;
    }


    public boolean isLogin(String user, String pass, String type) throws SQLException {
        PreparedStatement pr = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM loginDetails WHERE userName = ? and userType = ?";


        try{
            pr = this.connection.prepareStatement(sql);
            pr.setString(1,user);
            pr.setString(2,type);
            rs = pr.executeQuery();

            //username should be unique
            boolean pswdchck = BCrypt.checkpw(pass,rs.getString("password"));

            if(rs.next() && pswdchck) {
                return true;
            }else{
                return false;
            }
        }catch (SQLException e){
            return false;
        }
        finally {
            {
                pr.close();
                rs.close();
            }
        }
    }
}
