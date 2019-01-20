<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fitness" uri="fitnessTag" %>,

<!doctype html>
<html lang="${sessionScope.language}">
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>Whitesquare</title>
    <link rel="stylesheet" href="../styless/baseStyles.css" type="text/css">
    <link rel="stylesheet" href="../styless/modalWindows.css" type="text/css">
    <link rel="stylesheet" href="../styless/orderPageStyle.css" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Staatliches" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Noto+Serif+TC" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Oswald:300" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Oswald:300" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet">
    <link href="../styless/buyGymMembershipStyles.css" rel="stylesheet">
    <script src="../scripts/buttonStyleChanger.js"></script>
    <script src="../scripts/gymCostSetter.js"></script>

</head>
<body>
<div id="wrapper">
    <jsp:include page="menu.jsp">
        <jsp:param name="pageTopic" value="buy_gym_membership"/>
        <jsp:param name="currentPage" value="get_order_page"/>
    </jsp:include>
    <section>
        <div class = "container">

            <div class="rightcolumn" style="max-height: 260px;">
                <div class="card">
                    <div class="col-25">
                        <label for="period_cost">Select the duration</label>
                    </div>
                    <div class="col-75">
                        <select id="period_cost" onchange="setCost()" name="period">
                            <fitness:tariffs/>
                        </select>
                    </div>

                    <div class="col-25">
                        <label for="cost" voc>Cost</label>
                    </div>
                    <div class="col-75">
                        <input type="text" id="cost" name="cost" value="0.0$" readonly>
                    </div>

                    <div class="col-25">
                        <label for="personal_discount">Personal discount</label>
                    </div>
                    <div class="col-75">
                        <input type="text" id="personal_discount" name="personal_discount" value="${client_personal_discount}" readonly>
                    </div>

                    <div class="col-25">
                        <label for="corporate_discount">Corporate discount</label>
                    </div>
                    <div class="col-75">
                        <input type="text" id="corporate_discount" name="corporate_discount" value="${client_corporate_discount}" readonly>
                    </div>

                    <div class="col-25">
                        <label for="final_cost">Final cost</label>
                    </div>
                    <div class="col-75">
                        <input type="text" id="final_cost" name="final_cost"  value="0.0$" readonly>
                    </div>
                    <input class="modal__check" type="checkbox" id="modal"/>
                    <div class="modal">
                        <label class="modal__closetwo" for="modal"></label>
                        <div class="modal__info">
                            <form name="form" action="${pageContext.servletContext.contextPath}/controller?command=update_gym_membership&period=" method="post">
                                <label class="modal__close" for="modal">&times;</label>
                                <label for="cardNumber"> Card number</label>
                                <input id="cardNumber" type="text">
                                <label for="finalCostModalWindow">Final cost</label>
                                <input id="finalCostModalWindow">
                                <input class="button" type="submit">
                            </form>
                        </div>
                    </div>
                    <c:choose>
                        <c:when test="${end_date_membership=='true'}">
                            <label for="modal" class="buttonSub" >Extend</label>
                        </c:when>
                        <c:otherwise>
                            <label for="modal" class="buttonSub" >Buy</label>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </section>
</div>

</body>
</html>



<input type="submit" class="button" value="Extend" id="findButton" style="color: white; text-align: center; margin: 10px 15px 5px -10px;width: 80px; height: 50px; font: 14px Tahoma, sans-serif; background: #29c5e6;
