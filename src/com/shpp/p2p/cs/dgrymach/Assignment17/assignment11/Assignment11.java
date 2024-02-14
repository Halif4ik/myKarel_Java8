package com.shpp.p2p.cs.dgrymach.Assignment17.assignment11;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Program takes on input a mathematical expression consisting of numbers
 * and mathematical symbols: "-", "+", "*", "/", "^" and functions sin, cos, tan, atan, log10, log2, sqrt ,  and letters of the Latin alphabet,
 * and the remaining arguments of the main function are values ​​of variables for example: a = 4.
 * The task of the program is to output a response to an expression in console.
 */
class Assignment11 {

    public static void main(String args[]) {
        /* print number argument introduction  in console*/
        for (String cell : args) System.out.println(cell + " ");

        Scanner inputData = new Scanner(System.in);
        boolean presentVars = true;
        String[] argsWithoutFormula = null;

        if (args.length < 1) {
            System.out.println("No arguments entered.");
            System.exit(0);
        }
        /*if we has not only formula and variables*/
        if (args.length == 1) presentVars = false;

        /*validation all args formula and vars*/
        validation(args);

        /*  first  cell in our array come formula other it is vars*/
        if (presentVars) {
            argsWithoutFormula = new String[args.length - 1];
            /* all next element it is variable and its values */
            System.arraycopy(args, 1, argsWithoutFormula, 0, args.length - 1);
        }

        Parser parsedToPostfix = new Parser(args[0].toLowerCase());
        System.out.println(parsedToPostfix.getPostfix());

        do {

            //-in constructor we parse variables  who need for calculation-//
            Calculation calculationFormula = new Calculation(argsWithoutFormula, presentVars);
            calculationFormula.insertingVarInPostfix(parsedToPostfix.getPostfix(), calculationFormula.variables);
            double result = calculationFormula.doCalculate();

            System.out.println(calculationFormula.getPostfixWhithVar() + "\n" + result + "\n" + "Enter the new variables " +
                    "in format \'a=2 b=3\': or input \'n\' to the and: ");
            if (!presentVars) System.exit(0);// if in formula in start was absent variable a=2
            String readFromKeyboard= inputData.nextLine();
            /* cut a=2 from b=2 */
            argsWithoutFormula = readFromKeyboard.toLowerCase().split(" ");
            validationVars(argsWithoutFormula);
        } while (!(inputData.nextLine().toLowerCase()).equals("n"));

    }

    /**
     * Checks given string for incorrect character sequences such as:  incorrect characters (e.g. //, &, **, etc)
     *
     * @param args array variables and his value in string
     */
    private static void validationVars(String[] args) {
        Matcher m;
        /*  create rule for variables*/
        if (args.length > 0) {
            Pattern p = Pattern.compile("[a-z]{1}+=(-?[0-9]+\\.[0-9]+|-?[0-9]+)");
            for (String currentArg : args) {
                m = p.matcher(currentArg);
                if (!m.matches()) {
                    System.out.println("Incorrect variables for example a=2");
                    System.exit(0);
                }
            }
        }
    }


    /**
     * Checks given string for incorrect  character sequences such as: double math operators one by one,
     * beginning and end of expression and illegal characters (e.g. //, &, **, etc)
     *
     * @param args parameters of command line
     */
    private static void validation(String[] args) {
        Pattern p;
        Matcher m;
        /*  create rule for present expression*/
        if (args.length == 0) {
            System.out.println("No input mathematical expression to calculate");
            System.exit(0);
        }
        /*  create rule for variables*/
        if (args.length > 1) {

            p = Pattern.compile("[a-z]{1}+=(-?[0-9]+\\.[0-9]+|-?[0-9]+)");
            for (int i = 1; i < args.length; i++) {
                m = p.matcher(args[i]);
                if (!m.matches()) {
                    System.out.println("Incorrect variables for example a=2");
                    System.exit(0);
                }
            }


        }

        /*  create rule for begin expression *5-a*/
        p = Pattern.compile("^[0-9a-z(\\-].*$");
        m = p.matcher(args[0]); // applied the rule to the formal parameter соответствие шаблону
        if (!m.matches()) {
            System.out.println("Incorrect begin of expression");
            System.exit(0);
        }
        /* create rule for end expression 5-a-*/
        p = Pattern.compile("^.*[0-9a-z)]$");
        m = p.matcher(args[0]);
        if (!m.matches()) {
            System.out.println("Incorrect end of expression");
            System.exit(0);
        }
        /* create rule for end expression 5//*a*/
        p = Pattern.compile("[+*/.^-]{2,}");
        m = p.matcher(args[0]);
        if (m.find()) {
            System.out.println("double operators");
            System.exit(0);
        }
    }


}

