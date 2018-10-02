package com.bellakin.botzzer.services;

import com.bellakin.core.data.actor.IUser;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * @author dan.rees.thomas@gmail.com
 */
public interface IUserService<T extends IUser> {

  Optional<T> createCustomer(String name, String password, String email);

  Optional<T> createAdmin(String name, String password, String email);

  Optional<T> findUser(String username);

  Optional<T> findUserByEmail(String email);

  Page<T> listUsers(int page, int size);

  void deleteUser(String id);

  boolean isUniqueName(String name);

}
