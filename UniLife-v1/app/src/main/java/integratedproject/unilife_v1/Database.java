package integratedproject.unilife_v1;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.lang.StringBuilder;

/*
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
*/

public class Database {
    private String results = "";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    private static final String TAG_USERS = "users";
    private static final String TAG_USERNAME = "userName";
    private static final String TAG_PASSWORD = "password";
    private static final String TAG_FIRSTNAME = "firstName";
    private static final String TAG_SURNAME = "surname";
    private static final String TAG_DEPARTMENT = "department";
    private static final String TAG_PRIVACY_LEVEL = "privacyLevel";
    private static final String TAG_COLOR_SCHEME = "colorScheme";

    private String requestURL = "http://unilife.kieranbrown.me/?query=";

    public static String getJSONString(String url) {
        HttpURLConnection con = null;
        try {
            URL u = new URL(url);
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

    public String getResults() {
        return results;
    }

    public void setResults(String input) {
        results = input;
    }

    public boolean runQuery(String queryType, Map values) {
        Iterator i = values.entrySet().iterator();
        StringBuilder url = new StringBuilder(requestURL);
        url.append(queryType);

        while(i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            url.append("&" + me.getKey() + "=" + me.getValue());
        }

        setResults(getJSONString(url.toString()));
        return true;
    }

    public boolean insertUser(Map values) {
        if(values.size() > 0) {
            return runQuery("insertUser", values);
        }
        return false;
    }

    public boolean requestFriend(Map values) {
        if(values.size() > 0) {
            return runQuery("requestFriend", values);
        }
        return false;
    }

    public boolean respondToRequest(Map values) {
        if(values.size() > 0) {
            return runQuery("respondFriendship", values);
        }
        return false;
    }

    private class getJSON extends AsyncTask<HashMap, Void, String> {

        @Override
        protected String doInBackground(HashMap... values) {
            HttpURLConnection con = null;
            String url = (String)values.get("url");
            String queryType = values.get("query");
            try {
                //TODO: put URL and query type into hashmap and make this work
                URL u = new URL(url);
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
    }

}

