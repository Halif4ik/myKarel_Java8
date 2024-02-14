package com.shpp.p2p.cs.dgrymach.Assignment16.ArrayList;

import java.util.ArrayList;
import java.util.Random;

public class TestByTime {
    private static final int DEFAULT_CIKLE = 300_000;


    public static void main(String[] args) {

        MyArrayList<Integer> test = new MyArrayList<>();
        Random random   = new Random();
        long startTime = System.currentTimeMillis();


        /*contain half Array list from begin to half */
        for (int i = 0; i < DEFAULT_CIKLE / 2; i++) test.add(i, 1);


        /*add in random index in center (from 0 to half)*/
        for (int i = 0; i < DEFAULT_CIKLE / 2; i++) {
            int rIndex = random.nextInt(DEFAULT_CIKLE / 2);
            test.add(rIndex, i);

        }

        System.out.println("originalSize " + test.size() + " mySize " /*+ test.size()*/);

        /*and delete 99% elements but will leave one element*/
        for (int i = 0; i < DEFAULT_CIKLE - 1; i++) {
            int rIndex = random.nextInt(test.size() - 1);
            test.remove(rIndex);
        }
        test.remove(test.size() - 1);

        System.out.println("Time of works program:" + (System.currentTimeMillis() - startTime) + " milisek.");

    }




}
