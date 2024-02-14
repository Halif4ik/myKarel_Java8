package com.shpp.p2p.cs.dgrymach.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This program read CSV file, ask column and if it real print him.
 */
public class assignment5Part4old extends TextProgram {
    private final static String FILENAME = "assets/food-origins.csv";

    /**
     * ask number column and print itф
     */
    public void run() {

        while (true) {
            ArrayList<String> column = null;
            int columnIndex = readInt("Enter column index");


            try {
                column = extractColumn(FILENAME, columnIndex);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (column != null) { // if not column
                for (String str : column) { // print array string
                    println(str);
                }
            } else println("Out of range column");
        } // loop
    }

    /**
     * @param filename    name and path file
     * @param columnIndex number of column
     * @return array list string of real column else null
     *
     */
    private ArrayList<String> extractColumn(String filename, int columnIndex) throws IOException {

        ArrayList<String> column = new ArrayList<>();

        ArrayList<ArrayList<String>> fileCSV = readFileCSV(filename);

        for (ArrayList<String> arr : fileCSV) {
            if (arr.size() <= columnIndex) return null;
            column.add(arr.get(columnIndex));
        }
        return column;
    }

    /**
     * @param filename filename
     * @return list<ArrayList> String
     * read CSV file in array List < ArrayList> string
     */
    private ArrayList<ArrayList<String>> readFileCSV(String filename) throws IOException {

        ArrayList<ArrayList<String>> arrayListString = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(filename));

        while (true) {

            String lineStr = br.readLine();

            if (lineStr == null) break;

            arrayListString.add(fieldIn(lineStr));
        }

        br.close();

        return arrayListString;
    }


    /**
     * @param lineStr line String
     * @return ArrayList String
     *     */
    private ArrayList<String> fieldIn(String lineStr) {

        ArrayList<String> strCSV = new ArrayList<>();

        String strbuff = "";
        Boolean flag = false;

        for (int i = 0; i < lineStr.length(); i++){
            char ch = lineStr.charAt(i);
            if ((ch == ',' || ch == '"') && !flag) {
                if (ch == ','){
                    strCSV.add(strbuff);
                    strbuff = "";
                    continue;
                }
                if (ch == '"'){
                    flag = true;
                    continue;
                }
            }
            if (ch == '"' && flag){
                strCSV.add(strbuff);
                strbuff = "";
                flag= false;
                continue;

            }
            strbuff = strbuff + ch;

        }
        if (strbuff.length() > 0){
            strCSV.add(strbuff);
        }

        return strCSV;

    } //end fieldIn

    }


