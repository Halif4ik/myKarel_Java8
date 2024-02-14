package com.shpp.p2p.cs.dgrymach.assignment4;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;


public class assignment4part1 extends WindowProgram {

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
     * Dimensions of the paddle
     */
    private static final int ROCKET_WIDTH = 60;
    private static final int ROCKET_HEIGHT = 10;

    /**
     * Offset of the paddle up from the bottom
     */
    private static final int PADDLE_Y_OFFSET = 30;

    /**
     * Number of bricks per row
     */
    private static final int NBRICKS_PER_ROW = 10;

    /**
     * Number of rows of bricks
     */
    private static final int NBRICK_ROWS = 10;

    /**
     * Separation between bricks
     */
    private static final int BRICK_SEP = 4;

    /**
     * Width of a brick
     */
    private static final int BRICK_WIDTH = (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;
    /**
     * Height of a brick and dimension wight all wall from our brick
     */
    private static final int BRICK_HEIGHT = 8;
    private static final double DIMENSION_WIGHT_WALL = (double) NBRICKS_PER_ROW * BRICK_WIDTH + ((NBRICKS_PER_ROW - 1) * BRICK_SEP);

    /**
     * Radius of the ball in pixels
     */
    private static final int BALL_RADIUS = 10;

    /**
     * Offset of the top brick row from the top
     */
    private static final int BRICK_Y_OFFSET = 70;

    /**
     * Number of turns
     */
    private static final int NTURNS = 3;
    /**
     * start speed which  ball begin fall
     */
    private static final double START_VY_SPEED = 3.0;
    /**
     * division in this number we conert milisecond to second
     */
    private static final double CONVERTATION_TO_SECOND = 1000;


    private static final Color PASTEL_GREEN = new Color(119, 203, 116);

    /**
     * Create array colors, from which we get color fo next row
     *
     * @return variable array Color
     */
    private Color[] makeArrayColors() {
        Color[] colorBall = {
                Color.RED,
                Color.ORANGE,
                Color.YELLOW,
                PASTEL_GREEN,
                Color.CYAN
        };
        return colorBall;
    }

    private boolean cheatbool;
    public GRect rocket;
    public GOval ball;
    private GLabel massageLifeDicremenOne, trecker;

    /**
     * marks the beginning run of the program
     */
    private static long START_GAME = System.currentTimeMillis();

    /**
     * array different fife color, for change fill our ball.
     */
    Color[] colorBall = makeArrayColors();
    /* boolean veriable for chance play with cheats*/

    public class Ball {
        double vx;
        double vy = START_VY_SPEED;
        double startPositionX;
        double startPositionY;
        int allBricks = NBRICK_ROWS * NBRICKS_PER_ROW;
    }

    /* Sliders for speed our game. */
    private JSlider pauseTime;
    /* Button for start foul play. */
    private JButton cheat;

    Toolkit zvuk = Toolkit.getDefaultToolkit();

    @Override
    public void init() {
        add(new JLabel("PauseTime form one ms to 20 ms "), SOUTH);
        pauseTime = new JSlider(1, 1000 / 20, 1000 / 120); //select fps from 50 to 1000 fps start initial 120 fps
        add(pauseTime, SOUTH);

        cheat = new JButton("Turn on cheats");//select boolean cheatbool true or fa
        add(cheat, NORTH);
        addActionListeners();  // add listerens in our canvas for button and rocker
        addMouseListeners();
    }

    /**
     * Repositions the moved object to the mouse's location when the mouse  is moved.
     *
     * @param e- event which return program mouse event
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        if (rocket != null && e.getX() + ROCKET_WIDTH < getWidth() && e.getX() > 0) {
            rocket.setLocation(e.getX(), rocket.getY());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cheat)
            cheatbool = !cheatbool;
    }

    @Override
    public void run() {
        Ball velocity = new Ball();

        ball = makeBall(velocity);
        rocket = makeRocket(ROCKET_WIDTH, ROCKET_HEIGHT, PADDLE_Y_OFFSET);
        add(rocket);
        addTracker();
        drawWall((getWidth() - DIMENSION_WIGHT_WALL) / 2, BRICK_Y_OFFSET);
        /*start play game*/
        for (int i = NTURNS; i > 0; i--) {
            if (velocity.allBricks == 0) break;
            waitForClick();
            /*if you have already played once, then remove the inscription about the lost life*/
            if (massageLifeDicremenOne != null) remove(massageLifeDicremenOne);
            add(ball, velocity.startPositionX, velocity.startPositionY);
            /* start move bal*/
            runGame(ball, velocity, i);
            remove(ball);
            add(massageLifeDicremenOne = printDecremenLife());
        }
        removeAllAndPrintResults(velocity);
    }

    /**
     * @param ball
     * @param velocity
     * @param nTurns
     */
    private void runGame(GOval ball, Ball velocity, int nTurns) {
        double vx = 0;
        double vy = START_VY_SPEED;

        while (velocity.allBricks > 0) {
            ball.move(vx, vy);
            GObject collider = getCollidingObject(ball);
            if (ballTouchTopEdgeY(ball) || collider == rocket || collider != null) {
                if (collider != rocket && collider instanceof GRect) {
                    remove(collider);
                    velocity.allBricks--;
                }
                if (velocity.allBricks == 0)
                    break;
                vx = fiftyOnFiftyChangeVelocityVectorX(vx);
                vy = -vy;
            } else if (ballTouchXEdge(ball)) {
                vx = -vx;
                zvuk.beep();
            } else if (isBallUnderGround(ball)) {
                if (cheatbool) {
                    vx = fiftyOnFiftyChangeVelocityVectorX(vx);
                    vy = -vy;
                } else {
                    break;
                }

            }
            velocity.vx = vx;
            velocity.vy = vy;
            pause(getPauseTime());
            showInfoInTracker(trecker, velocity, nTurns);
        }
    }


    /**
     * @param trecker  -  The treker to exchenge
     * @param velocity - class which save info from our game
     * @param nTurns   -  number of lives that keep
     */
    private void showInfoInTracker(GLabel trecker, Ball velocity, int nTurns) {
        long currentTime = System.currentTimeMillis(); // get the end point of time
        trecker.setLabel((int) ((currentTime - START_GAME) / CONVERTATION_TO_SECOND) + " sec /" + velocity.allBricks + " br/" + nTurns + " life");
    }

    /**
     * Returns the currently-selected speed ball.
     *
     * @return The current pause time setting
     */
    private double getPauseTime() {
        return pauseTime.getValue();
    }

    /**
     * Returns whether the ball is underground
     *
     * @param ball- The ball to check
     * @return - Whether ball underground.
     */
    private boolean isBallUnderGround(GOval ball) {
        return ball.getY() + ball.getHeight() >= getHeight();
    }

    /**
     * var for velocity our ball
     *
     * @param ball
     */
    private GObject getCollidingObject(GOval ball) {
        GObject collider;
        if ((collider = getElementAt(ball.getX(), ball.getY())) != null) return collider;
        if ((collider = getElementAt(ball.getX(), ball.getY() + ball.getHeight())) != null) return collider;
        if ((collider = getElementAt(ball.getX() + ball.getWidth(), ball.getY())) != null) return collider;
        if ((collider = getElementAt(ball.getX() + ball.getWidth(), ball.getY() + ball.getHeight())) != null)
            return collider;

        return null;
    }

    private void drawWall(double startCoordinateX, double startCoordinateY) {
        // veribel where we put necessary color
        colorBall = makeArrayColors();

        for (int i = 0; i < NBRICK_ROWS; i++) {
            Color nextColor = colorBall[i / 2];
            drawOneRow(startCoordinateX, startCoordinateY, i, nextColor);
        }
    }

    private void drawOneRow(double startCoordinateX, double startCoordinateY, int row, Color nextColor) {
        for (int j = 0; j < NBRICKS_PER_ROW; j++) drawBrick(startCoordinateX, startCoordinateY, row, j, nextColor);
    }

    private void drawBrick(double startCoordinateX, double startCoordinateY, int row, int col, Color nextColor) {
        GRect my = new GRect(startCoordinateX + col * (BRICK_WIDTH + BRICK_SEP), startCoordinateY + row * (BRICK_HEIGHT + BRICK_SEP),
                BRICK_WIDTH, BRICK_HEIGHT);

        my.setFilled(true);
        my.setColor(nextColor);
        my.setFillColor(nextColor);
        add(my);
        pause(getPauseTime());
    }

    private double fiftyOnFiftyChangeVelocityVectorX(double vx) {
        RandomGenerator rgen = RandomGenerator.getInstance();
        vx = rgen.nextDouble(1.0, 3.0);
        if (rgen.nextBoolean(0.5))
            vx = -vx;
        return vx;
    }

    /**
     * The ball has touch as soon as its left || right  the edge, function return 'true'
     *
     * @param ball - object ball from which are taken coordinates to determine its location
     * @return - Whether it's on-canvas.
     */
    private boolean ballTouchXEdge(GOval ball) {
        return ball.getX() + ball.getWidth() >= getWidth() || ball.getX() <= 0;

    }

    private void removeAllAndPrintResults(Ball velocity) {
        removeAll();
        GLabel labelResult;
        if (velocity.allBricks > 0) {
            labelResult = new GLabel("You lose.=(");
        } else {
            labelResult = new GLabel("Chiters WiN!!!=)");
        }
        labelResult.setFont("DejaVuSerif-35");
        labelResult.setVisible(true);
        labelResult.setLocation((getWidth() - labelResult.getWidth()) / 2, ((getHeight() - labelResult.getDescent()) / 2));
        add(labelResult);

    }

    private GLabel printDecremenLife() {
        GLabel l = new GLabel("You LoL turn again!=)");
        l.setFont("Times-20");
        l.setVisible(true);
        l.setLocation(getWidth() - l.getWidth(), (getHeight() - l.getDescent()));
        add(l);
        return l;
    }

    /**
     * @param ball - object ball from which are taken coordinates to determine its location
     * @return -  The romb has touch as soon as its top is below the edge, function return 'true'
     */
    private boolean ballTouchTopEdgeY(GOval ball) {
        return ball.getY() <= 0;
    }

    private GRect makeRocket(int rocketWidth, int rocketHeight, int paddleYOffset) {
        GRect rocket = new GRect((getWidth() - rocketWidth) / 2.0, (getHeight() - rocketHeight - paddleYOffset),
                rocketWidth, rocketHeight);
        rocket.setFilled(true);
        rocket.setFillColor(Color.BLACK);
        return rocket;

    }

    /**
     * add leable in canvas
     */
    private void addTracker() {
        trecker = new GLabel("Time/Brick/Leave");
        trecker.setFont("DejaVuSerif-13");
        add(trecker, 0, getHeight() - trecker.getDescent());
    }

    private GOval makeBall(Ball velocity) {
        velocity.startPositionX = (getWidth() - 2 * BALL_RADIUS) / 2.0;
        velocity.startPositionY = (getHeight() - BALL_RADIUS) / 2.0;
        GOval ball = new GOval(velocity.startPositionX, velocity.startPositionY,
                BALL_RADIUS * 2, BALL_RADIUS * 2);
        ball.setFilled(true);
        ball.setColor(Color.black);
        return ball;

    }

}




