package integratedproject.unilife_v1;

import org.json.JSONException;

/**
 * Created by Kieran Brown on 3/28/2018.
 */

public class User{
    private static String username;
    private static String firstName;
    private static String surname;
    private static String department;
    private static int privacyLevel;
    private static int colorScheme;
    private static boolean loggedIn;

    /*
    public static void createUser(JSONParser results) throws JSONException{
        setUsername(results.getString(0, "username"));
        setFirstName(results.getString(0, "firstName"));
        setSurname(results.getString(0, "surname"));
        setDepartment(results.getString(0, "department"));
        setPrivacyLevel(results.getInt(0, "privacyLevel"));
        setColorScheme(results.getInt(0, "colorScheme"));
        logIn();
    }
    */

    public static void setUsername(String un) {
        username = un;
    }

    public static String getUsername() {
        return username;
    }

    public static void setFirstName(String fn) {
        firstName = fn;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static void setSurname(String sn) {
        surname = sn;
    }

    public static String getSurname() {
        return surname;
    }

    public static void setDepartment(String dp) {
        department = dp;
    }

    public static String getDepartment() {
        return department;
    }

    public static void setPrivacyLevel(int pl) {
        privacyLevel = pl;
    }

    public static int getPrivacyLevel() {
        return privacyLevel;
    }

    public static void setColorScheme(int cs) {
        colorScheme = cs;
    }

    public static int getColorScheme() {
        return colorScheme;
    }

    public static  boolean isLoggedIn() {
        return loggedIn;
    }

    public static void logIn() {
        loggedIn = true;
    }

    public static void logOut() {
        loggedIn = false;
    }
}
