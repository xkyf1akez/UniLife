package integratedproject.unilife_v1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class MainScreenActivity extends AppCompatActivity implements onTaskCompleted{
    private ImageButton ib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        ib = (ImageButton)findViewById(R.id.imageButton);

        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), newEventActivity.class));
            }
        });
    }

    public void onTaskCompleted(String result) throws JSONException {
        JSONParser results = new JSONParser(result);
        //Toast.makeText(getApplicationContext(), Integer.toString(results.getInt(0, "privacyLevel")), Toast.LENGTH_SHORT).show();
    }
}
