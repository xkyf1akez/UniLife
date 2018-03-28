
package integratedproject.unilife_v1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity implements onTaskCompleted {
    private JSONParser results;
    private Button register;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        register = (Button)findViewById(R.id.registerButton);
        login = (Button)findViewById(R.id.loginButton);

        //returns user to previous page
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), LoginActivity.class));
            }
        });

        //sets up listener for registering
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            //extracts values from textboxes and puts them through hash map with their appropriate key
            //before passing it to the database thread
            public void onClick(View v) {
                Boolean validInputs = true;
                String error = "";

                String[] values = { getDetails(R.id.emailField) , getDetails(R.id.password) , getDetails(R.id.confirmPassword),
                        getDetails(R.id.firstName) , getDetails(R.id.surname) , getDetails(R.id.department) };

                //ensures all user inputs are valid
                for (int x = 0; x < values.length; x++){
                    if(values[x] == null || values[x].isEmpty()) {
                        validInputs = false;
                        x = values.length;
                        error = "please fill in all fields";
                    }
                }
                //password confirmation
                //TODO: more thorough validation on inputted text
                if (!values[1].equals(values[2])) {
                    validInputs = false;
                    error = "Passwords are not equal";
                }

                CheckBox tc = (CheckBox) findViewById(R.id.tcCheckbox);

                if(!tc.isChecked()) {
                    validInputs = false;
                    error = "Please agree to the terms and conditions";
                }

                if (validInputs) {
                    Map map = new HashMap();
                    map.put("queryType", "insertUser");
                    map.put("username", values[0]);
                    map.put("password", values[1]);
                    map.put("firstName", values[3]);
                    map.put("surname", values[4]);
                    map.put("department", values[5]);

                    new Database(RegistrationActivity.this).execute(map);
                } else {
                    Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //gets user input from appropriate textbox
    private String getDetails(int inputID){
        TextView getValue = (TextView)findViewById(inputID);
        return getValue.getText().toString();
    }

    public void onTaskCompleted(String result) throws JSONException{

        results = new JSONParser(result);
        if(results.getSuccess()) {
            Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            Toast.makeText(getApplicationContext(), results.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
