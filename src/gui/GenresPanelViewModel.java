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
public class GenresPanelViewModel {
    private final MovieRepository movieRepo;
    private final GenreRepository genreRepo;
    
    private Genre selectedGenre;
    
    public GenresPanelViewModel() {
        this.movieRepo = Repositories.getMovieRepository();
        this.genreRepo = Repositories.getGenreRepository();
    }
    
    public Observable<List<Genre>> getGenresObservable() {
        return genreRepo.getGenresObservable();
    }
    
    public Observable<List<Movie>> getMoviesObservable() {
        return movieRepo.getGenreMoviesObservable();
    }
    
    public int getSelectedIndex() {
        if (selectedGenre == null || getGenresObservable().getValue() == null) {
            return 0;
        }
        return getGenresObservable().getValue().indexOf(selectedGenre);
    }
    
    private void fetchMovieList() {
        if (selectedGenre == null) {
            return;
        }
        movieRepo.fetchGenreMovies(selectedGenre);
    }
    
    public void fetchGenreList() {
        genreRepo.fetchGenres();
    }
    
    public void handleSelectedGenreChange(Genre g) {
        selectedGenre = g;
        fetchMovieList();
    }

    void handleGenreDelete(Genre g) {
        genreRepo.deleteGenre(g);
    }

    void handleGenreCreate(String name) {
        genreRepo.createGenre(name);
    }
}
