package integratedproject.unilife_v1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class MainScreenActivity extends AppCompatActivity implements onTaskCompleted{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        //Toast.makeText(getApplicationContext(), User.getPrivacyLevel(), Toast.LENGTH_SHORT).show();
        Map map = new HashMap();
        map.put("queryType", "getAllUsers");
        new Database(this).execute(map);
    }

    public void onTaskCompleted(String result) throws JSONException {
        JSONParser results = new JSONParser(result);
        Toast.makeText(getApplicationContext(), results.getInt(6, "privacyLevel"), Toast.LENGTH_SHORT).show();
    }
}
