package integratedproject.unilife_v1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements onTaskCompleted {
    private JSONParser results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Map map = new HashMap();
        map.put("queryType", "getAllUsers");
        new Database(this).execute(map);
    }

    public void onTaskCompleted(String result) throws JSONException{
        TextView tv = (TextView) findViewById(R.id.Title);

        results = new JSONParser(result);

        tv.setText(result);
        Log.d("mainResults", result);
        Log.d("test", "test");
        Log.d("json string test", results.getMessage());
        Log.d("JSON Array test", results.getString(0, "userName"));
        Log.d("array numbers", results.NumOfResults() + ".");
        //TODO: make it not think each inner array is a string. Needs to be converted to JSONArray then back or some shit
    }

}