package com.shpp.p2p.cs.dgrymach.assignment3;
import acm.graphics.GRect;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class assigment3part6 extends WindowProgram {
        private static final double ROMB_DIMMEN = 45;
        private static final double VELOCITY = 10.0;
        /* How much time to pause between frames. */
        private static final double PAUSE_TIME = 1000/50.0;
        private static final double AXEL = 1.1;


        public void run() {
            setBackground(Color.GRAY);
            GRect romb = makeRomb();
            add(romb);

            performMove(romb); //play vipolnyat ruhi
        }


        private GRect makeRomb() {
            GRect romb = new GRect((getWidth() - 2 * ROMB_DIMMEN) / 2.0, (getHeight() - ROMB_DIMMEN) / 2.0,
                    ROMB_DIMMEN, ROMB_DIMMEN);
            romb.setFilled(true);
            romb.setColor(Color.YELLOW);
            return romb;
        }
        /**     * Simulates a move.          */

        private void performMove(GRect romb) {
            Toolkit zvuk = Toolkit.getDefaultToolkit();

            double dy = VELOCITY;
            double dx = VELOCITY;
            RandomGenerator ran = RandomGenerator.getInstance();
            for (int i=0; i<250; i++){
                romb.move(dx, dy);
                if(hasEdgeY(romb) && dy > 0) {
                    dy = -AXEL*dy;
                    romb.setColor(ran.nextColor());
                    zvuk.beep();
                    } else if(hasEdgeY(romb) && dy < 0) {
                    dy = -AXEL*(dy);
                    romb.setColor(ran.nextColor());
                    zvuk.beep();
                    }
                if(hasEdgeX(romb) && dy > 0) {
                    dx = -AXEL*dx;
                    romb.setColor(ran.nextColor());
                    zvuk.beep();
                } else if(hasEdgeX(romb) && dy < 0) {
                    dx = -AXEL*(dx);
                    romb.setColor(ran.nextColor());
                    zvuk.beep();
                }
                pause(PAUSE_TIME);
                }

        }

    private boolean hasEdgeX(GRect romb) {
        return romb.getX()+ ROMB_DIMMEN > getWidth() || romb.getX() <= 0;
    }

    private boolean hasEdgeY(GRect romb) {
            /* The romb has touch as soon as its top is below the edge.             */
            return romb.getY()+ ROMB_DIMMEN > getHeight() || romb.getY() <= 0;
        }
    }
