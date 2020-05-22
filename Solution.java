package com.javarush.task.task22.task2209;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

/*
Составить цепочку слов
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        InputStream inputStream = new FileInputStream(reader.readLine());
        reader.close();
        byte[] array = new byte[inputStream.available()];
        inputStream.read(array);
        inputStream.close();
        String s = new String(array);
        String[] arr = s.split(" ");
        StringBuilder stringBuilder = getLine(arr);
        System.out.println(stringBuilder.toString());
    }

    public static StringBuilder getLine(String... words) {
        if (words.length==0) return new StringBuilder("");
        List<String> result = new ArrayList<>();
        result.add(words[0]);
        List<String> listWords = copyWithoutFirstWord(words);
        StringBuilder sB = getResult(listWords, result);
        return sB;
    }

    public static StringBuilder getResult(List<String> words, List<String> result) {
        StringBuilder sB = add(result);
        if (words.size() == 0) return sB;
        for (int i = 0; i < words.size(); i++) {
            for (int j = 0; j < result.size(); j++) {
                if (j == 0) {
                    if (isEndAndFirst(words.get(i), result.get(0))) {
                        result.add(0, words.get(i));
                        words.remove(i);
                        return getResult(words, result);
                    }
                }
                if (j < result.size() - 1) {
                    if (isEndAndFirst(result.get(j), words.get(i)) && isFirstAndEnd(result.get(j + 1), words.get(i))) {
                        result.add(j, words.get(i));
                        words.remove(i);
                        return getResult(words, result);
                    }
                }
                if (j == result.size() - 1) {
                    if (isFirstAndEnd(words.get(i), result.get(j))) {
                        result.add( words.get(i));
                        words.remove(i);
                        return getResult(words, result);
                    }
                }
            }
        }
        if (words.size()!=0){
            for (int i = 0; i <words.size(); i++) {
                result.add(result.size(),words.get(i));
                words.remove(i); i--;
            }
        }
        return getResult(words,result);
    }

    public static List<String> copyWithoutFirstWord(String[] words) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i < words.length; i++) {
            list.add(words[i]);
        }
        return list;
    }
    public static boolean isFirstAndEnd(String one, String two) {
        char first = one.toLowerCase().charAt(0);
        char end = two.toLowerCase().charAt(two.length() - 1);
        return first == end;
    }

    public static boolean isEndAndFirst(String one, String two) {
        char first = one.toLowerCase().charAt(one.length() - 1);
        char end = two.toLowerCase().charAt(0);
        return first == end;
    }
    public static StringBuilder add(List<String> words){
        StringBuilder sB = new StringBuilder();
        for (int i = 0; i < words.size(); i++) {
            sB.append(words.get(i)).append(" ");
        }
        return sB;
    }

}
