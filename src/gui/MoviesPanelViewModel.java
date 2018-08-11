/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.GenreRepository;
import data.MovieRepository;
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
    
    private List<Movie> movieList;
    private List<Genre> genreList;
    
    public MoviesPanelViewModel() {
        this.movieRepo = Repositories.getMovieRepository();
        this.genreRepo = Repositories.getGenreRepository();
    }
    
    public List<Movie> getMovieList() {
        return movieList;
    }
    
    public List<Genre> getGenreList() {
        return genreList;
    }
    
    public void fetchMovieList() {
        List<Movie> movies = movieRepo.getMovies();
        this.movieList = Collections.unmodifiableList(movies);
    }
    
    public void fetchGenreList() {
        List<Genre> genres = genreRepo.getGenres();
        this.genreList = Collections.unmodifiableList(genres);
    }

    void handleMovieCreate(String title, Genre genre) {
        movieRepo.createMovie(title, genre);
    }

    void handleDelete(Movie m) {
        movieRepo.deleteMovie(m);
    }
}
