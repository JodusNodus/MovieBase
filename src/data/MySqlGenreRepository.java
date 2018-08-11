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
public class MySqlGenreRepository implements GenreRepository {
    
    private static String SELECT_GENRES = "select * from genre";
    private static String INSERT_GENRE = "insert into genre (name) values (?)";
    private static String DELETE_GENRE = "delete from genre where id = ?";
    
    private static GenreRepository instance;
    private Connection conn;
    
    public static GenreRepository getInstance() throws Exception {
        if (instance == null) {
            instance = new MySqlGenreRepository();
        }
        return instance;
    }
    
    public MySqlGenreRepository() throws Exception {
        try{
            conn = MySqlConnection.getConnection();
        } catch (SQLException ex) {
            throw new Exception("Could not connect to database", ex);
        }
    }
    
    @Override
    public List<Genre> getGenres() {
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(SELECT_GENRES);
            List<Genre> genres = new ArrayList<>();
            while (rs.next())
            {
                int id = rs.getInt("id");
                String genre = rs.getString("name");
                Genre g = new Genre(id, genre);
                genres.add(g);
            }
            rs.close();
            return genres;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

    @Override
    public Genre createGenre(String name) {
        try (PreparedStatement stmt = conn.prepareStatement(INSERT_GENRE, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, name);
            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            Genre g = new Genre(rs.getInt(1), name);
            rs.close();
            return g;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    @Override
    public void deleteGenre(Genre genre) {
        try (PreparedStatement stmt = conn.prepareStatement(DELETE_GENRE)) {
            stmt.setInt(1, genre.id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
}
