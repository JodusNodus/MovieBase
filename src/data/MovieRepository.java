/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import domain.Genre;
import domain.Movie;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author jodus
 */
public interface MovieRepository {
    public Observable<List<Movie>> getMoviesObservable();
    public Observable<List<Movie>> getGenreMoviesObservable();
    public void fetchMovies();
    public void fetchGenreMovies(Genre genre);
    public void createMovie(String title, Genre genre);
    public void deleteMovie(Movie movie);
}
