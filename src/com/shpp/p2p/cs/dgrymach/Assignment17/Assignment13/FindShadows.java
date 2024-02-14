package com.shpp.p2p.cs.dgrymach.Assignment17.Assignment13;

import acm.graphics.GImage;
import com.shpp.p2p.cs.dgrymach.Assignment16.Queue.MyQueue;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class FindShadows implements Runnable, IConst {
    /* queue in what we save  our coordinats find pixel(cell) shadow(silhouettes)*/
    private MyQueue<int[]> searchQuery = new MyQueue<>();
    /*name opened file*/

    private final String args;

    private int[][] binaryArray;
    private int borderPicVsFon;
    private int[][] grayImage;
    private int numSilhouette;

    /*constructor set right path to file  and */
    FindShadows(String args) {
        GImage basicImage = new GImage(IMAGE_PATH[0] + args);
        this.args = args;
        /*convert image to binary and gray color*/
        grayImage = convertImage(basicImage);
        binaryArray = createBinArray(grayImage);
        if (log) printNewBinImag(binaryArray);
        counterPerimetrBackground(binaryArray);
        if (log) printNewBinImag(binaryArray);
    }

    /**
     * Transform image to RGBA matrix
     *
     * @param source - given image
     * @return- [][] array int RGBA
     */
    private int[][] convertImage(GImage source) {
        int[][] pixels = source.getPixelArray();
        grayImage = new int[pixels.length][pixels[0].length];
        int min = 0, max = 0;

        /*move for all array*/
        for (int row = 0; row < pixels.length; row++) {
            /* Extract the green and blue components and write them back, leaving the red component out.  */
            for (int col = 0; col < pixels[row].length; col++) {
                int green = ((pixels[row][col]) >> 8) & 255; //get green   rgb & 0xff00 >> 8
                int blue = GImage.getBlue(pixels[row][col]);
                int red = GImage.getRed(pixels[row][col]);
                int alpha = GImage.getAlpha(pixels[row][col]);

                /*create mix, this will be gray pixel*/
                int mixColor = (green + blue + red + alpha) / 4;
                grayImage[row][col] = mixColor;

                /*find min and max value  gray color for find and different fon and image*/
                if (mixColor < min) min = mixColor;
                if (mixColor > max) max = mixColor;
            }
        }
        borderPicVsFon = (min + max) / 2 + min;
        return grayImage;
    }

    /**
     * do fill array binary numbers
     *
     * @param grayImag -  array what contains numbers from 0 to 255 its mix value rgb
     * @return - array what contains in 1 or 0
     */
    private int[][] createBinArray(int[][] grayImag) {
        int[][] binImage = new int[grayImag.length][grayImag[0].length];
        for (int row = 0; row < grayImag.length; row++) {
            for (int col = 0; col < grayImag[row].length; col++) {
                if (grayImag[row][col] > borderPicVsFon) {
                    binImage[row][col] = BIN_FON;// fon 0  255 white>127
                } else binImage[row][col] = BIN_SILHOUETTES; // silhouettes 1 0 black
            }
        }
        return binImage;
    }

    /**
     * Check perimeter img and dicede what is fon
     */
    private void counterPerimetrBackground(int[][] binaryArray) {
        System.out.println(binaryArray.length + "x" + binaryArray[0].length + " dimension of image");

        /*create convert fon*/
        int one = 0;
        int zero = 0;
        /*go around in right and left edge array and count fon and Silhouettes */
        for (int row = 0; row < binaryArray.length; row++) {
            if (binaryArray[row][0] == curSilhouettes) one++;//first column
            else zero++;
            if (binaryArray[row][binaryArray[row].length - 1] == 1) one++;
            else zero++;
        }
        if (log) System.out.println(zero + " = zero0 in right and left edge " + one + " = one1 in right and left edge");

        /*walk in top and down edge array and count fon and siluet */
        for (int col = 0; col < binaryArray[0].length; col++) {
            if (binaryArray[0][col] == curSilhouettes) one++; //firs row
            else zero++;
            if (binaryArray[binaryArray.length - 1][col] == 1) one++;// last row
            else zero++;
        }
        /* if perimeter 1 is greater than 0,we  decide what is fon 1 and change background and silhouettes  */
        if (one > zero) {
            /* curSilhouettes = 0;  curFon = 1;*/
            for (int row = 0; row < binaryArray.length; row++)
                for (int col = 0; col < binaryArray[row].length; col++) {
                    if (binaryArray[row][col] == curSilhouettes) {
                        binaryArray[row][col] = BIN_FON;
                    } else binaryArray[row][col] = curSilhouettes;
                }
        }
        if (log)
            System.out.println(zero + " = zero(start as fon)  " + one + " = one(start as silhouettes) in perimeter img");
    }


    /**
     * make graph of 'Silhouettes' pixels and count how many silhouettes are in it with breadth-first search algorithm
     */
    @Override
    public void run() {
        findSilhouettes();
        if (askUserToCut()) {
            cutImg();
            printImg();
           findSilhouettes();
        }
    }

    private void findSilhouettes() {
        numSilhouette=0;
        /*round to aray and find image which we mark '1'*/
        for (int row = 0; row < binaryArray.length; row++) {
            for (int col = 0; col < binaryArray[row].length; col++) {
                /*if found start of silhouette it pixel mark as 1 that pixel we dont visit because all another
                pixel we mark 2 and height */
                if (binaryArray[row][col] ==curSilhouettes) {
                    /* mark currant silhouette next number different 1 or if invert fon begin from 0*/
                    binaryArray[row][col] = binaryArray[row][col] + (++numSilhouette);
                    findShadows(row, col);
                }
            }
        }
        if (log) printNewBinImag(binaryArray);
        System.out.println("Numbers silhouettes:" + numSilhouette);
        int sil = kickOutGarbage();
        printImg();
        System.out.println("Number silhouettes whits out trash = " + sil);
    }

    private void cutImg() {
        int maxHeightSilhouettes = 0;
        int currentHeightSilhouettes = 0;
        int[] countPixelInCurCollumn = new int[binaryArray[0].length];

        for (int col = 0; col < binaryArray[0].length; col++) {
            if (currentHeightSilhouettes > maxHeightSilhouettes) maxHeightSilhouettes = currentHeightSilhouettes;
            currentHeightSilhouettes = 0;
            for (int row = 0; row < binaryArray.length; row++)
                if (binaryArray[row][col] != BIN_FON) {
                  //  binaryArray[row][col] = 8;
                    currentHeightSilhouettes++;
                    //printNewBinImag(binaryArray);
                }
            countPixelInCurCollumn[col] = currentHeightSilhouettes; // write in already passed column
        }

        for (int col = 0; col < binaryArray[0].length; col++)
            /*if in this column count pixel less from necessary we delete this column pixels*/
            if (countPixelInCurCollumn[col] < maxHeightSilhouettes * ADHESION_COEFFICIENT)
                for (int row = 0; row < binaryArray.length; row++) binaryArray[row][col] = BIN_FON;
    /*make start image 0 and 1*/
        for (int col = 0; col < binaryArray[0].length; col++)
            for (int row = 0; row < binaryArray.length; row++)
            binaryArray[row][col] = binaryArray[row][col] != BIN_FON ? curSilhouettes: BIN_FON;

       if(log){
           printNewBinImag(binaryArray);
           System.out.println(maxHeightSilhouettes + " max  ");
       }
    }

    /**
     * @return
     * @throws IOException
     */
    private boolean askUserToCut() {
        byte data[] = new byte[1];
        System.out.print("Do you want to cut the silhouettes stuck together? (y/n): ");
        try {
            System.in.read(data);
        } catch (IOException e) {
            System.out.println("data input output error" + e);
            return false;
        }
        return Character.toLowerCase((char) data[0]) == 'y';
    }

    /**
     * @param
     * @param row
     * @param col
     */
    private void findShadows(int row, int col) {
        int[] coordinatesFirstFindCell;
        if (log) System.out.println(row + " " + col);

        /*add first find cell in queue find, as array with coordinates FirstFindCell*/
        searchQuery.add(new int[]{row, col});

        /*start finds silhouettes*/
        while (!searchQuery.isEmpty()) {
            /* extract the coordinates of the cell hat is first in the queue*/   /* del from queue first arr*/
            coordinatesFirstFindCell = searchQuery.poll();
            row = coordinatesFirstFindCell[0];
            col = coordinatesFirstFindCell[1];



            /* start breadth-first search from current cell in[][]*/
            breadthFirstSearch(row, col, numSilhouette);
        }
    }

    /**
     * breadth-first search from current cell [][] (pixel)
     *
     * @param row-           coordinate y current cell
     * @param col-           coordinate  x current cell
     * @param indexForChange - index begin from 2, all shadows and trash  mark as 1, in BFS
     *                       rename first shadows to 2 second to 3 etc.
     */
    private void breadthFirstSearch(int row, int col, int indexForChange) {
        /*check left cell*/
        if (indexColOk(col - 1) && binaryArray[row][col - 1] == curSilhouettes) {
            binaryArray[row][col - 1] += indexForChange; // mark currant silhouette next number different 1
            //  printNewBinImag(binaryArray);
            searchQuery.add(new int[]{row, col - 1});
        }
        /*check upper cell*/
        if ((indexRowOk(row - 1) && binaryArray[row - 1][col] == curSilhouettes)) {
            binaryArray[row - 1][col] += indexForChange;            // printNewBinImag(binaryArray);
            searchQuery.add(new int[]{row - 1, col});
        }
        /*check right cell*/
        int cellOnRight = col + 1;
        if (indexColOk(cellOnRight) && binaryArray[row][cellOnRight] == curSilhouettes) {
            binaryArray[row][cellOnRight] += indexForChange;             //  printNewBinImag(binaryArray);
            searchQuery.add(new int[]{row, cellOnRight});
        }
        /*check down cell*/
        int cellOnDown = row + 1;
        if ((indexRowOk(cellOnDown) && binaryArray[cellOnDown][col] == curSilhouettes)) {
            binaryArray[cellOnDown][col] += indexForChange;            //  printNewBinImag(binaryArray);
            searchQuery.add(new int[]{cellOnDown, col});
        }
    }

    /**
     * go around [][] binary array, if find curFon - it is fon, another - has been all shadows and trash
     * when we go around [][] binary array  and find  cell (pixel) with (for example) number 2 we increment
     * cel number 2, etc..
     *
     * @return -
     */
    private int kickOutGarbage() {
        int[] countPixInShadows = new int[numSilhouette + 2]; // first silhouette  we mark as 2  (0 it is fon)
        int silhou = 0;
        int max = 0;
        if (log) printNewBinImag(binaryArray);

        for (int row =0; row< binaryArray.length; row++)
            for (int col = 0; col < binaryArray[0].length; col++)
                if (binaryArray[row][col] != BIN_FON) countPixInShadows[binaryArray[row][col]]++;

        /*after went around [][] binary array, we will find the largest cell of the array, it is the greatest swadow(GS).
        We decide if current shadow is less in half GS, it is trash, if not it is men shadow and increment counter 'sil'*/

        for (int size : countPixInShadows) max = size > max ? size : max;
        System.out.println(max);
        /*log out  how many pixel we find in each shadows*/
        for (int i = 0; i < countPixInShadows.length; i++) System.out.println(i + " = " + countPixInShadows[i]);

        for (int size : countPixInShadows) if (size > (int) (max / COEFFICIENT_HUMANS)) silhou++;
        return silhou;
    }


    private boolean indexRowOk(int indx) {
        return indx >= 0 && indx < binaryArray.length;
    }

    /**
     * check index no out from age array
     *
     * @param indx -index what we check
     * @return - tru if index present or fslse
     */
    private boolean indexColOk(int indx) {
        return indx >= 0 && indx < binaryArray[0].length;
    }

    private void printNewBinImag(int[][] binaryArray) {
        for (int[] aBinaryArray : binaryArray) {
            for (int col = 0; col < aBinaryArray.length; col++) {
                if ((aBinaryArray[col] == BIN_FON)) System.out.print(".");
                else System.out.print((aBinaryArray[col] + ""));
            }
            System.out.println();
        }
    }

    /**
     *
     */
    private void printImg() {
        /* Create a new window. */
        JFrame jfrm = new JFrame("Find in " + args);
        /* Set up the window to have the right size and title. */
        jfrm.setSize(binaryArray[0].length + FRAME_TITLEBAR_HEIGHT / 2, binaryArray.length + FRAME_TITLEBAR_HEIGHT);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Install a component in the window. */
        jfrm.add(new JComponent() {
            @Override
            public void paintComponent(Graphics g) {
                /* Draw a one pixel. */
                g.setColor(Color.black);
                for (int row = 0; row < binaryArray.length; row++)
                    for (int col = 0; col < binaryArray[row].length; col++) {
                        if (binaryArray[row][col] != BIN_FON) g.fillRect(col, row, 1, 1);
                    }
            }
        });
        jfrm.setVisible(true);
    }
}
