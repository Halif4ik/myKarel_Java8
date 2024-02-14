package com.shpp.p2p.cs.dgrymach.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Assignment5part4 extends TextProgram {
    private final static String FILE_NAME = "assets/food-origins.csv";
    private final static char PATTERN_COMMA = ',';
    private final static char PATTERN_QUOTES = '"';
    private final static int COLUMN_INDEX = 1;

    public void run() {
      ArrayList <String> result = extractColumn(FILE_NAME, COLUMN_INDEX);
      println(result);
    }

    private ArrayList<String> extractColumn(String filename, int columnIndex) {
        String readString;
        ArrayList<String> rowOfTable; ArrayList<String> result=new ArrayList<>();
        ArrayList<ArrayList<String>> allTable = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            while ((readString = br.readLine()) != null) {
                rowOfTable = splitOneStrinInMany_FieldsIn(readString);
                allTable.add(rowOfTable);
            }
            br.close();
        } catch (IOException e) {
            allTable.add(null);
        }
        /* go from all table, an get all row but in all row get element (string) with ind == column indx  */
        for (ArrayList row :allTable){
            result.add((String) row.get(columnIndex));
        }
        return result;
    }

    /**
     *
     * @param restPartString - al strin with read file readerand buffarreader
     * @return - array(list) split string
     */
    private ArrayList<String> splitOneStrinInMany_FieldsIn(String restPartString) {
        ArrayList<String> arraySplitString = new ArrayList<>();
        String cutPartString;
        /* while in string present quotes or comma*/
        while (foundPatern(restPartString) > -1) {
            /* cut part string, from zero || one char(if) quotes ,to next commas or quotes*/
            cutPartString = restPartString.substring(beginSubString(restPartString),
                    foundPatern(restPartString));
            /* add one cut part in arraList*/
            arraySplitString.add(cutPartString);
            /*if the rest of the string was only the part in quotes, its the end for string with qoutes in and  */
            if (foundPatern(restPartString) == restPartString.length() - 1) break;

            /* now we cut same part of String and remember in this String and we less coma and qotes*/
            if (restPartString.charAt(0) == PATTERN_QUOTES) {
                restPartString = restPartString.substring(foundPatern(restPartString) + 2);
            } else {
                restPartString = restPartString.substring(foundPatern(restPartString) + 1);
            }
        }
        /* if  cycle end, but last part whath string with out quotes,this means last part nead add in arryList all */
        if (beginSubString(restPartString) == 0) arraySplitString.add(restPartString);
        return arraySplitString;
    }

    /** the function cuts off the quotes at the beginning of the string
     * @param restPartString - string in what we search
     * @return- zero or first sequence number char
     */
    private int beginSubString(String restPartString) {
        if (restPartString.charAt(0) == PATTERN_QUOTES) {
            return 1;
        } else {
            return 0;
        }
    }

    /** method find index first inside (sequence number) pattern, depending on whether
     * the quotes were at the beginning of the string
     *
     * @param restPartString-  part string in what we search
     * @return-  sequence number quotes or comma in string
     */
    private int foundPatern(String restPartString) {
        if (restPartString.charAt(0) == PATTERN_QUOTES) {
            restPartString = restPartString.substring(1);
            return restPartString.indexOf(PATTERN_QUOTES) + 1;
        } else {
            return restPartString.indexOf(PATTERN_COMMA);

        }

    }
}







