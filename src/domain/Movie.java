/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jodus
 */
public class Movie {
    public final int id;
    public final String title;
    public final Genre genre;
    
    public Movie(int id, String title, Genre genre) {
        this.id = id;
        this.title = title;
        this.genre = genre;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Movie) && ((Movie) obj).id == id;
    }
}
