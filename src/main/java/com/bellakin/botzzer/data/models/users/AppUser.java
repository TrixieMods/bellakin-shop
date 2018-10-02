package com.bellakin.botzzer.data.models.users;

import com.bellakin.botzzer.data.models.roles.ShopUserRole;

public class AppUser extends AbstractAppUser {

  public AppUser() {
  }

  public AppUser(String username, String password, ShopUserRole[] roles) {
    this(username, password, "dan.rees.thomas@gmail.com", roles);
  }

  public AppUser(String username, String password, String email, ShopUserRole[] roles) {
    super(username, password);
    this.setRoles(roles);
    this.setEmail(email);
  }
}