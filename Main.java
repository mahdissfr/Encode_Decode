package com.aut;
import java.io.File;
import java.util.ArrayList;

public class Main {

    public static ArrayList<String> listNameOfFiles(String inputPath) {
        File folder = new File(inputPath);
        ArrayList<String> names = new ArrayList<>();
        if (folder.isFile()) {
            names.add(inputPath);
        }
        if (folder.isDirectory()) {
            File[] listOfFiles = folder.listFiles();
            if (listOfFiles != null) {
                for (File eachFile : listOfFiles) {
                    names.add(folder.getParent() + "\\" + folder.getName() + "\\" + eachFile.getName());
                }
            }
        }
        return names;
    }

    public static void main(String[] args) {

        ArrayList<String> nameOfFiles;
        String inputPath, toWrite = "", encodedInput, password, path, toDelete;
        boolean pbeOutput = true;
        outer:
        for (int j = 0; j < args.length; j++) {
            if (args[j].equals("-i")) {
                if (!(args[j + 1].equals("-ec")) && !(args[j + 1].equals("-es")) && !(args[j + 1].equals("-r")) && !(args[j + 1].equals("-o")) && !(args[j + 1].equals("-dc")) && !(args[j + 1].equals("-ds"))) {
                    inputPath = args[j + 1];
                    nameOfFiles = listNameOfFiles(inputPath);
                    for (String eachName : nameOfFiles) {
                        InputFileReader ifr = new InputFileReader(eachName);
                        int lengthOfInputFile = ifr.length;
                        byte[] ib = ifr.inputBytes();
                        Coding coding = new Coding(lengthOfInputFile);
                        encodedInput = coding.encode(ib);
                        for (int i = 0; i < args.length; i++) {
                            if (args[i].equals("-es")) {
                                if (!(args[i + 1].equals("-ec")) && !(args[i + 1].equals("-dc")) && !(args[i + 1].equals("-r")) && !(args[i + 1].equals("-o")) && !(args[i + 1].equals("-i")) && !(args[i + 1].equals("-ds"))) {
                                    password = args[i + 1];
                                    SimpleShiftCryptography es = new SimpleShiftCryptography(password);
                                    toWrite = es.encrypt(encodedInput);
                                    pbeOutput = true;
                                    break;
                                } else {
                                    System.out.println("There is no password");
                                    break outer;
                                }
                            } else if (args[i].equals("-ds")) {
                                if (!(args[i + 1].equals("-ec")) && !(args[i + 1].equals("-es")) && !(args[i + 1].equals("-r")) && !(args[i + 1].equals("-o")) && !(args[i + 1].equals("-i")) && !(args[i + 1].equals("-dc"))) {
                                    password = args[i + 1];
                                    SimpleShiftCryptography ds = new SimpleShiftCryptography(password);
                                    toWrite = ds.decrypt(encodedInput);
                                    pbeOutput = false;
                                    break;
                                } else {
                                    System.out.println("There is no password");
                                    break outer;
                                }
                            } else if (args[i].equals("-ec")) {
                                if (!(args[i + 1].equals("-dc")) && !(args[i + 1].equals("-es")) && !(args[i + 1].equals("-r")) && !(args[i + 1].equals("-o")) && !(args[i + 1].equals("-i")) && !(args[i + 1].equals("-ds"))) {
                                    password = args[i + 1];
                                    ComplexShiftCryptography ec = new ComplexShiftCryptography(password);
                                    toWrite = ec.encrypt(encodedInput);
                                    pbeOutput = true;
                                    break;
                                } else {
                                    System.out.println("There is no password");
                                    break outer;
                                }
                            } else if (args[i].equals("-dc")) {
                                if (!(args[i + 1].equals("-ec")) && !(args[i + 1].equals("-es")) && !(args[i + 1].equals("-r")) && !(args[i + 1].equals("-o")) && !(args[i + 1].equals("-i")) && !(args[i + 1].equals("-ds"))) {
                                    password = args[i + 1];
                                    ComplexShiftCryptography dc = new ComplexShiftCryptography(password);
                                    toWrite = dc.decrypt(encodedInput);
                                    pbeOutput = false;
                                    break;
                                } else if (i == (args.length - 1)) {
                                    System.out.println("There is no password");
                                    break outer;
                                }
                            } else {
                                System.out.println("Wrong input about specifying the operation");
                                break outer;
                            }
                        }
                        for (int k = 0; k < args.length; k++) {
                            if (args[k].equals("-o")) {
                                if (!(args[k + 1].equals("-ec")) && !(args[k + 1].equals("-es")) && !(args[k + 1].equals("-r")) && !(args[k + 1].equals("-o")) && !(args[k + 1].equals("-dc")) && !(args[k + 1].equals("-ds"))) {
                                    if (pbeOutput) {
                                        path = args[k + 1] + ".pbe";
                                    } else path = args[k + 1];
                                    OutputFileWriter ofw = new OutputFileWriter(path);
                                    ofw.write(coding.decode(toWrite));
                                    break outer;
                                } else if (k == args.length - 1) {
                                    System.out.println("There is no output file address");
                                    break outer;
                                }
                            }
                        }
                        for (int k = 0; k < args.length; k++) {
                            if (args[k].equals("-r")) {
                                if (pbeOutput) {
                                    toDelete = inputPath;
                                    path = inputPath + ".pbe";
                                } else {
                                    path = inputPath.replace(".pbe", "");
                                    toDelete = inputPath;
                                }
                                OutputFileWriter ofw = new OutputFileWriter(path);
                                ofw.write(coding.decode(toWrite));
                                try {
                                    File file = new File(toDelete);
                                    if (!file.delete()) {
                                        System.out.println("Delete operation is failed.");
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break outer;
                            }
                        }
                    }
                } else {
                    System.out.println("There is no input file address");
                }
                break;
            } else if (j == (args.length - 1)) {
                System.out.println("Wrong input about input file");
            }
        }
    }
}