package integratedproject.unilife_v1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner ;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Database db = new Database();
        Map testMap = new HashMap();

        TextView tv = (TextView) findViewById(R.id.Title);
        db.runQuery("getAllUsers", testMap);
        //tv.setText(db.getResults());
        //tv.setText("Results");

    }
}

