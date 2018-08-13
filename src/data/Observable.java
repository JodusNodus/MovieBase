/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

/**
 *
 * @author jodus
 */
public class Observable<T> {
    
    public Set<Function<T, Void>> observers;
    
    private T value;
    
    public Observable() {
        observers = new HashSet<>();
    }
    
    public synchronized void observeChange(Function<T, Void> fun) {
        observers.add(fun);
    }
    public synchronized void setValue(T value) {
        this.value = value;
        notifyObservers();
    }
    
    public synchronized void notifyObservers() {
        for(Function<T, Void> observer: observers) {
            observer.apply(value);
        }
    }
    
    public synchronized T getValue() {
        return value;
    }
}
