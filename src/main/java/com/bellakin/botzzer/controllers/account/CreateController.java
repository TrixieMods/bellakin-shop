package com.bellakin.botzzer.controllers.account;

import com.bellakin.botzzer.controllers.UserSession;
import com.bellakin.botzzer.controllers.account.validation.IValidator;
import com.bellakin.botzzer.data.models.users.AppUser;
import com.bellakin.botzzer.security.EncryptionHelper;
import com.bellakin.botzzer.services.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@SuppressWarnings("unused")
public class CreateController {

  private static final Logger LOG = LoggerFactory.getLogger(CreateController.class);


  private final UserSession userSession;

  private final IUserService<AppUser> userService;
  private final IValidator validator;

  @Autowired
  public CreateController(UserSession userSession, IUserService<AppUser> userService, IValidator validator) {
    this.userSession = userSession;
    this.userService = userService;
    this.validator = validator;
  }

  @GetMapping("/account/create")
  public ModelAndView create(ModelMap model) {
    return new ModelAndView("account/create", model);
  }

  @RequestMapping(value = "/account/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, method = RequestMethod.POST)
  public ResponseEntity createAccount(CreateDto createDto) {
    if(userSession.isLoggedIn()) {
      return new ResponseEntity<>("Already logged in", HttpStatus.BAD_REQUEST);
    }

    List<String> errors = new ArrayList<>();

    validator.validateUsername(createDto.name).ifPresent(errors::add);
    validator.validatePassword(createDto.password, createDto.password2).ifPresent(errors::add);
    validator.validateEmail(createDto.email).ifPresent(errors::add);

    // Check terms agreement
    if(!createDto.accepted) {
      errors.add("You must accept the terms!");
    }

    if(!errors.isEmpty()) {
      return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // Create the user
    Optional<AppUser> user = userService.createAdmin(createDto.name, EncryptionHelper.encryptPassword(createDto.password), createDto.email);
    if(!user.isPresent()) {
      return new ResponseEntity<>(Collections.singleton("Something went wrong!"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Log in
    if(userSession.logIn(userService, createDto.name, EncryptionHelper.encryptPassword(createDto.password))) {
      return new ResponseEntity<>(HttpStatus.OK);
    }

    return new ResponseEntity<>(Collections.singleton("Account created - failed to log in"), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  public class CreateDto {
    public String name;
    public String password;
    public String password2;
    public String email;
    public boolean accepted;

    public void setName(String name) {
      this.name = name;
    }

    public void setPassword(String password) {
      this.password = password;
    }

    public void setAccepted(boolean accepted) {
      this.accepted = accepted;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public void setPassword2(String password2) {
      this.password2 = password2;
    }
  }

}
