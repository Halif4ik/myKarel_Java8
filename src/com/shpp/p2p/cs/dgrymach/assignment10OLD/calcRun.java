package com.shpp.p2p.cs.dgrymach.assignment10OLD;

class calcRun {

    public static void main(String args[]) {
        /* print number argument intro in console*/
        System.out.println("Програме передано " + args.length + " aргументов в командной  строки");

        CalculatorLogic calk = new CalculatorLogic(args);

        System.out.println(calk.getPostfix());
        System.out.println(calk.getPostfixWhithVar());
        System.out.println(calk.getResult());

    }

}

