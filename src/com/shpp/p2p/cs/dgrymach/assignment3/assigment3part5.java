package com.shpp.p2p.cs.dgrymach.assignment3;
import acm.util.RandomGenerator;
import com.shpp.cs.a.console.TextProgram;


public class assigment3part5 extends  TextProgram {
    public static final int WIN_SUM = 20;
    public static final int IN_TABLE = 1;  // looser put in table one dollars

        public void run() {
            int lucky = 0;
            int game =0;

            while (lucky <= WIN_SUM) {
                lucky = lucky + resulOneGame(IN_TABLE);
                game ++;
                println("Your total is "+lucky);
            }
            println("It took "+ game + " games to earn "+ WIN_SUM +"$");
        }

    private int resulOneGame(int inTable) {
        RandomGenerator rgen = RandomGenerator.getInstance();       // if true orel we think this == true
        while (rgen.nextBoolean() ){

            inTable = inTable+ inTable;
                    }
        println("This game, you earned "+inTable);
        return inTable;
    }

}

