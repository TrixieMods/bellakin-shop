package com.bellakin.botzzer.controllers;

import com.bellakin.botzzer.data.models.users.AbstractAppUser;
import com.bellakin.botzzer.data.models.users.AppUser;
import com.bellakin.botzzer.services.background.BackgroundProvider;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Random;

/**
 * Used to inject tasty attributes into the session for use in the template engine
 */
public class ResponseInterceptor extends HandlerInterceptorAdapter {
 
    private final UserSession session;

    private final BackgroundProvider<String> bgProvider;

    private final Random rng;

    public ResponseInterceptor(UserSession session, BackgroundProvider<String> bgProvider) {
        this.session = session;
        this.bgProvider = bgProvider;
        this.rng = new Random();
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession session = request.getSession();

        // User attributes
        AppUser appUser = this.session.getUser().isPresent() ? this.session.getUser().get() : null;
        putData(session, appUser, "isLoggedIn", item -> true, item -> false);
        putData(session, appUser, "roles", AbstractAppUser::getRoles, item -> new HashSet<>());
        putData(session, appUser, "username", AbstractAppUser::getUsername, item -> "User");
        putData(session, bgProvider, "randBackground", BackgroundProvider::next, rng -> 1);
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