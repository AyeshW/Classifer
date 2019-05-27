package fileHandler;

import javafx.fxml.Initializable;

import java.io.*;
import java.net.URL;
import java.util.*;

import jdk.nashorn.internal.runtime.JSONFunctions;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.json.JSONArray;
import org.json.JSONObject;

public class fileReader implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initialized a file reader");
    }

    private String getFileExtension(File file) {
        String extension = "";

        try {
            if (file != null && file.exists()) {
                String name = file.getName();
                extension = name.substring(name.lastIndexOf("."));
            }
        }catch (Exception e){
            extension = "";
        }

        return extension;

    }
    public JSONArray readFiles(List<File> files) throws IOException {

        JSONArray path_text = new JSONArray();
        if(files != null){
            for (File file : files) {
                String file_path = file.getAbsolutePath();
                if (this.getFileExtension(file).equals(".txt")) {

                    String content = "";
                    String temp = null;
                    try (BufferedReader br =
                                 new BufferedReader(new FileReader(file_path))) {
                        while ((temp = br.readLine()) != null) {
                            content = content + temp + ".";
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    JSONObject obj = new JSONObject();
                    obj.put("path",file_path);
                    obj.put("text",content);
                    path_text.put(obj);

                } else if (this.getFileExtension(file).equals(".pdf")) {
                    PDDocument document = PDDocument.load(file);

                    PDFTextStripper pdfStripper = new PDFTextStripper();

                    //extracting text from PDF document
                    String content = pdfStripper.getText(document);
                    JSONObject obj = new JSONObject();
                    obj.put("path",file_path);
                    obj.put("text",content);
                    path_text.put(obj);

                    document.close();
                } else {
                    System.out.println("Error: Non 'text' or 'pdf' file found");
                }
            }
        }
        System.out.println(path_text.toString());
        return path_text;
    }
}
