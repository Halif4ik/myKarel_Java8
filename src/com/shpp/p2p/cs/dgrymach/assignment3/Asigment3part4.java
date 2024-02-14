package com.shpp.p2p.cs.dgrymach.assignment3;

import acm.graphics.GRect;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
//24.07.2018
import static java.awt.Color.CYAN;

public class Asigment3part4 extends WindowProgram {

    private static final int BRICK_HEIGHT = 30;
    private static final int BRICK_WIDTH = 50;
    private static final int BRICKS_IN_BASE = 10;
    private static final int DISTANSE_FROM_DOWN = 1;
    private static final Color CARDINAL_RED = new Color(196, 30, 58);
    private static final Color PASTEL_GREEN = new Color(119, 203, 116);
    private static final Color CALIFORNIA = new Color(230, 145, 56);
    private static final int ARRAY_COLOR = 5;

    public static final int APPLICATION_HEIGHT = BRICK_HEIGHT * BRICKS_IN_BASE + BRICK_HEIGHT;
    public static final int APPLICATION_WIDTH = BRICK_WIDTH * BRICKS_IN_BASE + BRICK_WIDTH;

    public void run() {
        double dimWightPyram = (double) BRICKS_IN_BASE * BRICK_WIDTH; // calculete dimensions our picture
        drawOurPyramid((getWidth() - dimWightPyram) / 2, (getHeight() - BRICK_HEIGHT));  // locate singer in cornr convas whith ascent возхождение but because of the inverted coordinates, subtract

    }

    /**
     * Print in cicle one row of pyramid
     *
     * @param startCoordinateX -  down coordinare our all pyramid
     * @param startCoordinateY - left coordinare our all pyramid
     */
    private void drawOurPyramid(double startCoordinateX, double startCoordinateY) {
        int margin = BRICK_WIDTH / 2;         //  margin next row on half brick  to right смещаем
        //* each next row decrement one brick for base*/
        for (int i = 0; i < BRICKS_IN_BASE; i++)
            drawOneBrickInRow(startCoordinateX + (i * margin), startCoordinateY, i, BRICKS_IN_BASE - i);

    }

    /**
     * Draw one Brick in cikl, whith start coordinate x,y. and change color evry column? create array color from fife element
     *
     * @param startCoordinateX - down coordinare our all pyramid
     * @param startCoordinateY - left coordinare our all pyramid
     * @param row - roe which draw now
     * @param bricksInBase - start noumber brick
     */
    private void drawOneBrickInRow(double startCoordinateX, double startCoordinateY, int row, int bricksInBase) {

        Color[] colorRow = makeArrayColors();

        // int randoomNumberColor =  RandomGenerator.getInstance().nextInt(0,colorRow.length-1);

        for (int j = 0; j < bricksInBase; j++) {
            Color nextColor = colorRow[j % colorRow.length];
            drawBrick(startCoordinateX, startCoordinateY, row, j, nextColor);
        }
    }

    /**
     *
     * @return array different fife color for change fill our ball.
     */
    private Color[] makeArrayColors() {
        Color[] colorRow = new Color[ARRAY_COLOR];
        colorRow[0] = CARDINAL_RED;
        colorRow[1] = PASTEL_GREEN;
        colorRow[2] = CALIFORNIA;
        colorRow[3] = Color.CYAN;
        colorRow[4] = Color.YELLOW;
        return  colorRow;
    }

    /**
     * drow one rect/rectangle and have pause 25 msec, tobto 40 itenation cicle in one secont time.
     *
     * @param startCoordinateX - start coordinate pyramid
     * @param startCoordinateY - start coordinate pyramid
     * @param row              - noumder row which we now draw
     * @param j-               noumder column which we now draw
     * @param nextColor        -color which need in this column or row
     */
    private void drawBrick(double startCoordinateX, double startCoordinateY, int row, int j, Color nextColor) {


        GRect my = new GRect(startCoordinateX + j * BRICK_WIDTH, startCoordinateY - row * BRICK_HEIGHT, BRICK_WIDTH, BRICK_HEIGHT);

        my.setFilled(true);
        my.setColor(Color.BLACK);
        my.setFillColor(nextColor);
        add(my);
        pause(1000.0 / 40);
    }


}
