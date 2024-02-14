package com.shpp.p2p.cs.dgrymach.assignment1;

import com.shpp.karel.KarelTheRobot;

public class assignment1part3 extends KarelTheRobot {
    public void run() throws Exception {
        if (frontIsClear()) ciclSeachCentaer();
        putBeeper();

    }


    private void ciclSeachCentaer() throws Exception {
        if (facingEast()) {
            moveForvard();
            moveForvard();
            if (frontIsBlocked()) tornAround();
            ciclSeachCentaer();
            moveForvard();
        }

    }

    private void moveForvard() throws Exception {
        if (frontIsClear()) {
            move();
        }
    }

    private void tornAround() throws Exception {
        for (int i = 0; i < 2; i++) {
            turnLeft();
        }
    }


}
