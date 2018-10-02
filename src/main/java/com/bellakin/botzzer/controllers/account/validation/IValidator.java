package com.bellakin.botzzer.controllers.account.validation;

import java.util.Optional;

/**
 * @author dan.rees.thomas@gmail.com
 */
public interface IValidator {

  Optional<String> validateUsername(String username);

  Optional<String> validatePassword(String password, String password2);

  Optional<String> validateEmail(String email);

}
