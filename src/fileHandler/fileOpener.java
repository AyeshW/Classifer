package fileHandler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class fileOpener implements Initializable {

    private List<File> selectedFiles;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initialized a file opener");
    }

    @FXML
    public List<File> multipleFileChooser(Window window) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open text/pdf files");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF/Text Files","*.pdf","*.txt"));
        this.selectedFiles = fileChooser.showOpenMultipleDialog(window);
        return this.selectedFiles;
    }
}
