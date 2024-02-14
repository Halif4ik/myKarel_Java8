package com.shpp.p2p.cs.dgrymach.Assignment12;

import acm.graphics.GImage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


class FindShadowsDfs implements Runnable, constantsDfs {
    private int[][] binaryArray;
    private int borderPicVsFon;
    private int[][] grayImage;
    private int numSilhouette = 0;
    private int curFon = 0;
    private int curSilhouettes = 1;
    private String args;


    /*constructor set right path to file  */
    FindShadowsDfs(String args) throws IOException {
        GImage basicImage = new GImage(IMAGE_PATH[0] + args);
        this.args =args;
        /*convert image to binary and gray color*/
        grayImage = convertImage(basicImage);
        binaryArray = createBinArray(grayImage);
        if (log) printNewBinImag(binaryArray);
        counterPerimetrBackground(binaryArray);
        if (log) printNewBinImag(binaryArray);
    }
    /*-------constructor---*/

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
            if (binaryArray[row][0] == 1) one++;//first column
            else zero++;
            if (binaryArray[row][binaryArray[row].length - 1] == 1) one++;
            else zero++;
        }
        if (log) System.out.println(zero + " = zero0 in right and left edge " + one + " = one1 in right and left edge");

        /*walk in top and down edge array and count fon and siluet */
        for (int col = 0; col < binaryArray[0].length; col++) {
            if (binaryArray[0][col] == 1) one++; //firs row
            else zero++;
            if (binaryArray[binaryArray.length - 1][col] == 1) one++;// last row
            else zero++;
        }
        /* if perimeter 1 is greater than 0,we  decide what is fon 1 */
        if (one > zero) {
           /* curSilhouettes = 0;
            curFon = 1;*/
            for (int row = 0; row < binaryArray.length; row++)
                for (int col = 0; col < binaryArray[row].length; col++) {
                    if (binaryArray[row][col] == 1) {
                        binaryArray[row][col] = 0;
                    } else binaryArray[row][col] = 1;
                }

        }
        if (log)
            System.out.println(zero + " = zero(start as fon)  " + one + " = one(start as silhouettes) in perimeter img");
    }

    /**
     * Transform image to RGBA matrix
     *
     * @param source - given image
     * @return - [][] array int RGBA
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
                    binImage[row][col] = binFon;// fon 0
                } else binImage[row][col] = binSilhouettes; // silhouettes 1
            }
        }
        return binImage;
    }

    private void printNewBinImag(int[][] binaryArray) {
        for (int[] aBinaryArray : binaryArray) {
            for (int col = 0; col < aBinaryArray.length; col++) {
                if ((aBinaryArray[col] == curFon)) System.out.print(".");
                else System.out.print((aBinaryArray[col] + ""));
            }
            System.out.println();
        }
    }

    /**
     * make graph of 'Silhouettes' pixels and count how many silhouettes are in it with Depth-first search algorithm
     */
    @Override
    public void run() {
        /*round to aray and find image which we mark '1'*/
        for (int row = 0; row < binaryArray.length; row++) {
            for (int col = 0; col < binaryArray[row].length; col++) {
                /*if found start of silhouette  */
                if (binaryArray[row][col] == curSilhouettes) {
                    /* mark currant silhouette next number different 1 or if invert fon begin from 0*/
                    binaryArray[row][col] = binaryArray[row][col] + (++numSilhouette);
                    /*start Depth-first search from current cell in[][]*/
                    findNextNumberOne(row, col, numSilhouette);
                }
            }
        }
        System.out.println("Numbers silhouettes:" + numSilhouette);
        int sil = getSil();
        printImg();
        System.out.println("Number silhouettes whits out trash = " + sil);
    }

    /**
     *
     */
    private void printImg() {
        /* Create a new window. */
        JFrame jfrm = new JFrame("Find in "+args);
        /* Set up the window to have the right size and title. */
        jfrm.setSize(binaryArray[0].length + FRAME_TITLEBAR_HEIGHT/2, binaryArray.length + FRAME_TITLEBAR_HEIGHT );
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Install a component in the window. */
        jfrm.add(new JComponent() {
            @Override
            public void paintComponent(Graphics g) {
                /* Draw a one pixel. */
                g.setColor(Color.black);
                for (int row = 0; row < binaryArray.length; row++)
                    for (int col = 0; col < binaryArray[row].length; col++) {
                        if( binaryArray[row][col]  != curFon)g.fillRect(col, row, 1, 1);
                    }
            }
        });
        jfrm.setVisible(true);
    }

    /**
     * go around [][] binary array, if find curFon - it is fon, another - has been all shadows and trash
     * when we go around [][] binary array  and find  cell (pixel) with (for example) number 2 we increment
     * cel number 2, etc..
     *
     * @return -
     */
    private int getSil() {
        int[] countPixInShadows = new int[numSilhouette + 2]; // first silhouette  we mark as 2  (0 it is fon)
        int silhou = 0;
        int max = 0;
        if (log) printNewBinImag(binaryArray);

        for (int[] rowBinaryArray : binaryArray)
            for (int col = 0; col < rowBinaryArray.length; col++)
                if (rowBinaryArray[col] != curFon) countPixInShadows[rowBinaryArray[col]]++;

        /*after went around [][] binary array, we will find the largest cell of the array, it is  the greatest swadow(GS).
        We decide if current shadow is less in half GS, it is trash, if not it is men shadow and increment counter 'sil'*/

        for (int size : countPixInShadows) max = size > max ? size :max;
        /*log out  how many pixel we find in each shadows*/
        for (int i = 0; i < countPixInShadows.length; i++) System.out.println(i + " = " + countPixInShadows[i]);

        for (int size : countPixInShadows) if (size > (int) (max / coefficient_humans)) silhou++;
        return silhou;
    }


    /**
     * Recurtion metod Depth-first search and change in bin array index all shadows from first found (1->2) to the end.
     *
     * @param row  -row  y coordinate current cell
     * @param col- column x coordinate current cell
     * @param indx - what number in current Silhouettes
     */
    private void findNextNumberOne(int row, int col, int indx) {
        /*check left cell*/                                         //1
        if (indexColOk(col - 1) && binaryArray[row][col - 1] == curSilhouettes) {
            binaryArray[row][col - 1] += indx;
            // printNewBinImage(binaryArray);
            findNextNumberOne(row, col - 1, indx);
        }
        /*check upper cell*/
        if ((indexRowOk(row - 1) && binaryArray[row - 1][col] == curSilhouettes)) {
            binaryArray[row - 1][col] += indx;
            // printNewBinImage(binaryArray);
            findNextNumberOne(row - 1, col, indx);
        }
        /*check right cell*/
        if (indexColOk(col + 1) && binaryArray[row][col + 1] == curSilhouettes) {
            binaryArray[row][col + 1] += indx; // mark currant silhouette next number different 1
            // printNewBinImage(binaryArray);
            findNextNumberOne(row, col + 1, indx);
        }
        /*check down cell*/
        if ((indexRowOk(row + 1) && binaryArray[row + 1][col] == curSilhouettes)) {
            binaryArray[row + 1][col] += indx;
            // printNewBinImage(binaryArray);
            findNextNumberOne(row + 1, col, indx);
        }
    }

    /**
     * check index no to go out from edge array in width
     *
     * @param index -index what we check
     * @return - true if index present or false
     */
    private boolean indexColOk(int index) {
        return index >= 0 && index < binaryArray[0].length;
    }

    /**
     * check index no to go out from edge array in height
     *
     * @param index -index what we check
     * @return - true if index present or false
     */
    private boolean indexRowOk(int index) {
        return index >= 0 && index < binaryArray.length;
    }
}
