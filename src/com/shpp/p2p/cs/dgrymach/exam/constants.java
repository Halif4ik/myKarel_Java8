package com.shpp.p2p.cs.dgrymach.exam;
/*
 * File: exam.java
 * ------------------------------
 * This file declares several constants that are shared by the
 * different modules in the NameSurfer application.  Any class
 * that implements this interface can use these constants.
 */

import java.awt.*;

public interface constants {
    /**
     * Width and height of application window in pixels
     */
    public static final int APPLICATION_WIDTH = 600;
    public static final int APPLICATION_HEIGHT = 400;

    public static final int PAUSE_TIME = 1000/50;

    /**
     * Dimensions of game board (usually the same)
     */

    public static final int MY_HEIGHT = APPLICATION_HEIGHT;

    /**
     * Start radius of the ball in pixels
     */
    public static final int BALL_RADIUS = 50;

    /**
     * start speed which  ball begin fall
     */
    public   double VY_GRAVITY = 0.5;

    public   double ELASTICITY = 0.6;

    public static final Color START_COLOR = new Color(255, 255, 0);
}
