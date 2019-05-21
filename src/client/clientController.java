package client;

import fileHandler.fileOpener;
import fileHandler.fileReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class clientController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    protected Button filechooser;

    public void selectFiles(ActionEvent event){
        fileOpener opener = new fileOpener();
        fileReader reader = new fileReader();
        try {
            reader.readFiles(opener.multipleFileChooser(filechooser.getScene().getWindow()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
