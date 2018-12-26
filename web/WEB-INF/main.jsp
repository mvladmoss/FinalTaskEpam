<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="${sessionScope.language}">
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>Whitesquare</title>
    <link rel="stylesheet" href="../styless/baseStyles.css" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Staatliches" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Noto+Serif+TC" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Oswald:300" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Oswald:300" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet">
    <script src="../scripts/button.js"></script>

</head>
<body>
<div id="wrapper">
    <nav>
        <ul class="top-menu">
            <li id="home"><a href="${pageContext.servletContext.contextPath}/controller?command=main" onmouseover="changeStyle('home')" onmouseout="changeStyleBack('home')">HOME</a></li>
            <li id="signOut" style="position: relative; left: 630px"><a href="${pageContext.servletContext.contextPath}/controller?command=sign_out"  onmouseover="changeStyle('signOut')" onmouseout="changeStyleBack('signOut')">SIGN OUT</a></li>
            <li id="language" style="position: relative; left: 640px"><a href="${pageContext.servletContext.contextPath}/controller?command=language&currentPage=main&language=${sessionScope.nextLanguage}"  onmouseover="changeStyle('language')" onmouseout="changeStyleBack('language')">${sessionScope.language}</a></li>
        </ul>
    </nav>
    <div id="heading">
        <h1>ABOUT US</h1>
    </div>
    <aside>
        <h2>MENU</h2>
        <nav>
            <ul class="aside-menu">
                <c:if test="${role == 'client'}">
                    <li id="profile">
                        <a href="${pageContext.servletContext.contextPath}/controller?command=profile" onmouseover="changeStyle('profile')" onmouseout="changeStyleBack('profile')" >Profile</a>
                    </li>
                    <li id="my_exercises" >
                        <a href="${pageContext.servletContext.contextPath}/controller?command=show_client_exercises" onmouseover="changeStyle('my_exercises')" onmouseout="changeStyleBack('my_exercises')">My exercises</a>
                    </li>
                    <li id="my_nutrition" >
                        <a href="${pageContext.servletContext.contextPath}/controller?command=show_client_nutrition" onmouseover="changeStyle('my_nutrition')" onmouseout="changeStyleBack('my_nutrition')">My nutrition</a>
                    </li>
                    <li id="buyMembership">
                        <a href="${pageContext.servletContext.contextPath}/controller?command=get_order_page" onmouseover="changeStyle('buyMembership')" onmouseout="changeStyleBack('buyMembership')" >Buy membership</a>
                    </li>
                    <li id="coaches">
                        <a href="${pageContext.servletContext.contextPath}/controller?command=coaches"  onmouseover="changeStyle('coaches')" onmouseout="changeStyleBack('coaches')">Our coaches</a>
                    </li>
                </c:if>
                <c:if test="${role == 'coach'}">
                    <li id="myClients">
                        <a href="${pageContext.servletContext.contextPath}/controller?command=all_coach_clients" onmouseover="changeStyle('myClients')" onmouseout="changeStyleBack('myClients')">My clients</a>
                    </li>
                </c:if>
            </ul>
        </nav>
        <h2>OUR GYM</h2>
        <p>
            <img src="../images/gym.jpg" o width="250" height="181" alt="Our offices">
        </p>
    </aside>
    <section>
        <blockquote>
            <p>
                &ldquo;FITNESS IS NOT ONLY ONE OF THE KEYS TO A HEALTHY BODY, BUT
                ALSO THE BASIS OF DYNAMIC AND CREATIVE IDEAS&rdquo;
            </p>
            <cite>John F. Kennedy</cite>
        </blockquote>
        <div class="block">
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean non neque ac sem accumsan rhoncus ut ut turpis. In hac habitasse platea dictumst. Proin eget nisi erat, et feugiat arcu. Duis semper porttitor lectus, ac pharetra erat imperdiet nec. Morbi interdum felis nulla. Aenean eros orci, pellentesque sed egestas vitae, auctor aliquam nisi. Nulla nec libero eget sem rutrum iaculis. Quisque in enim velit, at dignissim est. Nulla ullamcorper, dolor ac pellentesque placerat, justo tellus gravida erat, vel porttitor libero erat condimentum metus. Donec sodales aliquam orci id suscipit. Proin sed risus sit amet massa ultrices laoreet quis a erat. Aliquam et metus id erat vulputate egestas. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</p>
            <p>Donec vel nisl nibh. Aenean quam tortor, tempus sit amet mattis dapibus, egestas tempor dui. Duis vestibulum imperdiet risus pretium pretium. Nunc vitae porta ligula. Vestibulum sit amet nulla quam. Aenean lacinia, ante vitae sodales sagittis, leo felis bibendum neque, mattis sagittis neque urna vel magna. Sed at sem vitae lorem blandit feugiat.</p>
            <p>Donec vel orci purus, ut ornare orci. Aenean rutrum pellentesque quam. Quisque gravida adipiscing augue, eget commodo augue egestas varius. Integer volutpat, tellus porta tincidunt sodales, lacus est tempus odio, fringilla blandit tortor lectus ut sem. Pellentesque nec sem lacus, sit amet consequat neque. Etiam varius urna quis arcu cursus in consectetur dui tincidunt. Quisque arcu orci, lacinia eget pretium vel, iaculis pellentesque nibh. Etiam cursus lacus eget neque viverra vestibulum. Aliquam erat volutpat. Duis pulvinar tellus ut urna facilisis mollis. Maecenas ac pharetra dui. Pellentesque neque ante, luctus eget congue eget, rhoncus vel mauris. Duis nisi magna, aliquet a convallis non, venenatis at nisl. Nunc at quam eu magna malesuada dignissim. Duis bibendum iaculis felis, eu venenatis risus sodales non. In ligula mi, faucibus eu tristique sed, vulputate rutrum dolor.</p>
        </div>
        <figure>
            <img src="../images/zal1.jpg" width="320" height="175" alt="">
        </figure>
        <figure>
            <img src="../images/zal2.jpg" width="320" height="175" alt="">
        </figure>

        <h2>OUR TEAM</h2>
        <div class="team-row">
            <figure>
                <img src="../images/ceo.jpg" width="136" height="96" alt="">
                <figcaption>John Doe<span>Manager</span></figcaption>
            </figure>
            <figure>
                <img src="../images/artDirecor.jpg" width="120" height="96" alt="">
                <figcaption>Ericka Nobriga<span>Art director</span></figcaption>
            </figure>
            <figure>
                <img src="../images/administrator.jpg" width="137" height="96" alt="">
                <figcaption>Saundra Pittsley<span>Administrator</span></figcaption>
            </figure>
            <figure>
                <img src="../images/administrator2.jpg" width="134" height="96" alt="">
                <figcaption>Margery Venuti<span>Spa administrator</span></figcaption>
            </figure>
        </div>
        <div class="team-row">

            <figure>
                <img src="../images/administrator1.jpg" width="120" height="96" alt="">
                <figcaption>Julio Simser<span>Administrator</span></figcaption>
            </figure>
            <figure>
                <img src="../images/mass.jpg" width="136" height="96" alt="">
                <figcaption>Cody Rousselle<span>masseur</span></figcaption>
            </figure>
            <figure>
                <img src="../images/mass2.jpg" width="137" height="96" alt="">
                <figcaption>Erik Wollman<span>masseur</span></figcaption>
            </figure>
            <figure>
                <img src="../images/mainCoach.jpg" width="134" height="96" alt="">
                <figcaption>Dona Shoff<span>Main coach</span></figcaption>
            </figure>

        </div>
    </section>
</div>

</body>
</html>