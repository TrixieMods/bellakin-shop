package com.bellakin.shop.controllers;

import com.bellakin.shop.data.models.users.AbstractAppUser;
import com.bellakin.shop.data.models.users.AppUser;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

/**
 * Used to inject tasty attributes into the session for use in the template engine
 */
public class ResponseInterceptor extends HandlerInterceptorAdapter {
 
    private final UserSession session;

    public ResponseInterceptor(UserSession session) {
        this.session = session;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession session = request.getSession();

        // User attributes
        AppUser appUser = this.session.getUser().isPresent() ? this.session.getUser().get() : null;
        putData(session, appUser, "isLoggedIn", item -> true, item -> false);
        putData(session, appUser, "roles", AbstractAppUser::getRoles, item -> new HashSet<>());
        putData(session, appUser, "username", AbstractAppUser::getUsername, item -> "User");

    }

    private <T, R> void putData(HttpSession session, @Nullable T optional, String key,
                                Mapper<T, R> valTrue, Mapper<T, R> valFalse) {
        if(optional != null) {
            session.setAttribute(key, valTrue.get(optional));
        } else {
            session.setAttribute(key, valFalse.get(null));
        }
    }

    @FunctionalInterface
    public interface Mapper<T, R> {
        R get(T item);
    }
}