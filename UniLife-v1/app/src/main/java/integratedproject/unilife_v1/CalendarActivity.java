package integratedproject.unilife_v1;

/**
 * Created by OllieLewis on 05/03/2018.
 */
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.util.Log;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;
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
    private FloatingActionButton newEvent;
    private ArrayList<CalendarDataModel> dataModel;
    private ArrayList<ScheduleDataModel> friendsDataModel;
    private static CalendarAdaptor calendarAdaptor;
    private static CalendarFriendsAdaptor calendarFriendsAdaptor;

    private int day;
    private int month;
    private int year;

    private boolean showFriendSchedules = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstantState) {
        super.onCreate(savedInstantState);
        setContentView(R.layout.calendar_layout);
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        events = (ListView)findViewById(R.id.events);
        newEvent = (FloatingActionButton)findViewById(R.id.newEventFAB);

        DateFormat yearFormat = new SimpleDateFormat("yyyy");
        DateFormat monthFormat = new SimpleDateFormat("MM");
        DateFormat dayFormat = new SimpleDateFormat("dd");
        Date date = Calendar.getInstance().getTime();
        //gets current date

        getEvents(Integer.parseInt(yearFormat.format(date)), Integer.parseInt(monthFormat.format(date)) - 1, Integer.parseInt(dayFormat.format(date)));
        //gets events for current date

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int day) {
                getEvents(year, month, day);
                //gets events for selected page
            }
        });

        newEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CalendarActivity.this, newEventActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.calendar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //runs code when menu item is selected
        switch(item.getItemId()) {
            case R.id.toggleEvents:
                if(!showFriendSchedules) {
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

                    Map map = new HashMap();
                    map.put("queryType", "getFriendSchedules");
                    map.put("username", User.getUsername());
                    map.put("date", year + "-" + month + "-" + day);
                    map.put("time", sdf.format(Calendar.getInstance().getTime()));
                    new Database(this).execute(map);
                    showFriendSchedules = true;
                } else {
                    getEvents(year, month - 1, day);
                    showFriendSchedules = false;
                }
                return true;

            default:
                return true;
        }
    }

    private void getEvents(int year, int month, int day) {
        String date = year + "-" + (month + 1) + "-" + day;
        this.year = year;
        this.month = month + 1;
        this.day = day;
        Map map = new HashMap();
        map.put("queryType", "getEvents");
        map.put("username", User.getUsername());
        map.put("date", date);
        //formats date and gets events on that day
        new Database(CalendarActivity.this).execute(map);
    }

    public void onTaskCompleted(String result) throws JSONException{
        results = new JSONParser(result);
        dataModel = new ArrayList<>();
        friendsDataModel = new ArrayList<>();

        if(results.getQueryType().equals("getEvents")) {
            if (results.getSuccess()) {
                for (int i = 0; i < results.numOfResults(); i++) {
                    //adds all event details to a dataModel and displays it in the listView
                    dataModel.add(new CalendarDataModel(results.getString(i, "title"), results.getString(i, "startTime").substring(0, 5) + " - " + results.getString(i, "endTime").substring(0, 5), results.getString(i, "location"), results.getString(i, "category"), results.getString(i, "status")));
                }
                calendarAdaptor = new CalendarAdaptor(dataModel, getApplicationContext());
                events.setAdapter(calendarAdaptor);

            } else {
                Toast.makeText(getApplicationContext(), results.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else if(results.getQueryType().equals("getFriendSchedules")) {
            if(results.getSuccess()) {
                for(int i = 0; i < results.numOfResults(); i++) {
                    friendsDataModel.add(new ScheduleDataModel(results.getString(i, "username"), results.getString(i, "name"), results.getString(i, "availability")));
                }
                calendarFriendsAdaptor = new CalendarFriendsAdaptor(friendsDataModel, getApplicationContext());
                events.setAdapter(calendarAdaptor);
            } else {
                Toast.makeText(getApplicationContext(), results.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Internal server error, please try again", Toast.LENGTH_SHORT).show();
        }
    }
}
