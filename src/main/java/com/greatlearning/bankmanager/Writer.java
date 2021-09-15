package com.greatlearning.bankmanager;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

    private BufferedWriter writer;

    public Writer() {
        String fileName = "transactions.txt";
        File file = new File("output");
        if (!file.exists()) {
            file.mkdir();
        }
        File fileToWrite = new File(file, fileName);
        try {
            writer = new BufferedWriter(new FileWriter(fileToWrite, true));
        } catch (Exception e) {
            System.out.println("Cannot open the file");
        }
    }

    public void storeTransaction(String transaction) {
        try {
            writer.write(transaction);
            writer.write("\n");
            writer.flush();
        } catch (Exception e) {
            System.out.println("unable to write to file");
        }
    }

    public void closeWriter(){
        try {
            writer.close();
        } catch (IOException e) {
            System.out.println("unable to close the writer");
        }
    }
}
