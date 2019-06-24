package signUp;

import dbUtil.dbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class signupController implements Initializable {
    private signupModel sgModel = new signupModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField confpswd;

    @FXML
    private TextField productKey;

    @FXML
    private Button registerButton;

    @FXML
    public void register(ActionEvent event){
        this.sgModel.registerAdmin(this.username.getText(), this.password.getText(), this.confpswd.getText(), this.productKey.getText());
        try {
            Stage loginStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("/logginApp/login.fxml").openStream());


            Scene scene = new Scene(root,630, 463);
            loginStage.setScene(scene);
            loginStage.setResizable(false);
            loginStage.setTitle("Classifer | Login");
            loginStage.show();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

}
