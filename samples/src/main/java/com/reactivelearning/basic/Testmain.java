package com.reactivelearning.basic;

import java.util.HashMap;
import java.util.Map;

public class Testmain {
    public static void main(String[] args) {
        String[] strArr = {"javablog","javaworld","javabean","javatemp","nodeScript"};
        Map<String,Integer> prefixOccurance = new HashMap<>();

        for (int i = 0; i < strArr.length; i++) {
            String indexString = strArr[i]; // javablog
            int indexLen = indexString.length();
            int charIndex = 0;
            while (charIndex < indexLen) {
                char indexChar = indexString.charAt(charIndex);
                for (int j = 1; j < strArr.length; j++) {
                    if (strArr[j].startsWith(String.valueOf(charIndex))) { //j.j
                        if (prefixOccurance.get(String.valueOf(indexChar))== null) {
                            prefixOccurance.put(String.valueOf(indexChar),1);
                        } else {
                            Integer value = prefixOccurance.get(String.valueOf(indexChar));
                            prefixOccurance.put(String.valueOf(indexChar),value+1);
                        }
                    }
                }
                charIndex++;
            }
        }
        prefixOccurance.entrySet().stream().forEach(stringIntegerEntry -> System.out.println("key => "+stringIntegerEntry.getKey()+" value => "+stringIntegerEntry.getValue()));
    }
}
