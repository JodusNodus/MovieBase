/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.GenreRepository;
import data.MovieRepository;
import data.Observable;
import data.Repositories;
import domain.Genre;
import domain.Movie;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author jodus
 */
public class MoviesPanelViewModel {
    private final MovieRepository movieRepo;
    private final GenreRepository genreRepo;
    
    public MoviesPanelViewModel() {
        this.movieRepo = Repositories.getMovieRepository();
        this.genreRepo = Repositories.getGenreRepository();
    }
    
    public Observable<List<Movie>> getMoviesObservable() {
        return movieRepo.getMoviesObservable();
    }
    
    public Observable<List<Genre>> getGenresObservable() {
        return genreRepo.getGenresObservable();
    }
    
    public void fetchMovieList() {
        movieRepo.fetchMovies();
    }
    
    public void fetchGenreList() {
        genreRepo.fetchGenres();
    }

    void handleMovieCreate(String title, Genre genre) {
        movieRepo.createMovie(title, genre);
    }

    void handleDelete(Movie m) {
        movieRepo.deleteMovie(m);
    }
}
