package integratedproject.unilife_v1;

/**
 * Created by OllieLewis on 05/03/2018.
 */
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.util.Log;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalendarActivity extends AppCompatActivity implements onTaskCompleted{

    private CalendarView mCalendarView;
    private JSONParser results;
    private ListView events;
    private ArrayList<CalendarDataModel> dataModel;
    private static CalendarAdaptor calendarAdaptor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstantState) {
        super.onCreate(savedInstantState);
        setContentView(R.layout.calendar_layout);
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        events = (ListView)findViewById(R.id.events);

        DateFormat yearFormat = new SimpleDateFormat("yyyy");
        DateFormat monthFormat = new SimpleDateFormat("MM");
        DateFormat dayFormat = new SimpleDateFormat("dd");
        Date date = Calendar.getInstance().getTime();

        getEvents(Integer.parseInt(yearFormat.format(date)), Integer.parseInt(monthFormat.format(date)) - 1, Integer.parseInt(dayFormat.format(date)));

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int day) {
                getEvents(year, month, day);
            }
        });
    }

    private void getEvents(int year, int month, int day) {
        String date = year + "-" + (month + 1) + "-" + day;
        Map map = new HashMap();
        map.put("queryType", "getEvents");
        map.put("username", User.getUsername());
        map.put("date", date);
        new Database(CalendarActivity.this).execute(map);
    }

    public void onTaskCompleted(String result) throws JSONException{
        results = new JSONParser(result);
        dataModel = new ArrayList<>();
        if(results.getSuccess()) {
            for (int i = 0; i < results.numOfResults(); i++) {
                dataModel.add(new CalendarDataModel(results.getString(i, "title"), results.getString(i, "startTime").substring(0,5) + " - " + results.getString(i, "endTime").substring(0,5), results.getString(i, "location"), results.getString(i, "category"), results.getString(i, "status")));
            }
            calendarAdaptor = new CalendarAdaptor(dataModel, getApplicationContext());
            events.setAdapter(calendarAdaptor);

        } else {
            Toast.makeText(getApplicationContext(), results.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
