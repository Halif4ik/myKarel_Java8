import com.shpp.karel.KarelTheRobot;

public class assignment1part1 extends KarelTheRobot {
    /**
    pre condition Karel stand in North west part room and see intro the East
    After decomposition Karel take newspaper and cumeback intro first position and see in deap */
    public void run() throws Exception{      // main task forwadrd to biper take it end cambek
        pickUpNewspaper();
        /*turnAround();
        cumBLack();*/

    }


    private void cumBLack() throws Exception { // this method cumback kerel on firs position
        moveForward();
        turnRight();
        move();
        turnRight();

    }

    private void turnAround() throws Exception {
        for (int i=0; i <2; i++) turnLeft();
    }

    private void pickUpNewspaper() throws Exception {   // this funcion allows позволяет you to approach the newspaper
        turnRight();
        /*move();
        turnLeft();
        moveForbiper();
        pickBeeper();*/

    }

    private void moveForbiper() throws Exception { // this is funtion force move kerel to beper
        while (!beepersPresent()) move();
            }

    private void turnRight() throws Exception {
        for (int i=0; i<3; i=i+1)
            turnLeft();
    }

    private void moveForward() throws Exception {
        while (frontIsClear()) {
            move();
        }
    }
}
