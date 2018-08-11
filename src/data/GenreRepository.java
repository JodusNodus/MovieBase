/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import domain.Genre;
import domain.Movie;
import java.util.List;

/**
 *
 * @author jodus
 */
public interface GenreRepository {
    public List<Genre> getGenres();
    public Genre createGenre(String name);
    public void deleteGenre(Genre g);
}
