<%@ page import="com.example.clinic_mng_app.Model.Patient" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%--<%--%>
<%--  // Check if the user is logged in by checking the session attribute--%>
<%--//    HttpSession session = request.getSession(false);--%>
<%--  if (session == null || session.getAttribute("loggedInUser") == null) {--%>
<%--    // Redirect to login page if no session or user not found in session--%>
<%--    response.sendRedirect("login.jsp");--%>
<%--    return;--%>
<%--  }--%>
<%--%>--%>

<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Manage Patients</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f4;
      margin: 0;
      padding: 0;
    }
    .container {
      width: 80%;
      margin: 0 auto;
      padding: 20px;
      background-color: #fff;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
      margin-top: 40px;
      border-radius: 8px;
    }
    h1 {
      color: #2C3E50;
    }
    form {
      margin-bottom: 30px;
    }
    input[type="text"], input[type="number"] {
      width: 100%;
      padding: 10px;
      margin-bottom: 10px;
      border-radius: 4px;
      border: 1px solid #ccc;
    }
    input[type="submit"] {
      background-color: #3498db;
      color: white;
      padding: 10px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
    }
    input[type="submit"]:hover {
      background-color: #2980b9;
    }
    table {
      width: 100%;
      margin-top: 30px;
      border-collapse: collapse;
    }
    table, th, td {
      border: 1px solid #ddd;
    }
    th, td {
      padding: 10px;
      text-align: left;
    }
    th {
      background-color: #3498db;
      color: white;
    }
    a {
      color: #3498db;
      text-decoration: none;
      font-weight: bold;
    }
    a:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>Manage Patients</h1>

  <!-- Form for adding new patients -->
  <form action="ManagePatientsController" method="POST">
    <h2>Add New Patient</h2>
    <label for="name">Name: </label>
    <input type="text" id="name"  name="name" required><br>

    <label for="age">Age: </label>
    <input type="number" id="age" name="age" required><br>

    <label for="diagnosis">Diagnosis: </label>
    <input type="text" id="diagnosis" name="diagnosis" required><br>

    <input type="submit" value="Add Patient">
  </form>


</div>
</body>
</html>
