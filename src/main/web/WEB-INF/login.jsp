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
	 <div class="header">
		  <a href="controller?command=language&language=${sessionScope.nextLanguage}" style="float:right">${sessionScope.language}</a>
	  </div>
		<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab" >${enter}</label>
		<input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">${registration}</label>
			<div class="login-form" >
				<div class="sign-in-htm">
					<form action="${pageContext.servletContext.contextPath}/controller?command=login" method="post">
					<div class="group">
						<label for="login" class="label">${username}</label>
						<input  id="login" oninput="check()" name="login" type="text" class="input" required placeholder="${username_placeholder}" title="${username_pattern_error}">
					</div>
					<div class="group">
						<label for="password" class="label">${password}</label>
						<input  name="password" id="password" type="password" class="input" placeholder="${password_placeholder}" title="${password_pattern_error}" >
					</div>
						<c:if test="${errorLoginMessage eq true}">
							<br>
							<h3 style="color: red;margin-top: -20px;margin-left: 3px;">Enter invalid login or password.Please try again</h3>
						</c:if>
					<div class="group">
						<input onclick="checkForEnterAnyData()" type="submit" class="button" value="${enter}">
					</div>
					</form>
				</div>

			<div class="sign-up-htm">
					<form action="${pageContext.servletContext.contextPath}/controller?command=client_registration" method="post">
						<div class="group">
							<label for="loginRegister" class="label">${username}</label>
							<input id="loginRegister" name="loginRegister" type="text" title="Required field" class="input" required>
						</div>
						<div class="group">
							<label for="passwordRegister" class="label">${password}</label>
							<input id="passwordRegister" name="passwordRegister" type="password" title="Required field" required class="input" data-type="password">
						</div>
						<div class="group">
							<label for="nameRegister" class="label">${name}</label>
							<input id="nameRegister" name="nameRegister" type="text" class="input" title="Required field" required data-type="text">
						</div>
						<div class="group">
							<label for="surnameRegister" class="label">${surname}</label>
							<input id="surnameRegister" name="surnameRegister" type="text" title="Required field" required class="input">
						</div>

						<div class="group">
							<input type="submit" onclick="checkForRegistrationAnyData()" class="button" value="${registration}">
						</div>
					</form>
				</div>
			</div>
	</div>
</div>
  
  

</body>

</html>
