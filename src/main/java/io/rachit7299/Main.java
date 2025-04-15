package io.rachit7299;

public class Main {
    public static void main(String[] args) {

        System.out.println("!!!!!!!Creating Custom HashMAp!!!!!!!!");
        CustomHashMap<String, Integer> map = new CustomHashMap<>();
        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);

        System.out.println("Size of map: " + map.size());
        System.out.println("Value for key 'Two': " + map.get("Two"));
        System.out.println("Contains key 'Three': " + map.containsKey("Three"));

        map.put("Two", 22);

        map.remove("One");

        System.out.println("Updated value for key 'Two': " + map.get("Two"));
    }
}