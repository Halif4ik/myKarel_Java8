package com.shpp.p2p.cs.dgrymach.Testi;


import java.util.Scanner;

public class inetlesson {
    public static void main(String args[]) {
        String result = "";
        Scanner scan = new Scanner(System.in);

        String number = scan.nextLine();
        int hex = 0xffffff;
        System.out.println(hex);

        for (int i=0; i<number.length(); i++) {
            int ch = number.charAt(i);

            result = (ch - '0') + result;

            }

            if (number.equals(result)) {
            System.out.print("YES"+ result);
        }else { System.out.print("NO");
        }


        }
    }

//    int count = 0;
//    Scanner scan = new Scanner(System.in);
//    int colCoin = scan.nextInt();
//    int [] arr = new int [colCoin];
//
//        for (int i=0; i<colCoin; i++) {
//        int zeroOrOne = scan.nextInt();
//        arr[i] =zeroOrOne;
//        }
//        for (int i=0; i<colCoin; i++) {
//        if (arr[i] == 1) count++;
//        }
//        if (colCoin / 2 < count) {
//        System.out.print(colCoin-count);
//        }else { System.out.print(count);
