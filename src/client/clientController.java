package client;

import analyzer.analyzer;
import fileHandler.fileOpener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class clientController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public clientController() { }

    public static List<File> selectedFiles;

    @FXML
    protected Button filechooser;

    @FXML
    protected  Label path1, path2, path3, path4, path5, path6, path7, path8, path9, path10;

    public void setPathValues(){

        int n = 1;
        Iterator it = selectedFiles.iterator();

        while (it.hasNext()){
            String path = it.next().toString();
            if(n == 1){
                this.path1.setText(path);
                n += 1;
            }
            else if(n == 2){
                this.path2.setText(path);
                n += 1;
            }
            else if(n == 3){
                this.path3.setText(path);
                n += 1;
            }
            else if(n == 4){
                this.path4.setText(path);
                n += 1;
            }
            else if(n == 5){
                this.path5.setText(path);
                n += 1;
            }
            else if(n == 6){
                this.path6.setText(path);
                n += 1;
            }
            else if(n == 7){
                this.path7.setText(path);
                n += 1;
            }
            else if(n == 8){
                this.path8.setText(path);
                n += 1;
            }
            else if(n == 9){
                this.path9.setText(path);
                n += 1;
            }
            else if(n == 10){
                this.path10.setText(path);
                n += 1;
            }
        }
    }

    public void selectFiles(ActionEvent event){
        fileOpener opener = new fileOpener();
        try {
            selectedFiles = opener.multipleFileChooser(filechooser.getScene().getWindow());
            setPathValues();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void analyze(){
        analyzer analyzer = new analyzer();
        analyzer.analyze();
    }
}
