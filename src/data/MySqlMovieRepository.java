/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import domain.Genre;
import domain.Movie;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.MySqlConnection;

/**
 *
 * @author jodus
 */
public class MySqlMovieRepository implements MovieRepository {
    
    private static String SELECT_MOVIES = "select * from movie join genre on movie.genre_id = genre.id";
    private static String SELECT_MOVIES_WITH_GENRE = SELECT_MOVIES + " where genre_id = ?";
    private static String SELECT_GENRES = "select * from genre";
    private static String INSERT_MOVIE = "insert into movie (title, genre_id) values (?, ?)";
    private static String DELETE_MOVIE = "delete from movie where id = ?";
    
    private static MovieRepository instance;
    private Connection conn;
    
    public synchronized static MovieRepository getInstance() throws Exception {
        if (instance == null) {
            instance = new MySqlMovieRepository();
        }
        return instance;
    }
    
    public MySqlMovieRepository() throws Exception {
        try{
            conn = MySqlConnection.getConnection();
        } catch (SQLException ex) {
            throw new Exception("Could not connect to database", ex);
        }
    }
    
    @Override
    public synchronized List<Movie> getMovies() {
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(SELECT_MOVIES);
            List<Movie> movies = new ArrayList<>();
            while (rs.next())
            {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int genreId = rs.getInt("genre_id");
                String genre = rs.getString("genre.name");
                Genre g = new Genre(genreId, genre);
                movies.add(new Movie(id, title, g));
            }
            rs.close();
            return movies;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

    @Override
    public synchronized List<Movie> getGenreMovies(Genre genre) {
        try (PreparedStatement stmt = conn.prepareStatement(SELECT_MOVIES_WITH_GENRE)) {
            stmt.setInt(1, genre.id);
            ResultSet rs = stmt.executeQuery();
            List<Movie> movies = new ArrayList<>();
            while (rs.next())
            {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                movies.add(new Movie(id, title, genre));
            }
            rs.close();
            return movies;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    @Override
    public synchronized Movie createMovie(String title, Genre genre) {
        try (PreparedStatement stmt = conn.prepareStatement(INSERT_MOVIE, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, title);
            stmt.setInt(2, genre.id);
            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            Movie movie = new Movie(rs.getInt(1), title, genre);
            rs.close();
            return movie;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    @Override
    public synchronized void deleteMovie(Movie movie) {
        try (PreparedStatement stmt = conn.prepareStatement(DELETE_MOVIE)) {
            stmt.setInt(1, movie.id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
