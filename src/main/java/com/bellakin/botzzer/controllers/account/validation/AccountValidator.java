package com.bellakin.botzzer.controllers.account.validation;

import com.bellakin.botzzer.data.models.users.AppUser;
import com.bellakin.botzzer.services.IUserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Pattern;

/**
 * @author dan.rees.thomas@gmail.com
 */
@Component
public class AccountValidator implements IValidator {

  private static final Pattern STRONG_PATTERN =
    Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{8,})");

  private static final Pattern MEDIUM_PATTERN =
    Pattern.compile("^(((?=.*[a-z])(?=.*[A-Z]))|((?=.*[a-z])(?=.*[0-9]))|((?=.*[A-Z])(?=.*[0-9])))(?=.{6,})");

  private static final Pattern EMAIL_PATTERN =
    Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])");


  private final IUserService<AppUser> userService;

  public AccountValidator(IUserService<AppUser> userService) {
    this.userService = userService;
  }

  public  Optional<String> validateUsername(String username) {
    if(Strings.isBlank(username) || username.length() < 4 || username.length() > 32) {
      return Optional.of("Username must be 4 to 32 characters long!");
    }

    if(!userService.isUniqueName(username)) {
      return Optional.of("That username is taken!");
    }
    return Optional.empty();
  }

  public  Optional<String> validatePassword(String password, String password2) {
    // Check password
    if(Strings.isBlank(password) || Strings.isBlank(password2)) {
      return Optional.of("Password must be set!");
    }

    if(!MEDIUM_PATTERN.matcher(password).find() && !STRONG_PATTERN.matcher(password).find()) {
      return Optional.of("Password is not strong enough!");
    }

    if(!password.equals(password2)) {
      return Optional.of("Passwords do not match!");
    }
    return Optional.empty();

  }

  public  Optional<String> validateEmail(String email) {
    // Check email
    if(Strings.isBlank(email) || !EMAIL_PATTERN.matcher(email).find()) {
      return Optional.of("Invalid email address!");
    }
    return Optional.empty();
  }

}
