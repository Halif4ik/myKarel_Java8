package com.shpp.p2p.cs.dgrymach.assignment3;

import com.shpp.cs.a.console.TextProgram;

import java.util.Locale;

public class Asigment3part3 extends TextProgram {

    public void run() {
        Locale.setDefault(new Locale("en", "EN"));

        double base = askUserBase();
        int exponent = askUserExponen();
        println(raiseToPower(base, exponent));
    }

    /**
     *
     * @param base -
     * @param exponent-
     * @return-
     */
    private double raiseToPower(double base, int exponent) {
        double result;
        if (exponent == 0) {
            result = 1;
        } else if (exponent > 0) {
            result = exponentiation(base, exponent);
        } else {
            switch (exponent = -exponent) {
            }
            result = 1 / exponentiation(base, exponent);
        }
        return result;
    }

    /**
     *
     * @param base-
     * @param exponent-
     * @return-
     */
    private double exponentiation(double base, int exponent) {
        double result = 1;
        for (int i = 0; i < exponent; i++) result = base * result;
        return result;
    }

    /**
     * @return
     */
    private int askUserExponen() {
        String enterStr = readLine(" Enter a exponent type int: ");
        while (stringIsNaNWhisOutPoint(enterStr)) {
            enterStr = readLine(" blind? enter only the numbers, come on again!: ");
        }
        return Integer.parseInt(enterStr);

    }

    private boolean stringIsNaNWhisOutPoint(String enterStr) {
        // chek first sumbol and allow only '-' and digit chek if usver forget enter symbol
        if (enterStr == null || enterStr.length() == 1 && enterStr.charAt(0) == '-' || !Character.isDigit(enterStr.charAt(0)) && enterStr.charAt(0) != '-')
            return true;

        //*chek second and all sumbol, and allow only  digit if catch  in string isNaN  STOP.*/
        for (int i = 1; i < enterStr.length(); i++) if (!Character.isDigit(enterStr.charAt(i))) return true;

        return false;   // is enter string dont cath isNaN return false
    }

    /**
     * @return
     */
    private double askUserBase() {
        String enterStr = readLine(" Enter a base type double: ");
        while (stringIsNaN(enterStr)) {
            enterStr = readLine(" blind? enter only the numbers, come on again!: ");
        }
        return Double.parseDouble(enterStr);

    }

    /**
     * @param enterStr
     * @return
     */
    private boolean stringIsNaN(String enterStr) {
        // chek first sumbol and allow only '-' and digit chek if usver forget enter symbol
        if (enterStr == null || enterStr.length() == 1 && enterStr.charAt(0) == '-' || !Character.isDigit(enterStr.charAt(0)) && enterStr.charAt(0) != '-')
            return true;
        // chek second and all sumbol, and allow only '.' and digit, if catch  in string isNaN besides '.'  STOP.
        for (int i = 1; i < enterStr.length(); i++)
            if (!Character.isDigit(enterStr.charAt(i)) && enterStr.charAt(i) != '.') return true;

        return false;   // is enter string dont cath isNaN return false
    }
}
