package Tests;

import fileHandler.fileOpener;
import javafx.stage.Window;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static org.junit.jupiter.api.Assertions.*;

class fileOpenerTest {

    @Test
    void multipleFileChooserTest() {
        //File file = new File("C:\\Users\\Ayesh\\Desktop\\example.docx");
        File file1 = new File("C:\\Users\\Ayesh\\Desktop\\sample\\example.txt");
        File file2 = new File("C:\\Users\\Ayesh\\Desktop\\evaluation files\\musicians.pdf");
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

        fileOpener opener = new fileOpener();
        //List<File> result = opener.multipleFileChooser();
    }
}