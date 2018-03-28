package integratedproject.unilife_v1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements onTaskCompleted {
    private JSONParser results;
    private Button register;
    private Button login;
    private TextView username;
    private TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button)findViewById(R.id.login);
        register = (Button)findViewById(R.id.signup);
        username = (TextView)findViewById(R.id.username);
        password = (TextView)findViewById(R.id.password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean validInput = true;

                String[] values = {getValue(username), getValue(password)};

                for(int i = 0; i < values.length; i++) {
                    if(values[i] == null || values[i].isEmpty()) {
                        validInput = false;
                        i = values.length;
                    }
                }

                if(validInput) {
                    Map map = new HashMap();
                    map.put("queryType", "login");
                    map.put("username", values[0]);
                    map.put("password", values[1]);

                    new Database(LoginActivity.this).execute(map);
                } else {
                    Toast.makeText(LoginActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(v.getContext(), RegistrationActivity.class);
                startActivity(in);
            }
        });
    }

    private String getValue(TextView textField) {
        return textField.getText().toString();
    }

    public void onTaskCompleted(String result) throws JSONException{

        results = new JSONParser(result);
        if(results.getSuccess()) {
            Toast.makeText(getApplicationContext(), "Successfully logged in", Toast.LENGTH_SHORT).show();
            //Intent intent = new Intent(this, mainscreen.class);
            //startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), results.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}