package com.shpp.p2p.cs.dgrymach.exam;


/*
 * File: exam.java
 * ------------------------------
 * This file declares several constants that are shared by the
 * different modules in the NameSurfer application.  Any class
 * that implements this interface can use these constants.
 */

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class exam extends WindowProgram implements constants {

    private ArrayList<superOval> listBall = new ArrayList<>();

    @Override
    public void init() {

        addActionListeners();  // add listerens in our canvas for button and rocker
        addMouseListeners();
    }
        superOval temp ;
    @Override
    public void mousePressed(MouseEvent e) {
        println(e.getWhen());

    }

    /**
     * Repositions the moved object to the mouse's location when the mouse  is moved.
     *
     * @param e- event which return program mouse event
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        println(e.getWhen());
        Object temp;
        if (listBall.contains(temp = getElementAt(e.getX(), e.getY()))) {
            ((superOval) temp).fallingInTop = true;
        } else {
             createNewBall(e.getX(), e.getY());
        }

    }

    private void createNewBall(int x, int y) {
        superOval ball = new superOval(x, y, BALL_RADIUS, BALL_RADIUS);
        ball.setColor(START_COLOR);
        ball.setFillColor(START_COLOR);
        ball.setFilled(true);

        listBall.add(ball);

        add(ball) ;
    }

    @Override
    public void run() {
        waitForClick();
        while (listBall.size() > 0) {
            for (superOval ball : listBall) startPerfomeOneBall((superOval) ball);
            pause(PAUSE_TIME);
        }
    }

    private void startPerfomeOneBall(superOval ball) {
        double dy = ball.gravity;

        /* Until the ball hits the ground, keep simulating the ball falling. */
        if (isBallInWindow(ball)) {
            if (ball.fallingInTop) {
                dy -= VY_GRAVITY;
            } else {
                dy += VY_GRAVITY;
            }
        } else {
            if (ball.fallingInTop) {
                dy *= -ELASTICITY;
                moveBallOutOfTop(ball);
            } else {
                dy *= -ELASTICITY;
                moveBallOutOfGround(ball);
            }

        }

        ball.move(0, dy);
        ball.gravity = dy;
    }

    private void moveBallOutOfTop(superOval ball) {
        ball.move(0, 0 - (ball.getY()));
    }

    private boolean isBallInWindow(GOval ball) {
        return ball.getY() + ball.getHeight() < MY_HEIGHT && ball.getY() >= 0;
    }

    private void moveBallOutOfGround(GOval ball) {
        ball.move(0, MY_HEIGHT - (ball.getY() + ball.getHeight()));
    }


    public class superOval extends GOval {
        double gravity = 0;
        boolean fallingInTop = false;

        superOval(double v, double v1, double v2, double v3) {
            super(v, v1, v2, v3);
        }
    }
}
