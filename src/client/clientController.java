package client;

import analyzer.analyzer;
import fileHandler.fileOpener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import static java.lang.Thread.sleep;

public class clientController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public clientController() { }

    public static List<File> selectedFiles;

    protected JSONArray results = null;

    @FXML
    protected Button filechooser;

    @FXML
    protected  Label path1, path2, path3, path4, path5, path6, path7, path8, path9, path10;

    @FXML
    protected RadioButton genButton, confButton;

    @FXML
    protected Label result1, result2, result3, result4, result5, result6, result7, result8, result9, result10;

    @FXML
    protected VBox vbox;

    @FXML
    protected ProgressIndicator progress;

    @FXML
    protected Button analyzer;

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

    public void clearPaths(){
        this.path1.setText("");
        this.path2.setText("");
        this.path3.setText("");
        this.path4.setText("");
        this.path5.setText("");
        this.path6.setText("");
        this.path7.setText("");
        this.path8.setText("");
        this.path9.setText("");
        this.path10.setText("");
    }

    public void clearResults(){
        this.result1.setVisible(false);
        this.result2.setVisible(false);
        this.result3.setVisible(false);
        this.result4.setVisible(false);
        this.result5.setVisible(false);
        this.result6.setVisible(false);
        this.result7.setVisible(false);
        this.result8.setVisible(false);
        this.result9.setVisible(false);
        this.result10.setVisible(false);
    }

    public void selectFiles(ActionEvent event){
        //Clearing previous results before loading new files
        clearPaths();
        clearResults();

        fileOpener opener = new fileOpener();
        try {
            selectedFiles = opener.multipleFileChooser(filechooser.getScene().getWindow());
            setPathValues();
            this.progress.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void alertAnalyzeWarning(){
        Alert alert = new Alert(Alert.AlertType.WARNING, "Select Files Before Analyze", ButtonType.CLOSE);
        //Optional<ButtonType> resu
        alert.showAndWait();
    }
    //Common Analyze methode for both Client and Admin
    public void clientAnalyze(){
        if(selectedFiles != null) {
            analyzer analyzer = new analyzer();
            if (this.genButton.isSelected()) {
                this.results = analyzer.analyze("general");
            }
            else if (this.confButton.isSelected()) {
                // temporory same classifier is used for both general and confidential
                this.results = analyzer.analyze("general");
                //analyzer.analyze("confidential");
            }
            this.showResults();
        }
        else{
            alertAnalyzeWarning();
        }
    }

    public void showResults(){
        int n = 1;
        int size;
        if(this.results.length() >10){
            size = 10;
        }
        else{
            size = this.results.length();
        }
        for(int i = 0; i < size; i++) {
            JSONObject object = this.results.getJSONObject(i);
            String path = object.getString("path");
            String category = object.getString("category");
            switch (category){
                case "tech":
                    category = "Technology";
                    break;
                case "sport":
                    category = "Sports";
                    break;
                case "business":
                    category = "Business";
                    break;
                case "politics":
                    category = "Politics";
                    break;
                case "entertainment":
                    category = "Entertainment";
                    break;
                 default:
                     break;
            }
            if(n == 1){
                this.path1.setText(path);
                this.result1.setText(category);
                this.result1.setVisible(true);
                n += 1;
            }
            else if(n == 2){
                this.path2.setText(path);
                this.result2.setText(category);
                this.result2.setVisible(true);
                n += 1;
            }
            else if(n == 3){
                this.path3.setText(path);
                this.result3.setText(category);
                this.result3.setVisible(true);
                n += 1;
            }
            else if(n == 4){
                this.path4.setText(path);
                this.result4.setText(category);
                this.result4.setVisible(true);
                n += 1;
            }
            else if(n == 5){
                this.path5.setText(path);
                this.result5.setText(category);
                this.result5.setVisible(true);
                n += 1;
            }
            else if(n == 6){
                this.path6.setText(path);
                this.result6.setText(category);
                this.result6.setVisible(true);
                n += 1;
            }
            else if(n == 7){
                this.path7.setText(path);
                this.result7.setText(category);
                this.result7.setVisible(true);
                n += 1;
            }
            else if(n == 8){
                this.path8.setText(path);
                this.result8.setText(category);
                this.result8.setVisible(true);
                n += 1;
            }
            else if(n == 9){
                this.path9.setText(path);
                this.result9.setText(category);
                this.result9.setVisible(true);
                n += 1;
            }
            else if(n == 10){
                this.path10.setText(path);
                this.result10.setText(category);
                this.result10.setVisible(true);
                n += 1;
            }

        }
        this.progress.setVisible(false);

    }
}
