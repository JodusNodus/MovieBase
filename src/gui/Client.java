/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import transactions.AddGenre;
import transactions.AddMovie;
import transactions.DeleteGenre;
import transactions.DeleteMovie;
import transactions.Transaction;
import transactions.TransactionExecuted;
import transactions.TransactionHandler;

/**
 *
 * @author jodus
 */
public class Client implements Runnable, TransactionHandler {

    private static Client instance;

    private ObjectInputStream in;
    private ObjectOutputStream out;
    
    private Set<GUIPanel> listeners;
    
    public static Client getInstance() {
        if (instance == null) {
            instance = new Client();
            new Thread(instance).start();
        }
        return instance;
    }
    
    private Client() {
        listeners = new HashSet<>();
        try {
            Socket socket = new Socket("localhost", 1234);
            out = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("out");
            in = new ObjectInputStream(socket.getInputStream());
            System.out.println("in");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void run() {
        while(true) {
            try {
                Transaction tx = (Transaction) in.readObject();
                tx.accept(this);
            } catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public synchronized void transmit(Transaction tx) {
        try {
            out.writeObject(tx);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public synchronized void accept(GUIPanel panel) {
        listeners.add(panel);
    }

    @Override
    public void receive(AddGenre tx) {
    }

    @Override
    public void receive(AddMovie tx) {
    }

    @Override
    public void receive(DeleteGenre tx) {
    }

    @Override
    public void receive(DeleteMovie tx) {
    }

    @Override
    public void receive(TransactionExecuted tx) {
        System.out.println("TransactionExecuted");
        for(GUIPanel panel: listeners) {
            panel.update();
        }
    }

}
