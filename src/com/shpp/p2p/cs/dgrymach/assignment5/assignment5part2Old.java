package com.shpp.p2p.cs.dgrymach.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.util.ArrayList;

public class assignment5part2Old extends TextProgram {
    /* Sit in a loop, reading numbers and adding them.*/
    public void run() {
        String numb1;
        String numb2;
        do {
            numb1 = readLine("Enter first number:  ");
            numb2 = readLine("Enter second number: ");
        } while (userPutInWord(numb1, numb2));
        println(numb1 + " + " + numb2 + " = " + addNumericStrings(numb1, numb2));
        println();
    }

    private boolean userPutInWord(String n1, String n2) {
        for (int i = 0; i < n1.length(); i++) {
            char ch = n1.charAt(i);
            if (Character.isDigit(ch)) continue;
            return true;                                // if in string absent no numbes, thisis return dont do,
        }
        for (int i = 0; i < n2.length(); i++) {
            char ch = n2.charAt(i);
            if (Character.isDigit(ch)) continue;
            return true;                                // if in string absent no numbes, thisis return dont do,
        }

        return false;
    }

    /**
     * Given two string representations of no nnegative integers, adds the
     * numbers represented by those strings and returns the result.
     * Учитывая два строковых представления неотрицательных целых чисел, добавляет числа, представленные этими строками и возвращающие результат.
     *
     * @param numb1 The first number.
     * @param numb2 The second number.
     * @return A String representation of n1 + n2
     */
    private String addNumericStrings(String numb1, String numb2) {
        int summ;
        int x;
        int y;
        int z = 0;                  // data that we will contein in further
        ArrayList<Integer> arr = new ArrayList<>();

        for (int i = 0; i < calculateLengthMaxNumber(numb1, numb2); i++) {
            x = y = 0;
            if (i <= numb1.length() - 1)
                x = numb1.charAt(numb1.length() - 1 - i) - '0';  //get last char from string 'numb1' convert it in number and remember in x
            if (i <= numb2.length() - 1)
                y = numb2.charAt(numb2.length() - 1 - i) - '0';  //get last char from string 'numb2' convert it in number and remember in y

            arr.add((summ = x + y + z) % 10);       // summation two numbers in a column, leave number from 0 to 9, and add sum number in temp array
            z = summ / 10;                          // if burn number 10 or 18 we remember 1 and replese 1 in next iteration
        }
        if (z > 0) arr.add(z);          // if burn number 10 or 18 we remember 1 and put it in the and array

        return convertAndMirrorResult(arr);
    }

    private int calculateLengthMaxNumber(String numb1, String numb2) {

        int compare = numb1.compareTo(numb2);

        if (compare == 0) {
            return numb1.length();       //("Stringi 1  end str 2 ravni");
        } else if (compare > 0) {
            return numb1.length();      //"Strin 1 bolse za srt 3"
        } else if (compare < 0) {
        }
        return (numb2.length());        //"String 1 menshe za str 2"
    }

    private String convertAndMirrorResult(ArrayList<Integer> arr) {
        String result = "";
        for ( Integer j : arr)         {
            result = (char) (j + '0') + result;
        }
        return result;
    }

}

