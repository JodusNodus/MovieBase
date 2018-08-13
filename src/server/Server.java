/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import data.Repositories;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import transactions.AddGenre;
import transactions.AddMovie;
import transactions.DeleteGenre;
import transactions.DeleteMovie;
import transactions.Transaction;
import transactions.TransactionHandler;

/**
 *
 * @author jodus
 */
public class Server implements Runnable {
    
    private ServerSocket listener;
    
    public Server() {
        try {
            listener = new ServerSocket(1234);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public void run() {
        while (true) {
            try {
                Socket socket = listener.accept();
                new Thread(new SocketHandler(socket)).start();
            } catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
