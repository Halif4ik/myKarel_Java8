package com.shpp.p2p.cs.dgrymach.assignment3.lec06;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MouseClickProgram extends WindowProgram {

    public void mouseMoved(MouseEvent event) {
        tracker.setLabel("  x: "+event.getX()+ ", y: "+event.getY() );
    }

    //@Override
    public void run() {
        addLabel();
        addMouseListeners();
    }
    GLabel tracker = null;
    private void addLabel() {
        tracker = new GLabel("text", 30,30);
        tracker.setFont("Times-23");  // type of fonts
        tracker.setLocation(getWidth()/2 - tracker.getWidth()/2, getHeight()/2 - (tracker.getHeight()/2)); // Set start locations of label
        tracker.setVisible(true);
        add(tracker);
    }

    private GObject comp;

    public void mouseClicked(MouseEvent me) {
        if (me.isControlDown()) {
            comp = getElementAt(me.getX(), me.getY());
            if (comp != null && !(comp instanceof GLabel) )  //&& comp != tracker
                remove(comp);
        } else {
            GOval o = getMeFilledOval(me.getX(), me.getY(), 50, RandomGenerator.getInstance().nextColor());
            add(o);
    }



    }

    private GOval getMeFilledOval(int x, int y, int r, Color c) {
        GOval o = new GOval(x, y, r * 2, r * 2);
        o.setFilled(true);
        o.setColor(c);
        return o;
    }
    }



