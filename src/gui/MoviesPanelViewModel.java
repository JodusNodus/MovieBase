/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.GenreRepository;
import data.MovieRepository;
import data.Repositories;
import data.TransactionRepository;
import domain.Genre;
import domain.Movie;
import java.util.Collections;
import java.util.List;
import transactions.AddMovie;
import transactions.DeleteMovie;

/**
 *
 * @author jodus
 */
public class MoviesPanelViewModel {
    private final MovieRepository movieRepo;
    private final GenreRepository genreRepo;
    
    private List<Movie> movieList;
    private List<Genre> genreList;
    private final GUIPanel panel;
    
    public MoviesPanelViewModel(GUIPanel panel) {
        this.panel = panel;
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
        Client.getInstance().transmit(new AddMovie(title, genre));
    }

    void handleDelete(Movie m) {
        Client.getInstance().transmit(new DeleteMovie(m));
    }
}
