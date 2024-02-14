package com.shpp.p2p.cs.dgrymach.assignment2;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class assigment2part6 extends WindowProgram {

    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 200;
    private static final int DIAMETER_SEGMENT = 60;
    private static final Color redd = new Color(196, 30, 58);

    public void run() {
        int y;
        int x =0;
        int segm_caterp = (getWidth() / (DIAMETER_SEGMENT / 2) - 1);

        for (int i = 0; i < segm_caterp; i++) {
            if (i % 2 == 0) {                                 //faind coordinate y
                y = ((getHeight() - DIAMETER_SEGMENT) / 2) + (DIAMETER_SEGMENT / 4);
            } else {
                y = ((getHeight() - DIAMETER_SEGMENT) / 2) - (DIAMETER_SEGMENT / 4);}

            getMyOval(x, y);
            x = x + (DIAMETER_SEGMENT / 2);
        }
    }

    private void getMyOval(int x, int y) {
        GOval o = new GOval(x, y, DIAMETER_SEGMENT, DIAMETER_SEGMENT);
        o.setFilled(true);
        o.setFillColor(Color.green);
        o.setColor(redd);
        add(o);

    }

}


