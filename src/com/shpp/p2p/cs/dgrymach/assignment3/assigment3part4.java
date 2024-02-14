package com.shpp.p2p.cs.dgrymach.assignment3;

import acm.graphics.GRect;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.util.Random;

public class assigment3part4 extends WindowProgram {

    private static final int BRICK_HEIGHT = 40;
    private static final int BRICK_WIDTH = 60;
    private static final int BRICKS_IN_BASE = 22;
    private static final int DISTANSE_FROM_DOWN =2;

    public static final int APPLICATION_HEIGHT = BRICKS_IN_BASE*BRICK_HEIGHT+BRICK_HEIGHT;
    public static final int APPLICATION_WIDTH = BRICKS_IN_BASE*BRICK_WIDTH+BRICK_WIDTH;

                public void run() {
            drawPirramid(BRICK_HEIGHT, BRICK_WIDTH, BRICKS_IN_BASE);
        }

                private void drawPirramid(int bHeig, int bwidt, int brInBase) {
            for (int y = BRICKS_IN_BASE; y >=0; y--) {
                drowPiramRow(brInBase, y, bwidt, bHeig);
                }
        }


    private void drowPiramRow(int brInBase, int y, int bwidt, int bHeig) {
                    int xStart = (getWidth()-bwidt*brInBase)/2;
                    int yStart = (getHeight()-(bHeig+bHeig*y)-DISTANSE_FROM_DOWN); // else erplese in tut y we can glad enahet build piramid
                    RandomGenerator rgen = RandomGenerator.getInstance();
                    Color color = rgen.nextColor();
                  for (int x = 0; x < brInBase-y; x++) {
                      drowBrick (x, y, xStart, yStart, bwidt, bHeig,color);
            }
        }

    private void drowBrick(int x, int y, int xStart, int yStart, int bwidt, int bHeig, Color color) {

        int realKoordX = xStart + x * bwidt+ y *bwidt/2;

        GRect my = new GRect(realKoordX, yStart, bwidt, bHeig);
        my.setFilled(true);
        my.setColor(Color.BLACK);
        my.setFillColor(color);
        add(my);
        pause(30);
    }

}

