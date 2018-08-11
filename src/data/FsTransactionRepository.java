/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import transactions.Transaction;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import util.MovieBaseException;

/**
 *
 * @author jodus
 */
public class FsTransactionRepository implements TransactionRepository {
    
    static TransactionRepository instance;

    public static TransactionRepository getInstance() throws FileNotFoundException {
        if (instance == null) {
            instance = new FsTransactionRepository();
        }
        return instance;
    }
    
    
    private final File file;
    
    public FsTransactionRepository() throws FileNotFoundException {
        this.file = new File("./transactions.txt");
    }

    @Override
    public void addTransaction(Transaction tx) {
        try( FileOutputStream fos = new FileOutputStream(file, false);
            ObjectOutputStream oos = new ObjectOutputStream(fos); ){
            
            List<Transaction> transactions = getTransactions();
            transactions.add(tx);
            System.out.println(transactions.size());
            
            oos.writeObject(transactions);

        } catch (IOException ex) {
            throw new MovieBaseException("Can't save the transaction to file.", ex);
        }
    }

    @Override
    public List<Transaction> getTransactions() {
        try( FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis); ){
            return (List<Transaction>) ois.readObject();

        } catch (IOException | ClassNotFoundException ex) {
            return new ArrayList<>();
            //throw new MovieBaseException("Can't read the logs from file.", ex);
        }
    }

    @Override
    public void execute() {
        for(Transaction tx: getTransactions()) {
            tx.execute();
        }
        file.delete();
    }

}
