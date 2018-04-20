package integratedproject.unilife_v1;

/**
 * Created by Kieran Brown on 4/18/2018.
 */

public class ScheduleDataModel {
    private String username;
    private String name;
    private String availability;

    //custom dataModel for the listview for the calendar page
    public ScheduleDataModel(String username, String name, String availability) {
        this.username = username;
        this.name = name;
        this.availability = availability;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getAvailability() {
        return availability;
    }

}
