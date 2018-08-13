/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transactions;

import data.Repositories;

/**
 *
 * @author jodus
 */
public interface TransactionHandler {
    public void receive(AddGenre tx);
    public void receive(AddMovie tx);
    public void receive(DeleteGenre tx);
    public void receive(DeleteMovie tx);
    public void receive(TransactionExecuted tx);
}
