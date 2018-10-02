package com.bellakin.botzzer.controllers.general;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.NestedServletException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
@Scope("session")
@SuppressWarnings("unused")
public class FailController implements ErrorController  {

  private static final Logger LOG = LoggerFactory.getLogger(FailController.class);

  private final String errorPath = "/error";

  @GetMapping(errorPath)
  public ModelAndView error(ModelMap model, HttpServletRequest request) {
    Object errorStatusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    Object errorMessage = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
    Object errorException = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

    if (errorStatusCode != null) {
      model.put("errorCode", Integer.valueOf(errorStatusCode.toString()));
      model.put("errorPhrase", HttpStatus.valueOf(Integer.valueOf(errorStatusCode.toString())).getReasonPhrase());
    }

    if (errorMessage != null) {
      model.put("errorMessage", errorMessage);
    }

    if (errorException instanceof NestedServletException) {
      model.put("errorException", ((NestedServletException) errorException).getMessage());
    }

    return new ModelAndView("error", model);
  }

  @Override
  public String getErrorPath() {
    return errorPath;
  }
}
