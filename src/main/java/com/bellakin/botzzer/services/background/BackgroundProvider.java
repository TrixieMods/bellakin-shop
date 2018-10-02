package com.bellakin.botzzer.services.background;

/**
 * @author dan.rees.thomas@gmail.com
 */
public interface BackgroundProvider<T> {

  public T next();

}
