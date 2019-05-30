package analyzer;

import org.json.JSONArray;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class httpConnection {

    public JSONArray readWrite(String classifier, JSONArray json) throws IOException {
        HttpURLConnection conn = null;
        URL url = null;
        if(classifier == "general"){
            url = new URL("http://127.0.0.1:5000/gen_category");
        }
        else if(classifier == "confidential"){
            url = new URL("http://127.0.0.1:5000/conf_category");
        }
        else{
            url = new URL("http://127.0.0.1:5000");
        }
        conn = (HttpURLConnection) url.openConnection();

        conn.setConnectTimeout(5000);
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");

        //Sending the request
        OutputStream os = conn.getOutputStream();
        os.write(json.toString().getBytes("UTF-8"));
        os.close();

        //Reading the response
        InputStream is = conn.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        StringBuilder response = new StringBuilder();
        while((line = br.readLine()) != null){
            response.append(line);
            response.append('\r');
        }

        JSONArray result = new JSONArray(response.toString());
        br.close();

        if(conn != null){
            conn.disconnect();
            System.out.println("http connection disconnected");
        }
        return result;
    }

}
