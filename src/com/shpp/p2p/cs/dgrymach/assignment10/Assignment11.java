package com.shpp.p2p.cs.dgrymach.assignment10;

class Assignment11 {

    public static void main(String args[]) {
        /* print number argument intro in console*/
        System.out.println("Програме передано " + args.length + " aргументов в командной  строки");

        Calculator calc = new Calculator(args);

        System.out.println(calc.getPostfix());
        System.out.println(calc.getPostfixWhithVar());
        System.out.println(calc.getResult());

    }

}

