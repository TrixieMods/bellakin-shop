package com.bellakin.shop.configurations;

import com.bellakin.shop.controllers.UserInterceptor;
import com.bellakin.shop.controllers.UserSession;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author dan.rees.thomas@gmail.com
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

  private final UserSession session;

  public WebConfig(UserSession session) {
    this.session = session;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new UserInterceptor(session));
  }
}
