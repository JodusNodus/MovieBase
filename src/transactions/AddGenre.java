/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transactions;

import data.Repositories;
import java.io.Serializable;
import server.SocketHandler;

/**
 *
 * @author jodus
 */
public class AddGenre implements Transaction, Serializable {

    public String name;
    
    public AddGenre(String name) {
        this.name = name;
    }

    @Override
    public void accept(TransactionHandler handler) {
        handler.receive(this);
    }
    
}
