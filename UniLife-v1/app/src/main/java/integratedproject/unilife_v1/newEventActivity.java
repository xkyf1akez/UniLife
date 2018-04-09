package integratedproject.unilife_v1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

public class newEventActivity extends AppCompatActivity {
    private TextView eventTitle;
    private Spinner eventCategory;
    private CheckBox allDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newevent);
    }

}
