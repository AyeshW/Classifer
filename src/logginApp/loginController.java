package logginApp;

import admin.adminController;
import client.clientController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    private ComboBox<userType> usertype;

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

        this.usertype.setItems(FXCollections.observableArrayList(userType.values()));
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
            clientStage.setTitle("Classifer | Client Dashboard");
            clientStage.show();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

    @FXML
    public void login(ActionEvent event) throws SQLException {
        try {
            if(this.usertype.getValue() != null) {
                userType usertype = this.usertype.getValue();
                if (this.lgModel.isLogin(username.getText(), password.getText(), usertype.toString())) {
                    Stage stage = (Stage) this.loginbutton.getScene().getWindow();
                    stage.close();

                    switch (usertype.toString()) {
                        case "Admin":
                            adminLogin();
                            break;
                        case "Client":
                            clientLogin();
                            break;
                        default:
                            break;
                        }

                } else {
                    this.loginStatus.setText("Invalid User Name/Password/\n     User Type Combination");
                }
            }else{
                this.loginStatus.setText("Select the User Type");
            }
        }catch (Exception localException){
            localException.printStackTrace();
        }
    }

}
