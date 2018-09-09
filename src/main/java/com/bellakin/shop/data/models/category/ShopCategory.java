package com.bellakin.shop.data.models.category;

import com.bellakin.core.data.actor.ICategory;
import com.bellakin.core.data.model.Identifiable;
import org.springframework.data.annotation.Id;

/**
 * @author dan.rees.thomas@gmail.com
 */
public class  ShopCategory implements Identifiable, ICategory {

  @Id
  private String id;

  private String name, description;

  public ShopCategory() {}

  public ShopCategory(String name, String description) {
    this.name = name;
    this.description = description;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getId() {
    return this.id;
  }

  @Override
  public void setId(String id) {
    this.id = id;
  }
}
