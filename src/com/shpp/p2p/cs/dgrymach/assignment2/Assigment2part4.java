package com.shpp.p2p.cs.dgrymach.assignment2;

import acm.graphics.GLabel;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assigment2part4 extends WindowProgram {
    public static final int APPLICATION_HEIGHT = 400;
    public static final int APPLICATION_WIDTH = 400;
    public static final int WIDTH_STRIPE = 100;
    public static final int HIGTH_STRIPE = 200;
    public static final int NUM_COLUM = 3;
    public static final int ASCENT_SINGER = 8;
    public static final Color colorFirst = Color.black;
    public static final Color colorSecond = Color.YELLOW;
    private static final Color colorThird = new Color(196, 30, 58);

    public void run() {
        drawFlaf((getWidth() - WIDTH_STRIPE * 3) / 2, (getHeight() - HIGTH_STRIPE) / 2); // draw flag whis coordinat x,y in center canvas
        GLabel signature = drawSinger(); //create objeck singer
        add(signature, getWidth() - (signature.getWidth()), getHeight() - signature.getDescent()); // locate singer in cornr convas whith ascent возхождение but because of the inverted coordinates, subtract
    }

    private GLabel drawSinger() {
        GLabel singer = new GLabel("Flag of Ukraine =)");
        singer.setFont("Times-20");
        return singer;

    }

    /**
     * @param x -
     * @param y -
     */
    private void drawFlaf(int x, int y) {
        Color nowColor = null; // veribel where we put necessary color

        for (int i = 0; i < NUM_COLUM; i++) { // cilc  draw  necessarystrips
            switch (i) {
                case 0:
                    nowColor = colorFirst;
                    break;
                case 1:
                    nowColor = colorSecond;
                    break;
                case 2:
                    nowColor = colorThird;
                    break;
            }
            drawStripe(x + i * WIDTH_STRIPE, y, WIDTH_STRIPE, HIGTH_STRIPE, nowColor); // draw one strip of flag whis margin coordinate x
            pause(1000.0 / 5); // five iteration per second 5fps

        }

    }

    /**
     * @param wdth
     * @param hgth
     * @param colorFirst
     */
    private void drawStripe(int x, int y, int wdth, int hgth, Color colorFirst) {
        GRect stripe = new GRect(x, y, wdth, hgth);
        stripe.setFilled(true);
        stripe.setColor(colorFirst);
        add(stripe);
//        stripe.setFillColor(Color.green);
//        stripe.setColor(Color.red);
//        stripe.setSize(wdth+33, hgth+20)  сделать размер
//         stripe.scale(2);   маштаб
        //stripe.setBounds(0,0,wdth,hgth); //установить границы переместить обект и задать ему габариты.

    }
}