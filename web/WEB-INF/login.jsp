
<!DOCTYPE html>
<html lang="en" >

<head>
  <meta charset="UTF-8">
  <title>Day 001 Login Form</title>
  <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Open+Sans:600'>
	  <link rel="stylesheet" href="../styless/loginStyle.css">
	  <script src="../scripts/sliderHard.js" defer></script>
</head>



<body>
  <div class="login-wrap">
		<div class="login-html">
	 <div class="header">
	 		<div class="logo-header">
	 			<img width="47" height="47" class="logo-header" src="../pictures/LOGO_1.png" alt="">
	 		</div>
		  <a href="controller?command=language&currentPage=&language=${sessionScope.nextLanguage}" style="float:right">RU</a>
	  </div>
		<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Sign  In</label>
		<input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Sign Up</label>
		<div class="login-form">
			<form action="${pageContext.servletContext.contextPath}/controller?command=login" method="post">
				<div class="sign-in-htm">
					<div class="group">
						<label for="user" class="label">Username</label>
						<input  id="login" name="login" type="text" class="input">
					</div>
					<div class="group">
						<label for="pass" class="label">Password</label>
						<input  name="password" id="password" type="password" class="input" data-type="password">
					</div>
					<div class="group">
						<input id="check" type="checkbox" class="check" checked>
						<label for="check"><span class="icon"></span> Keep me Signed in</label>
					</div>
					<div class="group">
						<input type="submit" class="button" value="Sign In">
					</div>
				</div>
			</form>

			<div class="sign-up-htm">

				<div class="group">
					<label for="user" class="label">Username</label>
					<input id="user" type="text" class="input">
				</div>
				<div class="group">
					<label for="pass" class="label">Password</label>
					<input id="pass" type="password" class="input" data-type="password">
				</div>
				<div class="group">
					<label for="pass" class="label">Repeat Password</label>
					<input id="pass" type="password" class="input" data-type="password">
				</div>
				<div class="group">
					<label for="pass" class="label">Email Address</label>
					<input id="pass" type="text" class="input">
				</div>
				<div class="group">
					<input type="submit" class="button" action="${pageContext.servletContext.contextPath}/controller?command=login" value="Sign Up">
	  </div>
				</div>
			</div>

	</div>
</div>
  
  

</body>

</html>
