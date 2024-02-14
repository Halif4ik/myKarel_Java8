package com.shpp.p2p.cs.dgrymach.Assignment15;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * this class checks incoming parameters from the command line and assigns names to incoming and outgoing files
 * and sets the archive/unzip flag. name in put file for example test.txt
 */
class CheckCMD implements IConst {
    /**
     * name file default input (example test.txt)
     */
    String startFile;
    /**
     * name archive default output(example  test.txt.par)
     */
    String resultArhiv;
    /**
     * name unZip file output(example test.txt)
     */
    String fileResult;
    /**
     * set coppressed (A) or unzip(U)
     */
    String action;


    /* constructor initialization default name+path files which will be write */
    CheckCMD() {
        startFile = START_FILE;
        resultArhiv = FILE_RESULT_ARHIV;
        fileResult = FILE_RESULT;
        action = "-a";
    }

    /**
     * check cmd an decide what action we must will do
     */
    void setNamesAndAction(String[] args) {
        Pattern p;
        Matcher m;
        switch (args.length) {
            case 0:
                break;
            case 1:
                /*if incorrect args */
                p = Pattern.compile("^-{1}[a-z]$");
                m = p.matcher(args[0]);
                if (m.matches()) {
                    System.out.println("Incorrect.Was enter only key -u for example. Take default");
                    break;
                }
                /*if correct poem.txt */
                if (args[0].matches("[A-Za-z0-9]{1,}+\\.(txt)")) startFile = PATH + args[0];

                /*if correct poem.txt.par */
                if (args[0].matches("[A-Za-z0-9]{1,}+(\\.txt)+(\\.par)")) {
                    resultArhiv = PATH + args[0];
                    fileResult = PATH + args[0].substring(0, args[0].indexOf(".par"));
                    action = "-u";
                }
                break;
            case 2: /* if need compressed poem.txt arvhied_poem.par*/
                if (args[0].matches("[A-Za-z0-9]{1,}+\\.(txt)") &&
                        args[1].matches("[A-Za-z0-9_]{1,}+\\.(par)")) {
                    startFile = PATH + args[0];
                    resultArhiv = PATH + args[1];
                    break;
                }
                /*if need unzip poem.txt.par unarvhived_poem.txt*/
                if (args[0].matches("[A-Za-z0-9]{1,}+(\\.txt)+(\\.par)") &&
                        args[1].matches("[A-Za-z0-9]{1,}+\\.(txt)")) {
                    resultArhiv = PATH + args[0];
                    fileResult = PATH + args[1];
                    action = "-u";
                    break;
                }
                /*if correct poem archived */
                if (args[0].matches("[A-Za-z0-9]+") && args[1].matches("[A-Za-z0-9]{1,}")) {
                    startFile = PATH + args[0];
                    resultArhiv = PATH + args[1];
                }
                break;
            case 3:
                /* *if correct 3 args -a archive.par archived_twice.par COMPRESS*/
                if (args[0].equals("-a")) {
                    startFile = PATH + args[1];
                    resultArhiv = PATH + args[2];
                    break;
                }
                /* *if correct 3 args -u archived unarchived UnZIp*/
                if (args[0].equals("-u")) {
                    resultArhiv = PATH + args[1];
                    fileResult = PATH + args[2];
                    action = "-u";
                    break;
                }
            default:
                System.out.println("Count param in CMD must be less than 4");
                System.exit(0);
        }


    }


}

