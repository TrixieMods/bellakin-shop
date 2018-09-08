package com.bellakin.shop.data.models.users;

import com.bellakin.shop.data.models.roles.ShopUserRole;

public class AppUser extends AbstractAppUser {

  public AppUser() {
  }

  public AppUser(String username, String password, ShopUserRole[] roles) {
    super(username, password);
    this.setRoles(roles);
  }
}