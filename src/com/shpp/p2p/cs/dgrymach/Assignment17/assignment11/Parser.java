package com.shpp.p2p.cs.dgrymach.Assignment17.assignment11;

import com.shpp.p2p.cs.dgrymach.Assignment16.ArrayList.MyArrayList;
import com.shpp.p2p.cs.dgrymach.Assignment16.Stack.MyStack;

import java.util.ArrayList;
import java.util.Stack;
import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;

/**
 * This class represents reverse polish notation algorithm.
 * It parses parameters from command line, such as formula.
 */
class Parser {
    private MyStack<String> steOperators = new MyStack<>();
    private MyArrayList<String> postFixFormulaInArray = new MyArrayList<>();

    /**
     * Transfer infix formula to postfix
     *
     * @param inFixFormula - formula what came from users in infix view in string
     */
    Parser(String inFixFormula) {
        String currentChar = "";
        boolean searchDouble = false;
        boolean searchSinLog2 = false;
        String readyToken;

        for (int i = 0; i < inFixFormula.length(); i++) {
            /*parse string to separate double or sing or operators*/
            Character symbol = inFixFormula.charAt(i); // one symbol fo string formula

            if (isDigit(symbol) && !searchSinLog2 || symbol == '.') { // if we start search double we change flag
                searchDouble = true;
                currentChar = currentChar + symbol;
                if (i != inFixFormula.length() - 1) continue; // if in the and formula one integer
                readyToken = currentChar;

                /*begin find sin/cos/ log2 if we have two in series letter its means its sin */
            } else if (isLetter(symbol) && i!=inFixFormula.length()-1 && isLetter(inFixFormula.charAt(i + 1)) || searchSinLog2) {
                searchSinLog2 = true;
                if (symbol != '(') {  // if it is name  function create string with her name
                    currentChar = currentChar + symbol;
                    continue;
                }
                searchSinLog2 = false;
                readyToken = currentChar;
                currentChar = ""; // clean to empty buffer string for function
                i--; // repeat read the sing for don't lost open bracket

                /* if symbol operator*/
            } else {
                if (searchDouble) {
                    readyToken = currentChar;
                    currentChar = "";
                    searchDouble = false; // we stop search double because we already read the sign
                    i--; // repeat read the sing for don't lost
                } else {
                    if (symbol == '-' && i == 0) {
                        postFixFormulaInArray.add("0"); // add zero if we read unary minus
                        readyToken = symbol.toString();// this else for character variables etc. a, b, c,
                    } else
                        readyToken = symbol.toString();// this else for character variables etc. a, b, c, +-/
                }
            }

            /* continue transfer formula to postfix*/
            switch (readyToken) {
                case "sin":
                case "cos":
                case "tan":
                case "atan":
                case "log10":
                case "log2":
                case "sqrt":
                    checkStackAndPush(readyToken, 4);
                    break;
                case "(": // if is it bracket simply push in steck with operatori
                    steOperators.push(readyToken);
                    break;
                case ")": // if is it  closing bracket we now lost ) and begin find open bracket
                    extractAndFindBracket();
                    break;
                case "+":
                case "-":
                    checkStackAndPush(readyToken, 1);
                    break;
                case "*":
                case "/":
                    checkStackAndPush(readyToken, 2);
                    break;
                case "^":
                    steOperators.push(readyToken);
                    break;
                default:          // if pop char operand, add it in result string
                    postFixFormulaInArray.add(readyToken);
                    //System.out.println(postFixFormulaInArray);
            }

        }
        /* when walk for formula to end, add in arraylist result all operator from stack */
        while (!steOperators.empty()) postFixFormulaInArray.add(steOperators.pop());

    }


    /**
     * Chek stack for operator, and add in this stack new operator or move peek operator stack to result string.
     *
     * @param operator        - new operator what we wont add in stack
     * @param preorThisOper - priority this new operator from 1 to 3
     */
    private void checkStackAndPush(String operator, int preorThisOper) {
        String operatorInPeek;

        /*while in stack operator present operators chek its all and stay him priority*/
        while (!steOperators.empty()) {
            int priorPeek = 0; // by default 0 priority for open bracket
            operatorInPeek = steOperators.peek();

            if (operatorInPeek.equals("+") || operatorInPeek.equals("-")) priorPeek = 1;
            if (operatorInPeek.equals("*") || operatorInPeek.equals("/")) priorPeek = 2;
            if (operatorInPeek.equals("^")) priorPeek = 3;
            if (operatorInPeek.equals("cos") || operatorInPeek.equals("sin") || operatorInPeek.equals("tan") ||
                    operatorInPeek.equals("atan") || operatorInPeek.equals("log10") || operatorInPeek.equals("log2")
                    || operatorInPeek.equals("sqrt")) priorPeek = 4;

            if (priorPeek < preorThisOper) {
                steOperators.push(operator);
                return;
            } else
                postFixFormulaInArray.add(steOperators.pop());
        }
        /*  if stack for operator empty */
        steOperators.push(operator);

    }

    /**
     * if the operator is different from '(' , move operator from stack to array, if find '(' quit its cycle
     */
    private void extractAndFindBracket() {
        /* find open backed*/
        while (!steOperators.peek().equals("(")) postFixFormulaInArray.add(steOperators.pop());
        steOperators.pop(); // if it is ( get open bracket from stack
    }

    /**
     * @return formula in postfix form.
     */
    public MyArrayList<String> getPostfix() {
        return postFixFormulaInArray;
    }

}



