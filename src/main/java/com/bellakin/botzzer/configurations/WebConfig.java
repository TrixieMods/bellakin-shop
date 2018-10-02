package com.bellakin.botzzer.configurations;

import com.bellakin.botzzer.controllers.ResponseInterceptor;
import com.bellakin.botzzer.controllers.UserSession;
import com.bellakin.botzzer.services.background.BackgroundProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author dan.rees.thomas@gmail.com
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

  private final UserSession session;

  private final BackgroundProvider<String> bgProvider;

  public WebConfig(UserSession session, BackgroundProvider<String> bgProvider) {
    this.session = session;
    this.bgProvider = bgProvider;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new ResponseInterceptor(session, bgProvider));
  }
}
