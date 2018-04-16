
package integratedproject.unilife_v1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
private RecyclerView friendsList;
private RecyclerView.Adapter friendsListAdapter;
private RecyclerView.LayoutManager friendsListLayout;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.SearchFriends); //TO DO: page name unknown atm

        search = (TextView)findViewById(R.id.search); //TO DO: id unknown atm
        friendsList = (RecyclerView)findViewById(R.id.friendsList); //TO DO: id unknown atm
         
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
        
        //TO DO: set up listener for friend request

            public void onClick(View v) {
              Toast.makeText(getApplicationContext(), "Friend Request Sent", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //gets user input from appropriate textbox
    private String getDetails(TextView tv){
        return tv.getText().toString();
    }

    public void onTaskCompleted(String result) throws JSONException{
        //TO DO: display results
        results = new JSONParser(result);
        int numResults = results.numOfResults();
        String[] resultsList = new String [numResults];
        for (int x = 0; x<numResults; x++){
            resultsList[x] = results.getString(x, result)
        }
        
        if(results.getSuccess()) {
            Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            Toast.makeText(getApplicationContext(), results.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}

