package com.bellakin.shop.data.models.users;

import com.bellakin.shop.data.models.roles.ShopUserRole;
import org.springframework.stereotype.Component;

/**
 * @author dan.rees.thomas@gmail.com
 */
@Component
public class AppUserFactory {

  public AppUser createUser(String username, String password, ShopUserRole... roles) {
    return new AppUser(username, password, roles);
  }

//  @Bean("customer")
  public AppUser createCustomer(String username, String password) {
    return createUser(username, password, ShopUserRole.CUSTOMER);
  }

//  @Bean("admin")
  public AppUser createAdmin(String username, String password) {
    return createUser(username, password, ShopUserRole.CUSTOMER, ShopUserRole.ADMIN);
  }

}
