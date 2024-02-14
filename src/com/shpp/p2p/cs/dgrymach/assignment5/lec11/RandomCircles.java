package com.shpp.p2p.cs.dgrymach.assignment5.lec11;

/*
 * File: RandomCircles.java
 * ========================================================
 * A program that adds random circles to the window.
 * This program now allows the user to individually control
 * the colour and size of each circle.
 */

import acm.graphics.GOval;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class RandomCircles extends WindowProgram {
    /* Width of the JTextField */
    private static final int NUM_COLUMNS = 10;
    /* Max radius of a circle */
    private static final int MAX_RADIUS = 100;

    private JSlider sizeSlider;
    private JTextField colourField;
    private RandomGenerator rgen;

    private ArrayList<GOval> circles;
    private JButton clearButton;

    /* Initialize instance variables and add interactors
     * to the display.
     */
    public void init() {
        sizeSlider = new JSlider(0, 100, 50);
        add(sizeSlider, SOUTH);
        colourField = new JTextField(NUM_COLUMNS);
        add(colourField, SOUTH);

        clearButton = new JButton("Clear");
        add(clearButton, NORTH);

        add(new JButton("Change Colours"), NORTH);

        clearButton.setActionCommand("clearEmAll");
        colourField.setActionCommand("");

        colourField.addActionListener(this);
        addActionListeners();

        rgen = RandomGenerator.getInstance();
        circles = new ArrayList<GOval>();
    }

    /*    Respond to action events by adding a new circle,
                     существующих
     *  clearing the existing circles, or randomly
     *      changing the colour of all circles.
       */
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        println(e.getModifiers());


        if (e.getSource() == colourField){//(cmd.equals("")) {
            addRandomCircle(colourField.getText());
        } else if (cmd.equals("clearEmAl")) {  //e.getSource() == clearButton
            clearCircles();
        } else {
            println("mm...");
            randomlyChangeColours();
        }
    }

    /* Choose a new random colour for each circle */
    private void randomlyChangeColours() {
        for (int i = 0; i < circles.size(); i++) {
            GOval circle = circles.get(i);
            circle.setColor(rgen.nextColor());
        }
    }

    /* Remove all circles and clear the ArrayList */
    private void clearCircles() {
        removeAll();    //c канваса
        circles.clear(); // с ерей листа
    }

    /* Add a circle with the given colour at a randomly chosen
     * position. Size is given by the value of sizeSlider.      Добавьте круг с заданным цветом в случайно выбранном
     * позиция. Размер задается значением ползунка Размер.
     */
    private void addRandomCircle(String colour) {
        double radius = MAX_RADIUS * (sizeSlider.getValue() / 100.0);
        double x = (getWidth() - radius * 2) * rgen.nextDouble(0, 1);
        double y = (getHeight() - radius * 2) * rgen.nextDouble(0, 1);

        GOval circle = new GOval(x, y, radius * 2, radius * 2);
        circle.setColor(getColor(colour));
        circle.setFilled(true);

        add(circle); // на канвас
        circles.add(circle); // и его же в ерейлист масив
    }

    /* Given a String, returns the corresponding Color */  //Если задана строка, возвращает соответствующий Цвет
    private Color getColor(String colour) {
        colour = colour.toLowerCase();
        if (colour.equals("black"))
            return Color.BLACK;
        if (colour.equals("red"))
            return Color.RED;
        if (colour.equals("blue"))
            return Color.BLUE;
        if (colour.equals("yellow"))
            return Color.YELLOW;
        if (colour.equals("green"))
            return Color.GREEN;
        if (colour.equals("orange"))
            return Color.ORANGE;
        return Color.BLACK;
    }

}
