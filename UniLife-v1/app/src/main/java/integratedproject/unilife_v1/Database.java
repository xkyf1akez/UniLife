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

import java.util.Map;

/**
 * Created by Kieran Brown on 3/4/2018.
 */
// TODO: Tidy this class up
// TODO: Convert to JSON object(s)

public class Database extends AsyncTask<Map, Void, String>{
    private final String requestURL = "http://unilife.kieranbrown.me/?query=";
    private onTaskCompleted listener;

    //Takes in the calling object so it can pass back results
    public Database(onTaskCompleted listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Map... values) {
        HttpURLConnection con = null;
        String tempURL = requestURL + values[0].get("queryType");
        try {
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
            toJSON(sb.toString());
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

    protected void toJSON(String json) {
        try {
            Log.d("JSON Success", new JSONObject(json).getJSONArray("results").getString(0).getString("username"));
        } catch(JSONException e) {

        }
    }

    @Override
    protected void onPostExecute(String result) {
        listener.onTaskCompleted(result);
    }
}