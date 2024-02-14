package com.shpp.p2p.cs.dgrymach.Testi;

import com.shpp.cs.a.console.TextProgram;

public class heshmapchiki extends TextProgram {
    public void run() {

         String name = null;
         int rank[] = new int[12];
         String temp="";
            String line = "Aaliyah 0 0 0 0 0 0 0 0 0 380 215 146";

            String[] splitStringLine = line.split(" ");

            for (int i = 0; i < splitStringLine.length; i++) {
                if (i == 0) {
                    name = splitStringLine[i].toLowerCase();
                } else {
                    rank[i - 1] = Integer.parseInt(splitStringLine[i]);
                }
            }

            String result ="";// возвращает с большой букви стринг
        for ( int i = 0; i<name.length(); i++)
            if (i == 0) {
                result += (char) ((int) name.charAt(0) & 65503);
            }else {
                result += name.charAt(i);
            }
        for( int i=0; i<rank.length; i++)  result = result +" "+ rank[i];

        println(result);


//        String allSAtring;
//        try {
//            BufferedReader br = new BufferedReader(new FileReader("assets/names-data.txt"));
//            while ( (allSAtring = br.readLine()) != null) {
//
//                println(allSAtring);
//            }
//            br.close();
//        } catch (IOException e) {
//            System.out.println(e);
        }

        }




//   /** String name = null;
//    ArrayList<Integer> rank = new ArrayList<>();
//    String line = "Aaliyah 0 0 0 0 0 0 0 0 0 380 215 146";
//
//    String[] splitStringLine = line.split(" ");
//
//            for (int i = 0; i < splitStringLine.length; i++) {
//        if (i == 0) {
//        name = splitStringLine[i].toLowerCase();
//        } else {
//        rank.add( Integer.parseInt(splitStringLine[i]));
//        }
//        }
//        System.out.println(name);
//        System.out.println(rank.toString());
//
//        }
//
//        }
//*/