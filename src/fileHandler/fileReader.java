package fileHandler;

import javafx.fxml.Initializable;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

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
    public ArrayList<String> readFiles(List<File> files) throws IOException {

        ArrayList<String> texts_to_analyze = new ArrayList<String>();

            for (File file : files) {
                if (this.getFileExtension(file).equals(".txt")) {

                    String content = "";
                    String temp = null;
                    try (BufferedReader br =
                                 new BufferedReader(new FileReader(file.getAbsolutePath()))) {
                        while ((temp = br.readLine()) != null) {
                            content = content + temp + ".";
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    texts_to_analyze.add(content);
                    System.out.println(content);
                }
                else if(this.getFileExtension(file).equals(".pdf")){
                    PDDocument document = PDDocument.load(file);

                    PDFTextStripper pdfStripper = new PDFTextStripper();

                    //extracting text from PDF document
                    String text = pdfStripper.getText(document);
                    System.out.println(text);
                    texts_to_analyze.add(text);
                    document.close();
                }
                else{
                    System.out.println("Error: Non 'text' or 'pdf' file found");
                }
            }
        return texts_to_analyze;
    }
}
