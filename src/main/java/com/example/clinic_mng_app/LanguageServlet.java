package com.example.clinic_mng_app;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Locale;

public class LanguageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lang = request.getParameter("lang");
        Locale locale = new Locale(lang);

        HttpSession session = request.getSession();
        session.setAttribute("language", locale);

        response.sendRedirect(request.getHeader("Referer"));
    }


}