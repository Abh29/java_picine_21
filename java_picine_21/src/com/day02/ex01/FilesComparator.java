package com.day02.ex01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class FilesComparator {

    private Map<String, int[]> dictionary;
    private File file1;
    private File file2;
    private double similarity = -10;


   public FilesComparator(String filePath1, String filePath2) throws IOException{
        file1 = new File(filePath1);
        file2 = new File(filePath2);
        dictionary = new TreeMap<>();
        loadDictionary();
    }

    private void loadDictionary() throws IOException{
       loadFile(file1, 0);
       loadFile(file2, 1);
    }

    private void loadFile(File file, int i) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String str;
        while ((str = br.readLine()) != null){
            String[] words = str.split("\\s+");
            for (String word : words) {
                if (dictionary.containsKey(word)){
                    dictionary.get(word)[i]++;
                } else {
                    int[] t = new int[2];
                    t[i] = 1;
                    dictionary.put(word, t);
                }
            }
        }
        br.close();
    }

    public double getSimilarity() {

       if (similarity == -10){
           double a2 = 0, b2 = 0, ab = 0;
           for (int[] d : dictionary.values()) {
               ab += d[0] * d[1];
               a2 += d[0] * d[0];
               b2 += d[1] * d[1];
           }

           similarity = ab / (Math.sqrt(a2) * Math.sqrt(b2));
       }

        return similarity;
    }

}
