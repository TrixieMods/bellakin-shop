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
public class TermsController {

  private static final Logger LOG = LoggerFactory.getLogger(TermsController.class);

  @Autowired
  public TermsController() {
  }

  @GetMapping("/privacy")
  public ModelAndView privacy(ModelMap model) {
    return new ModelAndView("terms/privacy", model);
  }

  @GetMapping("/terms")
  public ModelAndView terms(ModelMap model) {
    return new ModelAndView("terms/terms", model);
  }

}
