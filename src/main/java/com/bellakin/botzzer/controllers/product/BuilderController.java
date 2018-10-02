package com.bellakin.botzzer.controllers.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SuppressWarnings("unused")
public class BuilderController {

  private static final Logger LOG = LoggerFactory.getLogger(BuilderController.class);

  @Autowired
  public BuilderController() {
  }

  @GetMapping("/build-a-bot")
  public ModelAndView index(ModelMap model) {
    return new ModelAndView("product/builder/builder", model);
  }

}
