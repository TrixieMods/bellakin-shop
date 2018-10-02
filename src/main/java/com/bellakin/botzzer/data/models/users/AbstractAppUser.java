package com.bellakin.botzzer.data.models.users;

import com.bellakin.core.data.actor.IRole;
import com.bellakin.botzzer.data.models.roles.ShopUserRole;
import org.springframework.data.annotation.Id;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractAppUser implements IAppUser {

  @Id
  private String id;
  private String username;
  private String password;
  private String email;
  private Set<ShopUserRole> roles;

  public AbstractAppUser() {}

  protected AbstractAppUser(String username, String password) {
    this.username = username;
    this.password = password;
    this.roles = new HashSet<>();
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public void setId(String id) {
    this.id = id;
  }

  @Override
  public IRole[] getRoles() {
    IRole[] rolesArray = new IRole[roles.size()];
    return roles.toArray(rolesArray);
  }

  public Set<ShopUserRole> getRoleSet() {
    return roles;
  }

  public void setRoles(ShopUserRole[] roles) {
    this.roles = new HashSet<>(Arrays.asList(roles));
  }

  public void addRole(ShopUserRole role) {
    this.roles.add(role);
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String getEmail() {
    return email;
  }

  @Override
  public void setEmail(String email) {
    this.email = email;
  }
}
