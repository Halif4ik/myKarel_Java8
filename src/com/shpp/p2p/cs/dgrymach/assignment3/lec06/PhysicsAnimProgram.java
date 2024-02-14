package com.shpp.p2p.cs.a.lectures.lec06;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class PhysicsAnimProgram extends WindowProgram {
    /* The size of the ball. */
    private static final double BALL_SIZE = 50;

    /* The amount of time to pause between frames (48fps). */ //Время паузы между кадрами (48fps)
    private static final double PAUSE_TIME = 1000.0 / 50;

    /* The initial horizontal velocity of the ball. */
    private static final double HORIZONTAL_VELOCITY = 1.0;

    /* Gravitational acceleration. */
    private static final double GRAVITY = 0.425;

    /* Elasticity. */
    private static final double ELASTICITY = 0.75;

    public void run() {
        GOval ball = makeBall(BALL_SIZE);
        add(ball);
        bounceBall(ball);
    }

    /**
     * Creates a ball that can be bounced, placing it in the upper-left corner
     * of the screen.
     *
     * @return A ball that can be bounced.
     */
    private GOval makeBall(double h) {
        GOval ball = new GOval(0, 0, h, h);
        ball.setFilled(true);
        ball.setColor(Color.BLUE);
        return ball;
    }

    /**
     * Simulates a bouncing ball.
     *
     * @param ball The ball to bounce.
     */
    private void bounceBall(GOval ball) {
		double dy = 0;

        while (true) {
            ball.move(HORIZONTAL_VELOCITY, dy);
            dy += GRAVITY;
            if (ballBelowFloor(ball) && dy > 0 ) {
                dy *= -ELASTICITY; // 10 * -(0.5) ==> -5
            }

            pause(1000.0 / 50);
        }
    }

    /**
     * Determines whether the ball has dropped below floor level.
     *
     * @param ball The ball to test.
     * @return Whether it's fallen below the floor.
     */
    private boolean ballBelowFloor(GOval ball) {
        return ball.getY() + BALL_SIZE > getHeight();
    }
}
