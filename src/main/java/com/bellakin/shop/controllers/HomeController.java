package com.bellakin.shop.controllers;

import com.bellakin.shop.models.Customer;

import com.bellakin.shop.models.CustomerMongoDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@SuppressWarnings("unused")
public class HomeController {

  private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

  private final CustomerMongoDAO customerRepo;
  private final CustomerMongoDAO copyDao;

  @Autowired
  public HomeController(
    @Qualifier("customerDao") CustomerMongoDAO customerRepo,
    @Qualifier("customerDao2") CustomerMongoDAO copyDao) {
    this.customerRepo = customerRepo;
    this.copyDao = copyDao;
  }

  @GetMapping("/")
  public ModelAndView index(Map<String, Object> model) {
    Customer c = customerRepo.getRepo().save(new Customer("Knob", "Cheese"));
    model.put("saved", c);
    customerRepo.getRepo().findAll().forEach(customer -> copyDao.getRepo().save(customer));

    long count = customerRepo.getRepo().count();

    customerRepo.getRepo().existsById(c.getId());

    return new ModelAndView("index", model);
  }


}
