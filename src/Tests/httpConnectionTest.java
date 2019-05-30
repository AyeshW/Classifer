package Tests;

import analyzer.httpConnection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import sun.plugin.javascript.JSObject;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class httpConnectionTest {

    private JSONArray input = new JSONArray();
    private JSONArray output = new JSONArray();
    private httpConnection http = new httpConnection();

    public void addJson(JSONObject ob1, JSONObject ob2, JSONObject ob3, JSONObject ob4){
         ob1.put("path", "C:\\Users\\Ayesh\\Desktop\\evaluation files\\musicians.pdf");
         ob1.put("text", "People of Estonia love music. Every five years, in Tallinn, there is a cultural event- “the Song Festival”. It is the Estonian Song and Dance Celebration which involves people from all over Estonia as well as other countries. 905 choirs and 26, 430 singers and musicians performed in Song Celebration and XVIII Dance Celebration with the theme To Breathe as One");

         ob2.put("path","C:\\Users\\Ayesh\\Deskto\\evaluation files\\1946-03-05 winston churchill speech.pdf");
         ob2.put("text", "President McCluer, ladies and gentlemen, and last, but certainly not least, the President of the United States of America");

         ob3.put("path", "C:\\Users\\Ayesh\\Desktop\\evaluation files\\musicians.pdf");
         ob3.put("category", "entertainment");

         ob4.put("path", "C:\\Users\\Ayesh\\Deskto\\evaluation files\\1946-03-05 winston churchill speech.pdf");
         ob4.put("category","business");

         this.input.put(ob1);
         this.input.put(ob2);

         this.output.put(ob3);
         this.output.put(ob4);
    }

    //unit test + integration testing with post method testing
    @Test
    void readWritePassTest() {
        JSONObject o1 = new JSONObject();
        JSONObject o2 = new JSONObject();
        JSONObject o3 = new JSONObject();
        JSONObject o4 = new JSONObject();
        addJson(o1, o2, o3, o4);

        JSONArray result = null;
        try {
            result = http.readWrite("general" ,input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONAssert.assertEquals(output,result, true);
    }

}