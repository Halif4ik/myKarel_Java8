package com.shpp.p2p.cs.dgrymach.Testi.lesson_3;

import com.shpp.cs.a.console.TextProgram;

import java.util.HashMap;

public class HashMapI extends TextProgram {
    public void run() {
        HashMap<String, String> hech = new HashMap<>();
        hech.put("dog", "vasya");
        hech.put("cat", "dima");
        hech.put("som", null);

//        for (Map.Entry<String, String> el : hech.entrySet()) {
//            println(el);
              //  }
        println(hech.get("cat"));
        if (hech.containsKey("dog"))
        println(hech.get("dog"));

        String value =hech.get("som");
        if (value != null) println(hech.get("som"));


    }
}

