package com.shpp.p2p.cs.dgrymach.Assignment12;

public interface constantsDfs {
    /**
     * default path to file
     */
     String IMAGE_PATH[] = {"assets/"};
     String BASE_IMAGE_NAME[] = {"test.jpg"};
    /**
     * the label which marks the pixel silhouette
     */
     int binSilhouettes = 1;
    /**
     * the label which marks the pixel fon
     */
     int binFon = 0;
    /**
     * in on(true) in console println binary array from img
     */
     boolean log = true;
    /**
     * if the number of pixels of the current silhouette is less than 2.3 times from the
     * pixels of the largest silhouette, then we take the current silhouette as garbage
     */
     double coefficient_humans =2.3;

    /**height title bar in window*/
    int FRAME_TITLEBAR_HEIGHT = 40;


}
