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

    public boolean getSuccess() throws JSONException{
        return json.getInt("success") == 1;
    }

    public String getMessage() throws JSONException {
        return json.getString("message");
    }

    public JSONArray getResults() throws JSONException {
        return json.getJSONArray("results");
    }

    public JSONArray getSpecificResults(int i) throws JSONException {
        return json.getJSONArray("results").getJSONArray(i);
    }
}
