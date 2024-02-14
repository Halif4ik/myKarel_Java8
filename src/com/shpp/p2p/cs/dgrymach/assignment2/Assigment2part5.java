package com.shpp.p2p.cs.dgrymach.assignment2;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assigment2part5 extends WindowProgram {

    public static final int APPLICATION_HEIGHT = 400;
    public static final int APPLICATION_WIDTH = 400;
    public static final int LARGE_CEL = 40;
    public static final int DISTBETWEENSQARE = 10;
    public static final int NUM_ROWS = 5;
    public static final int NUM_COLUM = 6;

    public void run() {
        double dimentionWightChekBoar = (double) NUM_COLUM * LARGE_CEL + ((NUM_COLUM - 1) * DISTBETWEENSQARE); // calculete dimention our picture
        double dimentionHeightChekBoar = (double) NUM_ROWS * LARGE_CEL + ((NUM_ROWS - 1) * DISTBETWEENSQARE); // calculete dimention our picture
        drawChekBoar(NUM_ROWS, NUM_COLUM, dimentionWightChekBoar, dimentionHeightChekBoar);
    }

    /**
     * this metod  in cicle  "for" draw rows chekBoard
     *
     * @param dimentionHeightChekBoar -
     * @param dimentionWightChekBoar  -
     * @param numRows                 -how mach row we need in chekBoard
     * @param numcolum                -how mach column we need in chekBoard
     */
    private void drawChekBoar(int numRows, int numcolum, double dimentionWightChekBoar, double dimentionHeightChekBoar) {
        for (int y = 0; y < NUM_ROWS; y++) {
            drowCekerbordRow(numRows, y, numcolum, DISTBETWEENSQARE, dimentionWightChekBoar, dimentionHeightChekBoar);
        }
    }

    /**
     * this metod  in cicle  "for" draw cell row/
     *
     * @param numRows                 - how mach row we need in chekBoard
     * @param y                       - number column hu drow now
     * @param numcolum                - how mach column we need in chekBoard
     * @param dimentionHeightChekBoar - demention
     * @param distbetweensqare        - global constant
     * @param dimentionWightChekBoar  -
     */
    private void drowCekerbordRow(int numRows, int y, int numcolum, int distbetweensqare, double dimentionWightChekBoar,
                                  double dimentionHeightChekBoar) {
        for (int x = 0; x < NUM_COLUM; x++) {
            drowCell(x, y, numRows, numcolum, LARGE_CEL, distbetweensqare, dimentionWightChekBoar, dimentionHeightChekBoar);
        }

    }

    /**
     * draw rect whith marging fo axis 'x' and 'y'
     *
     * @param x                       - number column? hu drow now
     * @param y                       - number row? hu drow now
     * @param numRows                 -global constant
     * @param numcolum                - global constant
     * @param largeCell               - global constant
     * @param distBetweenCell         -global constant
     * @param dimentionWightChekBoar  -
     * @param dimentionHeightChekBoar -
     */
    private void drowCell(int x, int y, int numRows, int numcolum, int largeCell, int distBetweenCell, double dimentionWightChekBoar, double dimentionHeightChekBoar) {

        int margin = largeCell + distBetweenCell; // the distance  on which we are marging   смещаем
        double startCoordinateX = (getWidth() - dimentionWightChekBoar) / 2; // coordinate x left hight corner our pictures
        double startCoordinateY = (getHeight() - dimentionHeightChekBoar) / 2; // coordinate  y left hight corner our pictures

        GRect my = new GRect(startCoordinateX + x * margin, startCoordinateY + y * margin, largeCell, largeCell);

        my.setFilled(true);
        my.setColor(Color.BLACK);
        my.setFillColor(Color.BLACK);
        add(my);
        pause(1000.0 / 50);
    }
}
