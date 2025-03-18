<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="java.util.Locale" %>

<%

    String lang = (String) session.getAttribute("lang");
    if (lang == null) {
        lang = "en";
    }
    Locale locale = new Locale(lang);
    ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);


    String errorMessage = request.getParameter("error");
%>

<html lang="<%= locale.getLanguage() %>">
<head>
    <meta charset="UTF-8">
    <title><%= bundle.getString("register") %></title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #ecf0f1;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 400px;
            padding: 20px;
            background-color: #fff;
            margin: 100px auto;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        h2 {
            color: #2C3E50;
            text-align: center;
        }
        label {
            font-weight: bold;
            margin-bottom: 10px;
            display: block;
        }
        input {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border-radius: 4px;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }
        button {
            background-color: #3498db;
            color: white;
            padding: 10px;
            width: 100%;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #2980b9;
        }
    </style>
    <script type="text/javascript">
        window.onload = function() {

            var urlParams = new URLSearchParams(window.location.search);
            var errorMessage = urlParams.get('error');
            if (errorMessage) {
                document.getElementById('error-message').innerText = errorMessage;
            }
        };
    </script>
</head>
<body>
<div class="container">
    <h2><%= bundle.getString("register") %></h2>
    <form action="auth?action=register" method="post">
        <%--@declare id="email"--%><%--@declare id="username"--%><%--@declare id="password"--%><%--@declare id="password"--%><label for="email"><%= bundle.getString("email") %>:</label>
        <input type="email" name="email" required>

        <label for="username"><%= bundle.getString("username") %>:</label>
        <input type="text" name="username" required>

        <label for="password"><%= bundle.getString("password") %>:</label>
        <input type="password" name="password" required>

        <button type="submit"><%= bundle.getString("register") %></button>
    </form>


    <div id="error-message" style="color: red; text-align: center; margin-top: 10px;">
        <%= errorMessage != null ? errorMessage : "" %>
    </div>
</div>
</body>
</html>



