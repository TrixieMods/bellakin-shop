package com.bellakin.botzzer.data.models.category;

import com.bellakin.core.data.actor.ICategory;

import java.util.List;

/**
 * @author dan.rees.thomas@gmail.com
 */
public interface ICategoryDao<T extends ICategory> {

  List<T> getCategories();

  T addCategory(T category);

}
