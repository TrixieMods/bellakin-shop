package com.bellakin.botzzer.controllers.admin;

import com.bellakin.botzzer.controllers.UserSession;
import com.bellakin.botzzer.controllers.exceptions.ForbiddenException;
import com.bellakin.botzzer.data.models.roles.ShopUserRole;
import com.bellakin.botzzer.data.models.users.AppUser;
import com.bellakin.botzzer.services.IUserService;
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
public class UserController {

  private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

  private final IUserService<AppUser> userService;

  private final UserSession session;

  @Autowired
  public UserController(IUserService<AppUser> userService, UserSession session) {
    this.userService = userService;
    this.session = session;
  }

  @GetMapping("/admin/users")
  public ModelAndView userList(ModelMap model) {

    if(!session.isAdmin()) {
      throw new ForbiddenException("Not Authorized");
    }

    Page shopUsers = userService.listUsers(0, 10);
    model.put("users", shopUsers);
    model.put("roles", ShopUserRole.values());

    return new ModelAndView("admin/users", model);
  }

  @PostMapping("/admin/addUser")
  public ResponseEntity addUser(
    @RequestParam(value = "name") String name,
    @RequestParam(value = "password") String password,
    @RequestParam(value = "role") ShopUserRole role
  ) {
    Optional<AppUser> user = Optional.empty();

    switch (role) {
      case ADMIN:
        user = userService.createAdmin(name, password, "default@default.com");
        break;
      case CUSTOMER:
        user = userService.createCustomer(name, password,"default@default.com");
        break;
    }

    return user.<ResponseEntity>map(shopUser -> new ResponseEntity<>(shopUser, HttpStatus.OK))
      .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));

  }

  @PostMapping(value = "/admin/deleteUser", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity deleteUser(
    @RequestBody DeleteRequest request
  ) {
    userService.deleteUser(request.getId());
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
