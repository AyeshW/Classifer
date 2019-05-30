package admin;

import client.clientController;
import dbUtil.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import logginApp.loginController;
import org.mindrot.jbcrypt.BCrypt;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class adminController extends clientController implements Initializable {


    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;

    @FXML
    private TextField email;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField confirmPass;

    //For changing admin password
    @FXML
    private TextField usernamechk;

    @FXML
    private PasswordField currPass;

    @FXML
    private PasswordField newPass;

    @FXML
    private PasswordField confNewPass;

    @FXML
    private TableView<userData> usertable;

    @FXML
    private TableColumn<userData, String> idcolumn;

    @FXML
    private TableColumn<userData, String> firstnamecolumn;

    @FXML
    private TableColumn<userData, String> lastnamecolumn;

    @FXML
    private TableColumn<userData, String> emailcolumn;

    private Connection connection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.connection = dbConnection.getConnection();
    }

    private ObservableList<userData> getUserData() {
        ObservableList<userData> data = null;
        String sql = "SELECT * FROM userDetails";
        try {
            data = FXCollections.observableArrayList();
            ResultSet rs = connection.createStatement().executeQuery(sql);

            while (rs.next()) {
                data.add(new userData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    @FXML
    public void loadUserData(ActionEvent event){
        ObservableList<userData> data = getUserData();

        this.idcolumn.setCellValueFactory(new PropertyValueFactory<userData, String>("ID"));
        this.firstnamecolumn.setCellValueFactory(new PropertyValueFactory<userData, String>("firstname"));
        this.lastnamecolumn.setCellValueFactory(new PropertyValueFactory<userData, String>("lastname"));
        this.emailcolumn.setCellValueFactory(new PropertyValueFactory<userData, String>("email"));

        this.usertable.setItems(null);
        this.usertable.setItems(data);
    }

    public void clearAddFields(){
        this.firstname.setText("");
        this.lastname.setText("");
        this.email.setText("");
        this.username.setText("");
        this.password.setText("");
        this.confirmPass.setText("");
    }

    @FXML
    public void addUser() {

        try {
            this.connection.setAutoCommit(false);
            String sqlUserDetails = "INSERT INTO userDetails(firstName, lastName, email) VALUES(?, ?, ?)";
            PreparedStatement pr1 = null;

            String sqlLoginDetails = "INSERT INTO loginDetails(userName, password, userType) VALUES(?, ?, ?)";
            PreparedStatement pr2 = null;

            pr2 = this.connection.prepareStatement(sqlLoginDetails);
            String password = this.password.getText();
            String confirm = this.confirmPass.getText();
            if (password.equals(confirm)) {
                String pass = BCrypt.hashpw(this.password.getText(), BCrypt.gensalt());
                pr2.setString(1, this.username.getText());
                pr2.setString(2, pass);
                pr2.setString(3, "Client");
                pr2.executeUpdate();

                pr1 = this.connection.prepareStatement(sqlUserDetails);
                pr1.setString(1, this.firstname.getText());
                pr1.setString(2, this.lastname.getText());
                pr1.setString(3, this.email.getText());
                pr1.executeUpdate();

                this.connection.commit();
                pr1.close();
                pr2.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully Added", ButtonType.CLOSE);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Password Confirmation Error", ButtonType.CLOSE);
                alert.showAndWait();

            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (this.connection != null) {
                try {
                    this.connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
    clearAddFields();
    }

    @FXML
    public void changePassword(){
        String sql1 = "SELECT * FROM loginDetails WHERE username = ?";
        //default password check value is false
        boolean pswdchk = false;
        ResultSet rs = null;
        try {
            PreparedStatement pr1 = this.connection.prepareStatement(sql1);
            pr1.setString(1, this.usernamechk.getText());
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
                    pr.setString(2, this.usernamechk.getText());
                    pr.executeUpdate();

                    pr.close();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Password Successfully Changed", ButtonType.CLOSE);
                    alert.showAndWait();
                    this.usernamechk.setText("");
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
