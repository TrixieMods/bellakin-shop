package com.bellakin.botzzer.controllers.admin;

import com.bellakin.botzzer.controllers.UserSession;
import com.bellakin.botzzer.controllers.exceptions.ForbiddenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SuppressWarnings("unused")
public class AdminController {

  private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);

  private final UserSession session;

  @Autowired
  public AdminController(UserSession session) {
    this.session = session;
  }

  @GetMapping("/admin")
  public ModelAndView userList(ModelMap model) {
    if(!session.isAdmin()) {
      throw new ForbiddenException("Not Authorized");
    }
    return new ModelAndView("admin/admin", model);
  }

}
