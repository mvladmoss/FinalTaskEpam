<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="text" var="text"/>

<fmt:message bundle="${text}" key="login.username.placeholder" var="username_placeholder"/>
<fmt:message bundle="${text}" key="login.pattern.error" var="username_pattern_error"/>
<fmt:message bundle="${text}" key="login.password.placeholder" var="password_placeholder"/>

<!DOCTYPE html>
<html lang="${sessionScope.language}" >

  <meta charset="UTF-8">
  <title>Day 001 Login Form</title>
  <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Open+Sans:600'>
  <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
  <link rel="stylesheet" href="../styless/loginStyles.css">
  <script src="../scripts/Validation.js"></script>
  </head>



<body>
  <div class="login-wrap">
		<div class="login-html">
	 <div class="header">

		  <a href="controller?command=language&language=${sessionScope.nextLanguage}" style="float:right">${sessionScope.language}</a>
	  </div>
		<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab" >Sign  In</label>
		<input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Sign Up</label>
		<div class="login-form" >
			<form action="${pageContext.servletContext.contextPath}/controller?command=login" method="post">
				<div class="sign-in-htm">
					<div class="group">
						<label for="login" class="label">Username</label>
						<input  id="login" oninput="check()" name="login" type="text" class="input" required placeholder="${username_placeholder}" lang="${sessionScope.language}"  title="${username_pattern_error}">
					</div>
					<div class="group">
						<label for="password" class="label">Password</label>
						<input  name="password" id="password" type="password" class="input" placeholder="${password_placeholder}" data-type="password">
					</div>

					<div class="group">
						<input type="submit" class="button" value="Sign In">
					</div>
				</div>
			</form>

			<div class="sign-up-htm">
					<form action="${pageContext.servletContext.contextPath}/controller?command=client_registration" method="post">
						<div class="group">
							<label for="loginRegister" class="label">Login</label>
							<input id="loginRegister" name="loginRegister" type="text" class="input">
						</div>
						<div class="group">
							<label for="passwordRegister" class="label">Password</label>
							<input id="passwordRegister" name="passwordRegister" type="password" class="input" data-type="password">
						</div>
						<div class="group">
							<label for="nameRegister" class="label">Name</label>
							<input id="nameRegister" name="nameRegister" type="text" class="input" data-type="text">
						</div>
						<div class="group">
							<label for="surnameRegister" class="label">Surname</label>
							<input id="surnameRegister" name="surnameRegister" type="text" class="input">
						</div>

						<div class="group">
							<input type="submit" class="button" value="Sign Up">
						</div>
					</form>
				</div>
			</div>

	</div>
</div>
  
  

</body>

</html>
