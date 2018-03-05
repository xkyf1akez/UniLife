package integratedproject.unilife_v1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements onTaskCompleted {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Map map = new HashMap();
        map.put("queryType", "getAllUsers");
        new Database(this).execute(map);
    }

    public void onTaskCompleted(String result) {
        TextView tv = (TextView) findViewById(R.id.Title);
        tv.setText(result);
        Log.d("mainResults", result);
    }

}