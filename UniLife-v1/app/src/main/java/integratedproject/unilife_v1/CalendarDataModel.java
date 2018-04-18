package integratedproject.unilife_v1;

/**
 * Created by Kieran Brown on 4/18/2018.
 */

public class CalendarDataModel {
    private String title;
    private String time;
    private String location;
    private String category;
    private String attending;

    //custom dataModel for the listview for the calendar page
    public CalendarDataModel(String title, String time, String location, String category, String attending) {
        this.title = title;
        this.time = time;
        this.location = location;
        this.category = category;
        this.attending = attending;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public String getCategory() {
        return category;
    }

    public String getAttending() {
        return attending;
    }

}
