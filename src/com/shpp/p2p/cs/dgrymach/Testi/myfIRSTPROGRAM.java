package com.shpp.p2p.cs.dgrymach.Testi;
import  com.shpp.cs.a.console.TextProgram;

public class myfIRSTPROGRAM extends TextProgram{
    @Override
    public void run(){
        int h = readInt("Please enter higth");
        printTree(h);
        printLostRow(h);

    }

    private void printLostRow(int h) {
        for (int i=0; i< h-1; i++) {
            print(" ");
        }
        print("*");
    }

    private void printTree(int h) {
        for(int i=0; i < h; i++){
            printRow(i,h);
            println();
        }

    }

    private void printRow(int i, int h) {
        int zw = h-i;
        for (int j=0; j < zw-1; j++){
            print(" ");
        }
        for (int j=0; j<(1+2*i); j++)
            print("*");
        }
}
