package com.shpp.p2p.cs.dgrymach.assignment3;
import  com.shpp.cs.a.console.TextProgram;

public class assigment3part2 extends TextProgram {

    public void run() {
        String strToInt;
        while (!testToInt(strToInt = readLine(" Enter a positive integer number and i'am test it: ")));
        int n = Integer.parseInt(strToInt);
        if(findOne(n)){
            println("theAnd! you did not find the unique number = ( ");
        }else{
            println("Congratulation you win Nobel primium!!Please inpun number of you visa card");
        }
    }

    private boolean findOne(int n) {
        do {
            if (n%2 == 0){
                println(n +" is even so I take half: " + (n/2));
                n = n/2;
            } else {
                println(n + " is odd so I make 3n + 1: " + (3*n+1));
                n = 3*n+1;
            }
        } while ( n != 1);
       return  true;
    }

    private boolean testToInt(String strToInt) {
        for (int i=0; i<strToInt.length(); i++){
            char ch= strToInt.charAt(i);
            if (ch == 45 ||ch < 48 || ch > 57) return  false;
        }
       return  true;
  }
}

//
//s.matches("-?\\d+"); true oe false


