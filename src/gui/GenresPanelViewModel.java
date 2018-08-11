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
import transactions.Transaction;

/**
 *
 * @author jodus
 */
public class GenresPanelViewModel {
    private final MovieRepository movieRepo;
    private final GenreRepository genreRepo;
    
    private Genre selectedGenre;
    private List<Movie> movieList;
    private List<Genre> genreList;
    
    public GenresPanelViewModel() {
        this.movieRepo = Repositories.getMovieRepository();
        this.genreRepo = Repositories.getGenreRepository();
    }
    
    public List<Genre> getGenreList() {
        return genreList;
    }
    
    public List<Movie> getMovieList() {
        return movieList;
    }
    
    public int getSelectedIndex() {
        int index = getGenreList().indexOf(selectedGenre);
        if (index == -1) {
            return 0;
        }
        return index;
    }
    
    public void fetchMovieList() {
        if (selectedGenre == null) {
            selectedGenre = getGenreList().get(0);
        }
        List<Movie> movies = movieRepo.getGenreMovies(selectedGenre);
        this.movieList = Collections.unmodifiableList(movies);
    }
    
    public void fetchGenreList() {
        List<Genre> genres = genreRepo.getGenres();
        this.genreList = Collections.unmodifiableList(genres);
    }
    
    public void handleSelectedGenreChange(Genre g) {
        selectedGenre = g;
    }

    void handleGenreDelete(Genre g) {
        genreRepo.deleteGenre(g);
    }

    void handleGenreCreate(String name) {
        Genre g = genreRepo.createGenre(name);
    }
}
