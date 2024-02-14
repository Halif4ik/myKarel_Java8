package com.shpp.p2p.cs.dgrymach.assignment12OLD;

import acm.graphics.GImage;
import com.shpp.cs.a.graphics.WindowProgram;

public class Assignment119 extends WindowProgram {
    private int[][] binaryArray;
    private int binSiluheuts = 1;
    private int binFon = 0;
    private int center = 0;

    public void run() {
        GImage basicImage = new GImage("assets/test.jpeg");
        add(basicImage);
        /*convert image to binary and gray color*/
        basicImage = convertImage(basicImage);
        removeAll();
        add(basicImage);//add gray image
         //binaryArray = createBinArray(grayImag);

        /*round to aray and find image which we mark '1'*/
//        for (int row = 0; row < binaryArray.length; row++) {
//            for (int col = 0; col < binaryArray[row].length; col++) {
//                /*if found start of silhouette  */
//                if (binaryArray[row][col] == 1) {
//                    binaryArray[row][col] = binaryArray[row][col] + (++numSiluet); // mark currant silhouette next number different 1
//                    findNextNumberOne(row, col, numSiluet++); // start Depth-first search from current cell in[][]
//                }
//            }
//            println("Numbers silhouettes:" + numSiluet);

        //}
    }

    private GImage convertImage(GImage source) {
        int[][] pixels = source.getPixelArray();
        int[][] grayImag = new int[pixels.length][pixels[0].length];
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
        println(center);


        for (int row = 0; row < grayImag.length; ++row) {
            for (int col = 0; col < grayImag[row].length; ++col) {
                if (grayImag[row][col] > center) {
                    grayImag[row][col] = 1;
                } else {
                    grayImag[row][col] = 8;
                }

                print(grayImag[row][col] + " ");
            }
            println();
        }

        return new GImage(pixels);
    }

    private int[][] createBinArray(int[][] grayImag) {

        /*contain arayy binari number */
        for (int row = 0; row < grayImag.length; row++) {
            for (int col = 0; col < grayImag[row].length; col++) {
                if (grayImag[row][col] > center) {
                    grayImag[row][col] = binFon; //0
                } else grayImag[row][col] = binSiluheuts;  // 1
                print(grayImag[row][col] + " ");
            }
            println();
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
        /*check right cell*/
        if (indexOk(col + 1) && binaryArray[row][col + 1] == binSiluheuts) {
            binaryArray[row][col + 1] += indx; // mark currant silhouette next number different 1
            findNextNumberOne(row, col + 1, indx);
            /*check left cell*/
        } else if (indexOk(col - 1) && binaryArray[row][col - 1] == binSiluheuts) {
            binaryArray[row][col - 1] += indx;
            findNextNumberOne(row, col - 1, indx);
            /*check upper cell*/
        } else if ((indexOk(row + 1) && binaryArray[row + 1][col] == binSiluheuts)) {
            binaryArray[row + 1][col] += indx;
            findNextNumberOne(row + 1, col, indx);
            /*check down cell*/
        } else if ((indexOk(row - 1) && binaryArray[row - 1][col] == binSiluheuts)) {
            binaryArray[row - 1][col] += indx;
            findNextNumberOne(row - 1, col, indx);
        }


    }

    /**
     * check index no out from age array
     *
     * @param indx -index what we check
     * @return - tru if index present or fslse
     */
    private boolean indexOk(int indx) {
        if (indx >= 0 & indx < binaryArray.length) return true;
        return false;
    }

}

