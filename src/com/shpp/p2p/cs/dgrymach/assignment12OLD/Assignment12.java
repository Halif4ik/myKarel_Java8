package com.shpp.p2p.cs.dgrymach.assignment12OLD;

import acm.graphics.GImage;
import com.shpp.cs.a.graphics.WindowProgram;

public class Assignment12 extends WindowProgram {

    private int[][] binaryArray;
    private int binSiluheuts = 1;
    private int binFon = 0;
    private int center = 0;
    int[][] grayImag;
    int numSiluet = 0;

    public void run() {

        GImage basicImage = new GImage("assets/test.jpeg");
        add(basicImage);

        /*convert image to binary and gray color*/
        basicImage = convertImage(basicImage);
        removeAll();
        add(basicImage);//add gray image

        binaryArray = createBinArray(grayImag);
        printNewBinImag(binaryArray);

        /*round to aray and find image which we mark '1'*/
        for (int row = 0; row < binaryArray.length; row++) {
            for (int col = 0; col < binaryArray[row].length; col++) {
                /*if found start of silhouette  */
                if (binaryArray[row][col] == 1) {
                    binaryArray[row][col] = binaryArray[row][col] + (++numSiluet); // mark currant silhouette next number different 1
                    findNextNumberOne(row, col, numSiluet); // start Depth-first search from current cell in[][]
                }
            }
        }
        println("Numbers silhouettes:" + numSiluet);

    }

    /**
     * @param source
     * @return
     */
    private GImage convertImage(GImage source) {
        int[][] pixels = source.getPixelArray();
        grayImag = new int[pixels.length][pixels[0].length];
        int min = 0, max = 0;

        /*move for all array*/
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < pixels[row].length; col++) { /* Extract the green and blue components and write them back, leaving the red component out.  */
                int green = GImage.getGreen(pixels[row][col]);
                int blue = GImage.getBlue(pixels[row][col]);
                int red = GImage.getRed(pixels[row][col]);
                int alfa = GImage.getAlpha(pixels[row][col]);

                /*create mix or gray pixel*/
                int mixColor = (green + blue + red + alfa) / 4;
                grayImag[row][col] = mixColor;

                /*find min and max value  gray color for find and different fon and image*/
                if (mixColor < min) min = mixColor;
                if (mixColor > max) max = mixColor;
                /*create image in array with 'grey' value integer */
                pixels[row][col] = GImage.createRGBPixel(mixColor, mixColor, mixColor, mixColor);
            }
        }

        center = (min + max) / 2 + min;
        return new GImage(pixels);
    }

    /**
     * contain arayy binari number
     *
     * @param grayImag
     * @return
     */
    private int[][] createBinArray(int[][] grayImag) {
        for (int row = 0; row < grayImag.length; row++) {
            for (int col = 0; col < grayImag[row].length; col++) {
                if (grayImag[row][col] > center) {
                    grayImag[row][col] = binFon;// fon 0
                } else grayImag[row][col] = binSiluheuts; // sil 1
            }
        }
        return grayImag;
    }

    /**
     * Recurtion metod Depth-first search
     *
     * @param row
     * @param col
     * @param indx
     */
    private void findNextNumberOne(int row, int col, int indx) {
        /*check left cell*/                                         //1
        if (indexColOk(col - 1) && binaryArray[row][col - 1] == binSiluheuts) {
            binaryArray[row][col - 1] += indx;
            // printNewBinImag(binaryArray);
            findNextNumberOne(row, col - 1, indx);
        }
        /*check upper cell*/
        if ((indexRowOk(row - 1) && binaryArray[row - 1][col] == binSiluheuts)) {
            binaryArray[row - 1][col] += indx;
             //printNewBinImag(binaryArray);
            findNextNumberOne(row - 1, col, indx);
        }
        /*check right cell*/
        if (indexColOk(col + 1) && binaryArray[row][col + 1] == binSiluheuts) {
            binaryArray[row][col + 1] += indx; // mark currant silhouette next number different 1
            // printNewBinImag(binaryArray);
            findNextNumberOne(row, col + 1, indx);
        }
        /*check down cell*/
        if ((indexRowOk(row + 1) && binaryArray[row + 1][col] == binSiluheuts)) {
            binaryArray[row + 1][col] += indx;
            //printNewBinImag(binaryArray);
            findNextNumberOne(row + 1, col, indx);
        }
    }

    private boolean indexRowOk(int indx) {
        if (indx >= 0 && indx < binaryArray.length) return true;
        return false;
    }

    /**
     * check index no out from age array
     *
     * @param indx -index what we check
     * @return - tru if index present or fslse
     */
    private boolean indexColOk(int indx) {
        if (indx >= 0 && indx < binaryArray[0].length) return true;
        return false;
    }

    private void printNewBinImag(int[][] binaryArray) {
        for (int row = 0; row < grayImag.length; row++) {
            for (int col = 0; col < grayImag[row].length; col++) {
                print(grayImag[row][col] + " ");
            }
            println();
        }
    }

}

