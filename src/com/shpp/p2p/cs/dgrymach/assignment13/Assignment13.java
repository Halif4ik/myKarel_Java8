package com.shpp.p2p.cs.dgrymach.assignment13;

/*Program take arguments from users as image  for example "test.png", and find silhouette in this image
end print in console numbers . too maybe print log in cmd
* */
class Assignment13 implements IConst {

    public static void main(String args[]) {
        /* Class scope. */
        final String fileName;
        /*create new anonymous class Thread for expansion stack size  for deep find recursion */
        try {
            if (args.length > 0 && args[0].matches("^.+\\.(jpg|jpeg|png|gif)$")) fileName = args[0];
            else fileName = BASE_IMAGE_NAME[0];

            /*thread with stack size long = 32 mbit (4mb) LeftShift by 25 bit */
            new Thread(null, new FindShadows(fileName), "Main thread", 1 << 25).start();

        } catch (Exception e) {
        System.out.println("Wrong path to image!" + e);
    }
    }

}


