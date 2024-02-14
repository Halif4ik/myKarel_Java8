package com.shpp.p2p.cs.dgrymach.Recurtion;

class FindResult {

    public static void main(String args[]) {
        int startNumb = 20;
        int divisor = 2;

        System.out.println("result is " + findNextNumberOne(startNumb, divisor));
    }

    private static int findNextNumberOne(int startNumb, int divisor) {
         int result;
         if (startNumb <= 0) return 0;

        result = findNextNumberOne(startNumb - divisor,divisor) + 1;

        return result;
    }


}
