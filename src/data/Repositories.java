/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jodus
 */
public class Repositories {
    public static MovieRepository getMovieRepository(){
        try {
            return MySqlMovieRepository.getInstance();
        } catch (Exception ex) {
            Logger.getLogger(Repositories.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public static GenreRepository getGenreRepository(){
        try {
            return MySqlGenreRepository.getInstance();
        } catch (Exception ex) {
            Logger.getLogger(Repositories.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
