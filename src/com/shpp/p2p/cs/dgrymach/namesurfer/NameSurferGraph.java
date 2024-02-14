package com.shpp.p2p.cs.dgrymach.namesurfer;

/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import acm.graphics.GLine;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {

    private ArrayList<NameSurferEntry> listEntry = new ArrayList<>();

    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        addComponentListener(this);
    }


    /**
     * Очищает список записей name surfer, хранящихся в этом классе.
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        listEntry.clear();   /* Remove all daw grafics and clear the ArrayList */
        update();
    }


    /* Method: addEntry(entry) */

    /**
     *                                              записей
     * Adds a new NameSurferEntry to the list of entries on the display.
     *                              фактически
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {
        if (!listEntry.contains(entry)) {
            listEntry.add(entry);
            update();
        }
    }


    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {
        removeAll();
        creatGrid();
        creatGrafic();
    }

    private void creatGrafic() {
        /*  print graphic for every entry who present in arraylist listantry */
        for (int i = 0; i < listEntry.size(); i++) {
            for (int j = 0; j < NDECADES-1; j++) {
                double x1, y1, x2, y2;                                   //   1 % 4 = 1
                x1 = ndecadeToXCoordinate(j);
                x2 = ndecadeToXCoordinate(j);

                y1 = rankToYCoordinate(listEntry.get(i).getRank(j));
                y2 = rankToYCoordinate(listEntry.get(i).getRank(j+1));

                //calculateCoordinateLine(x1, y1, x2, y2, j, i, listEntry);
                creatLine(x1, y1, x2, y2, COLORS_GRAFIC[i % COLORS_GRAFIC.length]);

                GLabel singer = drawSinger(listEntry.get(i).toString(j)); // maybe rerurn name from litle char
                singer.setFont("Times-18");
                singer.setLocation(x1, y1);
            }

            String nameNowDraw = listEntry.get(i).getName();
            int y1 = listEntry.get(i).getRank(0);
            System.out.println(nameNowDraw);
            System.out.println(y1);

        }
    }

    /**
     * Given a rank name in this decade, returns the screen y coordinate where rank should be displayed.
     * @param rank
          * @return
     */
    private double rankToYCoordinate(int rank) {//     1000    -  20
               return  getHeight() *  (rank - GRAPH_MARGIN_SIZE) / (MAX_RANK - GRAPH_MARGIN_SIZE);
    }

    /**
     * Given a row dekade, returns the screen x coordinate where  row should be displayed.
     * @param j
     * @return
     */
    private double ndecadeToXCoordinate(int j) {
        return j * getWidth() / NDECADES;
    }


    private void creatGrid() {
        /*plot labels  */
        GLabel singer;
        for (int i = 0; i < NDECADES; i++) {
            singer = drawSinger((START_DECADE + 10 * i) + "");
            //          text                        x
            singer.setLocation(i * (getWidth() / NDECADES), getHeight() - singer.getDescent());

            /* dravVerticalLine*/
            creatLine(i * (getWidth() / NDECADES), 0.0, i * (getWidth() / NDECADES), getHeight(), Color.BLACK);
        }

        /*draw gorizontal  Line*/
        for (int i = 0; i < 2; i++)
            creatLine(0, i * (getHeight() - GRAPH_MARGIN_SIZE * 2) + GRAPH_MARGIN_SIZE,
                    getWidth(),i * (getHeight() - GRAPH_MARGIN_SIZE * 2) + GRAPH_MARGIN_SIZE, Color.BLACK);

    }


    private GLabel drawSinger(String text) {
        GLabel singer = new GLabel("" + text);
        singer.setFont("Times-17");
        add(singer);
        return singer;
    }

    private void creatLine(double x1, double y1, double x2, double y2, Color color) {
        GLine line = new GLine(x1, y1, x2, y2);
        line.setColor(color);
        add(line);
    }

    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        update();
    }

    public void componentShown(ComponentEvent e) {
    }
}
