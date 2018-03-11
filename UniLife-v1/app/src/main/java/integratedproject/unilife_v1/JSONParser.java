package integratedproject.unilife_v1;

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

    //returns all results as a string (ignore)
    private JSONArray getResults() throws JSONException {
        return json.getJSONArray("results");
    }

    //returns number of results for iteration
    public int numOfResults() throws JSONException {
        return getResults().length();
    }

    //returns a specific value at a given index
    public String getString(int index, String name) throws JSONException {
        return new JSONObject(getResults().getString(index)).getString(name);
    }
}
