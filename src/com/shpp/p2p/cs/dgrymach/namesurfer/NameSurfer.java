package com.shpp.p2p.cs.dgrymach.namesurfer;

/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 *                          описанная   раздаточном материале.
 * the baby-name database described in the assignment handout.
 */

import com.shpp.cs.a.simple.SimpleProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class NameSurfer extends SimpleProgram implements NameSurferConstants {

    private NameSurferGraph graph;
    private NameSurferDataBase nameSurferDatabase = new NameSurferDataBase(NAMES_DATA_FILE);

    /* Method: init() */
    private JTextField nameField;
    /* Width of the JTextField */
    private static final int WIDTH_CELL = 30;
    /* Button for start foul play. */
    private JButton jButtonGraph, clear;

    /**
     * отвечает
     * This method has the responsibility for reading in the data base
     * and initializing the interactors at the top of the window.
     */
    @Override
    public void init() {
        graph = new NameSurferGraph();
        add(graph);

        add(new JLabel("Name: "), NORTH);
        nameField = new JTextField(WIDTH_CELL);
        jButtonGraph = new JButton("Graph");
        clear = new JButton("Clear");

        add(nameField, NORTH);
        add(jButtonGraph, NORTH);
        add(clear, NORTH);

        nameField.addActionListener(this);
        addActionListeners();// add listerens in our canvas for button
    }

    /* Method: actionPerformed(e) */

    /**
     *              отвечает        за определение
     * This class is responsible for detecting when the buttons are
     *                              определить          отвечал
     * clicked, so you will have to define a method to respond to
     * button actions.
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        NameSurferEntry entr;

        if (e.getSource() == nameField || e.getSource() == jButtonGraph) {
            entr = nameSurferDatabase.findEntry(nameField.getText());
            graph.addEntry(entr);

            println(entr.toString());
        } else {
            graph.clear();
        }
    }
}

