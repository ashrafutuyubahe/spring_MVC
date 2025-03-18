<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="java.util.Locale" %>

<%

    String lang = request.getParameter("lang");
    if (lang != null) {
        session.setAttribute("lang", lang);
    } else if (session.getAttribute("lang") == null) {
        session.setAttribute("lang", "rw");
    }


    Locale locale = new Locale((String) session.getAttribute("lang"));
    ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
%>

<html lang="<%= locale.getLanguage() %>">
<head>
    <meta charset="UTF-8">
    <title><%= bundle.getString("title") %></title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }
        body {
            background: #f4f4f9;
            text-align: center;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            flex-direction: column;
        }
        .hero {
            width: 100%;
            max-width: 800px;
            padding: 40px;
            background: white;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #2C3E50;
            font-size: 2.5em;
            margin-bottom: 10px;
        }
        p {
            color: #555;
            font-size: 1.2em;
            margin-bottom: 20px;
        }
        .buttons {
            display: flex;
            justify-content: center;
            gap: 20px;
            margin-top: 20px;
        }
        .btn {
            text-decoration: none;
            font-size: 1.2em;
            padding: 12px 25px;
            border-radius: 5px;
            color: white;
            transition: 0.3s;
        }
        .btn-primary {
            background: #3498db;
        }
        .btn-primary:hover {
            background: #2980b9;
        }
        .btn-secondary {
            background: #2ecc71;
        }
        .btn-secondary:hover {
            background: #27ae60;
        }
        .language-select {
            margin-top: 20px;
        }
        select {
            padding: 8px;
            font-size: 1em;
            border-radius: 5px;
            border: 1px solid #ccc;
        }
    </style>
</head>
<body>

<div class="hero">
    <h1><%= bundle.getString("welcome") %></h1>
    <p><%= bundle.getString("description") %></p>
    <div class="buttons">
        <a href="login" class="btn btn-primary"><%= bundle.getString("login") %></a>
        <a href="register" class="btn btn-secondary"><%= bundle.getString("register") %></a>
    </div>
    <div class="language-select">
        <form method="get">
            <label for="lang"><strong><%= bundle.getString("language") %>:</strong></label>
            <select name="lang" id="lang" onchange="this.form.submit()">
                <option value="en" <%= "en".equals(session.getAttribute("lang")) ? "selected" : "" %>>English</option>
                <option value="rw" <%= "rw".equals(session.getAttribute("lang")) ? "selected" : "" %>>Kinyarwanda</option>
            </select>
        </form>
    </div>
</div>

</body>
</html>
