
package integratedproject.unilife_v1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity implements onTaskCompleted {
    private JSONParser results;
    private Button register;
    private TextView getEmail;
    private TextView getPassword;
    private TextView getConfirmation;
    private TextView getForename;
    private TextView getSurname;
    private TextView getDepartment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup );
        register = (Button)findViewById(R.id.button4);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String details = getDetails();
                onTaskCompleted(details);
                Map map = new HashMap();
                map.put("register", "getAllUsers");
                new Database(this).execute(map);
            }
        });
    }

    public String getDetails(){
        getEmail = (TextView)findViewById(R.id.editText5);
        String email = getEmail.getText().toString();
        getPassword = (TextView)findViewById(R.id.editText8);
        String password = getPassword.getText().toString();
        getConfirmation = (TextView)findViewById(R.id.editText2);
        String confirmation = getConfirmation.getText().toString();
        getForename = (TextView)findViewById(R.id.editText2);
        String forename = getForename.getText().toString();
        getSurname = (TextView)findViewById(R.id.editText6);
        String surname = getSurname.getText().toString();
        getDepartment = (TextView)findViewById(R.id.editText6);
        String department = getDepartment.getText().toString();
        String details = email + " " + password + " " + confirmation + " " + forename + " " + surname + " " + department;
        return details;
    }

    public void onTaskCompleted(String result) throws JSONException{
        TextView tv = (TextView) findViewById(R.id.Title);

        results = new JSONParser(result);
        Log.d("mainResults", result);
        Log.d("test", "test");
        Log.d("json string test", results.getMessage());
        Log.d("JSON Array test", results.getString(0, "userName"));
        Log.d("array numbers", results.numOfResults() + ".");
        //TODO: make it not think each inner array is a string. Needs to be converted to JSONArray then back or some shit
    }

}

