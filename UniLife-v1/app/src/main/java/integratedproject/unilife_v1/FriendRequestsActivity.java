package integratedproject.unilife_v1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FriendRequestsActivity extends AppCompatActivity implements onTaskCompleted{
    private JSONParser results;
    private ListView people;
    private ArrayList<SearchDataModel> dataModel;
    private static SearchAdapter requestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_requests); //TO DO: find XML name
        people = (ListView) findViewById(R.id.requests);
        dataModel = new ArrayList<>();

        getUsers();
        
        requestAdapter = new SearchAdapter(dataModel, getApplicationContext());
        people.setAdapter(requestAdapter);

        people.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SearchDataModel data = dataModel.get(position);

                Toast.makeText(FriendRequestsActivity.this, "Friend Request Sent", Toast.LENGTH_SHORT).show();
                //when an item is clicked on, try sending a friend request to that person
                Map map = new HashMap();
                map.put("queryType", "respondFriendship");
                map.put("username", User.getUsername());
                map.put("friend", data.getUsername());
                map.put("responseType", 1);
                new Database(FriendRequestsActivity.this).execute(map);
            }
        });
    }

    private void getUsers() {
        Map map = new HashMap();
        map.put("queryType", "getFriendRequests");
        map.put("username", User.getUsername());
        new Database(FriendRequestsActivity.this).execute(map);
    }

    public void onTaskCompleted(String result) throws JSONException{
        results = new JSONParser(result);
        dataModel = new ArrayList<>();

        if(results.getQueryType().equals("getFriendRequests")) {
            if(results.numOfResults() > 0) {
                for (int i = 0; i < results.numOfResults(); i++) {
                    dataModel.add(new SearchDataModel(results.getString(i, "username"), results.getString(i, "firstName") + " " + results.getString(i, "surname"), results.getString(i, "department")));
                }
            } else {
                Toast.makeText(this, results.getMessage(), Toast.LENGTH_SHORT).show();
            }
            requestAdapter = new SearchAdapter(dataModel, getApplicationContext());
            people.setAdapter(requestAdapter);
        } else if(results.getQueryType().equals("respondFriendship")) {
            Toast.makeText(this, results.getMessage(), Toast.LENGTH_SHORT).show();
            if(results.getSuccess()) {
                getUsers();
            }
        } else {
            Toast.makeText(this, "Internal server error, please try again", Toast.LENGTH_SHORT).show();
        }
    }

}
