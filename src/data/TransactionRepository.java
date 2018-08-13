/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import transactions.Transaction;
import java.util.List;

/**
 *
 * @author jodus
 */
public interface TransactionRepository {
    public void addTransaction(Transaction tx);
    public List<Transaction> getTransactions();
}
