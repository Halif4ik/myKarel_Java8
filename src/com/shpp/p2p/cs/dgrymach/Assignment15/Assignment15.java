package com.shpp.p2p.cs.dgrymach.Assignment15;

import java.io.*;

/**
 * Program take arguments from users  for example first== test.txt second test.par, and compressed or unzip
 * (if .par) and write in file, name file choose in class CheckCMD
 */
class Assignment15 implements IConst {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        ProcessedFile inputData = null;

        CheckCMD currentCMD = new CheckCMD();
        currentCMD.setNamesAndAction(args);

        /* Compressed*/
        if (currentCMD.action == ("-a")) {
            System.out.println("Put to use: " + currentCMD.startFile + " Compressed to " +
                    currentCMD.resultArhiv);

            /*write header 4+8 byte in  file*/
            try (BufferedInputStream bufInS = new BufferedInputStream(new FileInputStream(currentCMD.startFile));
                 DataOutputStream dataOutS = new DataOutputStream(new FileOutputStream(currentCMD.resultArhiv))) {
                                /* return obj, which has field table unique symbols consists in file,
                with their frequency found in file*/
                inputData = new ProcessedFile(bufInS);
                /* get table associations byte - code. code it is serial number in ArrList*/
                inputData.WriteHeaderArchive(dataOutS);
            } catch (IOException ex) {
                System.out.println("Error Input or output!" + ex);
                System.exit(0);
            }

            /*open the file again & compression file*/
            try (BufferedInputStream buffInpS = new BufferedInputStream(new FileInputStream(currentCMD.startFile));
                 BufferedOutputStream buffOutS = new BufferedOutputStream(new FileOutputStream(currentCMD.resultArhiv,
                         true))) {
                Node root = inputData.createTree();
                inputData.bypassInOrderMakeTable(root, "", true);
                inputData.compressedWrite(buffInpS, buffOutS);
            } catch (IOException ex) {
                System.out.println("Error Input or output!" + ex);
                System.exit(0);
            }

            /*UNCOMPRESSED*/
        } else {
            System.out.println("UNCOMPRESSED: " + (currentCMD.resultArhiv) + " In " + currentCMD.fileResult);
            //*READ header 4+8 byte in  file*//
            try (BufferedInputStream buffInpS = new BufferedInputStream(new FileInputStream(currentCMD.resultArhiv));
                 BufferedOutputStream buffOutS = new BufferedOutputStream(new FileOutputStream(currentCMD.fileResult))) {
                //* initializing constructor counters in second construct*//
                new ProcessedFile(buffInpS, buffOutS);
            } catch (IOException ex) {
                System.out.println("Error Input or output!" + ex);
                System.exit(0);
            } catch (Exception e) {
                System.out.println("It's still unclear what the problem is" + e);
                System.exit(0);
            }
        }
        System.out.println("Time of works program:" + (System.currentTimeMillis() - startTime) + " milisek.");

    }
}




