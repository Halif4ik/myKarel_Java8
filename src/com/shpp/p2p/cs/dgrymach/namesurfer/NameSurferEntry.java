package com.shpp.p2p.cs.dgrymach.namesurfer;

/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 *        NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 *
 * Этот класс представляет одну вхождение в базе данных.  Каждый NameSurferEntry содержит
 * имя и список, дающий популярность этого имени для каждого десятилетия, начиная с 1900 года.
 */

public class NameSurferEntry implements NameSurferConstants {

    private String name = null;
    private int rank[] = new int[NDECADES];

    /* Constructor: NameSurferEntry(line) */

    /**
     * Creates a new NameSurferEntry from a data line as it appears
     * in the data file.  Each line begins with the name, which is
     * <p>
     * followed by integers giving the rank of that name for each
     * decade.      (String line)
     */
    public NameSurferEntry(String line) {
        String[] splitStringLine = line.split(" ");

        for (int i = 0; i < splitStringLine.length; i++)
            if (i == 0) {
                name = splitStringLine[i].toLowerCase();
            } else {
                rank[i - 1] = Integer.parseInt(splitStringLine[i]);
            }
    }

    /* Method: getName() */

    /**
     * Returns the name associated with this entry.
     */
    public String getName() {
        return name;
    }

    /* Method: getRank(decade) */

    /**
     * Returns the rank associated with an entry for a particular
     * decade.  The decade value is an integer indicating how many
     * с первого года существования базы данных прошли десятилетия
     * decades have passed since the first year in the database,
     * задается
     * which is given by the constant START_DECADE.  If a name does
     * появляется
     * not appear in a decade, the rank value is 0.
     */
    public int getRank(int decade) {
        return rank[decade];
    }

    /* Method: toString() */

    /**
     * Returns a string that makes it easy to see the value of a
     * NameSurferEntry.
     */
    public String toString(int j) {
        String result = "";// возвращает с большой букви стринг
        for (int i = 0; i < name.length(); i++)
            if (i == 0) {
                result += (char) ((int) name.charAt(0) & 0b1111111111011111);
            } else {
                result += name.charAt(i);
            }
        return result + " " + getRank(j);
    }
}


