package com.cebem.RickAndMorty.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {

    public static boolean isPalindrome(String word) {
        String invertedWord = new StringBuilder(word).reverse().toString();
        return word.equalsIgnoreCase(invertedWord);
    }
    
    public static float maxOfElements(float ...numbers) throws Exception{
        if (numbers == null || numbers.length == 0)
            throw new NumberFormatException();
        float max = numbers[0];
        for (float n : numbers)
            if (n > max) max = n;
        return max;
    }

    public static void writeOnDisk(String fileName, String info) throws IOException {
        FileWriter fw = null;
        try {
            // true escribe al final, false sobre escirbe
            fw = new FileWriter(fileName, true);
            fw.write(info);
        } finally {
            if (fw != null) fw.close();
        }
        
    }

    public static String readFromDiks(String fileName) throws IOException, FileNotFoundException {
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader("datos.txt");
            br = new BufferedReader(fr);
            String line, content = "<ul>";
            while ((line = br.readLine()) != null) {
                content += "<li>" + line + "<li/>";
            }
            content += "<ul/>";
            return content;
        } finally {
            try {
                if (fr != null) fr.close();
                if (br != null) br.close();
            } catch (IOException e) {
                
            }
        }
    }
    
    public static boolean deleteFile(String fileName) {
        File f = new File(fileName);
        return f.delete();
    }

    public static void deleteData(String fileName) throws IOException{
        FileWriter fw = null;
        try {
            fw = new FileWriter(fileName, false);
            fw.write("");
        } finally {
            if (fw != null) fw.close();        
        }
    }

    public static String capitalizeText(String text) {
        String[] words = text.split(" ");
        String capitalizedText = "";
        for (String word : words) {
            char letter = Character.toUpperCase(word.charAt(0));
            String rest = word.substring(1).toLowerCase();
            capitalizedText += letter + rest + " ";
        }
        return capitalizedText;
    }

    public static int generateRandom(int max) {
        return  (int) Math.floor((Math.random() * max));

    }

}