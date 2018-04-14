package integratedproject.unilife_v1;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by Kieran Brown on 3/4/2018.
 */

public class Database extends AsyncTask<Map, Void, String>{
    private final String requestURL = "http://unilife.kieranbrown.me/?";
    private onTaskCompleted listener;

    //Takes in the calling object so it can pass back results
    public Database(onTaskCompleted listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Map... values) {
        HttpURLConnection con = null;
        Iterator i = values[0].entrySet().iterator();
        StringBuilder url = new StringBuilder(requestURL);

        while(i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            url.append("&" + me.getKey() + "=" + me.getValue());
        }

        Log.d("query", url.toString());

        try {
            URL u = new URL(url.toString());
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

    @Override
    protected void onPostExecute(String result){
        try {
            listener.onTaskCompleted(result);
        } catch (JSONException e) { }
    }
}