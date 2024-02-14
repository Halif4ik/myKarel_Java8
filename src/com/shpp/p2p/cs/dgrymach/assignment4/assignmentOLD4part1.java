package com.shpp.p2p.cs.dgrymach.assignment4;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * This program is a game whose goal is to knock out blocks with a ball controlled by the mouse
 */

public class assignmentOLD4part1 extends WindowProgram {
    /**
     * Width and height of application window in pixels
     */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    /**
     * Dimensions of game board (usually the same)
     */
    private static final int WIDTH = APPLICATION_WIDTH;
    private static final int HEIGHT = APPLICATION_HEIGHT;

    /**
     * Dimensions of the rocket
     */
    private static final int ROCKET_WIDTH = 60;
    private static final int ROCKET_HEIGHT = 10;

    /**
     * Offset of the  ROCKET up from the bottom
     */
    private static final int ROCKET_Y_OFFSET = 30;

    /**
     * Number of bricks per row  Количество кирпичей в ряде
     */
    private static final int BRICKS_COLUMN = 10;

    /**
     * Number of rows of bricks  Количество рядов кирпичей
     */
    private static final int NBRICK_ROWS = 10;

    /**
     * Separation between bricks
     */
    private static final int BRICK_SEP = 4;

    /**
     * Width of a brick
     */
    private static final int BRICK_WIDTH = (WIDTH - (BRICKS_COLUMN - 1) * BRICK_SEP) / BRICKS_COLUMN;

    /**
     * Height of a brick
     */
    private static final int BRICK_HEIGHT = 8;

    /**
     * Radius of the ball in pixels
     */
    private static final int BALL_RADIUS = 10;

    /**
     * Offset of the top brick row from the top  Смещение верхнего кирпичного ряда сверху
     */
    private static final int BRICK_Y_OFFSET = 70;

    /**
     * Number of turns
     */
    private static final int NTURNS = 3;

    /* How much time to pause between frames. */
    private static final double PAUSE_TIME = 1000 / 20.0;
    private double dx, dy;
    private GOval ball;
    private GRect rocketObject;
    int brickCounter = NBRICK_ROWS * BRICKS_COLUMN;
    GLabel tracker = null;
    int turns = NTURNS;

    public void run() {
        addMouseListeners();   // we add al object lose which we need
        drawWall(BRICK_HEIGHT, BRICK_WIDTH, BRICKS_COLUMN, BRICK_SEP, BRICK_Y_OFFSET, NBRICK_ROWS);
        rocketObject = creatRocket(ROCKET_WIDTH, ROCKET_HEIGHT, ROCKET_Y_OFFSET);
        addLabel();
        ball = makeBall();
        // and star for mouse clisk
        for (int i = 0; i < NTURNS; i++) {
            waitForClick();
            standBall(ROCKET_WIDTH, BALL_RADIUS); // stand ball on rocket
            if (performMove()) break; // mail boolean function who decide win we or lose
        }
        // print result ouer game
        if (brickCounter > 0) {
            rocketObject.setVisible(false);
            tracker.setFont("DejaVuSerif-" + 35);  // type of fonts
            tracker.setLocation(getWidth() / 2 - tracker.getWidth() / 2, getHeight() / 2 - tracker.getHeight() / 2); //
            tracker.setLabel("YOU LOOSER =( ");
        } else {
            ball.setVisible(false);
            rocketObject.setVisible(false);
            tracker.setFont("DejaVuSerif-" + 35);  // type of fonts
            tracker.setLocation(getWidth() / 2 - tracker.getWidth() / 2, getHeight() / 2 - tracker.getHeight() / 2); // Set start locations of label
            tracker.setLabel("CHITER WIN!!! ;)");
        }
    }

       private boolean performMove() {
        Toolkit zvuk = Toolkit.getDefaultToolkit(); //strange, the function should trigger the sound

        RandomGenerator rgen = RandomGenerator.getInstance();
        dx = rgen.nextDouble(1.0, 3.0);
        if (rgen.nextBoolean(0.5))
            dx = -dx;
        dy = 3.0;

        while (brickCounter > 0) {
            ball.move(dx, dy);
            GObject contact = getCollidingObject();

            if (hasEdgeY(ball) && dy > 0) {
                dy = -(dy); //for chiters ;)
                //turns--;
                //ball.setVisible(false);
                //break;
            } else if (hasEdgeY(ball) && dy < 0) {
                dy = -(dy);
                }
            if (hasEdgeX(ball) && dx > 0) {
                dx = -dx;
            } else if (hasEdgeX(ball) && dx < 0) {
                dx = -(dx);
            } else if (contact == rocketObject) { //&& comp != tracker
                dy = -dy;
                if (rgen.nextBoolean(0.5))dx = -dx;
             } else if (contact != null && contact != tracker) {
                remove(contact);//&& comp != tracker
                if (rgen.nextBoolean(0.5))dy = -dy;
                brickCounter--;
                ball.setColor(rgen.nextColor());
                zvuk.beep();
                }
            tracker.setLabel("  brisk: " + brickCounter + ", leav: " + turns);
            pause(PAUSE_TIME);
            }

            if (brickCounter>0) {
                return false;
            } else {
                return true;
            }

       }   // метод заставляет двигатся мячик

    @Override
    public void mouseMoved(MouseEvent event) {
        if (rocketObject != null && event.getX() + ROCKET_WIDTH / 2 < getWidth() && event.getX() - ROCKET_WIDTH / 2 >= 0) {
            double y = rocketObject.getY();
            double newX = event.getX() - rocketObject.getWidth() / 2.0;
            rocketObject.setLocation(newX, y);
        }
    }   // метод который слушает мышку и присваюет координаты ракетке

    private void standBall(int rocketWidth, int ballRadius) {
        double y =rocketObject.getY();
        double x =rocketObject.getX();
        ball.setLocation(x+rocketWidth/2, y  - ballRadius*2);
        ball.setVisible(true);
        add(ball);

    }   // stand ball in posision on center rocket

    private GObject getCollidingObject() { // function decide where stand edge
        GObject object;
        if ((object = getElementAt(ball.getX(), ball.getY())) != null ){
            return object;
        } else if ((object = getElementAt(ball.getX() +  BALL_RADIUS*2, ball.getY())) != null ) {
            return object;
        } else if(getElementAt(ball.getX() + BALL_RADIUS*2, ball.getY() + BALL_RADIUS*2 ) != null ){
            return object;
        } else if(getElementAt(ball.getX(), ball.getY() +  BALL_RADIUS*2 ) != null ){
            return object;
        } else {
            return null;
        }
    }

    private boolean hasEdgeX(GOval ball) {
        return ball.getX() + BALL_RADIUS * 2 > getWidth() || ball.getX() <= 0;
    }

    private boolean hasEdgeY(GOval ball) {
        /* The ball has touch as soon as its top is below the edge.             */
        return ball.getY() + BALL_RADIUS * 2 > getHeight() || ball.getY() <= 0;
    }

    private void drawWall(int brickHeight, int brickWidth, int bricksColumn, int brickSep, int brickYOffset, int nbrickRows) {
        for (int y = 0; y < nbrickRows; y++) {
            Color [] colorBrick = {Color.red, Color.red, Color.orange, Color.orange, Color.YELLOW,Color.YELLOW,
                    Color.GREEN,Color.GREEN, Color.CYAN, Color.CYAN};
            Color color = colorBrick[y];

            drowWallRow(bricksColumn, y, brickWidth, brickHeight,color, brickSep, brickYOffset);  // in cicle we drows ten rows
        }
    }

    private void drowWallRow(int bricksColumn, int y, int brickWidth, int brickHeight, Color color, int brickSep, int brickYOffset){
        // in cicle we drows ten  brisk(column)
        for (int x = 0; x < bricksColumn; x++) {
            drowBrick(x, y, brickWidth, brickHeight, color, brickSep, brickYOffset);
        }
    }

    private void drowBrick(int x, int y, int brickWidth, int brickHeight, Color color, int brickSep, int brickYOffset) {

        double marTCRig = (getWidth() / 2 - (double) BRICKS_COLUMN * (brickWidth + brickSep) / 2);

        GRect brick = new GRect(marTCRig + x * (brickWidth + brickSep), brickYOffset + y * (brickHeight + brickSep), brickWidth, brickHeight);
        brick.setFilled(true);
        brick.setFillColor(color);
        add(brick);
        pause(1000.0 / 200);
    }

    private GOval makeBall() {
        GOval ball = new GOval((getWidth() - 2 * BALL_RADIUS) / 2.0, (getHeight() - BALL_RADIUS) / 2.0,
                BALL_RADIUS * 2, BALL_RADIUS * 2);
        ball.setFilled(true);
        ball.setColor(Color.BLACK);
        return ball;
    }


    private GRect creatRocket(int w, int h, int rocYOff) {
        GRect rocket = new GRect((getWidth() - w) / 2, getHeight() - rocYOff - h, w, h);
        rocket.setColor(Color.BLACK);
        rocket.setFilled(true);
        rocket.setFillColor(Color.BLACK);
        add(rocket);
        return rocket;

    }

    private void addLabel() {
        RandomGenerator rgen = RandomGenerator.getInstance();
        tracker = new GLabel("Raund&Leavs", 5, 20);
        tracker.setFont("DejaVuSerif-" + rgen.nextInt(10, 20));  // type of fonts
        tracker.setVisible(true);
        add(tracker);
    }

}

