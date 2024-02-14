package com.shpp.p2p.cs.dgrymach.Testi.lesson_3;

import com.shpp.cs.a.console.TextProgram;

import java.util.ArrayList;

public class ArrayListi extends TextProgram {
    public void run() {

        ArrayList<String> strArLis = new ArrayList<>();

        String line;
        while ( !(line = readLine(" Enter a positive: ")).equals("") ) {

            strArLis.add(line);

        }

        String name = readLine("Enter you name: ");
        boolean found = false;
        for (String sameName : strArLis)
            if (name.equals(sameName)) {
                found = true;
                break;
            }
        if (found)  println(" yea I now you!");

            ArrayList <Integer> vasya = new ArrayList();
            String s ="2";
            Integer x = 1;
            int y = x;
            vasya.add(x);

            println(vasya);


    }
}
