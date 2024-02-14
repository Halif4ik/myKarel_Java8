package com.shpp.p2p.cs.dgrymach.Assignment15;

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

    Node(Integer byteInt, int index, boolean isLeaf) {
        uniqueByte = byteInt;
        frequency = index;
        this.isLeaf = isLeaf;
    }

    /*constructor for create node who was not leaf*/
    Node(int index) {
        frequency = index;
    }

   @Override
    public int compareTo(Node present) {
        return Integer.compare(frequency, present.frequency);
    }

}




