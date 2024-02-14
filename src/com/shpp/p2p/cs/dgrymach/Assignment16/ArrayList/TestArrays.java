package com.shpp.p2p.cs.dgrymach.Assignment16.ArrayList;


/**
 * This class consist some one methods for testing myArraylist class , shuch metod us:
 * add, set,get,remove, add (index), contains(object)
 */

class TestArrays {
    public static void main(String[] args) {
        MyArrayList<String> temp =new MyArrayList<>();

        temp.add("A");//0
        temp.add("B");//1
        temp.add("C");//2
        temp.add("D");
        temp.add("E");//4
        printLn(temp);
        temp.add(2, "K");
        printLn(temp);

        temp.set(0, null);
        printLn(temp);

        if (temp.contains(null)) System.out.println("Null+ true");
        else System.out.println("RRR + false");


        for (String it : temp) System.out.print(it + " ");

    }

    private static void printLn(MyArrayList<String> temp) {
        for (int i = 0; i < temp.size(); i++) {
            System.out.print(temp.get(i) + " ");
        }
        System.out.println(temp.size() + "size ");
    }
}

