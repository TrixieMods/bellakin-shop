package com.bellakin.shop.data.models.users;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.bellakin.core.data.persistence.mongodb.MongoRepo;

/**
 * @author dan.rees.thomas@gmail.com
 */
@Repository
public class AppUserMongoDao {

  private final MongoRepo<AppUser> repo;

  public AppUserMongoDao(@Qualifier("userDao") MongoRepo<AppUser> customerRepo) {
    this.repo = customerRepo;
  }

  public MongoRepo<AppUser> getRepo() {
    return repo;
  }
}
