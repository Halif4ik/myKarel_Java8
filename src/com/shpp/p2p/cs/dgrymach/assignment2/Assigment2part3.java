package com.shpp.p2p.cs.dgrymach.assignment2;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
/** TODO: Replace these file comments with a description of what your program
 * does.
 */
public class Assigment2part3 extends WindowProgram {
    /**
     * Constants controlling the relative positions of the
     * three toes to the upper-left corner of the pawprint.
     * <p>
     * (Yes, I know that actual pawprints have four toes.
     * Just pretend it's a cartoon animal. ^_^)
     */
    private static final double FIRST_TOE_OFFSET_X = 0;
    private static final double FIRST_TOE_OFFSET_Y = 20;
    private static final double SECOND_TOE_OFFSET_X = 30;
    private static final double SECOND_TOE_OFFSET_Y = 0;
    private static final double THIRD_TOE_OFFSET_X = 60;
    private static final double THIRD_TOE_OFFSET_Y = 20;

    /* The position of the heel relative to the upper-left
     * corner of the pawprint.
     */
    private static final double HEEL_OFFSET_X = 20;
    private static final double HEEL_OFFSET_Y = 40;

    /* Each toe is an oval with this width and height. */
    private static final double TOE_WIDTH = 20;
    private static final double TOE_HEIGHT = 30;

    /* The heel is an oval with this width and height. */
    private static final double HEEL_WIDTH = 40;
    private static final double HEEL_HEIGHT = 60;

    /* The default width and height of the window. These constants will tell Java to
     * create a window whose size is *approximately* given by these dimensions. You should
     * not directly use these constants in your program; instead, use getWidth() and
     * getHeight(), which return the *exact* width and height of the window.
     */
    public static final int APPLICATION_WIDTH = 500;
    public static final int APPLICATION_HEIGHT = 220;


    public void run() {
        /**   Draws a pawprint. The parameters should specify the upper-left corner of the
         bounding box containing that pawprint.
         @param x The x coordinate of the upper-left corner of the bounding box for the pawprint.  ограничивающего прямоугольника для отпечатка.
         @param y The y coordinate of the upper-left corner of the bounding box for the pawprint. */

        drawPawprint(20, 20);
        drawPawprint(180, 70);
    }


    /**
     * @param x-
     * @param y-
     */
    private void drawPawprint(double x, double y) {
        drawToe_1(FIRST_TOE_OFFSET_X + x, FIRST_TOE_OFFSET_Y + y, TOE_WIDTH, TOE_HEIGHT);
        drawToe_1(SECOND_TOE_OFFSET_X + x, SECOND_TOE_OFFSET_Y + y, TOE_WIDTH, TOE_HEIGHT);
        drawToe_1(THIRD_TOE_OFFSET_X + x, THIRD_TOE_OFFSET_Y + y, TOE_WIDTH, TOE_HEIGHT);
        drawToe_1(HEEL_OFFSET_X + x, HEEL_OFFSET_Y + y, HEEL_WIDTH, HEEL_HEIGHT);
    }

    /**
     * @param firstToeOffsetX-
     * @param firstToeOffsetY-
     * @param toeWidth-
     * @param toeHeight -
     */
    private void drawToe_1(double firstToeOffsetX, double firstToeOffsetY, double toeWidth, double toeHeight) {
        GOval my = new GOval(firstToeOffsetX, firstToeOffsetY, toeWidth, toeHeight);
        my.setFilled(true);
        my.setFillColor(Color.BLACK);
        my.setColor(Color.black);
        add(my);
    }
}
