package com.shpp.p2p.cs.dgrymach.assignment1;

import com.shpp.karel.KarelTheRobot;

public class assignment1part4 extends KarelTheRobot {
    public void run() throws Exception {
        while (frontIsClear()) {
            fillCellTypeAAndBack();
            checkAndLevel(); //vistavlayaem bipera dlya prodolgeniya ili okonchaniya
            fillCellTypeBAndBack();
            checkAndLevel();
        }
    }
    private void checkAndLevel() throws Exception {
        if (frontIsClear()) {
            move();
            turnRight();
        }
    }
    private void fillCellTypeBAndBack() throws Exception {
            while (frontIsClear()) {
                moveNeatlyOneSteapAndPutBeep();
                moveNeatlyOneSteap();
            }
            if(facingEast()) {
                cumBackAndTuerRigth();
            }
    }
    private void fillCellTypeAAndBack() throws Exception {
            putBeeper();
            while (frontIsClear()) {
                moveNeatlyOneSteap();
                moveNeatlyOneSteapAndPutBeep();
            }
            cumBackAndTuerRigth();
    }

    private void cumBackAndTuerRigth() throws Exception {
        if (facingEast()) {
            turnAround();
            while (frontIsClear()) {
                move();
            }
        }
        turnRight();
    }

    private void turnAround() throws Exception {
        turnLeft();
        turnLeft();
    }

    private void turnRight() throws Exception {
        turnLeft();
        turnLeft();
        turnLeft();
    }

    private void moveNeatlyOneSteapAndPutBeep() throws Exception {
        if (frontIsClear()) {
            move();
            putBeeper();
        }
    }

    private void moveNeatlyOneSteap() throws Exception {
        if (frontIsClear()) {
            move();
        }
    }
}



















