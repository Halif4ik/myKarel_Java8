package com.shpp.p2p.cs.dgrymach.assignment5;

import com.shpp.cs.a.console.TextProgram;

/**
 * necessary create method which takes a word and returns the number of syllables in it.
 */
public class Assignment5part1 extends TextProgram {
    /* list of vawes leter*/
    private static final char[] PATTERN = {'a', 'e', 'i', 'o', 'u', 'y'};
    private static final char EXEPTION = 'e';

    public void run() {
        /* Repeatedly prompt the user for a word and print out the estimated
         * number of syllables in that word.
         */
        String word = " ";
        while (!enterOnlyWord(word)) {
            word = readLine("Enter a single word: ");
        }
        println("Syllable count: " + syllablesIn(word.toLowerCase(), PATTERN));
    }

    /**
     * Given a word, estimates the number of syllables in that word according to the
     * heuristic specified in the handout.
     *
     * @param word    A string containing a single word and in lowercase.
     * @param pattern - list of vowel leters.
     * @return An estimate of the number of syllables in that word.  implement
     */
    private int syllablesIn(String word, char[] pattern) {
        int counter = 0;     /* check first symbol if ok we don't check preview symbol and check next symbols*/
        for (int i = 0; i < word.length(); i++)
            if (i == 0 && isCharVowel(word.charAt(i), pattern) ||
                    /* check second            and     first  symbol*/
                    isCharVowel(word.charAt(i), pattern) && !isCharVowel(word.charAt(i - 1), pattern)) counter++;
        if (counter > 1 && word.lastIndexOf(EXEPTION) == word.length() - 1 && !isCharVowel(word.charAt(word.length()-2), pattern)) counter--;

        return counter;

    }

    /**
     * if 'i' element is not a letter method returns FALSE, else going through the whole word and not finding no letter returns true
     *
     * @param word - string which check for correct input
     * @return - found or not, the introduction of incorrect characters
     */
    private boolean enterOnlyWord(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (!Character.isLetter(word.charAt(i))) return false;
        }
        return true;
    }

    /**
     * Compare one character our 'word' with array pattern.
     *
     * @param oneLetter - one character of  string "word"
     * @param pattern   - list with vowels pattern
     * @return - found or not match pattern
     */
    private boolean isCharVowel(char oneLetter, char[] pattern) {
        for (char ch : pattern) if (oneLetter == ch) return true;
        return false;

    }

}
