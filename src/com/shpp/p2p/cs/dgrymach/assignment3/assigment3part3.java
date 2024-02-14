package com.shpp.p2p.cs.dgrymach.assignment3;
import com.shpp.cs.a.console.TextProgram;

public class assigment3part3 extends TextProgram {
    public void run(){
        double base = readDouble(" Enter a double_base, i beleew you: "); // what we подносим в степень
        String strToInt;
        while (!testToInt(strToInt = readLine(" Enter a exponent, it must bee integer number and i'am test it: ")));// it's exponent beleew me
        int exponent = Integer.parseInt(strToInt);
        double x = Double.parseDouble(strToInt);
        //println(x);
        //println(exponent);
        println(raiseToPower(base, exponent));
    }

    private double raiseToPower(double base, int exponent) {
        double pow;
        if (exponent == 0){
            return 1;
        } else if ( exponent < 0) {
             exponent = -(exponent);
             pow = 1/riseToPowerPositive(base,exponent);

        }else {
            pow = riseToPowerPositive(base,exponent);
            }
            return pow;
    }

    private double riseToPowerPositive(double base, int exponent) {
        double pow = base;
        for (int i = 1; i < exponent; i++) {
            pow = pow * base;
        }
        return  pow;
    }


    private boolean testToInt(String strToInt) {
        for (int i=0; i<strToInt.length(); i++){
            char ch= strToInt.charAt(i);

            if (((ch < 45) || (ch > 57) || ((ch>45)&&(ch < 48) ))) return  false;
        }
        return  true;
    }

}
