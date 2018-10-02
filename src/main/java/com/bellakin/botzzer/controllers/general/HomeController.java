package com.bellakin.botzzer.controllers.general;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SuppressWarnings("unused")
public class HomeController {

  private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

  @Autowired
  public HomeController() {
  }

  @GetMapping("/")
  public ModelAndView index(ModelMap model) {
    return new ModelAndView("index", model);
  }

}
