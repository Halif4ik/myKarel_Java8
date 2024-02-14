package com.shpp.p2p.cs.dgrymach.assignment3;

import com.shpp.cs.a.console.TextProgram;

public class Asigment3part1 extends TextProgram {
    //int renge = minivan.fuelcap * minivan.mpg;
    public static final int SEVEN_DAY_ON_WEEK = 7;
    public static final int NEEDED_DAY_FOR_CARDIOVASCULAR = 5;
    public static final int NEEDED_DAY_FOR_BLOOD_PRESSURE = 3;
    public static final int NEEDED_MINUTE_FOR_BLOOD_PRESSURE = 40;
    public static final int NEEDED_MINUTE_FOR_CARDIOVASCULAR = 30;
    public  int minPerDay = 0;

    class Usver {
        int cardiovacularDay;
        int bloodPressureDay;
    }
 @Override
    public void run() {
        Usver ThisStudent = new Usver();

        for (int i = 0; i < SEVEN_DAY_ON_WEEK; i++) {
            minPerDay = askUserHowManyAndCetIt(i);
            if (minPerDay >= NEEDED_MINUTE_FOR_CARDIOVASCULAR) ThisStudent.cardiovacularDay++;
            if (minPerDay >= NEEDED_MINUTE_FOR_BLOOD_PRESSURE) ThisStudent.bloodPressureDay++;
        }

        if (ThisStudent.cardiovacularDay >= NEEDED_DAY_FOR_CARDIOVASCULAR) {
            println("Cardiovacular health:\n" +
                    "  Great job! You've done enough exercise for cardiovacular health.");
        } else {
            println("Cardiovacular health:\n" +
                    "  You needed to train hard for at least " + (NEEDED_DAY_FOR_CARDIOVASCULAR - ThisStudent.cardiovacularDay) + " more day(s) a week!");
        }

        if (ThisStudent.bloodPressureDay >= NEEDED_DAY_FOR_BLOOD_PRESSURE) {
            println("Blood pressure: \n" +
                    "  Great job! You've done enough exercise for cardiovacular health.");
        } else {
            println("Blood pressure::\n" +
                    "  You needed to train hard for at least " + (NEEDED_DAY_FOR_BLOOD_PRESSURE - ThisStudent.bloodPressureDay) + " more day(s) a week!");
        }
    }

    /**
     *
     * @param i -
     * @return -
     */
    private int askUserHowManyAndCetIt(int i) {
        String enterStr = readLine("How many minutes did you do on day " + (i + 1) + " ?: ");
        while (!checEnterStringToNomber(enterStr)) {
            enterStr = readLine(" blind? enter only the numbers, come on again!: ");
        }

        return Integer.parseInt(enterStr);
    }

    /**
     *
     * @param enterStr -
     * @return -
     */
    private boolean checEnterStringToNomber(String enterStr) {
        for (int i = 0; i < enterStr.length(); i++)
            if (!Character.isLetter(enterStr.charAt(i)))
                return false; // если елемент введеной строки не цифра то ретурн фалсе
        return true;
    }

    }
