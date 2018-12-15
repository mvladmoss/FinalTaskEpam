<%--
  Created by IntelliJ IDEA.
  User: mvlad
  Date: 05.12.2018
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="${sessionScope.language}">
<head>
    <link rel="stylesheet" type="text/css" href="../styless/userProfileStyle.css">
    <script type="text/javascript" language="javascript" src="../scripts/setCost1.js"></script>

    <meta charset="UTF-8">
    <title>ENAUCT</title>
</head>
<body>

<header>
    <a href="controller?command=main" style="float:left">HOME</a>
    <a href="controller?command=language&currentPage=profile&language=${sessionScope.nextLanguage}" style="float:right">${sessionScope.language}</a>
    <a href="controller?command=sign_out">SIGN OUT</a>
    <a href="controller?command=profile">PROFILE</a>
</header>


<div class = "container">

    <div class="rightcolumn">
        <div class="card">
            <div class="col-25">
                <label for="period_cost">Select the duration</label>
            </div>
            <div class="col-75">
                <select id="period_cost" onchange="setCost1()" name="period">
                    <option value="Choose tariff"></option>
                    <c:forEach items="${tariffs}" var="tariff">
                        <option value="${tariff.value}">${tariff.key}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="col-25">
                <label for="cost" voc>Cost</label>
            </div>
            <div class="col-75">
                <input type="text" id="cost" name="cost" value="0.0$" readonly>
            </div>

            <div class="col-25">
                <label for="personal_sale">Personal sale</label>
            </div>
            <div class="col-75">
                <input type="text" id="personal_sale" name="personal_sale" value="${client_personal_sale}" readonly>
            </div>

            <div class="col-25">
                <label for="corporate_sale">Corporate sale</label>
            </div>
            <div class="col-75">
                <input type="text" id="corporate_sale" name="corporate_sale" value="${client_corporate_sale}" readonly>
            </div>

            <div class="col-25">
                <label for="final_cost">Final cost</label>
            </div>
            <div class="col-75">
                <input type="text" id="final_cost" name="final_cost"  value="0.0$" readonly>
            </div>

            <form name="form" action="${pageContext.servletContext.contextPath}/controller?command=update_gym_membership" method="post">
                <div name="lol" class="findButtonDiv">
                    <c:choose>
                        <c:when test="${end_date_membership=='true'}">
                            <h3>You have a valid membership</h3>
                            <input type="submit" value="Extend" id="findButton">
                        </c:when>
                        <c:otherwise>
                            <input type="submit" value="Buy" id="findButton">
                        </c:otherwise>
                    </c:choose>
                </div>
            </form>
        </div>
    </div>
</div>

<footer>
    <h4>${contact_us}: enauct@gmail.com</h4>
</footer>

</body>
</html>
