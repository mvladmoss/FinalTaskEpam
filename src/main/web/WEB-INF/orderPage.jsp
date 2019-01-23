<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fitness" uri="fitnessTag" %>

<!doctype html>
<html lang="${sessionScope.language}">
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>Fitness-centre</title>
    <link rel="stylesheet" href="../styless/baseStyles.css" type="text/css">
    <link rel="stylesheet" href="../styless/buyGymMembershipStyles.css" type="text/css">
    <link rel="stylesheet" href="../styless/modalWindows.css" type="text/css">
    <link rel="stylesheet" href="../styless/orderPageStyles.css" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Staatliches" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Noto+Serif+TC" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Oswald:300" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Oswald:300" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lato|Sarabun" rel="stylesheet">
    <script src="../scripts/buttonStyleChanger.js"></script>
    <script src="../scripts/gymCostSetters.js"></script>
    <script src="../scripts/notification.js"></script>
</head>

<body>
<div id="wrapper">
    <jsp:include page="menu.jsp">
        <jsp:param name="pageTopic" value="buy_gym_membership"/>
        <jsp:param name="currentPage" value="get_order_page"/>
    </jsp:include>
    <section>

        <div class = "container">
            <div class="rightcolumn" style="min-height: 270px;">
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
                        <label for="cost">Cost</label>
                    </div>
                    <div class="col-75">
                        <input type="text" id="cost" name="cost" value="0.0$" style="width: 95%;" readonly>
                    </div>

                    <div class="col-25">
                        <label for="personal_discount">Personal discount</label>
                    </div>
                    <div class="col-75">
                        <input type="text" id="personal_discount" style="width: 95%;" name="personal_discount" value="${client_personal_discount}" readonly>
                    </div>

                    <div class="col-25">
                        <label for="corporate_discount">Corporate discount</label>
                    </div>
                    <div class="col-75">
                        <input type="text" id="corporate_discount" style="width: 95%;" name="corporate_discount" value="${client_corporate_discount}" readonly>
                    </div>

                    <div class="col-25">
                        <label for="final_cost">Final cost</label>
                    </div>
                    <div class="col-75">
                        <input type="text" id="final_cost" name="final_cost" style="width: 95%;"  value="0.0$" readonly>
                    </div>
                    <input class="modal__check" type="checkbox" id="modal"/>
                    <div class="modal">
                        <label class="modal__closetwo" for="modal"></label>
                        <div class="modal__info" style="min-height: 160px;max-width: 600px;">
                            <form name="form" action="${pageContext.servletContext.contextPath}/controller?command=update_gym_membership" method="post" style="margin-top: 10px;">
                                <input id="period" name="period" style="display: none;">
                                <label class="modal__close" for="modal">&times;</label>
                                <label for="cardNumber" style="padding-top: 5px;margin-top: 5px;font-size: 14px; font-family: 'Poppins', sans-serif; color: black;"> Card number</label>
                                <input id="cardNumber" name="cardNumber" type="text" style="margin-top: -8px;">
                                <label for="finalCostModalWindow" style="display: table-cell;position: absolute;margin-top: 17px;font-size: 14px; font-family: 'Poppins', sans-serif; color: black;">Final cost</label>
                                <input id="finalCostModalWindow" name="cost" type="text" style="margin-top:10px;margin-left: 98px;" readonly>
                                <input class="button" type="submit" style="position: absolute;margin-top: 60px; margin-left: -530px">
                            </form>
                        </div>
                    </div>
                    <c:choose>
                        <c:when test="${end_date_membership=='true'}">
                            <label onclick="checkSelectOption()" class="buttonSub" >Extend</label>
                            <c:if test="${payment_error == true}">
                                <h2 style="position: absolute;color: red;margin-left: 100px;padding-top: 214px;">Was input incorrect card data.Please try again</h2>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                            <label  onclick="checkSelectOption()" class="buttonSub">Buy</label>
                            <c:if test="${payment_error == true}">
                                <h2 style="position: absolute;color: red;margin-left: 100px;padding-top: 214px;">Was input incorrect card data.Please try again</h2>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </section>
</div>

</body>
</html>

