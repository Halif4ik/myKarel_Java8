package com.shpp.p2p.cs.dgrymach.assignment10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;

public class CalculatorLogic10 {
    private Stack<String> steOperators = new Stack<>();
    private ArrayList<String> postFixFormulInArray = new ArrayList<>();
    private ArrayList<String> readyPostfixFormula = new ArrayList<>();
    private double result;

    /*constructor*/
    CalculatorLogic10(String[] args) {
        HashMap<String, Double> variables = new HashMap<>();

        /* first  cell in our array come formula*/
        String inFixFormula = args[0];
        /* take formula view String*/
        transferToPostfix(inFixFormula);

        /*if we has not only formula and variables  start parsing next element array args*/
        if (args.length > 1) {
            /* all next element it is variable and its values */
            for (int i = 1; i < args.length; i++) {
            /* split all element array[args]for regex = , from the left size of the "="is the variable , with the right
             size it is value*/
                String[] splitVarDoubl = args[i].split("=");
                /* put variable and his value in hashmap */
                variables.put(splitVarDoubl[0], Double.parseDouble(splitVarDoubl[1]));  // we take only "0" symbol a,b,c,  and "1" cell it is value etc FIXME
            }
        }

        insertingVarsInPostfix(variables);
        result = doCalculate();
    }

    /**
     * Transfer infix formulu to postfix Polish anatotions
     *
     * @param inFixFormula - furmula what came from users in infix view in string
     * @return -  postfix formula in ArrayList String-ov
     */
    private ArrayList<String> transferToPostfix(String inFixFormula) {
        System.out.println(inFixFormula);
        StringBuilder currentChar = new StringBuilder();
        boolean seachDoubl = false;
        boolean seachSinCos = false;
        String result;


        for (int i = 0; i < inFixFormula.length(); i++) {
            /*psrse string to separate double or sing*/
            Character symbol = inFixFormula.charAt(i); // one symbol fo string formula

            if (isDigit(symbol) && !seachSinCos || symbol == '.') { // if we start seach double we change flag
                seachDoubl = true;
                currentChar.append(symbol);
                if (i != inFixFormula.length() - 1) continue; // if in the and furmala one integer
                result = currentChar.toString();

                /*begin find sin/cos if we have two in series letter its means its sin */
            } else {
                if (seachDoubl) {
                    result = currentChar.toString();
                    currentChar = new StringBuilder();
                    seachDoubl = false; // we stop seach double because we already read the sign
                    i--; // repead read the sing fo dosnt lost
                } else {    // add zero if we read unary minus
                    if (symbol == '-' && i == 0 || symbol == '-' && !isDigit(inFixFormula.charAt(i - 1))) { //TODO; add check for many minus
                        postFixFormulInArray.add("0");
                        result = symbol.toString();// this else for characker variables etc. a, b, c,
                    } else
                        result = symbol.toString();// this else for characker variables etc. a, b, c, +, -, /,
                }
            }

            /* сontinue transfer formula to postfix*/
            switch (result) {
                case "+":
                case "-":
                    checkStackAndPush(result, 1);
                    break;
                case "*":
                case "/":
                    checkStackAndPush(result, 2);
                    break;
                case "^":
                    checkStackAndPush(result, 3);
                    break;
                default:          // if pop char operand, add it in result string
                    postFixFormulInArray.add(result);
                    //System.out.println(postFixFormulInArray);
            }
        }
        /* when walk for formula to end, add in arraylist result all operator from stack */
        while (!steOperators.empty()) {
            postFixFormulInArray.add(steOperators.pop());
        }
        return postFixFormulInArray;
    }

    /**
     * Chek stek for operator, and add in this stack new operator or move peek operator stack to result string.
     *
     * @param operat        - new operator what we wont add in stack
     * @param preorThisOper - priority this new operator from 1 to 3
     */
    private void checkStackAndPush(String operat, int preorThisOper) {
        String operatorInPeek;

        /*while in stack operator present operatori chek its all and stay him priority*/
        while (!steOperators.empty()) {
            int preorPeek = 0; // by default 0 priority for open bracket
            operatorInPeek = steOperators.peek();

            if (operatorInPeek.equals("+") || operatorInPeek.equals("-")) preorPeek = 1;
            if (operatorInPeek.equals("*") || operatorInPeek.equals("/")) preorPeek = 2;
            if (operatorInPeek.equals("^")) preorPeek = 3;


            if (preorPeek < preorThisOper) {
                steOperators.push(operat);
                return;
            } else
                postFixFormulInArray.add(steOperators.pop());
        }
        /*  if steck for operator empty fixme or in peek operator has higher priority  after .pop we add thisOperator */
        steOperators.push(operat);

    }

    /**
     * if current element array lista later change it to number and put result  to new array list String-ov
     *
     * @param variables
     */
    private void insertingVarsInPostfix(HashMap<String, Double> variables) {
        for (String characterKey : postFixFormulInArray) {//read next symbol in postfix formula
            // a. b.c. etc  need add check allstring to word
            if (isLetters(characterKey)) { //variable doesn't have name sin or cos
                Double valueVariable = variables.get(characterKey); // find value in hashmap po key name variable
                readyPostfixFormula.add(valueVariable.toString()); // put in araylist value variables
                continue;
            }
            /*or is it symbol +,- etc. or number , simple add it result araylist*/
            readyPostfixFormula.add(characterKey);
        }
    }

    /**
     * The function performs the mathematical calculations written in the expression
     *
     * @return - result mathematical calculation;
     */
    private double doCalculate() {
        Stack<Double> operandi = new Stack<>();
        double num1;
        double num2;

        /*for all symbol in our array we do calculate*/
        for (String oneElementPostfixFormula : readyPostfixFormula)
            try {   // if we pop from aray number, put it in stack  //todo not nesusary try catch
                operandi.push(Double.parseDouble(oneElementPostfixFormula));
            } catch (Exception e) { // else we chek what sign we pop
                switch (oneElementPostfixFormula) { //
                    case "+":
                        num2 = operandi.pop();
                        num1 = operandi.pop();
                        operandi.push(num1 + num2);
                        break;
                    case "-":
                        num2 = operandi.pop();
                        num1 = operandi.pop();
                        operandi.push(num1 - num2);
                        break;
                    case "*":
                        num2 = operandi.pop();
                        num1 = operandi.pop();
                        operandi.push(num1 * num2);
                        break;
                    case "/":
                        num2 = operandi.pop();
                        num1 = operandi.pop();
                        if (num2 == 0) {
                            System.out.println(" Division by zero! Result wrong!");
                            operandi.push(0.0);
                            break;
                        }
                        operandi.push(num1 / num2);
                        break;
                    case "^":
                        num2 = operandi.pop();
                        num1 = operandi.pop();
                        operandi.push(Math.pow(num1, num2));
                        break;
                    default:
                        System.out.println("404 ! What we do... INCORECT Formula maybe");
                }
            }
        return operandi.pop();
    }


    /**
     * check string and return true if string has only characters
     *
     * @param characterKey - string hou need check
     * @return - true or false
     */
    private boolean isLetters(String characterKey) {
        for (int i = 0; i < characterKey.length(); i++)
            if (!isLetter(characterKey.charAt(i)))
                return false; // if element intro in string not character  return false
        return true;
    }

    /**
     * @return formula in postfix form.
     */
    public String getPostfix() {
        return postFixFormulInArray.toString();
    }

    public String getPostfixWhithVar() {
        return readyPostfixFormula.toString();
    }

    public double getResult() {
        return result;
    }
}



