package com.shpp.p2p.cs.dgrymach.assignment4.lec10;

/* A program that shows off different sound modifications we can
 * make to a sound clip.
 */

import com.shpp.cs.a.console.TextProgram;


public class SoundWarper extends TextProgram {

    public void run() {
        double[] clip = StdAudio.read("assets/puppy.wav");

		/* Play the clip normally. */
        println("Original clip...");
        StdAudio.play(clip);
        pause(1000);

		/* Ssllooww ddoowwnn tthhee cclliipp. */
        println("Slower...");
        StdAudio.play(slowDown(clip));
        pause(1000);

		/* Spd th clp p! */
        println("Faster...");
        StdAudio.play(speedUp(clip));
    }

    /**
     * Given as input a sound clip represented as an array, returns a new       представленный в виде массива, возвращает новый
     * sound clip formed by repeating each sample twice in a row. This          звуковой клип формируется путем повторения каждого образца дважды подряд. Этот
     * gives the effect of a dramatically slowed sound.                  дает эффект резко замедленного звука.
     *
     * @param clip The original clip.
     * @return A sound clip representing a slower version of the original.
     */
    private double[] slowDown(double[] clip) {
        double[] result = new double[clip.length * 2];

        for (int i = 0; i < result.length; i++) {
			/* Each new sample is formed by going back to the original array and
			 * looking up at half the current position. This, due to rounding
			 * down with int division, doubles each entry.
			 */
            result[i] = clip[i / 2];
        }

        return result;
    }

    /**
     * Given a sound clip, returns a new sound clip that's twice as fast
     * as the original.
     *
     * @param clip The original sound clip.
     * @return A sped-up version of that sound clip.
     */
    private double[] speedUp(double[] clip) {
		/* We only need half as much space. */
        double[] result = new double[clip.length / 2];

		/* Sample from twice the current position. */
        for (int i = 0; i < result.length; i++) {
            result[i] = clip[i * 2];
        }

        return result;
    }
}
