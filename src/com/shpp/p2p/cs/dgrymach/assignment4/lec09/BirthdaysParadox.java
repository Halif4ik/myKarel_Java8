package com.shpp.p2p.cs.dgrymach.assignment4.lec09;

import acm.util.RandomGenerator;
import com.shpp.cs.a.console.TextProgram;

public class BirthdaysParadox extends TextProgram {

    @Override
    public void run() {
        int schetcki =0;

        for (int i = 0; i < 100; i++) {

        int[] birthdayCount = new int[365];
        boolean found = false;
        int count = 0;

            while (!found) {   //comein
                int man = RandomGenerator.getInstance().nextInt(365);
                println("-"+man);

                if (birthdayCount[man] == 1) {

                    found = true;
                } else {
                    birthdayCount[man] = 1;

                }
                count++;
            }

            println("ya... nashli " + count);
            schetcki += count;
        }
        println(" srednee = " +schetcki/100);

    }

    }


     /*   RandomGenerator r = RandomGenerator.getInstance();

        int[] days = new int[365];

        int skolko = 0;

        boolean found = false;
        while (!found) {
            int day = r.nextInt(365);
            skolko++;
            if (days[day] != 2) {
                days[day]++;
            } else {
                found = true;
            }
        }

        println("skolko? haha: "+skolko);*/






