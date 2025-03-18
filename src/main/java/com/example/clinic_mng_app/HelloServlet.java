package com.example.clinic_mng_app;

import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

//@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.sendRedirect("login");  // Redirect to the login page
    }

    public void destroy() {
    }
}
