/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;

/**
 *
 * @author jodus
 */
public class Genre implements Serializable {
    
    public final int id;
    public final String name;
    
    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Genre) && ((Genre) obj).id == id;
    }
}
