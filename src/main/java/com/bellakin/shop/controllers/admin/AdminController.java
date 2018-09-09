package com.bellakin.shop.controllers.admin;

import com.bellakin.shop.controllers.UserSession;
import com.bellakin.shop.controllers.exceptions.ForbiddenException;
import com.bellakin.shop.data.models.roles.ShopUserRole;
import com.bellakin.shop.data.models.users.AppUser;
import com.bellakin.shop.services.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import java.util.Optional;

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
