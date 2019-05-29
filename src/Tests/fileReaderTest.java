package Tests;

import fileHandler.fileReader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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

    /*
    @Test
    void readFilesOnPassTest() {
        File file1 = new File("C:\\Users\\Ayesh\\Desktop\\sample\\example.pdf");
        File file2 = new File("C:\\Users\\Ayesh\\Desktop\\sample\\example.txt");
        List<File> files = new List<File>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<File> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(File file) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends File> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends File> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public File get(int index) {
                return null;
            }

            @Override
            public File set(int index, File element) {
                return null;
            }

            @Override
            public void add(int index, File element) {

            }

            @Override
            public File remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<File> listIterator() {
                return null;
            }

            @Override
            public ListIterator<File> listIterator(int index) {
                return null;
            }

            @Override
            public List<File> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
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
        obj1.put("text", "my name is ayesh. i live in piliyanadala. now i am got stucked with reading files. ha haaaa.");
        obj2.put("path", "C:\\Users\\Ayesh\\Desktop\\sample\\example.txt");
        obj2.put("text", "my name is ayesh. i live in piliyanadala." + "now i am got stucked with reading files." + "ha haaaa.");
        JSONArray array = new JSONArray();
        array.put(obj1);
        array.put(obj2);

        Assertions.assertArrayEquals(new JSONArray[]{array}, new JSONArray[]{result});
    }*/
}
