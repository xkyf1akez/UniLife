package integratedproject.unilife_v1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
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
    private static SearchAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_friends);
        people = (ListView) findViewById(R.id.people);
        Map map = new HashMap();
        map.put("queryType", "getFriendRequests");
        map.put("Username", "aName"); //TO DO: get user's name
        new Database(SearchFriendsActivity.this).execute(map);
    }

    public void onTaskCompleted(String result) throws JSONException{
        results = new JSONParser(result);
        dataModel = new ArrayList<>();

        if(results.getSuccess()) {
            for(int i = 0; i < results.numOfResults(); i++) {
                dataModel.add(new SearchDataModel(results.getString(i, "username"), results.getString(i, "firstName") + " " + results.getString(i, "surname"), results.getString(i, "department")));
            }
            searchAdapter = new SearchAdapter(dataModel, getApplicationContext());
            people.setAdapter(searchAdapter);
        } else {
            Toast.makeText(this, results.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

}
