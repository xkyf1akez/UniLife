package integratedproject.unilife_v1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

public class MainScreenActivity extends AppCompatActivity implements onTaskCompleted{
    private ImageButton settings;
    private ImageButton today;
    private ImageButton calender;
    private ImageButton friends;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(!User.isLoggedIn()) {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        settings = (ImageButton)findViewById(R.id.settings);
        today = (ImageButton)findViewById(R.id.todayEvents);
        calender = (ImageButton)findViewById(R.id.calender);
        friends = (ImageButton)findViewById(R.id.friends);
        title = (TextView)findViewById(R.id.Title);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), settingsActivity.class));
            }
        });

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), SearchFriendsActivity.class));
            }
        });

        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), CalendarActivity.class));
            }
        });


    }

    public void onTaskCompleted(String result) throws JSONException {
        JSONParser results = new JSONParser(result);
    }
}
