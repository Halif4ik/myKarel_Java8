package com.shpp.p2p.cs.dgrymach.Testi;

public class Cliker1 {
     private int clickCount;
     private int step  =1;

    public Cliker1() {
        clickCount = 0;
    }
    @Override
    public  String toString(){
    return "{"+clickCount+"}";
    }
    public void reset(){
        clickCount =0;
    }

    public Cliker1(int initial) {
        if (initial < 0)         clickCount = 0;
            clickCount = initial;

    }
    public Cliker1(int initial, int step) {
     this(initial);
        this.step = step;

    }

    public Cliker1(Cliker1 frend) {
        clickCount = frend.getclicck();
        step =frend.step;;
    }

    public void increment() {
        clickCount+= step;
    }

    public int getclicck() {
        return clickCount;
    }

}
