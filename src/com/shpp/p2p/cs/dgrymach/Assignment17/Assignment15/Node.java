package com.shpp.p2p.cs.dgrymach.Assignment17.Assignment15;

/**
 * This is container when save 2 links in leftChild & rightChild another containers and 2 main filed in scope class
 * its byte(a=3) and its frequency in input file
 */
class Node implements Comparable<Node> {
    /* links to next node or null if its absent*/
    Node leftChild;
    Node rightChild;

    /* uniqueByte from readed file, save in table array list */
    int uniqueByte;
    /*  priority its uniqueByte in array list */
    int frequency;
    boolean isLeaf;

    Node(int uniqueByte, int index, boolean isLeaf) {
        this.uniqueByte = uniqueByte;
        frequency = index;
        this.isLeaf = isLeaf;
    }

     void setFrequency(int newFrequency) {
        frequency=newFrequency;
    }

    /*constructor for create node who was not leaf*/
    Node(int index) {
        frequency = index;
    }


    @Override
    public int compareTo(Node present) {
        return Integer.compare(frequency, present.frequency);
    }

    @Override
    public String toString() {
        return uniqueByte + "=" + frequency;
    }

}




