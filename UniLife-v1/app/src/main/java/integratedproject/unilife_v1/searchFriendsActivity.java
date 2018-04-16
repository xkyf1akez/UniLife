
package integratedproject.unilife_v1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
private RecyclerView friendsList;
private RecyclerView.Adapter friendsListAdapter;
private RecyclerView.LayoutManager friendsListLayout;

import android.widget.ArrayAdapter;  
import android.widget.ListView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class SearchFriendsActivity extends AppCompatActivity implements onTaskCompleted {
    private JSONParser results;
    private TextView search;
    private ListView friendsList;
    private ArrayAdapter<String> listAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.SearchFriends); //TO DO: page name unknown atm

        search = (TextView)findViewById(R.id.search); //TO DO: id unknown atm
        friendsList = (ListView)findViewById(R.id.friendsList); //TO DO: id unknown atm
        friendsListAdapter = new MyAdapter();
        friendsList.setAdapter(friendsListAdapter);
        
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override    
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override    
            public void onTextChanged(CharSequence s, int start, int before, int count){
                Map map = new HashMap();
                map.put("queryType", "getUsers");
                map.put("searchQuery", getDetails(search));
                new Database(SearchFriendsActivity.this).execute(map);
          }
        });
        
        });
    }

    //gets user input from appropriate textbox
    private String getDetails(TextView tv){
        return tv.getText().toString();
    }

    public void onTaskCompleted(String result) throws JSONException{
        results = new JSONParser(result);
        int numResults = results.numOfResults();
        String[] resultsList = new String [numResults];
        for (int x = 0; x<numResults; x++){
            resultsList[x] = results.getString(x, result)
        }
        //TO DO: display results
        //TO DO: pass items to list view, add friend on click
        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
        }
        
        if(results.getSuccess()) {
            Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            Toast.makeText(getApplicationContext(), results.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}

