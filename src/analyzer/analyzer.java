package analyzer;

import client.clientController;
import fileHandler.fileReader;
import javafx.fxml.Initializable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class analyzer implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    private fileReader reader;
    private JSONArray path_text = null;
    private httpConnection http;

    public analyzer(){
        this.reader  = new fileReader();
    }

    public JSONArray readToAnalyze() {
        try {
            this.path_text = reader.readFiles(clientController.selectedFiles);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path_text;
    }

    public JSONArray analyze(String classifier) {
        this.readToAnalyze();
        this.http = new httpConnection();
        JSONArray jarray = null;
        try {
            jarray = http.readWrite(classifier, this.path_text);
            System.out.println("Analyzer");
            System.out.println(jarray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jarray;
    }
}
