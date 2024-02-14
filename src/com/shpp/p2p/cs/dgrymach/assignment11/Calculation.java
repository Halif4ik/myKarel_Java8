package com.shpp.p2p.cs.dgrymach.assignment11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import static java.lang.Character.isLetter;

/**
 * This class insert vars in postfix formula and calculate
 */
public class Calculation implements IConst {
    private ArrayList<String> readyPostfixFormula = new ArrayList<>();
    protected HashMap<String, Double> variables = new HashMap<>();

    /*constructor*/
    Calculation(String[] args, boolean presentVars) {
               /*if we has not only formula and variables  start parsing next element array args*/
        if (presentVars) {
            /* split all element array[args]for regex = , from the left size of the "="is the variable , with the right size
            it is value*/
            for (int i = 0; i < args.length; i++) {
                String[] splitVarDouble = args[i].split("=");
                /* put variable and his value in hashmap */
                variables.put(splitVarDouble[0], Double.parseDouble(splitVarDouble[1]));  // we take only first symbol a,b,c, etc
            }
        }
    }


    /**
     * if current element arraylist later change it to number and put result  to new array list Strings
     *
     * @param postFixFormulaInArray-
     * @param variables - hashmap whith variable and his value
     */
    void insertingVarInPostfix(ArrayList<String> postFixFormulaInArray, HashMap<String, Double> variables) {
        for (String characterKey : postFixFormulaInArray) {//read next symbol in postfix formula
            // a. b.c. etc  need add check allstring to word
            if (isLetters(characterKey)) { //variable doesn't have name sin or cos
                if (!(characterKey.equals("cos")) && !(characterKey.equals("sin"))
                        && !(characterKey.equals("tan")) && !(characterKey.equals("atan"))
                        && !(characterKey.equals("sqrt"))) {
                    Double valueVariable = variables.get(characterKey); // find value in hashmap po key name variable
                    readyPostfixFormula.add(valueVariable.toString()); // put in araylist value variables
                    continue;
                }
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
     double doCalculate() {
        Stack<Double> operandi = new Stack<>();
        double num1;
        double num2;

        /*for all symbol in our array we do calculate*/
         done:
        for (String oneElementPostfixFormula : readyPostfixFormula)
            try {   // if we pop from array number, put it in stack
                operandi.push(Double.parseDouble(oneElementPostfixFormula));
               /* System.out.print(oneElementPostfixFormula);
             if(oneElementPostfixFormula.contains(operators)) System.out.print(oneElementPostfixFormula);*/

            } catch (Exception e) { // else we chek what sign we pop
                switch (oneElementPostfixFormula) { // Fixme if sin or cos in begin
                    case "sin":
                    case "cos":
                    case "tan":
                    case "atan":
                    case "log10":
                    case "log2":
                    case "sqrt":
                        num2 = operandi.pop();
                        double tempForRounding = 0;
                        switch (oneElementPostfixFormula) {
                            case "sin":
                                num2 = convertToDegree(num2);
                                tempForRounding = (Math.round(Math.sin(num2) * 10000)) / 10000.0; // rounding to four sing
                                break;
                            case "cos":
                                num2 = convertToDegree(num2);
                                tempForRounding = (Math.round(Math.cos(num2) * 10000)) / 10000.0; // rounding to four sing
                                break;
                            case "tan":
                                num2 = convertToDegree(num2);
                                tempForRounding = (Math.round(Math.tan(num2) * 10000)) / 10000.0; // rounding to four sing
                                break;
                            case "atan":
                                num2 = convertToDegree(num2);
                                tempForRounding = (Math.round(Math.atan(num2) * 10000)) / 10000.0; // rounding to four sing
                                break;
                            case "log10":
                                tempForRounding = (Math.round(Math.log10(num2) * 10000)) / 10000.0; // rounding to four sing
                                break;
                            case "log2":
                                tempForRounding = (Math.round((Math.log(num2) / 0.693) * 10000)) / 10000.0; // rounding to four sing
                                break;
                            case "sqrt":
                                if (num2 < 0) {
                                    System.out.println("the square root of a negative number is not extracted");
                                                                        break done;
                                }
                                tempForRounding = Math.sqrt(num2); // rounding to four sing
                                break;
                        }
                        operandi.push(tempForRounding);
                        break;

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
                            break done;
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

    /***
     * convert number in radian to number degree
     * @param num2 radian
     * @return degree
     */
    private double convertToDegree(double num2) {
        return num2 / 57.2958; //
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


    public String getPostfixWhithVar() {
        return readyPostfixFormula.toString();
    }


}



