package com.shpp.p2p.cs.dgrymach.namesurfer;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 *
 * * Этот класс отслеживает полную базу данных имен.
 * Конструктор считывает базу данных из файла, и
 * единственный общедоступный метод позволяет
 * имя и вернуться соответствующие NameSurferEntry.
 * Имена сопоставляются независимо от регистра, так что " Eric"
 * и "Эрик" - это одинаковые имена.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class NameSurferDataBase implements NameSurferConstants {

     HashMap<String, NameSurferEntry> mapBase = new HashMap<>();
    /* Constructor: NameSurferDataBase(filename) */

    /**
     * Creates a new NameSurferDataBase and initializes it using the
     * data in the specified file.  The constructor throws an error
     *                  запрошенный
     * exception if the requested file does not exist or if an error
     * возникла
     * occurs as the file is being read.
     */
    public NameSurferDataBase(String filename) {
        String allString;
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            while ((allString = br.readLine()) != null) {
                NameSurferEntry  entry = new NameSurferEntry(allString); //Create hash map objekt
                mapBase.put(entry.getName().toLowerCase(), entry);
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }


    /* Method: findEntry(name) */

    /**
     * Returns the NameSurferEntry associated with this name, if one
     * существует
     *  exists.  If the name does not appear in the database, this
     * method returns null.
     */
    public NameSurferEntry findEntry(String name) {
        if (mapBase.containsKey(name.toLowerCase())) return mapBase.get(name.toLowerCase());
                    return null;
        }
    }


