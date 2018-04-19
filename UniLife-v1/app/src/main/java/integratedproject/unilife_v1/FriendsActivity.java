package integratedproject.unilife_v1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FriendsActivity extends AppCompatActivity implements onTaskCompleted{
    private ListView friends;
    private JSONParser results;
    private ArrayList<SearchDataModel> dataModel;
    private static SearchAdapter friendsAdapter;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        friends = (ListView)findViewById(R.id.friends);
        fab = (FloatingActionButton)findViewById(R.id.fab);
        dataModel = new ArrayList<>();

        Map map = new HashMap();
        map.put("queryType", "getFriends");
        map.put("username", User.getUsername());
        new Database(this).execute(map);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FriendsActivity.this, FriendRequestsActivity.class));
            }
        });

    }

    public void onTaskCompleted(String result) throws JSONException {
        results = new JSONParser(result);

        if(results.getSuccess()) {
            if(results.numOfResults() > 0) {
                for(int i = 0; i < results.numOfResults(); i++) {
                    dataModel.add(new SearchDataModel(results.getString(i, "username"), results.getString(i, "name"), results.getString(i, "department")));
                }
                friendsAdapter = new SearchAdapter(dataModel, getApplicationContext());
                friends.setAdapter(friendsAdapter);
            }
        } else {
            Toast.makeText(this, results.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
