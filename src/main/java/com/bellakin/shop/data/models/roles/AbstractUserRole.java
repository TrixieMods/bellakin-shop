package com.bellakin.shop.data.models.roles;

import com.bellakin.core.data.actor.IRole;

public abstract class AbstractUserRole implements IRole {

  private String name;

  public AbstractUserRole(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return getName();
  }
}
