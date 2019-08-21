<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<fmt:setBundle basename="properties.label" />
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
</head>
<body>
<div class="container">

    <form name = "LoginForm" method = "POST" action = "controller" class="form-signin" role="form">

        <input type = "hidden" name = "command" value = "login" />

        <input type="text" placeholder="Login" name="login" required>
        <input type="password" placeholder="Password" name="password" required>
        <br/>

        <button type="submit">Log in</button>

    </form>
