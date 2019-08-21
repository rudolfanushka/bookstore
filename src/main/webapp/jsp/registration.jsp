<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Registration</title>
   <!-- <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" /> -->
</head>
<body>

<div class="container">

    <form name = "RegistrationForm" method = "POST" action = "controller" class="form-signin" role="form">
        <input type="hidden" name="command" value="registration"/>

        <input type="text" placeholder="Name" name="name" required>
        <input type="text" placeholder="Login" name="login" required>
        <br/>
        <input type="password" placeholder="Password" name="password" required>
        <input type="password" placeholder="Repeat password" name="repeat_password" required>
        <br/>

        <button type="submit">Registration</button>

    </form>
</div>
</body>
</html>
