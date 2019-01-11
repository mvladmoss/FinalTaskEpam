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
    <link href="../styless/buyGymMembershipStyles.css" rel="stylesheet">
    <script src="../scripts/button.js"></script>
    <script src="../scripts/setCosts.js"></script>
    <script src="../scripts/setCommands.js"></script>

</head>
<body>
<div id="wrapper">
    <jsp:include page="menu.jsp">
        <jsp:param name="pageTopic" value="buy_gym_membership"/>
        <jsp:param name="currentPage" value="buy_gym_membership"/>
    </jsp:include>
    <section>
        <div class = "container">

            <div class="rightcolumn">
                <div class="card">
                    <div class="col-25">
                        <label for="period_cost">Select the duration</label>
                    </div>
                    <div class="col-75">
                        <select id="period_cost" onchange="setCosts()" name="period">
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
                        <div  class="findButtonDiv">
                            <c:choose>
                                <c:when test="${end_date_membership=='true'}">
                                    <input type="submit" class="button" value="Extend" id="findButton" style="margin: 10px 15px 5px -10px;">
                                </c:when>
                                <c:otherwise>
                                    <input type="submit" class="buttonSub" value="Buy" id="findButton">
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</div>

</body>
</html>




