<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="text" var="text"/>

<fmt:message bundle="${text}" key="sign_out" var="sign_out"/>
<fmt:message bundle="${text}" key="home" var="home"/>
<fmt:message bundle="${text}" key="menu" var="menu"/>
<fmt:message bundle="${text}" key="profile" var="profile"/>
<fmt:message bundle="${text}" key="about_us" var="abous_us"/>
<fmt:message bundle="${text}" key="my_exercises" var="my_exercises"/>
<fmt:message bundle="${text}" key="my_nutrition" var="my_nutrition"/>
<fmt:message bundle="${text}" key="buy_membership" var="buy_membership"/>
<fmt:message bundle="${text}" key="our_coaches" var="our_coaches"/>
<fmt:message bundle="${text}" key="my_clients" var="my_clients"/>
<fmt:message bundle="${text}" key="coaches" var="coaches"/>
<fmt:message bundle="${text}" key="coach_clients" var="coach_clients"/>
<fmt:message bundle="${text}" key="profile_topic" var="profile_topic"/>
<fmt:message bundle="${text}" key="nutrition" var="nutrition"/>
<fmt:message bundle="${text}" key="exercises" var="exercises"/>
<fmt:message bundle="${text}" key="buy_gym_membership" var="buy_gym_membership"/>
<fmt:message bundle="${text}" key="comments_about_me" var="comments_about_me"/>
<fmt:message bundle="${text}" key="our_gym" var="our_gym"/>
<fmt:message bundle="${text}" key="gym_photos" var="gym_photos"/>

<nav style="border-radius: 6px;">
    <ul class="top-menu">
        <li id="home"><a href="${pageContext.servletContext.contextPath}/controller?command=main" onmouseover="changeStyle('home')" onmouseout="changeStyleBack('home')">${home}</a></li>
        <li id="signOut" style="position: relative; left: 630px"><a href="${pageContext.servletContext.contextPath}/controller?command=sign_out"  onmouseover="changeStyle('signOut')" onmouseout="changeStyleBack('signOut')">${sign_out}</a></li>
        <li id="language" style="position: relative; left: 640px"><a href="${pageContext.servletContext.contextPath}/controller?command=language&currentPage=${param.currentPage}&language=${sessionScope.nextLanguage}"  onmouseover="changeStyle('language')" onmouseout="changeStyleBack('language')">${sessionScope.language}</a></li>
    </ul>
</nav>
<div id="heading">
    <c:if test="${param.pageTopic eq 'about_us'}">
        <h1>${abous_us}</h1>
    </c:if>

    <c:if test="${param.pageTopic eq 'coaches'}">
        <h1>${coaches}</h1>
    </c:if>

    <c:if test="${param.pageTopic eq 'coach_clients'}">
        <h1>${coach_clients}</h1>
    </c:if>

    <c:if test="${param.pageTopic eq 'profile'}">
        <h1>${profile_topic}</h1>
    </c:if>

    <c:if test="${param.pageTopic eq 'nutrition'}">
        <h1>${nutrition}</h1>
    </c:if>

    <c:if test="${param.pageTopic eq 'exercises'}">
        <h1>${exercises}</h1>
    </c:if>

    <c:if test="${param.pageTopic eq 'buy_gym_membership'}">
        <h1>${buy_gym_membership}</h1>
    </c:if>
    <c:if test="${param.pageTopic eq 'show_comments'}">
        <h1>${comments_about_me}</h1>
    </c:if>
    <c:if test="${param.pageTopic eq 'gym_photos'}">
        <h1>${gym_photos}</h1>
    </c:if>

</div>
<aside>
    <h2>${menu}</h2>
    <nav>
        <ul class="aside-menu">
            <c:if test="${role == 'client'}">
                <li id="profile">
                    <a href="${pageContext.servletContext.contextPath}/controller?command=profile" onmouseover="changeStyle('profile')" onmouseout="changeStyleBack('profile')" >${profile}</a>
                </li>
                <li id="my_exercises" >
                    <a href="${pageContext.servletContext.contextPath}/controller?command=show_client_exercises" onmouseover="changeStyle('my_exercises')" onmouseout="changeStyleBack('my_exercises')">${my_exercises}</a>
                </li>
                <li id="my_nutrition" >
                    <a href="${pageContext.servletContext.contextPath}/controller?command=show_client_nutrition" onmouseover="changeStyle('my_nutrition')" onmouseout="changeStyleBack('my_nutrition')">${my_nutrition}</a>
                </li>
                <li id="buyMembership">
                    <a href="${pageContext.servletContext.contextPath}/controller?command=get_order_page" onmouseover="changeStyle('buyMembership')" onmouseout="changeStyleBack('buyMembership')" >${buy_membership}</a>
                </li>
                <li id="coaches">
                    <a href="${pageContext.servletContext.contextPath}/controller?command=coaches"  onmouseover="changeStyle('coaches')" onmouseout="changeStyleBack('coaches')">${our_coaches}</a>
                </li>
            </c:if>
            <c:if test="${role == 'coach'}">
                <li id="myClients">
                    <a href="${pageContext.servletContext.contextPath}/controller?command=all_coach_clients" onmouseover="changeStyle('myClients')" onmouseout="changeStyleBack('myClients')">${my_clients}</a>
                </li>
                <li id="commentAboutMe">
                    <a href="${pageContext.servletContext.contextPath}/controller?command=show_coach_comments" onmouseover="changeStyle('commentAboutMe')" onmouseout="changeStyleBack('commentAboutMe')">${comments_about_me}</a>
                </li>
            </c:if>
        </ul>
    </nav>
    <h2>${our_gym}</h2>
    <a href="${pageContext.servletContext.contextPath}/controller?command=gym_photos">
        <img src="../images/gym.jpg" o width="250" height="181" style="margin-bottom: -5px;" border="0" alt="Our offices">
    </a>
</aside>