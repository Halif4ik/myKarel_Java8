package com.shpp.p2p.cs.dgrymach.assignment5;

import com.shpp.cs.a.console.TextProgram;

public class assignment5part1Old extends TextProgram {
    public void run() {
            /* Repeatedly prompt the user for a word and print out the estimated  number of syllables in that word.
            Неоднократно предлагайте пользователю слово и распечатывайте оценочное количество слогов в этом слове
             */
        String word = null;
        do {
            word = readLine("Enter a single word: ");
            } while (userPutInNumbers(word));               // protect from stupid
        println("  Syllable count: " + syllablesIn(word));  // Количество слогов
    }

    private boolean userPutInNumbers(String word) {

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (Character.isLetter(ch))   continue;
            return true;                                // if in string absent numbes, thisis return dont do,
            }

        return false;
    }
    /**
     * Given a word, estimates the number of syllables in that word according to the
     * heuristic specified in the handout.  Берем слово оценивает количество слогов в этом слове в соответствии с
     * эвристикой, указанной в раздаточном материале.
     *
     * @param word A string containing a single word.      содержащая
     * @return An estimate of the number of syllables in that word.     Оценка количества слогов в этом слове.
     */

    private int countSyllable;
    private String vowels = "a e i o u y"; // string of vowels with which we will compare chari of the entered string "word"

    private int syllablesIn(String word) {
        String wordLitle = word.toLowerCase();                  // do small all char in string

        for (int i = 0; i < wordLitle.length(); i++) {
            char ch = wordLitle.charAt(i);                             //get char from string 'word'

            if (isItVowels(ch)) {
                if (chekInFrontIsVowels(i,wordLitle)) continue;   // first chek in front there are vowels
                if (chekLastCharIsE(i,wordLitle))     continue;   // second chek is 'e' is last char in string
                countSyllable++;
            }
        }

        if (countSyllable == 0 && wordLitle.lastIndexOf('e') == wordLitle.length()-1) countSyllable++;  // add one syllable for word me,she,etc.
        return countSyllable;
    }

    private boolean chekInFrontIsVowels(int i, String wordLitle) {
        if(i > 0 && isItVowels(wordLitle.charAt(i - 1))) return true;
        return false;
    }

    private boolean chekLastCharIsE(int i, String wordLitle) {
        if (i == wordLitle.length() - 1 && wordLitle.charAt(i) == 'e') return true;
        return false;
    }

    private boolean isItVowels(char ch) {   // this is functioun check curent 'ch' is it vowels
        int foundVowels = vowels.indexOf(ch);
        if (foundVowels > -1) {
            return true;
        } else {
            return false;
        }
    }
}



