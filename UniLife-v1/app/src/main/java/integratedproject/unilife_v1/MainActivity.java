package integratedproject.unilife_v1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        Database db = new Database();
        Map testMap = new HashMap();

        TextView tv = (TextView) findViewById(R.id.tvTest);
        db.runQuery("getAllUsers", testMap);
        tv.setText(db.getResults());
    }

}
