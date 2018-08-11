/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author jodus
 */
public class MovieBaseException extends RuntimeException {
    public MovieBaseException(String msg, Exception ex) {
        super(msg, ex);
    }
}
