package com.shpp.p2p.cs.dgrymach.exam2;

import java.awt.*;

public interface Constants {
    /**
     * Width and height of application window in pixels
     */
    public static final int APPLICATION_WIDTH = 600;
    public static final int APPLICATION_HEIGHT = 400;

    public static final int MY_HIGTH = APPLICATION_HEIGHT;

    public static final int PAUSE_TIME = 1000 / 50;

    /**
     * Start radius of the ball in pixels
     */
    public static final int BALL_RADIUS_START = 5;

    /**
     * start speed which  ball begin fall
     */
    public double GRAVITY = 0.5;

    public double ELASTICITY = 0.6;

    public static final Color START_COLOR = new Color(254, 254, 254);

    public static final int INDX_DECREMENT_COLOR = 5;
}
