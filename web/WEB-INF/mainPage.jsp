<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="text" var="text"/>

<fmt:message bundle="${text}" key="about_us" var="abous_us"/>
<fmt:message bundle="${text}" key="about_gym" var="about_gym"/>
<fmt:message bundle="${text}" key="quote_author" var="quote_author"/>
<fmt:message bundle="${text}" key="our_team" var="our_team"/>


<!doctype html>
<html lang="${sessionScope.language}">
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>Fitness-centre</title>
    <link rel="stylesheet" href="../styless/baseStyles.css" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Staatliches" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Noto+Serif+TC" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Oswald:300" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Oswald:300" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lato|Sarabun" rel="stylesheet">
    <script src="../scripts/buttonStyleChanger.js"></script>

</head>
<body>
<div id="wrapper">
    <jsp:include page="menu.jsp">
        <jsp:param name="pageTopic" value="about_us"/>
        <jsp:param name="currentPage" value="main"/>
    </jsp:include>
    <section>
        <blockquote>
            <p>${about_gym}</p>
            <cite>${quote_author}</cite>
        </blockquote>
        <div class="block" style="height:405px;overflow-y: scroll; margin-top:20px;">
            <div class="myText" style="width: 640px;margin-left: 20px;">
                <h2 style="text-align: center;padding-top: 10px;font-family: 'Sarabun', sans-serif;">6 main reasons to come to Lifestyle Fitness & GYM</h2>
                <p class="textP">Taking care of the beauty of your body for many became a habit. Someone is just going to get a slim figure and tightened muscles, so begins to browse fitness sites. Minsk gives you the opportunity to engage in a healthy lifestyle, the main thing is to find your fitness club, which will be as comfortable as possible.</p>
                <p class="textP">We are happy to invite everyone who is interested in fitness in Minsk to the modern club "Lifestyle Fitness & GYM".</p>
                <h2 style="text-align:left;padding-top: 10px;font-family: 'Sarabun', sans-serif;">Why should we give preference to our club?</h2>
                <div style="margin-left: -15px;">
                    <ul>
                        <li>
                            <p class="textP"><b>Variety of choice.</b>A impressive area of about 1,400 square meters, where everyone will choose sports activities to their liking. We are talking about the gym, as well as three rooms for group classes in various areas of fitness, and a hall for martial arts.</p>
                        </li>
                        <li>
                            <p class="textP"><b>Individual approach.</b>Selection of individual programs for weight loss, fat burning, muscle building. This always takes into account the physical fitness of each.</p>
                        </li>
                        <li>
                            <p class="textP"><b>Professional instructors and trainers.</b>A team consisting of professionals, so we will be easy and comfortable to achieve your goals. You will feel the responsiveness, delicacy and attentiveness of the staff.</p>
                        </li>
                        <li>
                            <p class="textP"><b>Comfortable stay.</b>Comfortable changing rooms for men and women, free Wi - Fi, drinking water and clean towels.</p>
                        </li>
                        <li>
                            <p class="textP"><b>Nice prices.</b>The cost of a subscription or a single visit is democratic. Permanent discounts and promotions.</p>
                        </li>
                        <li>
                            <p class="textP"><b>Convenient location.</b>Location in the center of Minsk, so to get to us is not difficult.</p>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <figure>
            <img src="../images/zal1.jpg" style="margin-top: 15px;" width="320" height="175" alt="">
        </figure>
        <figure>
            <img src="../images/zal2.jpg" width="320" height="175" style="margin-left: 35px" alt="">
        </figure>

        <h2>${our_team}</h2>
        <div class="team-row">
            <figure>
                <img src="../images/workers/ceo.jpg" width="136" height="96" alt="">
                <figcaption>John Doe<span>Manager</span></figcaption>
            </figure>
            <figure>
                <img class="tabImage" src="../images/workers/artDirecor.jpg" width="120" height="96" alt="">
                <figcaption class="tabGrip">Ericka Nobriga<span>Art director</span></figcaption>
            </figure>
            <figure>
                <img class="tabImage" src="../images/workers/administrator.jpg" width="137" height="96" alt="">
                <figcaption class="tabGrip">Saundra Pittsley<span>Administrator</span></figcaption>
            </figure>
            <figure>
                <img class="tabImage" src="../images/workers/administrator2.jpg" width="134" height="96" alt="">
                <figcaption class="tabGrip">Margery Venuti<span>Spa administrator</span></figcaption>
            </figure>
        </div>
        <div class="team-row">

            <figure>
                <img src="../images/workers/administrator1.jpg" width="120" height="96" alt="">
                <figcaption>Julio Simser<span>Administrator</span></figcaption>
            </figure>
            <figure>
                <img class="tabImage" src="../images/workers/mass.jpg" width="136" height="96" alt="">
                <figcaption class="tabGrip">Cody Rousselle<span>masseur</span></figcaption>
            </figure>
            <figure>
                <img class="tabImage" src="../images/workers/mass2.jpg" width="137" height="96" alt="">
                <figcaption class="tabGrip">Erik Wollman<span>masseur</span></figcaption>
            </figure>
            <figure>
                <img class="tabImage" src="../images/workers/mainCoach.jpg" width="134" height="96" alt="">
                <figcaption class="tabGrip">Dona Shoff<span>Main coach</span></figcaption>
            </figure>
        </div>
    </section>
</div>

</body>
</html>