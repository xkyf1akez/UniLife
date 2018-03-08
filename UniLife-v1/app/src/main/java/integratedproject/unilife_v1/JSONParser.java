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

    public int NumOfResults() throws JSONException {
        return getResults().length();
    }

    public String getString(int index, String name) throws JSONException {
        return new JSONObject(getResults().getString(index)).getString(name);
    }
}
