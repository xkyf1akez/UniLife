package integratedproject.unilife_v1;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONException;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class newEventActivity extends AppCompatActivity implements onTaskCompleted{
    private TextView eventTitle;
    private Spinner eventCategory;
    private CheckBox allDay;
    private TextView startTimeTrigger;
    private TextView startDateTrigger;
    private TextView endTimeTrigger;
    private TextView endDateTrigger;
    private TextView location;
    private Button save;
    private CheckBox repeatedEvent;
    private TextView weeksToRepeat;

    private JSONParser results;

    private int startYear;
    private int startMonth;
    private int startDay;
    private int endYear;
    private int endMonth;
    private int endDay;

    private int startHour;
    private int startMinute;
    private int endHour;
    private int endMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(!User.isLoggedIn()) {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.newevent);

        eventTitle = (TextView)findViewById(R.id.titleEntry);
        eventCategory = (Spinner)findViewById(R.id.eventCategorySpinner);
        allDay = (CheckBox)findViewById(R.id.allDay);
        startTimeTrigger = (TextView)findViewById(R.id.startTimeTrigger);
        startDateTrigger = (TextView)findViewById(R.id.startDateTrigger);
        endTimeTrigger = (TextView)findViewById(R.id.endTimeTrigger);
        endDateTrigger = (TextView)findViewById(R.id.endDateTrigger);
        location = (TextView)findViewById(R.id.location);
        save = (Button)findViewById(R.id.save);
        repeatedEvent = (CheckBox)findViewById(R.id.repeatedEvent);
        weeksToRepeat = (TextView)findViewById(R.id.weeksToRepeat);


        startTimeTrigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar currentTime = Calendar.getInstance();
                int hour = currentTime.get(Calendar.HOUR_OF_DAY);
                int minute = currentTime.get(Calendar.MINUTE);
                TimePickerDialog timePicker = new TimePickerDialog(newEventActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        startTimeTrigger.setText(hour + ":" + minute);
                        startHour = hour;
                        startMinute = minute;
                    }
                }, hour, minute, true);
                timePicker.setTitle("Select time");
                timePicker.show();
            }
        });

        endTimeTrigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar currentTime = Calendar.getInstance();
                int hour = currentTime.get(Calendar.HOUR_OF_DAY);
                int minute = currentTime.get(Calendar.MINUTE);
                TimePickerDialog timePicker = new TimePickerDialog(newEventActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        endTimeTrigger.setText(hour + ":" + minute);
                        endHour = hour;
                        endMinute = minute;
                    }
                }, hour, minute, true);
                timePicker.setTitle("Select time");
                timePicker.show();
            }
        });

        startDateTrigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar currentDate = Calendar.getInstance();
                int year = currentDate.get(Calendar.YEAR);
                int month = currentDate.get(Calendar.MONTH);
                int day = currentDate.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePicker = new DatePickerDialog(newEventActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        startDateTrigger.setText(year + "-" + month + "-" + day);
                        startYear = year;
                        startMonth = month;
                        startDay = day;
                    }
                }, year, month, day);
                datePicker.setTitle("Select date");
                datePicker.show();
            }
        });

        endDateTrigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar currentDate = Calendar.getInstance();
                int year = currentDate.get(Calendar.YEAR);
                int month = currentDate.get(Calendar.MONTH);
                int day = currentDate.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePicker = new DatePickerDialog(newEventActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        endDateTrigger.setText(year + "-" + month + "-" + day);
                        endYear = year;
                        endMonth = month;
                        endDay = day;
                    }
                }, year, month, day);
                datePicker.setTitle("Select date");
                datePicker.show();
            }
        });

        repeatedEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(repeatedEvent.isChecked()) {
                    weeksToRepeat.setEnabled(true);
                } else {
                    weeksToRepeat.setEnabled(false);
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean validInputs = true;
                String error = "";

                String[] values = {
                        getValue(eventTitle),
                        eventCategory.getSelectedItem().toString(),
                        getValue(startTimeTrigger),
                        getValue(startDateTrigger),
                        getValue(endTimeTrigger),
                        getValue(endDateTrigger),
                        getValue(location)
                };

                for(int i = 0; i < values.length; i++) {
                    if(values[i] == null || values[i].isEmpty()) {
                        validInputs = false;
                        i = values.length;
                        error = "Please fill in all fields";
                    }
                }

                if((endYear < startYear) || (endMonth < startMonth) || (endDay < startDay)) {
                    validInputs = false;
                    error = "End date must be after the start";
                } else if((endYear == startYear) && (endMonth == startMonth) && (endDay == startDay)){
                    if(endHour < startHour) {
                        validInputs = false;
                        error = "End time must be after the start";
                    } else if(endHour == startHour) {
                        if(endMinute < startMinute) {
                            validInputs = false;
                            error = "End time must be after the start";
                        }
                    }
                }

                if(validInputs) {
                    Map map = new HashMap();
                    map.put("queryType", "addEvent");
                    map.put("title", values[0]);
                    map.put("category", values[1]);
                    map.put("startTime", values[2]);
                    map.put("startDate", values[3]);
                    map.put("endTime", values[4]);
                    map.put("endDate", values[5]);
                    map.put("location", values[6]);
                    map.put("createdBy", User.getUsername());
                    map.put("allDay", allDay.isChecked() ? 1 : 0);
                    map.put("repeatedEvent", repeatedEvent.isChecked() ? 1 : 0);
                    map.put("weeksToRepeat", getValue(weeksToRepeat));

                    new Database(newEventActivity.this).execute(map);
                } else {
                    Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private String getValue(TextView textField) {
        return textField.getText().toString();
    }

    public void onTaskCompleted(String result) throws JSONException{
        results = new JSONParser(result);
        if(results.getSuccess()) {
            Toast.makeText(getApplicationContext(), "Successfully registered event", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainScreenActivity.class));
            //need to change to whatever page allows you to make a new event
        } else {
            Toast.makeText(getApplicationContext(), results.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
