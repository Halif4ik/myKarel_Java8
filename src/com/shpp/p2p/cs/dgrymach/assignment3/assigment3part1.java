package com.shpp.p2p.cs.dgrymach.assignment3;

import com.shpp.cs.a.console.TextProgram;

public class assigment3part1 extends TextProgram {
    private static final int CARDIO_VASCULAR = 30; //MIN MINUTES PER DAY FOR Cardiovacular health
    private static final int DOWN_BLOOD = 40; //MIN MINUTES PER DAY FOR Blood pressure
    private static final int CARDIO_DAY = 5; //MIN DAY PER WEEK Cardiovacular health
    private static final int BLOOD_DAY = 3; //MIN DAY PER WEEK FOR Blood pressure

    public void run() {
        try {
            int blood = 0;
            int cardio = 0;              // exception input into our programm ISNAN
            for (int i = 1; i < 8; i++) {  // seven day on the week
                double n = readInt(" How many minutes did you do on day " + i + "?");
                if (n >= CARDIO_VASCULAR) cardio++;
                if (n >= DOWN_BLOOD) blood++;
            }

            comparisonDay(blood, cardio);
        } catch (Exception ex) {
            println("Opps 404!  need  to enter only  numbers! " + ex + "well, lets begin one more but only INT!!! ");
        }

    }

    private void comparisonDay(int blood, int cardio) {
        if (cardio >= CARDIO_DAY) {                       // test  if enough exercise
            println("Cardiovacular health:\n" +
                    "  Great job! You've done enough exercise for cardiovacular health.");
        } else {
            int x = CARDIO_DAY - cardio;
            println("Cardiovacular health:\n" +
                    "  You needed to train hard for at least " + x + " more day(s) a week!.");
        }
        if (blood >= BLOOD_DAY) {                        // test  if enough exercise
            println("Blood pressure:\n" +
                    "  Great job! You've done enough exercise to keep a low blood pressure.");
        } else {
            int x = BLOOD_DAY - blood;
            println("Blood pressure:\n" +
                    " You needed to train hard for at least " + x + " more day(s) a week!");
        }

    }
}


















