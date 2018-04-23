package integratedproject.unilife_v1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.MonthDisplayHelper;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

public class MainScreenActivity extends AppCompatActivity implements onTaskCompleted{
    private ImageButton settings;
    private ImageButton calender;
    private ImageButton friends;
    private ImageButton searchFriends;

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /*
        //TODO: FIX THE FOLLOWING CODE [splash screen]
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent SplashIntent = new Intent(getApplicationContext(),SplashActivity.class);
                startActivity(SplashIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
        //TODO: FIX THE ABOVE CODE
        */

        if(!User.isLoggedIn()) {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            //returns user to login page if not logged in
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);


        settings = (ImageButton)findViewById(R.id.settings);
        calender = (ImageButton)findViewById(R.id.calender);
        friends = (ImageButton)findViewById(R.id.friends);
        searchFriends = (ImageButton)findViewById(R.id.searchFriends);

        friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), FriendsActivity.class));
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), settingsActivity.class));
            }
        });

        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), CalendarActivity.class));
            }
        });

        searchFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), SearchFriendsActivity.class));
            }
        });


    }

    public void onTaskCompleted(String result) throws JSONException {
        JSONParser results = new JSONParser(result);
    }
}
