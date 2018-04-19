package integratedproject.unilife_v1;

/**
 * Created by Kieran Brown on 4/18/2018.
 */

public class SearchDataModel {
    private String username;
    private String name;
    private String department;

    //holds data for each item in a list
    public SearchDataModel(String username, String name, String department) {
        this.username = username;
        this.name = name;
        this.department = department;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }
}
