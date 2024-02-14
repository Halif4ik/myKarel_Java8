package com.shpp.p2p.cs.dgrymach.assignment3;

import acm.util.RandomGenerator;
import com.shpp.cs.a.console.TextProgram;


public class Asigment3part5 extends TextProgram {
    public static int JACKPOT = 20;

    class Gamer {
        int coin;
    }

    @Override
    public void run() {
        // create new gamer*/
        Gamer lucky = new Gamer();
        lucky.coin = 0;
        int counterGame = 0;
        //*I like these games, until we not win the game continue*/
        while (lucky.coin <= JACKPOT) {
            lucky = playGame(lucky);
            counterGame++;
        }
        println("It took " + counterGame + " games to earn $" + lucky.coin);
    }

    /**
     * @param lucky - object lucky with his coin or in start game .coin =0;
     * @return - object lucky with his coin
     */
    private Gamer playGame(Gamer lucky) {
        int coinOnTable = 1;                        //щеугыршн put $1 on table
        while (egle())
            coinOnTable += coinOnTable;  //If the eagle is poteushiy put on table exactly the same amount
        lucky.coin = lucky.coin + coinOnTable;  //if the tails are all on the table, goes to the lucky
        println("This game, you earned $" + coinOnTable);

        println("Your total is $ " + lucky.coin);
        return lucky;
    }

    /**
     * if true -it is egle, else tails
     *
     * @return -coinEgleOrTails true -it is egle.
     */
    private boolean egle() {
        return RandomGenerator.getInstance().nextBoolean();
    }

}


