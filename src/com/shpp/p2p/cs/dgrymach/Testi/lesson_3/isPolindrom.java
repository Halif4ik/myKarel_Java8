package com.shpp.p2p.cs.dgrymach.Testi.lesson_3;
import acm.graphics.GRect;
import com.shpp.cs.a.console.TextProgram;

public class isPolindrom extends  TextProgram {
    public void run() {
        String str = readLine("Enter the string");
        if (isPolindrome(str)) {
            println("yeah!");
        }
        else  {
            println("nope...");
        }
    }

    private boolean isPolindrome(String str) {
        str =cleanUp(str);
        String revstr = reevse(str);
        return revstr.equals(str);

    }

    private String cleanUp(String str) {
        String result = "";
        str = str.toLowerCase();
        for (int i = 0; i<str.length();i++){
            char rec = str.charAt(i);
            if(Character.isLetter(rec)){
                result = result +  rec;
            }

        }

        return result;
    }

    private String reevse(String str) {
        String result ="";
        for (int i =0; i<str.length();i++){
        char ch = str.charAt(i);
        result= ch+result;
        }

        return result;
    }

}

//        char[] hel  = {'h', 'e', 'l', 'l', 'o'};
//        for (char i : hel) {
//            print(i);



//hello





