package com.shpp.p2p.cs.dgrymach.assignment10;

/*
* Program takes on input a mathematical expression consisting of numbers
and mathematical symbols: "-", "+", "*", "/", "^" and functions sin, cos, tan, atan, log10, log2, sqrt ,  and letters of the Latin alphabet,
and the remaining arguments of the main function are values ​​of variables for example: a = 4.
The task of the program is to output a response to an expression in console.*/

class Assigment10 {



           /*   ArrayList<String> result = new ArrayList<>();
        String s = "а - (Ь + с) / Ь * с";
        StringTokenizer st = new StringTokenizer(s, "\t\n\r+*-/()", true);
        while (st.hasMoreTokens())
            // Получаем слово и что-нибудь делаем с ним, например, просто выводим на экран
            result.add(st.nextToken());
        System.out.println(result);*/



    public static void main(String args[]) {
        /* print number argument intro in console*/
        for ( String cell :args)
                    System.out.println(cell);


        if (args.length > 0) {
            CalculatorLogic10 calculator = new CalculatorLogic10(args);
            System.out.println(calculator.getPostfix());
            System.out.println(calculator.getPostfixWhithVar());
            System.out.println(calculator.getResult());
        } else {
            System.out.println("Не введено не одного аргумента");
        }
            /* create rule for all expression &><*/
//            p = Pattern.compile("([()+\\-*/.^]+)");
//            m = p.matcher(args[0]);
//            if (!m.matches()) {
//                System.out.println("invalid trigonometric name or name veriables");
//                System.exit(0);
//            }
//
//            //Pattern pattForFormul = Pattern.compile("([a-z]){1}|([a-z]{1,4}+[0-2]{0,2}){1}|(([0-9]+\\.[0-9]+|[0-9]+))([()+\\-*/.^]).{3,}");
//            Pattern pattrFormul = Pattern.compile("((?=.*\\d)(?=.*[()+\\-*/.^]).{1,})");
//
//            //Pattern.compile("([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.");
//            Pattern IP_Patern = Pattern.compile("([a-z])*");


    }

}

