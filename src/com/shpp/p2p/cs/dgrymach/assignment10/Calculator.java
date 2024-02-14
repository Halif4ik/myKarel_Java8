package com.shpp.p2p.cs.dgrymach.assignment10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;


import static java.lang.Character.isDigit;

/**
 *
 */
public class Calculator {
    private Stack<String> stekOperatori = new Stack<>();
    private ArrayList<String> postFixFormulStrInArrayL = new ArrayList<>();
    private ArrayList<String> postfixFormulaWithVariable = new ArrayList<>();

    /*constructor*/

    /**
     *
     * @param args
     */
    Calculator(String[] args) {
        HashMap<String, Double> variables = new HashMap<>();

        /* first  cell in our array come formula*/
        String inFixFormula = args[0];

        /* all next element it is variable and its values */
        for (int i = 1; i < args.length; i++) {
            /* split all element array[args]for regex = , from the left size of the "="is the variable , with the right
             size it is value*/
            String[] splitVarDoubl = args[i].split("=");
            /* put variable and his value in hashmap */
            variables.put(splitVarDoubl[0], Double.parseDouble(splitVarDoubl[1]));    // we take only first symbol a,b,c, etc FIXME
        }
        /* take formula view String*/
        doTransferToPostfix(inFixFormula);
         doInsertingVarInPostfix(variables);
    }

    /**
     * Transfer infix formulu to postfix
     *
     * @param inFixFormula - furmula what came from users in infix view in string
     * @return -  postfix formula in ArrayList String-ov
     */
    private ArrayList<String> doTransferToPostfix(String inFixFormula) {
        System.out.println(inFixFormula + " ,del this println");
        String itiyChar = "";
        boolean seachDoubl = false;
        String result;

        for (int i = 0; i < inFixFormula.length(); i++) {
            /*psrse string to separate double or sing*/
            Character symbol = inFixFormula.charAt(i); // one symbol fo string formula
            if (isDigit(symbol) || symbol == '.') { // if we start seach double we change flag
                seachDoubl = true;
                itiyChar = itiyChar + symbol;
                if (i != inFixFormula.length() - 1) continue; // if in the and furmala one number
                result = itiyChar;
            } else {
                if (seachDoubl) {
                    result = itiyChar;
                    itiyChar = "";
                    seachDoubl = false; // we stop seach double because we already read the sign
                    i--; // repead read the sing
                } else result = symbol.toString();
            }

            /* сontinue transfer formula to postfix*/
            switch (result) {
                case "(": // if is it bracket simply push in steck with operatori
                    stekOperatori.push(result);
                    break;
                case ")": // if is it  closing bracket we now lost ) and begin find open bracket
                    extractAndFindBracket();
                    break;
                case "+":
                case "-":
                    checkStakAndPush(result, 1);
                    break;
                case "*":
                case "/":
                    checkStakAndPush(result, 2);
                    break;
                case "^":
                    checkStakAndPush(result, 3);
                    break;
                default:          // if getnutiy char operand, add it in result string
                    postFixFormulStrInArrayL.add(result);
            }
        }
        /* when walk for formula to end, add in arraylist result all operator from stack */
        while (!stekOperatori.empty()) {
            postFixFormulStrInArrayL.add(stekOperatori.pop());
        }
        return postFixFormulStrInArrayL;
    }

    /**
     *
     */
    private void extractAndFindBracket() {
        String operatorInPeek;
        while (!stekOperatori.empty()) {
            operatorInPeek = stekOperatori.pop(); // get operator from stack
            if (operatorInPeek.equals("("))break; // find open bracked
            postFixFormulStrInArrayL.add(operatorInPeek); // else  move operator from stack to array
        }
    }

    /**
     * Chek stek for operator, and add in this stack new operator or move peek operator stack to result string.
     *
     * @param operat        - new operator what we wont add in stack
     * @param preorThisOper - priority this new operator from 1 to 3
     */
    private void checkStakAndPush(String operat, int preorThisOper) {
        int preorPeek = 0;
        String operatorInPeek;

        /*while in stack operator present operatori chek its all and stay him priority*/
        while (!stekOperatori.empty()) {
            operatorInPeek = stekOperatori.peek();

            if (operatorInPeek.equals("+") || operatorInPeek.equals("-")) preorPeek = 1;
            if (operatorInPeek.equals("*") || operatorInPeek.equals("/")) preorPeek = 2;
            if (operatorInPeek.equals("^")) preorPeek = 3;

            if (preorPeek < preorThisOper) {
                stekOperatori.push(operat);
                return;
            } else
                postFixFormulStrInArrayL.add(stekOperatori.pop());
        }
        /*  if steck for operator empty fixme or in peek operator has higher priority  after .pop we add thisOperator */
        stekOperatori.push(operat);

    }

    /**
     * if i-y element array lista later change it to number and put result  to new array list String-ov
     *
     * @param variables
     */
    private void doInsertingVarInPostfix(HashMap<String, Double> variables) {
        for (String characterKey : postFixFormulStrInArrayL) {//read next symbol in postfix formula
            // a. b.c. etc  need add check allstring to word
            if (isLetters(characterKey)) {
                Double valueVariable = variables.get(characterKey); // find value in hashmap po key
                postfixFormulaWithVariable.add(valueVariable.toString()); // put in araylist value variables
                continue;
            }
            /*or is it symbol +,- etc. or number , simple add it result araylist*/
            postfixFormulaWithVariable.add(characterKey);
        }
    }

    private double doCalculate() {
        Stack<Double> operandi = new Stack<>();
        double num1;
        double num2;

        /*for all symbol in our array we do calculate*/
        for (int i = 0; i < postfixFormulaWithVariable.size(); i++) {
            try {   // if we pop from aray number, put it in stack
                operandi.push(Double.parseDouble(postfixFormulaWithVariable.get(i)));
            } catch (Exception e) { // else we chek what sign we pop
                num2 = operandi.pop();
                num1 = operandi.pop();
                switch (postfixFormulaWithVariable.get(i)) {
                    case "+":
                        operandi.push(num1 + num2);
                        break;
                    case "-":
                        operandi.push(num1 - num2);
                        break;
                    case "*":
                        operandi.push(num1 * num2);
                        break;
                    case "/":
                        operandi.push(num1 / num2);
                        break;
                    case "^":
                        operandi.push(Math.pow(num1, num2));
                        break;
                    default:
                        System.out.println("404 ! What we do... INCORECT Formula maybe");
                }
            }
        }
        return operandi.pop();
    }

    private boolean isLetters(String characterKey) {
        for (int i = 0; i < characterKey.length(); i++)
            if (!Character.isLetter(characterKey.charAt(i)))
                return false; // если елемент введеной строки не charecter то ретурн фалсе
        return true;
    }

    /**
     * @return formula in postfix form.
     */
    public String getPostfix() {
        return postFixFormulStrInArrayL.toString();
    }

    public String getPostfixWhithVar() {
        return postfixFormulaWithVariable.toString();
    }

    public double getResult() {
        return doCalculate();
    }
}



