package com.vanzay;


public class Main {

    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("vasia", 12);
        map.put("almonstsdfsdfsd", 1);
        map.put("nastya", 22);
        System.out.println(map.get("nastya"));
        map.put("nastya", 13);

        System.out.println(map.get("nastya"));
        System.out.println(map.keySet());
        System.out.println(map.contains("nastya"));
    }
}
