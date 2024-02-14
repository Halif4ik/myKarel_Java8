package com.shpp.p2p.cs.dgrymach.Testi;

class dic   {
public  static void main(String args[]){
    System.out.println("Програме передано " + args.length+ " aргументов ко строки");

    System.out.println("список аргументов ");
    for (int i =0; i<args.length; i++){
        System.out.println("arg { " + i+" ] " +args[i]);
    }
}
}
