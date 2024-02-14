package com.shpp.p2p.cs.dgrymach.assignment3;

import com.shpp.cs.a.console.TextProgram;

public class Asigment3part2 extends TextProgram {

    public void run() {
        int positiveInteg;
/*
ask in usver positive Integer
 */
    do {positiveInteg = askUserPositiveInteg();}
    while (positiveInteg == 0);

        while (positiveInteg != 1) {
            if (positiveInteg == 0 ) {
                println("My congratulation you chiter!");
                break;
            } else if (positiveInteg % 2 == 0) {
                positiveInteg = takeHalfAndPrint(positiveInteg);
            } else {
                positiveInteg = multiplyByThreeAndAddOne(positiveInteg);
            }
        }
        println("The and.");
    }


    /**
     *
     * @param positiveInteg
     * @return
     */
    private int multiplyByThreeAndAddOne(int positiveInteg) {
        println(positiveInteg + "  is odd so I make 3n + 1:  " + (positiveInteg = positiveInteg *3 + 1));

        return positiveInteg;
    }

    /**
     *
     * @param positiveInteg
     * @return
     */
    private int takeHalfAndPrint(int positiveInteg) {
        println(positiveInteg + "  is even so I take half:  " + (positiveInteg = positiveInteg / 2));

        return positiveInteg;
    }


    /**
     * @return
     */
    private int askUserPositiveInteg() {
        String enterStr = readLine(" Enter a number: ");
        while (!checEnterStringToNomber(enterStr)) {
            enterStr = readLine(" blind? enter only the numbers, come on again!: ");
        }
        return Integer.parseInt(enterStr);
    }

    /**
     * @param enterStr
     * @return- if enter String is NaN, to return 'false'
     */
    private boolean checEnterStringToNomber(String enterStr) {
        boolean found = enterStr.matches("(\\d+)"); // if character intro string NaN || 1 , to return 'false'
        return found;
    }
}
