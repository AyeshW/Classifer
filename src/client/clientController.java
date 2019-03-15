package client;

import fileHandler.fileOpener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class clientController implements Initializable {
    @FXML
    private Button filechooser;
    /*Stage clientStage = new Stage();
    FXMLLoader loader = new FXMLLoader();
    Pane root;

    {
        try {
            root = loader.load(getClass().getResource("client.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void selectFiles(ActionEvent event){
        fileOpener opener = new fileOpener();
        opener.multipleFileChooser(filechooser.getScene().getWindow());
    }

}
