package integratedproject.unilife_v1;

import org.json.JSONException;

/**
 * Created by Kieran Brown on 3/4/2018.
 * Description: Interface that must be implemented by any activities wanting to
 * use database connectivity. The parameter is the results of the database
 * query. To use, execute the Database class with your HashMap of parameters
 * (at minimum the query type), and passing itself as a parameter and then this
 * function will provide the results
 */

//TODO: Make work with JSON object(s) instead
public interface onTaskCompleted {
    void onTaskCompleted(String result) throws JSONException;
}
