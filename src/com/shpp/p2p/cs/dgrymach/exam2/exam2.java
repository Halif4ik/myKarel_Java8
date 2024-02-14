package com.shpp.p2p.cs.dgrymach.exam2;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class exam2 extends WindowProgram implements Constants {
    /* Initialize and add interactors to the display.   */
    public void init() {
        addActionListeners();
        addMouseListeners();
    }

    /* Array whit all our ball whats we creat and add in canvas*/
    private ArrayList<SuperBall> listBalls = new ArrayList<>();

    @Override
    public void mousePressed(MouseEvent e) {
        Object cliksObj;
        if (listBalls.contains(cliksObj = getElementAt(e.getX(), e.getY()))) {
            /*if we clikc on ball we our obj privodim in type SuperBall and change his trajectory move */
            ((SuperBall) cliksObj).fallInTop = !((SuperBall) cliksObj).fallInTop;
        } else {
            /*create new ball and add its in array and add in canvas*/
            createNewBall(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        /*if in arrL presetn ball what stopped we change his fild srattmome on true and begin his move
         * also stop  increment his size*/
        for (int i = 0; i < listBalls.size(); i++)
            if (!listBalls.get(i).startMove) {
                listBalls.get(i).startMove = true;
                listBalls.get(i).stopIncrSize = true;
                listBalls.get(i).stopChangColor = true;
            }
    }

    @Override
    public void run() {

        for (; ; ) { //while not the end of the world
            /*go around for array for each ball*/
            for (int i = 0; i < listBalls.size(); i++) {
                SuperBall ball = listBalls.get(i);
                /*for each ball in array how not new create, we start perfoms*/
                if (ball.startMove)
                    startPerfomeOneBall(ball);
                else {
                    /*else if current ball new, we  begin increment dimentions new ball*/
                    ball.incrementWH(ball);
                    ball.decrementColor(ball);
                }
            }
            pause(PAUSE_TIME);
        }
    }

    /**
     *
     */
    private void startPerfomeOneBall(SuperBall ball) {
        double dy = ball.getDy();
        /* Until the ball not hits the ground or top, keep simulating the ball falling. */
        if (isBallInWindow(ball)) {
            /*if ball invers it falling in up*/
            if (ball.fallInTop) {
                dy -= GRAVITY;
            } else { // it is normal bal and fall in down
                dy += GRAVITY;
            }
            /*else if ball leave dimentions start canvas */
        } else {
            /*"if" for ball invers it falling in up*/
            if (ball.fallInTop) {
                moveBallOutOfTop(ball);
                dy *= -ELASTICITY;

            } else {
                moveBallOutOfGround(ball);
                dy *= -ELASTICITY;
            }

        }
        /*move one ball for cuttenr dy whith elastisiti or graviti*/
        ball.move(0, dy);
        /*remember new dy for current ball*/
        ball.setDy(dy);
    }


    class SuperBall extends GOval {
        /* new ball not move its wait for muuse event relased*/
        boolean startMove = false;

        boolean stopIncrSize = false;

        boolean stopChangColor = false;

        boolean fallInTop = false;

        private double gravitDy = 0;

        SuperBall(double v, double v1, double v2, double v3) {
            super(v, v1, v2, v3);
        }

        public double getDy() {
            return gravitDy;
        }

        public void setDy(double dy) {
            this.gravitDy = dy;
        }

        private void incrementWH(SuperBall ball) {

            double newHight = ball.getHeight() + 2;
            double newWid = ball.getWidth() + 2;
            ball.setSize(newWid, newHight);
        }

        /* change  black color from white*/
        private void decrementColor(SuperBall ball) {
            Color newColor = ball.getColor();
            int newRed = newColor.getRed() - INDX_DECREMENT_COLOR;
            int newGreen = newColor.getGreen() - INDX_DECREMENT_COLOR;
            int newBlue = newColor.getBlue() - INDX_DECREMENT_COLOR;

            if (newRed < 0) newRed = 0;
            if (newBlue < 0) newBlue = 0;
            if (newGreen < 0) newGreen = 0;

            newColor = new Color(newRed, newGreen, newBlue);
            ball.setColor(newColor);
            ball.setFillColor(newColor);
        }
    }
//----------end SuperBall class-----//

    /**
     * Moves the ball out of the fall (top).
     *
     * @param ball The ball to move out of the ground.
     */
    private void moveBallOutOfTop(SuperBall ball) {
        ball.move(0, 0 - (ball.getY()));
    }

    /**
     * Returns whether the given ball is on the screen.
     *
     * @param ball The ball to check.
     * @return Whether it's on-screen.
     */
    private boolean isBallInWindow(SuperBall ball) {
        return (ball.getY() + ball.getHeight()) < getHeight() && ball.getY() > 0;
    }

    /**
     * Moves the ball out of the ground.
     *
     * @param ball The ball to move out of the ground.
     */
    private void moveBallOutOfGround(SuperBall ball) {
        ball.move(0, APPLICATION_HEIGHT - (ball.getY() + ball.getHeight()));
    }

    /**
     * Creat new ball  in  current coordinats  and whith base dimentions add new  creat ball in ArrL
     *
     * @param x-current  coordinat where mouse did clik
     * @param y--current coordinat where mouse did clik
     */
    private void createNewBall(int x, int y) {
        SuperBall sball = new SuperBall(x, y, BALL_RADIUS_START, BALL_RADIUS_START);
        sball.setFilled(true);
        sball.setFillColor(START_COLOR);
        sball.setColor(START_COLOR);

        listBalls.add(sball);
        add(sball);

    }

}
