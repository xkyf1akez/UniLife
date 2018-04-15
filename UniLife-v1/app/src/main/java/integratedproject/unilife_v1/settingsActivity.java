package integratedproject.unilife_v1;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class settingsActivity extends AppCompatActivity implements onTaskCompleted{
    private JSONParser results;
    private Switch notifications;
    private Switch privacy;
    private Button save;
    private Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        notifications = (Switch)findViewById(R.id.notificationSwitch);
        privacy = (Switch)findViewById(R.id.privacySwitch);
        save = (Button)findViewById(R.id.save);
        delete = (Button)findViewById(R.id.delete);

        //1 means notifications are enabled, or sharing is enabled
        if(User.getNotificationLevel() == 1) {
            notifications.setChecked(true);
        } else {
            notifications.setChecked(false);
        }

        if(User.getPrivacyLevel() == 1) {
            privacy.setChecked(true);
        } else {
            privacy.setChecked(false);
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map map = new HashMap();
                map.put("queryType", "updateDetails");
                map.put("privacy", privacy.isChecked() ? 1 : 0);
                map.put("notifications", notifications.isChecked() ? 1 : 0);
                map.put("username", User.getUsername());

                new Database(settingsActivity.this).execute(map);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map map = new HashMap();
                map.put("queryType", "delete");
                map.put("username", User.getUsername());

                new Database(settingsActivity.this).execute(map);
            }
        });
    }

    public void onTaskCompleted(String result) throws JSONException{
        results = new JSONParser(result);

        if(results.getQueryType().equals("updateDetails")) {
            if(results.getSuccess()) {
                Toast.makeText(getApplicationContext(), "Successfully updated", Toast.LENGTH_SHORT).show();
                User.setPrivacyLevel(privacy.isChecked() ? 1 : 0);
                User.setNotificationLevel(notifications.isChecked() ? 1 : 0);
            } else {
                Toast.makeText(getApplicationContext(), results.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else if(results.getQueryType().equals("delete")) {
            if(results.getSuccess()) {
                Toast.makeText(getApplicationContext(), "Successfully deleted", Toast.LENGTH_SHORT).show();
                User.delete();
                startActivity(new Intent(getApplicationContext(), MainScreenActivity.class));
            }
        }
    }

}
