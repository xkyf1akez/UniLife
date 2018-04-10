package integratedproject.unilife_v1;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Kieran Brown on 3/5/2018.
 */

public class JSONParser{
    private JSONObject json;

    public JSONParser(String results) throws JSONException{
        json = new JSONObject(results);
    }

    //returns if the query was successful or not
    public boolean getSuccess() throws JSONException{
        return json.getInt("success") == 1;
    }

    //returns the success/error message
    public String getMessage() throws JSONException {
        return json.getString("message");
    }

    public String getQueryType() throws JSONException {
        return json.getString("queryType");
    }

    //returns all results as a string (ignore)
    public JSONArray getResults() throws JSONException {
            return json.getJSONArray("results");
    }

    //returns number of results for iteration
    public int numOfResults() throws JSONException {
        return getResults().length();
    }

    //returns a specific string at a given index
    public String getString(int index, String name) throws JSONException {
        return getResults().getJSONObject(index).getString(name);
    }

    public String objectToString(int index) throws JSONException {
        return getResults().getJSONObject(index).toString();
    }

    //returns a specific int at a given index
    public int getInt(int index, String name) throws JSONException {
        try {
            return getResults().getJSONObject(index).getInt(name);
        } catch(NumberFormatException e) {
            Log.d("number format", "Exception", e);
            return 0;
        }
    }
}
