package com.bellakin.botzzer.data.models.users;

import com.bellakin.botzzer.data.models.roles.ShopUserRole;
import org.springframework.stereotype.Component;

/**
 * @author dan.rees.thomas@gmail.com
 */
@Component
public class AppUserFactory {

  public AppUser createUser(String username, String password, String email, ShopUserRole... roles) {
    return new AppUser(username, password, email, roles);
  }

//  @Bean("customer")
  public AppUser createCustomer(String username, String password, String email) {
    return createUser(username, password, email, ShopUserRole.CUSTOMER);
  }

//  @Bean("admin")
  public AppUser createAdmin(String username, String password, String email) {
    return createUser(username, password, email, ShopUserRole.CUSTOMER, ShopUserRole.ADMIN);
  }

}
