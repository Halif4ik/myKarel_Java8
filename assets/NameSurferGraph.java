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
        // You fill in the rest //
    }


    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        // You fill this in //
        listEntry.clear();
        this.update();
    }


    /* Method: addEntry(entry) */

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {
        // You fill this in //
        if (!listEntry.contains(entry)) {
            listEntry.add(entry);
            this.update();
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
        // You fill this in //
        removeAll();
        gridPlot();
        schedulesPlot();
    }

    /**
     * Paint grid and label
     */
    private void gridPlot() {

        double x = getWidth();
        double y = getHeight();
        double step = x / NDECADES;
        add(new GLine(0, GRAPH_MARGIN_SIZE, x, GRAPH_MARGIN_SIZE));
        add(new GLine(0, y - GRAPH_MARGIN_SIZE, x, y - GRAPH_MARGIN_SIZE));
        for (int i = 0; i < NDECADES; i++) {
            add(new GLine(step * i, 0, step * i, y));
            add(new GLabel("" + (START_DECADE + i * 10), step * i + GRAPH_MARGIN_SIZE / 4, y - GRAPH_MARGIN_SIZE / 4));
        }
    }

    /**
     * Paint color schedules in grid
     */
    private void schedulesPlot() {
        for (int i = 0; i < listEntry.size(); i++) {
            NameSurferEntry entry = listEntry.get(i);
            entryPlot(entry, COLORS[i % 4]);
        }

    }

    /**
     * plot graphic name entry and label
     * @param entry  NameSurferEntry
     * @param color  color
     */
    private void entryPlot(NameSurferEntry entry, Color color) {
        double step = getWidth() / (double) NDECADES;
        for (int i = 0; i < NDECADES - 1; i++) {
            double x1 = step * i;
            double x2 = step * (i + 1);

            int rank1 = entry.getRank(i) == 0 ? 1000 : entry.getRank(i);
            int rank2 = entry.getRank(i + 1) == 0 ? 1000 : entry.getRank(i + 1);

            double y1 = ((getHeight() - GRAPH_MARGIN_SIZE * 2) / (double) MAX_RANK) * rank1 + GRAPH_MARGIN_SIZE;
            double y2 = ((getHeight() - GRAPH_MARGIN_SIZE * 2) / (double) MAX_RANK) * rank2 + GRAPH_MARGIN_SIZE;

            //System.out.println(y1 + " " + y2);

            plotLine(x1, y1, x2, y2, color);
            plotLabel("" + entry.getName() + " " + (entry.getRank(i) == 0 ? "*" : entry.getRank(i)), x1,
                    (entry.getRank(i) == 0 ? (getHeight() - GRAPH_MARGIN_SIZE) : y1), color);

            // end label in graphic
            if (i == NDECADES - 2) {
                plotLabel("" + entry.getName() + " " + (entry.getRank(i + 1) == 0 ? "*" : entry.getRank(i + 1)), x2,
                        (entry.getRank(i + 1) == 0 ? (getHeight() - GRAPH_MARGIN_SIZE) : y2), color);
            }
        }
    }

    private void plotLabel(String s, double x, double y, Color color) {
        s = s.substring(0, 1).toUpperCase() + s.substring(1);
        GLabel label = new GLabel(s, x, y);
        label.setColor(color);
        add(label);
    }

    private void plotLine(double x1, double y1, double x2, double y2, Color color) {
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
