package com.bellakin.shop.controllers;

import com.bellakin.shop.data.models.roles.ShopUserRole;
import com.bellakin.shop.data.models.users.AbstractAppUser;
import com.bellakin.shop.services.IUserService;
import com.bellakin.shop.data.models.users.AppUser;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

/**
 * Injected into every request
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserSession {

  private AppUser user;

  private boolean isLoggedIn;

  public UserSession() {
  }

  public boolean logIn(IUserService<AppUser> userService, String username, String password) {
    return isLoggedIn = userService.findUser(username)
      .filter(appUser -> appUser.getPassword().equals(password))
      .map(appUser -> (user = appUser))
      .isPresent();
  }

  public void logOut() {
    this.isLoggedIn = false;
    this.user = null;
  }

  public boolean isLoggedIn() {
    return isLoggedIn;
  }

  public boolean isAdmin() {
    return getUser().map(AbstractAppUser::getRoleSet)
      .filter(iRoles -> iRoles.contains(ShopUserRole.ADMIN)).isPresent();
  }

  public Optional<AppUser> getUser() {
    return Optional.ofNullable(user);
  }
}
