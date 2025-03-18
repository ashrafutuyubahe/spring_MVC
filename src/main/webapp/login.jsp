<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="java.util.Locale" %>

<%

    Locale locale = new Locale((String) session.getAttribute("lang") != null ? (String) session.getAttribute("lang") : "en");

    ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

    String errorMessage = request.getParameter("error");
%>

<html lang="<%= locale.getLanguage() %>">
<head>
    <meta charset="UTF-8">
    <title><%= bundle.getString("login") %></title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }
        body {
            background: #f4f4f9;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            width: 100%;
            max-width: 400px;
            padding: 30px;
            background: white;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        h2 {
            color: #2C3E50;
            margin-bottom: 20px;
        }
        label {
            display: block;
            text-align: left;
            font-weight: bold;
            margin: 10px 0 5px;
            color: #333;
        }
        input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 1em;
        }
        button {
            width: 100%;
            padding: 12px;
            margin-top: 15px;
            border: none;
            border-radius: 5px;
            background: #3498db;
            color: white;
            font-size: 1.2em;
            cursor: pointer;
            transition: 0.3s;
        }
        button:hover {
            background: #2980b9;
        }
        .error-message {
            color: red;
            font-size: 0.9em;
            margin-top: 10px;
        }
        .register-link {
            margin-top: 15px;
            font-size: 0.9em;
        }
        .register-link a {
            color: #3498db;
            text-decoration: none;
            font-weight: bold;
        }
        .register-link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="container">
    <h2><%= bundle.getString("login") %></h2>

    <% if (errorMessage != null) { %>
    <p class="error-message"><%= errorMessage %></p>
    <% } %>

    <form action="auth?action=login" method="post">
        <label for="email"><%= bundle.getString("email") %>:</label>
        <input type="email" id="email" name="email" required>

        <label for="password"><%= bundle.getString("password") %>:</label>
        <input type="password" id="password" name="password" required>

        <button type="submit"><%= bundle.getString("login") %></button>
    </form>

    <p class="register-link">
        <%= bundle.getString("no_account") %>
        <a href="register"><%= bundle.getString("register_here") %></a>
    </p>
</div>

</body>
</html>
