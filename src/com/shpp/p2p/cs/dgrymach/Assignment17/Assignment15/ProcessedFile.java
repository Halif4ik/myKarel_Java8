package com.shpp.p2p.cs.dgrymach.Assignment17.Assignment15;

import com.shpp.p2p.cs.dgrymach.Assignment17.HashMap.MyHashMap;
import com.shpp.p2p.cs.dgrymach.Assignment17.PriorityQueue.MyPriorityQueue;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * This class can archive file, take from main stream (BufferedInputStream and DataOutputStream) go around in all file
 * and count unique byte and write header, in next round append in file archive data.
 * when in main create constructor with (BufferedInputStream BufferedOutputStream) we unzip file go around in file and
 * search new byte in table association(ArrayList)
 */
class ProcessedFile implements IConst {
    /** character(byte if unicode) in Integer view frequency in string(or in file),
     Queue unique byte sorted at the beginning  byte rare meet in the end often */
    private MyPriorityQueue<Node> qUniqueByte = new MyPriorityQueue();

    /** counters size input and output(compressed)file if we read/write one byte we increment this counter*/
    private long counterSizeInputFile;
    private long counterSizeCompressedFile;
    private long counterByteSizeUnCompressedFile;

    /**create codes tables Huffman */
    private MyHashMap<Integer, String> tableByteCodes = new MyHashMap<>();
    private MyHashMap<String, Integer> tableCodeByte = new MyHashMap<>();

    /* constructor create table (HashMap) of unique symbols and its frequency in file*/
    ProcessedFile(BufferedInputStream fInStr) throws IOException {
        int currentByte;

        int[] freqUniqueBytes = new int[256];
        while ((currentByte = fInStr.read()) != -1) {
            counterSizeInputFile++;
            /* if char don't present in our array ++else char already present */
            freqUniqueBytes[currentByte]++;
        }
// todo remove points breaks
        /*sorted our bytes from(hashmap old) array to priorityQ(table) by the number frequency (for_value)*/
        for (int i = 0; i < freqUniqueBytes.length; i++) {  //Unique Byte ==index in  array
            if (freqUniqueBytes[i] > 0) qUniqueByte.add(new Node(i, freqUniqueBytes[i], true));
        }

        if (LOGG) {
            System.out.println(qUniqueByte.size() + " counter of unique");
            for (Node freqByte : qUniqueByte) System.out.println(freqByte.toString());
        }
        System.out.println("Start size file: " + counterSizeInputFile + " byte");

        /* Rewrite frequency in Nodes :    data=uniqueByte         freq =index in sorted array   */
        int indexInsteadFreq = 0;
        for (Node uniqueByte : qUniqueByte) uniqueByte.setFrequency(indexInsteadFreq++);
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
        int currentByte;
        String readyCode = "";

        /*read next 8 byte and remember as size  compressed data in Bits*/
        long lenthCompresDataInByte = reaLong(buffInpSArh);
        if (LOGG) System.out.println(lenthTableUniqueB + " lenthTableUniqueB(int) " + lenthCompresDataInByte +
                " lenthCompresDataInBit(long)");

        /*start read table*/
        for (int i = 0; i < lenthTableUniqueB; i++) {
            qUniqueByte.add(new Node(buffInpSArh.read(), i, true));
        }

        if (LOGG) {
            System.out.println("Table: ");
            for (Node freqByte : qUniqueByte) System.out.print(freqByte.toString() + " ");
            System.out.println();
        }

        /*start read compressed data and unzip and write in ready file*/
        Node root = createTree();
        bypassInOrderMakeTable(root, "", false);
//todo deleted ponit goto
        /*until byte is present we read or while need to read*/
        while ((currentByte = buffInpSArh.read()) != -1) {
            /*  chek in order bit (in readed byte)and create current code (string++) what consist from some bits */
            /*        128                          7                        2>0             64    */
            for (int maskForByte = 1 << (LENTH_BYTE_IN_BITS - 1); maskForByte > 0; maskForByte >>= 1) {
                if (counterByteSizeUnCompressedFile >= lenthCompresDataInByte) break;
                /*set mask in first position 1000 0000 & check first bit*/
                if ((currentByte & maskForByte) != 0) {
                    readyCode = readyCode + "1";
                } else readyCode = readyCode + "0";

                if (tableCodeByte.containsKey(readyCode)) {
                    buffOutSTxtUnarh.write(tableCodeByte.get(readyCode));
                    counterByteSizeUnCompressedFile++;
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
        dataOutS.writeInt(qUniqueByte.size());
        counterSizeCompressedFile += COUNT_BYTE_IN_INT;

        /* size of unzip data in bytes are write in file  8  byte*/
        dataOutS.writeLong(counterSizeInputFile);
        counterSizeCompressedFile += COUNT_BYTE_IN_LONG;

        /*write table  of associations size table in byte == tableUniqueB.length*/
        for (Node byteToWr : qUniqueByte) {
            dataOutS.write(byteToWr.uniqueByte);
            counterSizeCompressedFile++;
        }
    }

    /**
     * create new node as fields is unique byte and its index in table (arraylist)
     */
    Node createTree() {
        /*create one node who was root tree*/
        while (qUniqueByte.size() != 1) {
            Node leftChild = qUniqueByte.poll();
            Node rightChild = qUniqueByte.poll();

            /*frequency node not leaf is sum of two child's*/
            Node parent = new Node(leftChild.frequency + rightChild.frequency);
            /*make link between nodes*/
            parent.leftChild = leftChild;
            parent.rightChild = rightChild;
            qUniqueByte.add(parent);

            if (LOGG) for (Node currentNode : qUniqueByte)
                System.out.println(currentNode.uniqueByte + " data freq " + currentNode.frequency);
        }
        return qUniqueByte.poll();
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
            int howMachZeroWeNeed = LENTH_BYTE_IN_BITS - codeBuffer.length();
            for (int i = 0; i < howMachZeroWeNeed; i++) codeBuffer = codeBuffer + "0";

            if (LOGG) System.out.println(Integer.parseInt(codeBuffer, 2));

            bufOuS.write(Integer.parseInt(codeBuffer, 2));
            counterSizeCompressedFile++;
        }
        System.out.println("Size compressed file: " + counterSizeCompressedFile + " Byte");
        System.out.println("Compression efficiency: " + ((double) (counterSizeInputFile - counterSizeCompressedFile)
                / counterSizeInputFile) * 100 + "%");
    }
}
