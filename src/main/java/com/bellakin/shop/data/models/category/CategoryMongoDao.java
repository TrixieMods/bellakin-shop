package com.bellakin.shop.data.models.category;

import com.bellakin.core.data.persistence.mongodb.MongoRepo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dan.rees.thomas@gmail.com
 */
@Repository
public class CategoryMongoDao {

  private final MongoRepo<ShopCategory> repo;

  public CategoryMongoDao(@Qualifier("categoryDao") MongoRepo<ShopCategory> repo) {
    this.repo = repo;
  }

  public MongoRepo<ShopCategory> getRepo() {
    return repo;
  }

  public List<ShopCategory> getCategories() {
    return getRepo().findAll();
  }

  public ShopCategory addCategory(ShopCategory category) {
    return getRepo().save(category);
  }

  public void deleteCategory(String id) {
    getRepo().delete(Query.query(Criteria.where("_id").is(id)));
  }


}
