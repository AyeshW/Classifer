package logginApp;

import admin.adminController;
import client.clientController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class loginController implements Initializable {
    private loginModel lgModel = new loginModel();

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private TextField usertype;

    @FXML
    private Button loginbutton;

    @FXML
    private Label loginStatus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(lgModel.isDBConnected()){
            System.out.println("Database Connected");
        }
        else{
            System.out.println("Database is not Connected");
        }
    }

    public void adminLogin(){
        try {
            Stage adminStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("/admin/admin.fxml").openStream());

            //adminController adminController = loader.getController();

            Scene scene = new Scene(root);
            adminStage.setScene(scene);
            adminStage.setTitle("Classifer | Admin Dashboard");
            adminStage.show();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public void clientLogin(){
        try {
            Stage clientStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("/client/client.fxml").openStream());

            //clientController clientController = (clientController)loader.getController();

            Scene scene = new Scene(root);
            clientStage.setScene(scene);
            clientStage.setTitle("Classifer | Admin Dashboard");
            clientStage.show();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }


    @FXML
    public void login(ActionEvent event) throws SQLException {
        try {

            if (this.lgModel.isLogin(this.username.getText(), this.password.getText())) {
                Stage stage = (Stage) this.loginbutton.getScene().getWindow();
                stage.close();

                //String userType = this.lgModel.getUserType(this.username.getText());
                switch (this.usertype.getText()) {
                    case "Admin":
                        adminLogin();
                        break;
                    case "Client":
                        clientLogin();
                        break;
                    default:
                        System.out.println("Error in user type");
                        clientLogin();
                        break;
                }

            } else {
                this.loginStatus.setText("Invalid User Name or Password");
            }
        }catch (Exception localException){
            localException.printStackTrace();
        }
    }

}
