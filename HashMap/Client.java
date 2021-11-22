// import java.util.*;

public class Client {
    public static void main(String[] args) {
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put("a", "b");
        // map.put(2, 3);
        // map.put(3, 4);
        // map.put(4, 5);
        // map.put(4, 6);
        // System.out.println(map.get(2));
        // System.out.println(map.remove(1));
        for (String key : map.keySet()) {
            System.out.println(key + " -> " + map.get(key));
        }
    }
}