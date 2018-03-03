package integratedproject.unilife_v1;

import java.util.Map;
import java.util.HashMap;

public class DatabaseTests {
    private static Map testMap;

    public static void setupMap() {

        testMap.put("userId","kb700");
        testMap.put("friend","kb707");
        testMap.put("reponseType", 1);
        Database db = new Database();
        db.respondToRequest(testMap);
        System.out.println(db.getResults());
    }

    public static void main(String[] args) {
        testMap = new HashMap();
        setupMap();
    }
}