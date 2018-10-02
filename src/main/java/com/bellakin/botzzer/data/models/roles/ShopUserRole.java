package com.bellakin.botzzer.data.models.roles;

import com.bellakin.core.data.actor.IRole;

public enum  ShopUserRole implements IRole {

  ADMIN,
  CUSTOMER;

  @Override
  public String toString() {
    return this.name();
  }

  @Override
  public String getName() {
    return this.name();
  }
}
