package integratedproject.unilife_v1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements onTaskCompleted {
    private JSONParser results;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register = (Button)findViewById(R.id.button2);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(v.getContext(), RegistrationActivity.class);
                startActivity(in);
            }
        });
    }

    public void onTaskCompleted(String result) throws JSONException{

        results = new JSONParser(result);
        Log.d("mainResults", result);
        Log.d("test", "test");
        Log.d("json string test", results.getMessage());
        Log.d("JSON Array test", results.getString(0, "userName"));
        Log.d("array numbers", results.numOfResults() + ".");
    }

}