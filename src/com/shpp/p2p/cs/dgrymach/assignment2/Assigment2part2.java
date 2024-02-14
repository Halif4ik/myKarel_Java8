package com.shpp.p2p.cs.dgrymach.assignment2;
import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assigment2part2 extends WindowProgram{
    public static final int APPLICATION_WIDTH = 300;
    public static final int APPLICATION_HEIGHT = 300;
    public  final  int DIAMETR_BLACK_CIRKLE = readInt("Please enter diametr circle etc.80:");

    public static  final Color colorCirkle = Color.black;
    public static  final Color colorRect = Color.WHITE;


    public  void run(){
            makeBlackCircle(0,0,DIAMETR_BLACK_CIRKLE, colorCirkle);
            makeBlackCircle(getWidth()-DIAMETR_BLACK_CIRKLE,0,DIAMETR_BLACK_CIRKLE, colorCirkle);
            makeBlackCircle(0,getHeight()-DIAMETR_BLACK_CIRKLE,DIAMETR_BLACK_CIRKLE, colorCirkle);
            makeBlackCircle(getWidth()-DIAMETR_BLACK_CIRKLE,getHeight()-DIAMETR_BLACK_CIRKLE,DIAMETR_BLACK_CIRKLE, colorCirkle);

            makeWhiteRectangle(DIAMETR_BLACK_CIRKLE/2, DIAMETR_BLACK_CIRKLE/2,getWidth() - DIAMETR_BLACK_CIRKLE,getHeight() - DIAMETR_BLACK_CIRKLE, colorRect);

    }
    /**
     * this method  create new objeck  as white rect
     * @param colorRect     -
     * @param diam -
     * @param higth -
     * @param x -
     * @param y-
     *
     * **/

    private void makeWhiteRectangle(int x, int y, int diam, int higth, Color colorRect) {
        GRect cirkl = new GRect(x,y,diam,higth);
        cirkl.setFilled(true);
        cirkl.setColor(colorRect);
        add(cirkl);
    }

    /**
     * this method  create new objeck as black cirkle
     * @param colorCirkle    -
     * @param diam -
     * @param x -
     * @param y-
     *
     * **/
    private  void makeBlackCircle(int x, int y, int diam, Color colorCirkle) {
        GOval cirkl = new GOval(x,y,diam,diam);
        cirkl.setFilled(true);
        cirkl.setColor(colorCirkle);
        add(cirkl);

    }
}
