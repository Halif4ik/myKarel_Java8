package com.shpp.p2p.cs.dgrymach.assignment2;

import com.shpp.cs.a.console.TextProgram;

public class Assigment2part1 extends TextProgram {
    @Override
    public void run() {
        double a = readDouble("Please enter numder \'a\': ");
        double b = readDouble("Please enter numder \'b\': ");
        double c = readDouble("Please enter numder \'c\': ");
        double dis = cacluleteDiscriminatnt(a, b, c);
        printResult(a, b, dis);

    }

    /**
     * this method  calculate depending on the signs discriminant print result
     *
     * @param a   - first numder
     * @param b   - second number
     * @param dis -  discriminant
     *          **/
    private void printResult(double a, double b, double dis) {
        if (dis < 0) {
            println("There are no real roots =(");
        } else if (dis == 0) {
            println("There is one root:" + (-b / (2 * a))); // this is formula calculate sqrt equation whis discriminant ==0
        } else {
            double resultOne = (-b + Math.sqrt(dis)) / (2 * a);
            double resultTwo = (-b - Math.sqrt(dis)) / (2 * a);
            println("There are two roots:" + resultOne + " and" + resultTwo + "");
        }
    }


    /**
     * this method  take three numbers for player calculate discriminant on this base
     *
     * @param a - first numder
     * @param b - second number
     * @param c -  third number
     * @return result us name discriminant
     **/
    private double cacluleteDiscriminatnt(double a, double b, double c) {
        double result = Math.pow(b, 2) - 4 * a * c;  // this is formula calculate discriminant
        return result;
    }
}
