<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="text" var="text"/>

<fmt:message bundle="${text}" key="login.username.placeholder" var="username_placeholder"/>
<fmt:message bundle="${text}" key="login.pattern.error" var="username_pattern_error"/>
<fmt:message bundle="${text}" key="login.password.placeholder" var="password_placeholder"/>
<fmt:message bundle="${text}" key="login.password.error" var="password_pattern_error"/>
<fmt:message bundle="${text}" key="login.enter" var="enter"/>
<fmt:message bundle="${text}" key="login.registration" var="registration"/>
<fmt:message bundle="${text}" key="login.username" var="username"/>
<fmt:message bundle="${text}" key="login.password" var="password"/>
<fmt:message bundle="${text}" key="name" var="name"/>
<fmt:message bundle="${text}" key="surname" var="surname"/>


<!DOCTYPE html>
<html lang="${sessionScope.language}" >

<meta charset="UTF-8">
<title>Day 001 Login Form</title>
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Open+Sans:600'>
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
<link rel="stylesheet" href="../styless/loginStyles.css">
<script src="../scripts/loginValidation.js"></script>
</head>

<body>
<div class="login-wrap">
    <div class="login-html">

        <h2>You have no access to this page</h2>
    </div>
</div>



</body>

</html>
