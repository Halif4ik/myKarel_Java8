package com.shpp.p2p.cs.dgrymach.assignment3;

import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Asigment3part6 extends WindowProgram {
    /* The size of the ball. */
    private static final double BALL_SIZE = 30;

    /* The amount of time to pause between frames (48fps). */
    private static final double PAUSE_TIME = 1000.0 / 50;

    /* The initial horizontal velocity of the ball. */
    private static final double HORIZONTAL_VELOCITY = 10.0;

    /* Gravitational acceleration. */
    private static final double VERTIKAL_VELOCITY = 10.5;

    private static final Color CARDINAL_RED = new Color(196, 30, 58);
    private static final Color PASTEL_GREEN = new Color(119, 203, 116);
    private static final Color CALIFORNIA = new Color(230, 145, 56);
    private static final double CONVERTATION_TO_SECOND = 1000;
    private static final int COUNTER_ITERATION = 250;
    private static final int ARRAY_COLOR = 5;
    private static final long TIME_MILI_SECOND = 5000;

    class Time { // create class for remember time  work program
        long startAnimation;
        long startAllProgram;
        long theEnd;
    }

    @Override
    public void run() {
        Time clock = new Time();
                /* get the starting point of time from the system*/
        clock.startAllProgram = System.currentTimeMillis();
                /*  array different fife color, for change fill our ball.*/
        Color[] colorBall = makeArrayColors();

        GRect ball = makeBall();
        GRect fon = makefon();
        add(fon);
        fon.sendToBack();
        add(ball);
        moveBall(ball,HORIZONTAL_VELOCITY,VERTIKAL_VELOCITY,colorBall,clock);

        println( "The total time is: "+ (clock.theEnd-clock.startAllProgram)/CONVERTATION_TO_SECOND + " second" ); // print reaul our adge 5 second
        println("The time of animation  is: "+ (clock.theEnd-clock.startAnimation)/CONVERTATION_TO_SECOND + " second");

    }

    /**
     * @param ball - object ball  which are move in cikle
     * @param dx
     * @param dy
     * @param colorBall
     * @param clock
     *
     */
    private void moveBall(GRect ball, double dx, double dy, Color[] colorBall, Time clock) {
        clock.startAnimation = System.currentTimeMillis(); // get the starting point of time from the system before start animation
        int i= 0;
        while ( TIME_MILI_SECOND >= (clock.theEnd-clock.startAnimation) ) {
            int randoomColor = RandomGenerator.getInstance().nextInt(0, colorBall.length - 1);
            ball.move(dx, dy);
            if (ballTouchYEdge(ball)) {
                ball.setColor(colorBall[randoomColor]);
                dy = -dy;
            } else if (ballTouchXEdge(ball)) {
                ball.setColor(colorBall[randoomColor]);
                dx = -dx;
            }
            pause(PAUSE_TIME);
            clock.theEnd = System.currentTimeMillis () ; // get the end point of time
            i++;
        //}  while ( i < COUNTER_ITERATION );
        }

    }

    /**
     * @param ball - object ball from which are taken coordinates to determine its location
     * @return - The romb has touch as soon as its left || right  the edge, function return 'true'
     */
    private boolean ballTouchXEdge(GRect ball) {
        return ball.getX() + ball.getWidth() >= getWidth() || ball.getX() <= 0;

    }

    /**
     * @param ball - object ball from which are taken coordinates to determine its location
     * @return -  The romb has touch as soon as its top is below the edge, function return 'true'
     */
    private boolean ballTouchYEdge(GRect ball) {
        return ball.getY() + ball.getHeight() >= getHeight() || ball.getY() <= 0;
    }

    /**
     *
     * @return - array different fife color for change fill our ball.
     */
    private Color[] makeArrayColors() {
        Color[] colorBall = new Color[ARRAY_COLOR];
        colorBall[0] = CARDINAL_RED;
        colorBall[1] = PASTEL_GREEN;
        colorBall[2] = CALIFORNIA;
        colorBall[3] = Color.CYAN;
        colorBall[4] = Color.YELLOW;
        return  colorBall;
    }

    /**
     * @return -Rect which we meak as fon
     */
    private GRect makefon() {
        GRect fon = new GRect(0, 0,
                getWidth(), getHeight());
        fon.setFilled(true);
        fon.setColor(Color.LIGHT_GRAY);
        return fon;
    }

    /**
     * @return - object ball  which are move in future
     */
    private GRect makeBall() {
        GRect ball = new GRect((getWidth() - 2 * BALL_SIZE) / 2.0, (getHeight() - BALL_SIZE) / 2.0,
                BALL_SIZE * 2, BALL_SIZE * 2);
        ball.setFilled(true);
        ball.setColor(CALIFORNIA);
        return ball;
    }
}
