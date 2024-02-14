package com.shpp.p2p.cs.dgrymach.Assignment15;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * This class can archive file, take from main stream (BufferedInputStream and DataOutputStream) go around in all file
 * and count unique byte and write header, in next round append in file archive data.
 * when in main create constructor with (BufferedInputStream BufferedOutputStream) we unzip file go around in file and
 * search new byte in table association(ArrayList)
 */
class ProcessedFile implements IConst {
    /* character in int frequency in string*/
    private HashMap<Integer, Integer> frequencyByte = new HashMap<>();


    /* table unique byte sorted at the beginning  byte rare meet in the end often */
    private ArrayList<Integer> tableUniqueB = new ArrayList<>();

    /* counters size input and output(compressed) file if we read/write one byte we increment this counter*/
    private long counterSizeInputFile;
    private long counterSizeCompressedFile;
    private long counterByteSizeUnCompressedFile;

    /*create codes tables Huffman */
    HashMap<Integer, String> tableByteCodes = new HashMap<>();
    HashMap<String, Integer> tableCodeByte = new HashMap<>();

    /* constructor create table (HashMap) of unique symbols and its frequency in file*/
    ProcessedFile(BufferedInputStream fInStr) throws IOException {
        int currentByte;
        int minFreq = 1;
        while ((currentByte = fInStr.read()) != -1) {
            counterSizeInputFile++;
            /* if char don't present in our array ++else char already present */
            int increment = frequencyByte.getOrDefault(currentByte, 0);
            frequencyByte.put(currentByte, ++increment);
        }

        if (LOGG) System.out.println(frequencyByte.size() + " counter of unique");

        /*sorted our bytes from hashmap to arrayList(table) by the number frequency (po value)*/
        while (!frequencyByte.isEmpty()) markBreak:{//goto operator for ConcurrentModificationException
            for (Map.Entry<Integer, Integer> keyPlusVal : frequencyByte.entrySet()) {
                if (keyPlusVal.getValue() <= minFreq) {
                    int currentKey = keyPlusVal.getKey();
                    tableUniqueB.add(currentKey); //in current row write in first cell key
                    frequencyByte.remove(currentKey);// remove current byte from table HashMap
                    break markBreak;
                }
            }
            /*  after first round increment min count frequency*/
            minFreq++;
        }
        if (LOGG) System.out.println(tableUniqueB.toString());
        System.out.println("Start size file: " + counterSizeInputFile + " byte");
    }

    /**
     * currentByte - read compressed byte whet consist from some cods(1 else 8 bit)
     * maskForByte - mask what check if stand current bit or not
     * * @return - character code it is index in table (array list)
     */
    /* constructor to unzip the file, first initializing counters size file in byte & count bit compressed file */
    ProcessedFile(BufferedInputStream buffInpSArh, BufferedOutputStream buffOutSTxtUnarh) throws IOException {
        /*read first int and remember as length list  tableUniqueB */
        int lenthTableUniqueB = readint(buffInpSArh);

        /*read next 8 byte and remember as size  compressed data in Bits*/
        long lenthCompresDataInByte = reaLong(buffInpSArh);
        if (LOGG) System.out.println(lenthTableUniqueB + " lenthTableUniqueB(int) " + lenthCompresDataInByte +
                " lenthCompresDataInBit(long)");

        /*start read table*/
        for (int i = 0; i < lenthTableUniqueB; i++) tableUniqueB.add(buffInpSArh.read());

        if (LOGG) System.out.println("Table: " + tableUniqueB.toString());

        /*start read compressed data and unzip and write in ready file*/
        int currentByte;
        String readyCode = "";

        Node root = createTree();

        bypassInOrderMakeTable(root, "", false);
        // tableCodeByte

        /*until byte is present we read or while need to read*/
        endUsefulBits:
        while ((currentByte = buffInpSArh.read()) != -1) {
            /*  chek in order bit (in readed byte)and create current code (string++) what consist from some bits */
            /*        128                          7                        2>0             64    */
            for (int maskForByte = 1 << (LENTH_BYTE_IN_BITS - 1); maskForByte > 0; maskForByte >>= 1) {
                /*set mask in first position 1000 0000 & check first bit*/
                if ((currentByte & maskForByte) != 0) {
                    readyCode = readyCode + "1";
                } else readyCode = readyCode + "0";

                if (tableCodeByte.containsKey(readyCode)) {
                    buffOutSTxtUnarh.write(tableCodeByte.get(readyCode));
                    counterByteSizeUnCompressedFile++;
                    if (counterByteSizeUnCompressedFile >= lenthCompresDataInByte) break endUsefulBits;
                    readyCode = "";
                }
            }
        }
        System.out.println("Size input Unzip file is: " + counterByteSizeUnCompressedFile + " Bytes");
    }

    /*-----------end constructors--------------*/

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

        /* size of unzip data in bytes are write in file  8  byte*/
        dataOutS.writeLong(counterSizeInputFile);
        counterSizeCompressedFile += COUNT_BYTE_IN_LONG;

        /*write table  of associations size table in byte == tableUniqueB.length*/
        for (int byteToWr : tableUniqueB) {
            dataOutS.write(byteToWr);
            counterSizeCompressedFile++;
        }
    }

    /**
     * create new node as fields is unique byte and its index in table (arraylist)
     */
    Node createTree() {
        PriorityQueue<Node> sortedNode = new PriorityQueue<>();
        /*                                              data=uniqueByte              freq =index in sorted array   */
        for (int i = 0; i < tableUniqueB.size(); i++) sortedNode.add(new Node(tableUniqueB.get(i), i, true));

        /*create one node who was root tree*/
        while (sortedNode.size() != 1) {
            Node leftChild = sortedNode.poll();
            Node rightChild = sortedNode.poll();

            /*frequency node not leaf is sum of two child's*/
            Node parent = new Node(leftChild.frequency + rightChild.frequency);
            /*make link between nodes*/
            parent.leftChild = leftChild;
            parent.rightChild = rightChild;
            sortedNode.add(parent);
            if (LOGG) for (Node currentNode : sortedNode)
                System.out.println(currentNode.uniqueByte + " data freq " + currentNode.frequency);
        }
        return sortedNode.poll();
    }

    /**
     * create cod Huffman when start recurtion to left + in string 0 ; else if in right +1 in string
     * and add its in table (hash map) on depend what we need table
     */
    void bypassInOrderMakeTable(Node currentNode, String bit, boolean compressedTrue) {
        if (currentNode == null) return;

        if (currentNode.isLeaf) {
            if (LOGG) System.out.println(currentNode.uniqueByte + " dataByte, codes " + bit);
            if (compressedTrue) tableByteCodes.put(currentNode.uniqueByte, bit);
            else tableCodeByte.put(bit, currentNode.uniqueByte);
            return;
        } else { /*start recursion search leaf */
            if (LOGG) System.out.println(bit);
            bypassInOrderMakeTable(currentNode.leftChild, bit + "0", compressedTrue);

            /*go to right in tree*/
            bypassInOrderMakeTable(currentNode.rightChild, bit + "1", compressedTrue);
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
        String codeCurrentByte;
        String codeBuffer = "";

        while ((currentByte = bufInS.read()) != -1) {
            codeCurrentByte = tableByteCodes.get(currentByte);

            /*add bit to ready byte in string form*/
            for (int i = 0; i < codeCurrentByte.length(); i++) {
                codeBuffer = codeBuffer + codeCurrentByte.charAt(i);

                if (codeBuffer.length() >= LENTH_BYTE_IN_BITS) {
                    bufOuS.write(Integer.parseInt(codeBuffer, 2));
                    counterSizeCompressedFile++;
                    codeBuffer = "";
                }
            }
        }

        if (codeBuffer.length() > 0) {
            for (int i = 0; i < LENTH_BYTE_IN_BITS - codeBuffer.length(); i++) codeBuffer = codeBuffer + "0";
            if (LOGG) System.out.println(Integer.parseInt(codeBuffer, 2));

            bufOuS.write(Integer.parseInt(codeBuffer, 2));
            counterSizeCompressedFile++;
        }
        System.out.println("Size compressed file: " + counterSizeCompressedFile + " Byte");
        System.out.println("Compression efficiency: " + ((double) (counterSizeInputFile - counterSizeCompressedFile)
                / counterSizeInputFile) * 100 + "%");
    }
}
