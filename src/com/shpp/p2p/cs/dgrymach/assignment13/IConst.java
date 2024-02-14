package com.shpp.p2p.cs.dgrymach.assignment13;

public interface IConst {
    /**
     * default path to file
     */
    String IMAGE_PATH[] = {"assets/"};
    String BASE_IMAGE_NAME[] = {"test.jpg"};
    /**
     * the label which marks the pixel silhouette
     */
    int BIN_SILHOUETTES = 1;
    /**
     * the label which marks the pixel fon
     */
    int BIN_FON = 0;
    int curSilhouettes = 1;
    double ADHESION_COEFFICIENT = 0.4;
    /**
     * in on(true) in console println binary array from img
     */
    boolean log = false;
    /**
     * if the number of pixels of the current silhouette is less than 2.3 times from the
     * pixels of the largest silhouette, then we take the current silhouette as garbage
     */
    double COEFFICIENT_HUMANS =2;

    /**height title bar in window*/
    int FRAME_TITLEBAR_HEIGHT = 40;


}
