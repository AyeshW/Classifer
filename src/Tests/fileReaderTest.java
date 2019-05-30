package Tests;

import fileHandler.fileReader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class fileReaderTest {

    private fileReader reader = new fileReader();

    @Test
    void getExtensionOnFailTest(){
        File file = new File("C:\\Users\\Ayesh\\Desktop\\evaluation files\\musicians");
        String extension = this.reader.getFileExtension(file);
        Assertions.assertEquals("", extension);
    }
    @Test
    void getExtensionOnPassDocxTest(){
        File file = new File("C:\\Users\\Ayesh\\Desktop\\example.docx");
        String extension = this.reader.getFileExtension(file);
        Assertions.assertEquals(".docx", extension);
    }

    @Test
    void getExtensionOnPassPdfTest() {
        File file = new File("C:\\Users\\Ayesh\\Desktop\\evaluation files\\musicians.pdf");
        String extension = this.reader.getFileExtension(file);
        Assertions.assertEquals(".pdf", extension);
    }
    @Test
    void getExtensionOnPassTxtTest() {
        File file = new File("C:\\Users\\Ayesh\\Desktop\\sample\\example.txt");
        String extension = this.reader.getFileExtension(file);
        Assertions.assertEquals(".txt", extension);
    }

    @Test
    void readFilesOnPassTest() {
        File file1 = new File("C:\\Users\\Ayesh\\Desktop\\sample\\example.pdf");
        File file2 = new File("C:\\Users\\Ayesh\\Desktop\\sample\\example.txt");
        List<File> files = new ArrayList<File>();

        files.add(file1);
        files.add(file2);

        JSONArray result = null;
        try {
            result = this.reader.readFiles(files);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject obj1 = new JSONObject();
        JSONObject obj2 = new JSONObject();
        obj1.put("path", "C:\\Users\\Ayesh\\Desktop\\sample\\example.pdf");
        obj1.put("text", "my name is ayesh. i live in piliyanadala \n" +
                "now i am got stucked with reading files \n" +
                "ha haaaa \n" + "\n");
        obj2.put("path", "C:\\Users\\Ayesh\\Desktop\\sample\\example.txt");
        obj2.put("text", "my name is ayesh. i live in piliyanadala.now i am got stucked with reading files.ha haaaa.");
        JSONArray array = new JSONArray();
        array.put(obj1);
        array.put(obj2);

        JSONAssert.assertEquals(array, result, true);
        //Assertions.assertArrayEquals(new JSONArray[]{array}, new JSONArray[]{result});
    }
}
