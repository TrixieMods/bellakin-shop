package com.bellakin.shop.services;

import com.bellakin.core.data.actor.IUser;
import com.bellakin.shop.data.models.users.AppUser;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * @author dan.rees.thomas@gmail.com
 */
public interface IUserService<T extends IUser> {

  Optional<AppUser> createCustomer(String name, String password);

  Optional<AppUser> createAdmin(String name, String password);

  Page<T> listUsers(int page, int size);

  Optional<AppUser> findUser(String username);

  void deleteUser(String id);


}
