package com.game.monopoly.helper;

import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

public class ServletPathHelper {
    public static String getServletPath(WebRequest request) {
        return ((ServletWebRequest) request).getRequest().getServletPath();
    }
}
