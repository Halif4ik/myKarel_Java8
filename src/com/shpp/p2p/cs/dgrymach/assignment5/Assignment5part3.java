package com.shpp.p2p.cs.dgrymach.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Assignment5part3 extends TextProgram {
    private static final int HOUMACH_LETTER_IN = 3;

    public void run() {
        String letters;
        do {
            letters = readLine("Enter thre  number car:  ");
        } while (userPutInNumbers(letters));
        letters = letters.toLowerCase();


        try {
            foundWord(letters);
        } catch (IOException e) {
            e.printStackTrace();
        }


        /**
         * Given as input a string of three letters and a list of strings, returns all  Берет на ввод стринг с 3-х букв и список строк, возвращает все
         * words in English that match that word according to the rules of the “license  слова на енгл которые соответствуют этому слову в соответствии с
         * plate game” (that is, all words that contain all of the letters in the string правилами лиц правил игры тойсть все слова которые содержат все
         * 'letters' in the order in which they appear).  букви в строке букв в порядке в ктором они появляются
         *
         * @param letters A string of three letters.
         * @param allWords A list of all the strings to test.                Список всех строк для тестирования.
         * @return A list of all the English words matching the given letter patter.
         */
        //private ArrayList<String> allMatchesFor(String letters, ArrayList<String> allWords);

    }

    private void foundWord(String letters) throws IOException {
        BufferedReader read = null;
        String allWords = null;
        String allWordsLitl = null;
        ArrayList<String> result = new ArrayList<>();
        String Substr = null;


        read = new BufferedReader(new FileReader("assets/dictionary.txt"));

        while ((allWords = read.readLine()) != null) {
            allWordsLitl = allWords.toLowerCase();

            int indxFoundCharInWord = allWordsLitl.indexOf(letters.charAt(0));

            if (indxFoundCharInWord > -1) {
                Substr = allWordsLitl.substring(indxFoundCharInWord + 1);
                if ((indxFoundCharInWord = Substr.indexOf(letters.charAt(1))) > -1) {
                    Substr = Substr.substring(indxFoundCharInWord + 1);
                    if ((Substr.indexOf(letters.charAt(2))) > -1) {
                        result.add(allWordsLitl);
                    }
                }
            }
        }
        read.close();
        printResul(result);
    }

    /**
     *
     * @param result
     */
    private void printResul(ArrayList<String> result) {
        for (int j = 0; j < result.size(); j++) {
            println((j + 1) + "[" + result.get(j) + "]");
        }
    }

    /**
     *
     * @param letters
     * @return
     */
    private boolean userPutInNumbers(String letters) {
        for (int i = 0; i < letters.length(); i++) {
            char ch = letters.charAt(i);
            if (letters.length() > HOUMACH_LETTER_IN) return true;
            if (Character.isLetter(ch)) continue;
            return true;                                // if in string absent numbes, thisis return dont do,
        }

        return false;
    }

}

