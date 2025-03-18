<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- Created by IntelliJ IDEA. User: achrafu Date: 3/4/2025 --%>
<%


    if (session == null || session.getAttribute("loggedInUser") == null) {
        System.out.println(session.getAttribute("loggedInUser"));
        response.sendRedirect("login");
        return;
    }
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Clinic Management Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
        }
        .navbar {
            background-color: #2C3E50;
            color: white;
            padding: 15px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .navbar .logo {
            font-size: 1.5em;
            font-weight: bold;
        }
        .navbar .profile {
            display: flex;
            align-items: center;
        }
        .navbar .profile img {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            margin-right: 10px;
        }
        .navbar .profile a, .logout-btn {
            color: white;
            text-decoration: none;
            margin-left: 15px;
            font-size: 1.1em;
        }
        .logout-btn {
            background: #e74c3c;
            padding: 8px 15px;
            border-radius: 5px;
        }
        .logout-btn:hover {
            background: #c0392b;
        }
        .container {
            max-width: 900px;
            margin: 50px auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        h2 {
            color: #2C3E50;
        }
        .links {
            margin-top: 20px;
            display: flex;
            justify-content: space-around;
        }
        .links a {
            text-decoration: none;
            color: white;
            background: #3498db;
            padding: 12px 20px;
            border-radius: 5px;
            font-size: 1.2em;
            transition: 0.3s;
        }
        .links a:hover {
            background: #2980b9;
        }
    </style>
    <script>
        // Prevent back action after login
        window.history.forward();
        function noBack() {
            window.history.forward();
        }
    </script>
</head>
<body onload="noBack();">

<div class="navbar">
    <div class="logo">Clinic Management</div>
    <div class="profile">
        <a href="#">Admin Profile</a>

        <form action="auth?action=logout" method="POST" style="display:inline;">
            <input type="hidden" name="action" value="logout">
            <button type="submit" class="logout-btn">Logout</button>
        </form>
    </div>
</div>

<div class="container">
    <h2>Admin Dashboard</h2>
    <div class="links">
        <a href="view">View Patients</a>
        <a href="add">Add Patient</a>
        <a href="manageDoctors">Manage Doctors</a>
        <a href="appointments">Appointments</a>
    </div>
</div>

</body>
</html>
