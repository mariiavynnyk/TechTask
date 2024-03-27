package org.test.javaTask2;

//Write a Java method to find all the longest string in a given array of strings.
//e.g.
//input: {"cat", "dog", "red", "is", "am"}
//result: [cat, dog, red]

import java.util.*;

public class TheLongestStringTest {
    public static List<String> findLongestString(String[] list) {
        List<String> newList = new ArrayList<>();
        for (String s : list) {
            if (newList.isEmpty()) {
                newList.add(s);
            } else if (s.length() > newList.get(newList.size() - 1).length()) {
                newList.clear();
                newList.add(s);
            } else if (s.length() == newList.get(newList.size() - 1).length()) {
                newList.add(s);
            }
        }
        return newList;
    }

    public static void main(String[] args) {
        String[] list = {"cat", "dog", "red", "is", "am"};
        System.out.println(findLongestString(list));
    }
}
