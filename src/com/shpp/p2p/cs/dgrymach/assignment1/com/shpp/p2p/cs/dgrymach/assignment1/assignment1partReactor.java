package com.shpp.p2p.cs.dgrymach.assignment1;

import com.shpp.karel.KarelTheRobot;

public class assignment1partReactor extends KarelTheRobot {
    public void run() throws Exception {
        while (frontIsClear()){
            if (!beepersPresent()){         //chek if clean reactor or not
                cleanReactor();
                standOnPosition();
            }
            goToNextReactor();
        }
        if (!beepersPresent()){         //chek if clean reactor or not
            cleanReactor();
            standOnPosition();
        }
           }

    private void goToNextReactor() throws Exception {
        move();
        while (leftIsBlocked()) {
            move();
                   }
    }

    private void moveForvard() throws Exception {
        while (frontIsClear()) move();
    }

    private void standOnPosition() throws Exception {
        turnAround();
        move();
        turnRight();
    }

    private void turnRight() throws Exception {
        for(int i=0 ;i<3; i++) turnLeft();
    }


    private void cleanReactor() throws Exception {
        turnLeft();
        move();
        while (beepersPresent())pickBeeper();
        turnAround();
        while (frontIsClear()) move();
        while (beepersPresent())pickBeeper();

            }

    private void turnAround() throws Exception {
        for (int i=0; i<2; i++) turnLeft();
            }
}

