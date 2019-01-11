
<!DOCTYPE html>
<html lang="en" >

<head>
  <meta charset="UTF-8">
  <title>Day 001 Login Form</title>
  <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Open+Sans:600'>
  <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
  <link rel="stylesheet" href="../styless/loginStyles.css">
</head>



<body>
  <div class="login-wrap">
		<div class="login-html">
	 <div class="header">

		  <a href="controller?command=language&language=${sessionScope.nextLanguage}" style="float:right">RU</a>
	  </div>
		<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Sign  In</label>
		<input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Sign Up</label>
		<div class="login-form">
			<form action="${pageContext.servletContext.contextPath}/controller?command=login" method="post">
				<div class="sign-in-htm">
					<div class="group">
						<label for="login" class="label">Username</label>
						<input  id="login" name="login" type="text" class="input">
					</div>
					<div class="group">
						<label for="password" class="label">Password</label>
						<input  name="password" id="password" type="password" class="input" data-type="password">
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
