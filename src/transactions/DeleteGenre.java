/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transactions;

import data.Repositories;
import domain.Genre;
import java.io.Serializable;

/**
 *
 * @author jodus
 */
public class DeleteGenre implements Transaction, Serializable {
    
    private Genre genre;
    
    public DeleteGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public void execute() {
        Repositories.getGenreRepository().deleteGenre(genre);
    }
    
}
