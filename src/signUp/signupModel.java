package signUp;

import dbUtil.dbConnection;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class signupModel {
    private Connection connection;

    public signupModel(){
        this.connection = dbConnection.getConnection();
        if (this.connection == null){
            System.out.println("Database Connection Error");
            System.exit(1);
        }
    }

    public boolean checkUsername(String username){
        PreparedStatement ps = null;
        ResultSet rs;
        boolean checkUser = false;
        String query = "SELECT * FROM loginDetails WHERE userName =?";

        try {
            ps = this.connection.prepareStatement(query);
            ps.setString(1, username);

            rs = ps.executeQuery();

            if(rs.next())
            {
                checkUser = true;
            }
        } catch (SQLException ex) {
            System.out.println("SQL Error in username checking");
            return true;
        }
        return checkUser;
    }

    public boolean checkProductKey(String key){
        PreparedStatement ps = null;
        ResultSet rs;
        boolean checkKey = false;
        String query = "SELECT * FROM productDetails WHERE productKey =?";

        try {
            ps = this.connection.prepareStatement(query);
            ps.setString(1, key);

            rs = ps.executeQuery();

            if(rs.next())
            {
                checkKey = true;
            }
        } catch (SQLException ex) {
            System.out.println("SQL Error in product key checking");
        }
        return checkKey;
    }

    public void registerAdmin(String username, String password, String confpass, String productkey){

        if(this.checkProductKey(productkey)){
            if(!checkUsername(username)){
                if(password.equals(confpass)) {
                    PreparedStatement ps = null;
                    String sqlLoginDetails = "INSERT INTO loginDetails(userName, password, userType) VALUES(?, ?, ?)";

                    try {
                        this.connection.setAutoCommit(false);
                        ps = this.connection.prepareStatement(sqlLoginDetails);
                        String pass = BCrypt.hashpw(password, BCrypt.gensalt());

                        ps.setString(1, username);
                        ps.setString(2, pass);
                        ps.setString(3, "Admin");
                        ps.executeUpdate();
                        this.connection.commit();
                        ps.close();

                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully Added", ButtonType.CLOSE);
                        alert.showAndWait();

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Password Confirmation Error", ButtonType.CLOSE);
                    alert.showAndWait();
                }

            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING, "Username Already Existing", ButtonType.CLOSE);
                alert.showAndWait();
            }

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Product Key Entered", ButtonType.CLOSE);
            alert.showAndWait();
        }

    }
}
