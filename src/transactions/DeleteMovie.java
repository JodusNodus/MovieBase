/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transactions;

import data.Repositories;
import domain.Movie;
import java.io.Serializable;

/**
 *
 * @author jodus
 */
public class DeleteMovie implements Transaction, Serializable {

    public Movie movie;
    
    public DeleteMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public void accept(TransactionHandler handler) {
        handler.receive(this);
    }
}
