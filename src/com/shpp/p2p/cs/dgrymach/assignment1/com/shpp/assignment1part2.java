package com.shpp;


import com.shpp.karel.KarelTheRobot;


public class assignment1part2 extends KarelTheRobot {

    // pre condition Karel stend in North wect part room and see intro the East
    //After decomposition Karel

    public void run() throws Exception {
        fillFirstColumn();
        while (frontIsClear()) {
            gotonextColumn();
            fillTheColumn();
            comebackForGoToNext();
        }

    }

    private void fillFirstColumn() throws Exception {
        turnLeft();         // ready to fill column
        fillColumn();
        turnRight();    // redy go to next column
    }

    private void gotonextColumn() throws Exception {
        move();
        while (leftIsClear()) {
            move();
        }
    }

    private void fillTheColumn() throws Exception {
        turnRight();    // ready to fill second third or other column
        fillColumn();
    }

    private void comebackForGoToNext() throws Exception {
        turnAround();
        if(beepersPresent()){
            moveForward();
        } else {
            putBeeper();
            moveForward();
        }
        turnRight();
    }


    private void turnAround() throws Exception {
        for (int i = 0; i < 2; i++) {
            turnLeft();
        }
    }

    private void turnRight() throws Exception {
        for (int i = 0; i < 3; i++) {
            turnLeft();
        }
    }

    private void fillColumn() throws Exception {        // kerel go forward to wall and decide where put bipers stop position near wall see on south or north
        while (frontIsClear()) {
            if (beepersPresent()) {
                move();
            } else {
                putBeeper();
            }
        }
    }

    private void moveForward() throws Exception {
        while (frontIsClear()) {
            move();
        }
    }

}
