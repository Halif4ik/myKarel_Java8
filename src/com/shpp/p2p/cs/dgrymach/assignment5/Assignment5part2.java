package com.shpp.p2p.cs.dgrymach.assignment5;

import com.shpp.cs.a.console.TextProgram;

/**
 * necessary create method which takes  2 values of type String and returns a value of type String, which is the sum of the accepted numbers.
 */
public class Assignment5part2 extends TextProgram {
    public void run() {
        String n1 = " ";
        String n2 = " ";
        /* Sit in a loop, reading numbers and adding them. */
        while (!enterInteger(n1) || !enterInteger(n2)) {
            n1 = readLine("Enter first number: ");
            n2 = readLine("Enter second number: ");
        }
        println(n1 + " + " + n2 + " = " + addNumericStrings(n1, n2));
        println();
    }

    /**
     * if 'i' element is not a digit method returns FALSE, else going through the whole word and not finding no digit returns true
     *
     * @param word  - first string which check for correct input
     * @return - found or not, the introduction of incorrect characters
     */
    private boolean enterInteger(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (!Character.isDigit(word.charAt(i))) return false;
        }
        return true;
    }

    /**
     * представленых
     * Given two string representations of nonnegative integers, adds the
     * numbers represented by those strings and returns the result.
     *
     * @param number1 The first number.
     * @param number2 The second number.
     * @return A String representation of n1 + n2
     */
    private String addNumericStrings(String number1, String number2) {
        String result = "";
        int longestNumber;
        int intInMemory = 0;
        int summ;
        ;

        /* check both number and decide how are longest*/
        if (number1.compareTo(number2) > 0) {   //String 1 bolse za srt 2"
            longestNumber = number1.length();
            println(longestNumber);
        } else {
            longestNumber = number2.length();
            println(longestNumber);
        }


        for (int i = 0; i < longestNumber; i++) {
            /* last integer in first number add   last int second number    and add to them the number that we keep in mind*/
            summ = (lastIntInNumber(number1, i) + (lastIntInNumber(number2, i)) + intInMemory);

            /*if summ two integer >=10, subtract 10 from the amount received, the balance will be recorded in the result, and the unit (10) keep in mind */
            intInMemory = summ / 10;

            /*  make result in char, add result in String, and reverse String*/
            result = (char) ((summ % 10) + '0') + result;
        }

        /* if in memory losе one, add it in result (if adding a new category) */
        if (intInMemory > 0) result = (char) ((intInMemory) + '0') + result;
        return result;
    }

    /**
     * get last character in current string number, and convert it in integer for adds, or return int zero.
     *
     * @param inputNumber - the number in the form of a string from which you want to take the last character and convert to integer
     * @param i      - number symbol in curent string, started from the and.
     * @return -  one integer
     */
    private int lastIntInNumber(String inputNumber, int i) {
        if (inputNumber.length() - i > 0) return inputNumber.charAt(inputNumber.length() - 1 - i) - '0';

        /*if one string the and, but in longest string yet no, return for current zero for add*/
        return 0;
    }

}

