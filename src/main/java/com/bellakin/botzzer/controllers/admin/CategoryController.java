package com.bellakin.botzzer.controllers.admin;

import com.bellakin.botzzer.controllers.UserSession;
import com.bellakin.botzzer.controllers.exceptions.ForbiddenException;
import com.bellakin.botzzer.data.models.category.CategoryMongoDao;
import com.bellakin.botzzer.data.models.category.ShopCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SuppressWarnings("unused")
public class CategoryController {

  private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);

  private final CategoryMongoDao categoryMongoDao;

  private final UserSession session;

  @Autowired
  public CategoryController(CategoryMongoDao categoryMongoDao, UserSession session) {
    this.categoryMongoDao = categoryMongoDao;
    this.session = session;
  }

  @GetMapping("/admin/categories")
  public ModelAndView userList(ModelMap model) {

    if(!session.isAdmin()) {
      throw new ForbiddenException("Forbidden");
    }

    model.addAttribute("categories", categoryMongoDao.getCategories());

    return new ModelAndView("/admin/categories");
  }

  @PostMapping(value = "/admin/deleteCategory", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity deleteUser(
    @RequestBody DeleteRequest request
  ) {
    if(!session.isAdmin()) {
      throw new ForbiddenException("Forbidden");
    }

    categoryMongoDao.deleteCategory(request.getId());

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping(value = "/admin/addCategory")
  public ResponseEntity addUser(
    @RequestParam(value = "name") String name,
    @RequestParam(value = "description") String description
  ) {
    if(!session.isAdmin()) {
      throw new ForbiddenException("Forbidden");
    }

    ShopCategory category = categoryMongoDao.addCategory(new ShopCategory(name, description));
    LOG.info("Category created: {} - {}", category.getId(), category.getName());

    return new ResponseEntity<>(HttpStatus.OK);
  }

  public static class DeleteRequest {
    String id;

    public DeleteRequest() {};

    public void setId(String id) {
      this.id = id;
    }

    public String getId() {
      return id;
    }
  }

}
