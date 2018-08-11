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
public class AddMovie implements Transaction, Serializable {

    private String title;
    private Genre genre;
    
    public AddMovie(String title, Genre genre) {
        this.title = title;
        this.genre = genre;
    }
    
    @Override
    public void execute() {
        Repositories.getMovieRepository().createMovie(title, genre);
    }
    
}
