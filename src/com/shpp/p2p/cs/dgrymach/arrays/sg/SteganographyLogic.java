package com.shpp.p2p.cs.dgrymach.arrays.sg;

import acm.graphics.*;

public class SteganographyLogic {
    /**
     * Given a GImage containing a hidden message, finds the hidden message
     * contained within it and returns a boolean array containing that message.
     * A message has been hidden in the input image as follows.  For each pixel
     * in the image, if that pixel has a red component that is an even number,
     * the message value at that pixel is false.  If the red component is an odd
     * number, the message value at that pixel is true.
     *
     * @param source The image containing the hidden message.
     * @return The hidden message, expressed as a boolean array.
     */
    public static boolean[][] findMessage(GImage source) {
        int[][] pixels = source.getPixelArray();
        boolean[][] arraySecretPixel = new boolean[pixels.length][pixels[0].length];

        for (int row = 0; row < pixels.length; row++)
            for (int col = 0; col < pixels[row].length; col++) {
                /* Extract the  red component out.*/
                int red = GImage.getRed(pixels[row][col]);
                /* if red canal odd , set pixel in result array true == black*/
                if (!(even(red))) arraySecretPixel[row][col] = true;
            }
                return arraySecretPixel;
    }

    /**
     * указанного
     * Hides the given message inside the specified image.
     * <p/>
     * The image will be given to you as a GImage of some size, and the message will
     * указано                                                         обозначается
     * be specified as a boolean array of pixels, where each white pixel is denoted
     * false and each black pixel is denoted true.
     * <p/>        должно                            путем
     * The message should be hidden in the image by adjusting the red channel of all
     * the pixels in the original image.  For each pixel in the original image, you
     * should make the red channel an even number if the message color is white at
     * that position, and odd otherwise. иначе
     * <p/>   предположить                                                   совпадают
     * You can assume that the dimensions of the message and the image are the same.
     * <p/>
     *
     * @param message The message to hide.
     * @param source  The source image.
     * @return A GImage whose pixels have the message hidden within it.
     */
    public static GImage hideMessage(boolean[][] message, GImage source) {
        int sourceArray[][] = source.getPixelArray();
        /*create array  in which will be put compleate pixels*/
                                                                                                                         //int imageWhithHideMessage[][] = new int[sourceArray.length][sourceArray[0].length];
        for (int row = 0; row < sourceArray.length; row++)
            for (int col = 0; col < sourceArray[row].length; col++) {
                /* if the secret pixel is black, represented by true, then you must make the red channel odd */
                if (message[row][col]) {
                    int red = GImage.getRed(sourceArray[row][col]);
                    int green = GImage.getGreen(sourceArray[row][col]);
                    int blue = GImage.getBlue(sourceArray[row][col]);
                    if (even(red)) if (red == 0) {
                        red++;
                    } else {
                        red--;
                    }
                    /* change pixel original image on pixel modifity image*/
                    sourceArray[row][col] = GImage.createRGBPixel(red, green, blue);

                    // if the pixel of the secret image is white, make it even
                } else {
                    int red = GImage.getRed(sourceArray[row][col]);
                    int green = GImage.getGreen(sourceArray[row][col]);
                    int blue = GImage.getBlue(sourceArray[row][col]);
                 if (!(even(red))) if (red == 255) {
                         red--;
                         } else {
                         red++;
                         }
                    /* change pixel original image on pixel modifity image*/
                    sourceArray[row][col] = GImage.createRGBPixel(red, green, blue);
                }
            }

        return new GImage(sourceArray);
    }

    /** return true if numder even
     *
     * @param red number which check
     * @return -true or false
     */
    private static boolean even(int red) {
        return red % 2 == 0;
    }
}
