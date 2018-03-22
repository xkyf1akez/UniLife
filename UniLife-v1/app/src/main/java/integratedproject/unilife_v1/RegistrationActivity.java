
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
import java.util.function.BooleanSupplier;

public class RegistrationActivity extends AppCompatActivity implements onTaskCompleted {
    private JSONParser results;
    private Button register;
    private TextView getValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup );
        register = (Button)findViewById(R.id.button4);
        Database dbThread = new Database(this);

        //sets up listener for button
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            //extracts values from textboxes and puts them through hash map with their appropriate key
            //before passing it to the database thread
            public void onClick(View v) {
                Boolean validInputs = true;
                //                {userName, password, confirmation, firstname, surname, department}
                String[] values = { getDetails(R.id.editText5) , getDetails(R.id.editText8) , getDetails(R.id.editText2),
                        getDetails(R.id.editText4) , getDetails(R.id.editText6) , getDetails(R.id.editText7) };
                //ensures all user inputs are valid
                for (int x; x < 6; x++){
                    if(values[x] == null || values[x].isEmpty()) {
                        validInputs = false;
                        x = 6;
                    }
                }
                //password confirmation
                //TODO: more thorough validation on inputted text
                if (values[1] != values[2]) {
                    validInputs = false;
                }
                if (validInputs == true) {
                    Map map = new HashMap();
                    map.put("userName", values[0]);
                    map.put("password", values[1]);
                    map.put("firstName", values[3]);
                    map.put("surname", values[4]);
                    map.put("department", values[5]);
                    dbThread.execute(map);
                }
                //TODO: print that not all input was valid
            }
        });
    }

    //gets user input from appropriate textbox
    public String getDetails(int inputID){
        getValue = (TextView)findViewById(inputID);
        String value = getValue.getText().toString();
        return value;
    }

    public void onTaskCompleted(String result) throws JSONException{
        TextView tv = (TextView) findViewById(R.id.Title);

        results = new JSONParser(result);
        if(results.getSuccess()) {
            //PRINT SUCCESS
            //GO BACK TO HOME PAGE
        } else {
            //PRINT results.getMessage()
        }
    }

}
