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
            System.exit(1);
        }
    }

    public boolean isDBConnected(){
        return this.connection != null;
    }

    /*public String getUserType(String user) throws SQLException{
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT userType FROM loginDetais WHERE userName = ?";

        try {
            pr = this.connection.prepareStatement(sql);
            pr.setString(1, user);
            rs = pr.executeQuery();

            boolean bool2;
            if (rs.next()){
                return String.valueOf(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            {
                pr.close();
                rs.close();
            }
        }
        return null;
    }*/

    public boolean isLogin(String user, String pass) throws SQLException {
        PreparedStatement pr = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM loginDetails WHERE userName = ?";


        try{
            pr = this.connection.prepareStatement(sql);
            pr.setString(1,user);
            //pr.setString(2,pass);
            rs = pr.executeQuery();
            boolean pswdchck = BCrypt.checkpw(pass,rs.getString("password"));
            boolean boo1;

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
