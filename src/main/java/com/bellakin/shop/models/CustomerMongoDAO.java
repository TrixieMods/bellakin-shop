package com.bellakin.shop.models;

import com.bellakin.core.persistence.mongodb.MongoRepo;
import org.springframework.data.mongodb.core.MongoTemplate;

public class CustomerMongoDAO {

  private final MongoRepo<Customer> repo;

  public CustomerMongoDAO(MongoTemplate template, String collection) {
    this.repo = new MongoRepo<>(Customer.class, template, collection);
  }

  public MongoRepo<Customer> getRepo() {
    return repo;
  }

}