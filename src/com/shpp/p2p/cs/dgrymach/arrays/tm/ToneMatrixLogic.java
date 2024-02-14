package com.shpp.p2p.cs.dgrymach.arrays.tm;

public class ToneMatrixLogic {
    /**
     * Given the contents of the tone matrix, returns a string of notes that should be played
     * to represent that matrix.
     * тоновой
     *
     * @param toneMatrix The contents of the tone matrix.       now
     * @param column     The column number that is currently being played.
     *                   связанные
     * @param samples    The sound samples associated with each row.
     *                   Образец звука     соответствующий               now
     * @return A sound sample corresponding to all notes currently being played.
     */
    public static double[] matrixToMusic(boolean[][] toneMatrix, int column, double[][] samples) {
        double[] summNowPlayColSeample = new double[ToneMatrixConstants.sampleSize()];
        double[] nowTurnLampCellSeample = new double[samples[0].length];
        System.out.println();
        for (int row = 0; row < toneMatrix.length; row++)
            /* if cell true  lamp turn on we add in playlist note from this row*/
            if (toneMatrix[row][column]) {
                nowTurnLampCellSeample = samples[row];
                /* summ all element new note with rest turn lamp */
                for (int i = 0; i < nowTurnLampCellSeample.length; i++)
                    summNowPlayColSeample[i] += nowTurnLampCellSeample[i];
            }

        summNowPlayColSeample = normalize(summNowPlayColSeample);

        return summNowPlayColSeample;

    }

    private static double[] normalize(double[] summNowPlayColSeample) {
        double maxIntensityCellMinus = 0;
        double maxIntensityCellPlus = 0;
        double maxTotalIntens = 0;

        /* search max intensity*/
        for (double oneElement : summNowPlayColSeample) {
            if (oneElement < 0) {
                if (oneElement < maxIntensityCellMinus) maxIntensityCellMinus = oneElement;
            } else {
                if (oneElement > maxIntensityCellPlus) maxIntensityCellPlus = oneElement;
            }
        }

            if (-maxIntensityCellMinus > maxIntensityCellPlus) {
                maxTotalIntens = maxIntensityCellMinus;
            } else {
                maxTotalIntens = maxIntensityCellPlus;

            }

        if (maxTotalIntens != 0) for (int i=0; i<summNowPlayColSeample.length; i++) {
            summNowPlayColSeample[i] = summNowPlayColSeample[i] / maxTotalIntens;
        }

        return summNowPlayColSeample;
    }
}

