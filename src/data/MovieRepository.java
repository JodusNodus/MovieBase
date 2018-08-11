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
public interface MovieRepository {
    public List<Movie> getMovies();
    public List<Movie> getGenreMovies(Genre genre);
    public Movie createMovie(String title, Genre genre);
    public void deleteMovie(Movie movie);
}
