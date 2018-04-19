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

public class SearchFriendsActivity extends AppCompatActivity implements onTaskCompleted{
    private TextView search;
    private JSONParser results;
    private ListView people;
    private ArrayList<SearchDataModel> dataModel;
    private static SearchAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_friends);
        search = (TextView)findViewById(R.id.searchBox);
        people = (ListView) findViewById(R.id.people);
        dataModel = new ArrayList<>();

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //if something's been typed in the text box, get users with similar names
                if(s.length() > 0) {
                    Map map = new HashMap();
                    map.put("queryType", "getUsers");
                    map.put("searchQuery", s.toString());
                    new Database(SearchFriendsActivity.this).execute(map);
                }
            }
        });

        searchAdapter = new SearchAdapter(dataModel, getApplicationContext());
        people.setAdapter(searchAdapter);
        people.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SearchDataModel data = dataModel.get(position);

                //when an item is clicked on, try sending a friend request to that person
                Map map = new HashMap();
                map.put("queryType", "requestFriend");
                map.put("username", User.getUsername());
                map.put("friend", data.getUsername());
                new Database(SearchFriendsActivity.this).execute(map);
            }
        });
    }

    public void onTaskCompleted(String result) throws JSONException{
        results = new JSONParser(result);
        dataModel = new ArrayList<>();

        if(results.getQueryType().equals("getUsers")) {
            if (results.getSuccess()) {
                //if successfully got users, add them to the dataModel and display in the listView
                for (int i = 0; i < results.numOfResults(); i++) {
                    dataModel.add(new SearchDataModel(results.getString(i, "username"), results.getString(i, "firstName") + " " + results.getString(i, "surname"), results.getString(i, "department")));
                }
                searchAdapter = new SearchAdapter(dataModel, getApplicationContext());
                people.setAdapter(searchAdapter);

            } else {
                Toast.makeText(this, results.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else if(results.getQueryType().equals("requestFriend")) {
            if(results.getSuccess()) {
                //if successfully sent request, let the user know
                Toast.makeText(this, results.getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, results.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Server error. Please try again", Toast.LENGTH_SHORT).show();
        }

    }

}
