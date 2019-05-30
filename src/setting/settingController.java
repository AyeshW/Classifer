package setting;

import dbUtil.dbConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.mindrot.jbcrypt.BCrypt;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class settingController implements Initializable {

    Connection connection;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.connection = dbConnection.getConnection();
    }

    @FXML
    private TextField username;

    @FXML
    private PasswordField currPass;

    @FXML
    private PasswordField newPass;

    @FXML
    private PasswordField confNewPass;

    @FXML
    public void change(){
        String sql1 = "SELECT * FROM loginDetails WHERE username = ?";
        //default password check value is false
        boolean pswdchk = false;
        ResultSet rs = null;
        try {
            PreparedStatement pr1 = this.connection.prepareStatement(sql1);
            pr1.setString(1, this.username.getText());
            rs = pr1.executeQuery();

            pswdchk = BCrypt.checkpw(this.currPass.getText(),rs.getString("password"));
            pr1.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(pswdchk){
            if(this.newPass.getText().equals(this.confNewPass.getText())){
                // create the update prepared statement
                String sql = "UPDATE loginDetails SET password = ? WHERE userName = ?";
                try {
                    PreparedStatement pr = this.connection.prepareStatement(sql);
                    //hashing the new password
                    String newpass = BCrypt.hashpw(this.newPass.getText(), BCrypt.gensalt());
                    pr.setString(1,newpass);
                    pr.setString(2, this.username.getText());
                    pr.executeUpdate();

                    pr.close();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Password Successfully Changed", ButtonType.CLOSE);
                    alert.showAndWait();
                    this.username.setText("");
                    this.currPass.setText("");
                    this.newPass.setText("");
                    this.confNewPass.setText("");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else{
                this.newPass.setText("");
                this.confNewPass.setText("");
                Alert alert = new Alert(Alert.AlertType.ERROR, "Password Confirmation Error", ButtonType.CLOSE);
                alert.showAndWait();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING, "Invalid Password Credentials", ButtonType.CLOSE);
            alert.showAndWait();
        }
    }

}
