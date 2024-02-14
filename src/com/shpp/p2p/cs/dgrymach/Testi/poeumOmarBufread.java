package com.shpp.p2p.cs.dgrymach.Testi;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class poeumOmarBufread extends TextProgram {

    @Override
    public void run() {
        try {
            foundWord();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void foundWord() throws IOException {
        BufferedReader r = null;
        r = new BufferedReader(new FileReader("assets/poem.txt"));
        String s = null;
        while ( (s=r.readLine()) != null ){
            String x= s.toLowerCase();
            println(x);
        }
        //println("[" + r.readLine()+"]");
        r.close();

    }
}


//    BufferedReader r = null;
//        try {
//                r = new BufferedReader(new FileReader("assets/poem.txt"));
//                println("["+r.readLine()+"]");
//
//                } catch (IOException e) {
//
//                e.printStackTrace();
//                }
//                if (r!=null) {
//                try {
//                r.close();
//                } catch (IOException e) {
//                println(e.getMessage());
//                e.printStackTrace();
//                }
//                }
//                }




