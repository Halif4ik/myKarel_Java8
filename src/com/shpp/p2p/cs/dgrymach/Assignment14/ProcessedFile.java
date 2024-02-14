package com.shpp.p2p.cs.dgrymach.Assignment14;

import java.io.*;
import java.util.ArrayList;

/**This class can archive file, take from main stream (BufferedInputStream and DataOutputStream) go around in all file
 * and count unique byte and write header, in next round append in file archive data.
 * when in main create constructor with (BufferedInputStream BufferedOutputStream) we unzip file go around in file and
 * search new byte in table association(ArrayList)*/

class ProcessedFile implements IConst {
    /* character in int frequency in string*/
    private ArrayList<Integer> tableUniqueB = new ArrayList<>();
    private int numBitToShift;
    /* counters size input and output(compressed) file if we read/write one byte we increment this counter*/
    private long counterSizeInputFile;//long
    private long counterSizeCompressedFile;//long
    private long counterByteSizeUnCompressedFile;

    /* constructor create table (array list) of unique symbols*/
    ProcessedFile(BufferedInputStream bfInStr) throws IOException {
        int currentByte;
        /* while not end chars in input file read po 2 byte*/
        while ((currentByte = bfInStr.read()) != -1) {
            counterSizeInputFile++;
            /* If char(byte) don't present in our array, Mark that we've been to this character one more time. */
            if (!tableUniqueB.contains(currentByte)) tableUniqueB.add(currentByte);
        }
        /* for depend of number unique bytes we decide how much must be length code in bits */
        /*                      (0000 00)11            4 -1= 3              2*/
        numBitToShift = Integer.toBinaryString(tableUniqueB.size() - 1).length();
        if (LOGG)
            System.out.println(tableUniqueB.toString() + "\n" + (tableUniqueB.size()) + " size array unique byte, " +
                    "in bits: " + Integer.toBinaryString(tableUniqueB.size() - 1) + " numBitToShift: " + numBitToShift);

        System.out.println("Start size file: " + counterSizeInputFile + " byte");
    }

    /**
     * currentByte - read compresset byte whet consist from some cods(1 else 8 bit)
     * maskForByte - mask what check if stand current bit or not
     * * @return - character code it is index in table (array list)
     */
    /* constructor to unzip the file, first initializing counters size file in byte & count bit compressed file */
    ProcessedFile(BufferedInputStream buffInpSArh, BufferedOutputStream buffOutSUzip) throws IOException {
        int currentByte;

        /*read first int and remember as length list  tableUniqueB */
        int lenthTableUniqueB = readint(buffInpSArh);

        /*read next 8 byte and remember as size  compressed data in Bits*/
        long lenthCompresDataInBit = reaLong(buffInpSArh);
        if (LOGG)
            System.out.println(lenthTableUniqueB + " lenthTableUniqueB(int) " + lenthCompresDataInBit +
                    " lenthCompresDataInBit(long)");

        /*start read table*/
        for (int i = 0; i < lenthTableUniqueB; i++) {
            tableUniqueB.add(buffInpSArh.read());
        }
        if (LOGG) System.out.println("Table: " + tableUniqueB.toString());

        /* for depend of number unique bytes we decide how much must be length code in bits */
        /*                      (0000 0)100               5 -1=       4      3*/
        numBitToShift = Integer.toBinaryString(tableUniqueB.size() - 1).length();


        /*start read compressed data and unzip and write in ready file*/
        int counter = 0;
        int readyCode = 0;
        int countWrote = 0;

        /*while byte is present we read or while need to read*/
        endUsefulBit:
        while ((currentByte = buffInpSArh.read()) != -1) {
            /*until we pass all bytes ,  create current code what consist from some bits (3 for example)  */
            /*        128                          7                        2>0             64    */
            for (int maskForByte = 1 << (LENTH_BYTE_IN_BITS - 1); maskForByte > 0; maskForByte >>= 1) {
                /*set mask in first position 1000 0000 & check first bit*/
                readyCode <<= 1;
                if ((currentByte & maskForByte) != 0) readyCode++;
                counter++;

                if (counter >= numBitToShift) {
                    buffOutSUzip.write(tableUniqueB.get(readyCode));
                    counterByteSizeUnCompressedFile++;
                    if (numBitToShift * ++countWrote >= lenthCompresDataInBit) break endUsefulBit;
                    readyCode = 0;
                    counter = 0;
                }
            }
        }
        System.out.println("Size input Unzip file is: " + counterByteSizeUnCompressedFile + " Bytes");
    }


    /**
     * move to the left to free up space for a new byte.if we uncompressed incorrect file do exit, else
     * add new byte in our integer
     *
     * @param buffInpSArh- stream from what we take for each byte
     * @return - read int
     * @throws IOException - if we need to read incorrect file , throw exception
     */
    private int readint(BufferedInputStream buffInpSArh) throws IOException {
        int readyInt = 0;
        int currentByte;
        for (int numbReadInt = 0; numbReadInt < COUNT_BYTE_IN_INT; numbReadInt++) {
            readyInt = readyInt << LENTH_BYTE_IN_BITS;
            currentByte = buffInpSArh.read();
            /* if byte is correct  add bytes to a temporary buffer*/
            if (currentByte == -1) {
                System.out.println("Incorrect input file, program shutdown!");
                throw new IOException();
            } else readyInt = readyInt | currentByte;
        }
        return readyInt;
    }

    /**
     * move to the left to free up space for a new byte.if we uncompressed incorrect file do exit, else
     * add new byte in our Long
     *
     * @param buffInpSArh- stream from what we take for each byte
     * @return - read long
     * @throws IOException - if we need to read incorrect file , throw exception
     */
    private long reaLong(BufferedInputStream buffInpSArh) throws IOException {
        long readyLong = 0;
        int currentByte;
        for (int numberReadByte = 0; numberReadByte < COUNT_BYTE_IN_LONG; numberReadByte++) {
            readyLong = readyLong << LENTH_BYTE_IN_BITS;
            currentByte = buffInpSArh.read();

            if (currentByte == -1) {
                System.out.println("Incorrect input file, program shutdown!");
                throw new IOException();
            } else readyLong = readyLong | currentByte; // add bytes to a temporary buffer
        }
        return readyLong;
    }

    /**
     * write table size in bytes 4 byte, size compressed data in bits 8 byte, and the table itself
     *
     * @param dataOutS- stream for write to file binary data
     */
    void WriteHeaderArchive(DataOutputStream dataOutS) throws IOException {
        /*write table size in bytes 4 byte*/
        dataOutS.writeInt(tableUniqueB.size());
        counterSizeCompressedFile += COUNT_BYTE_IN_INT;

        /* how mach data in bits are write in file sizeDataBit 8  byte*/
        dataOutS.writeLong(numBitToShift * counterSizeInputFile);
        counterSizeCompressedFile += COUNT_BYTE_IN_LONG;

        /*write table  of associations size table in byte == tableUniqueB.length*/
        for (int byteToWr : tableUniqueB) {
            dataOutS.write(byteToWr);
            counterSizeCompressedFile++;
        }
    }

    /**
     * open the input file again and contain buffer Byte, when it is full write him ii output file.par
     *
     * @param bufInS- input stream
     * @param bufOuS- output stream
     */
    void compressedWrite(BufferedInputStream bufInS, BufferedOutputStream bufOuS) throws IOException {
        int currentByte;
        int counter = 0;
        int codeCurrentByte;
        int codeBuffer = 0;

        while ((currentByte = bufInS.read()) != -1) {
            codeCurrentByte = tableUniqueB.indexOf(currentByte);

            for (int i = numBitToShift - 1; i >= 0; i--) {
                /*set the mask to the starting position*/
                int bitMask = 1 << i;
                /* shift all bits buffer in left for freedom 1 bit for accept mask */
                codeBuffer <<= 1;
                if ((codeCurrentByte & bitMask) != 0) codeBuffer++;
                counter++;

                if (counter >= LENTH_BYTE_IN_BITS) {
                    bufOuS.write(codeBuffer);
                    counterSizeCompressedFile++;
                    codeBuffer = 0;
                    counter = 0;
                }
            }
        }
        if (codeBuffer > 0 || counter > 0) {
            codeBuffer = codeBuffer << LENTH_BYTE_IN_BITS - counter;
            bufOuS.write(codeBuffer);
            counterSizeCompressedFile++;
        }
        if (LOGG) System.out.println("count bits: " + (numBitToShift * counterSizeInputFile));
        System.out.println("Size compressed file: " + counterSizeCompressedFile + " Byte");
        System.out.println("Compression efficiency: " + ((double) (counterSizeInputFile - counterSizeCompressedFile) / counterSizeInputFile) * 100 + "%");
    }
}

