/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import data.Repositories;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
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
public class SocketHandler implements Runnable, TransactionHandler {
    
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private final Socket socket;
    
    public SocketHandler(Socket socket) {
        System.out.println("Connection made");
        this.socket = socket;
        try {
            out = new ObjectOutputStream (socket.getOutputStream());
            System.out.println("out");
            in = new ObjectInputStream (socket.getInputStream());
            System.out.println("in");
        } catch (IOException ex) {
            Logger.getLogger(SocketHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public void run() {
        while(true) {
            try {
                Transaction tx = (Transaction) in.readObject();
                tx.accept(this);
            } catch(Exception ex) {
                
            }
        }
    }
    
    public void transmit(Transaction tx) {
        try {
            out.writeObject(tx);
            System.out.println(tx);
        } catch (Exception ex) {
            Logger.getLogger(SocketHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void receive(AddGenre tx) {
        System.out.println("AddGenre");
        Repositories.getGenreRepository().createGenre(tx.name);
        transmit(new TransactionExecuted());
    }

    @Override
    public void receive(AddMovie tx) {
        System.out.println("AddMovie");
        Repositories.getMovieRepository().createMovie(tx.title, tx.genre);
        transmit(new TransactionExecuted());
    }

    @Override
    public void receive(DeleteGenre tx) {
        System.out.println("DeleteGenre");
        Repositories.getGenreRepository().deleteGenre(tx.genre);
        transmit(new TransactionExecuted());
    }

    @Override
    public void receive(DeleteMovie tx) {
        System.out.println("DeleteMovie");
        Repositories.getMovieRepository().deleteMovie(tx.movie);
        transmit(new TransactionExecuted());
    }

    @Override
    public void receive(TransactionExecuted tx) {
    }
    
}
