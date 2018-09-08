package com.bellakin.shop.services;

import com.bellakin.shop.data.models.users.AppUser;
import com.bellakin.shop.data.models.users.AppUserFactory;
import com.bellakin.shop.data.models.users.AppUserMongoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author dan.rees.thomas@gmail.com
 */
@Service
public class AppUserService implements IUserService<AppUser> {

  private final AppUserFactory appUserFactory;
  private final AppUserMongoDao userMongoDao;

  @Autowired
  public AppUserService(AppUserFactory appUserFactory,
                        AppUserMongoDao userMongoDao) {
    this.appUserFactory = appUserFactory;
    this.userMongoDao = userMongoDao;
  }

  public Optional<AppUser> createCustomer(String name, String password) {
    AppUser customer = appUserFactory.createCustomer(name, password);
    return Optional.ofNullable(userMongoDao.getRepo().insert(customer));
  }

  public Optional<AppUser> createAdmin(String name, String password) {
    AppUser admin = appUserFactory.createAdmin(name, password);
    return Optional.ofNullable(userMongoDao.getRepo().insert(admin));
  }

  public Page<AppUser> listUsers(int page, int size) {
    return userMongoDao.getRepo().findAll(PageRequest.of(page, size));
  }

  public Optional<AppUser> findUser(String username) {
    return Optional.ofNullable(
      userMongoDao.getRepo().findOne(Query.query(Criteria.where("username").is(username))));
  }

  public void deleteUser(String id) {
    userMongoDao.getRepo().delete(Query.query(Criteria.where("_id").is(id)));
  }

}
