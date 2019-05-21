package admin;

import client.clientController;
import dbUtil.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class adminController extends clientController implements Initializable {

    @FXML
    private TextField ID;

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
    private TableView<userData> usertable;

    @FXML
    private TableColumn<userData, String> idcolumn;

    @FXML
    private TableColumn<userData, String> firstnamecolumn;

    @FXML
    private TableColumn<userData, String> lastnamecolumn;

    @FXML
    private TableColumn<userData, String> emailcolumn;

    private dbConnection conn;
    private ObservableList<userData> data;
    private String sql = "SELECT * FROM userDetails";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.conn = new dbConnection();
    }

    @FXML
    private void loadUserData(ActionEvent event) throws SQLException{
        try {


            Connection connection = dbConnection.getConnection();
            this.data = FXCollections.observableArrayList();
            ResultSet rs = connection.createStatement().executeQuery(sql);

            while (rs.next()) {
                this.data.add(new userData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        this.idcolumn.setCellValueFactory(new PropertyValueFactory<userData, String>("ID"));
        this.firstnamecolumn.setCellValueFactory(new PropertyValueFactory<userData, String>("firstname"));
        this.lastnamecolumn.setCellValueFactory(new PropertyValueFactory<userData, String>("lastname"));
        this.emailcolumn.setCellValueFactory(new PropertyValueFactory<userData, String>("email"));

        this.usertable.setItems(null);
        this.usertable.setItems(this.data);
    }
}
