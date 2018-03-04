package integratedproject.unilife_v1;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * Created by Kieran Brown on 3/4/2018.
 */

public class Database extends AsyncTask<Map, Void, String>{
    private String requestURL = "http://unilife.kieranbrown.me/?query=";
    public String results;
    private onTaskCompleted listener;

    public getJSON(onTaskCompleted listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Map... values) {
        HttpURLConnection con = null;
        //String tempURL = requestURL + values[0].get("queryType");
        String tempURL = requestURL + "getAllUsers";
        Log.d("request URL", tempURL);
        try {
            //TODO: make this work
            URL u = new URL(tempURL);
            con = (HttpURLConnection) u.openConnection();

            con.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            return sb.toString();


        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.disconnect();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }

    protected void onPostExecute(String result) {
        listener.onTaskCompleted(result);
    }
}