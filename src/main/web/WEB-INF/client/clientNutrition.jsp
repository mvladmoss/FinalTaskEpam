<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="${sessionScope.language}">
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>Whitesquare</title>
    <link rel="stylesheet" href="../../styless/baseStyles.css" type="text/css">
    <link rel="stylesheet" href="../../styless/client/clientProfileStyles.css" type="text/css">
    <link rel="stylesheet" href="../../styless/client/clientExerciseStyle.css" type="text/css">
    <link rel="stylesheet" href="../../styless/modalWindows.css" type="text/css">
    <link rel="stylesheet" href="../../styless/client/clientNutritionStyles.css" type="text/css">
    <link rel="stylesheet" href="../../styless/buyGymMembershipStyles.css" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Staatliches" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Noto+Serif+TC" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Oswald:300" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Oswald:300" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet">
    <script src="../../scripts/nutritionArgumentsSetter.js"></script>

<body>
<div id="wrapper">
    <jsp:include page="../menu.jsp">
        <jsp:param name="pageTopic" value="nutrition"/>
        <jsp:param name="currentPage" value="show_client_nutrition"/>
    </jsp:include>
    <section>
        <div class="container">
            <div class="rightcolumn">
                <c:choose>
                    <c:when test="${is_membership_valid == false}" >
                        <div class="flex-container">
                            <div class="flex-item" style="color:#516b9e;width:500px;">
                                <h3>You can not make a nutrition until you don't buy membership</h3>
                            </div>
                            <div class="flex-item" style="margin-top: -10px; margin-left: -15px;">
                                <form action="${pageContext.servletContext.contextPath}/controller?command=get_order_page" method="post">
                                    <input type="submit" class="button" style="color: white; text-align: center; margin: 10px 15px 5px -10px;width: 80px; height: 50px; font: 14px Tahoma, sans-serif; background: #29c5e6;
" value="Buy">
                                </form>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <jsp:useBean id="nutrition" class="com.epam.fitness.model.Nutrition" scope="request"/>
                        <div class="tabs">
                            <input class="tabs__tab" type="radio" id="tabs__tab1" onclick="setArguments('morning')" name="tabstab" style="display: none;" checked="checked"/>
                            <label class="tabs__title" for="tabs__tab1">Morning nutrition</label>
                            <div class="tabs__text" style="font-family: 'Poppins', sans-serif;color: #000;font-size: 15px;">
                                <pre id="morning" style="font-family: 'Poppins', sans-serif;color: #000;font-size: 15px;">${nutrition.morningNutrition}</pre>
                            </div>

                            <input class="tabs__tab" type="radio" id="tabs__tab2" onclick="setArguments('lunch')" name="tabstab" style="display: none;" checked="checked"/>
                            <label class="tabs__title" for="tabs__tab2">Lunch nutrition</label>
                            <div class="tabs__text" style="font-family: 'Poppins', sans-serif;color: #000;font-size: 15px;">
                                <pre  id="lunch" style="font-family: 'Poppins', sans-serif;color: #000;font-size: 15px;">${nutrition.lunchNutrition}</pre>

                            </div>

                            <input class="tabs__tab" type="radio" id="tabs__tab3" onclick="setArguments('dinner')" name="tabstab" style="display: none;" checked="checked"/>
                            <label class="tabs__title" for="tabs__tab3">Dinner nutrition</label>
                            <div class="tabs__text" style="font-family: 'Poppins', sans-serif;color: #000;font-size: 15px;">
                                <pre id="dinner" style="font-family: 'Poppins', sans-serif;color: #000;font-size: 15px;">${nutrition.dinnerNutrition}</pre>
                            </div>

                        </div>
                        <input class="modal__check" type="checkbox" id="modal"/>
                        <div class="modal">
                            <label class="modal__closetwo" for="modal"></label>
                            <div class="modal__info">
                                <label class="modal__close" for="modal">&times;</label>
                                <form name="form" action="${pageContext.request.contextPath}/controller?command=update_nutrition" method="post">
                                    <input id="nutrition_id" name="nutrition_id" value="${nutrition.id}" style="display: none">
                                    <input id="nutrition_time" name="nutrition_time" value="dinner" style="display: none">
                                    <textarea id="nutrition_description" name="nutrition_description" class="textArea">${nutrition.dinnerNutrition}</textarea>
                                    <input class="button" type="submit" value="Save">
                                    <h3 style="position: absolute;margin-top: -30px;margin-left: 120px;">Maximum number of characters ${max_number_symbols_attribute}</h3>
                                </form>
                            </div>
                        </div>
                        <label for="modal" class="buttonSub " style="color: white;text-align: center;margin-top: 10px;padding-top: 15px;width: 80px;height: 30px;font: 14px Tahoma, sans-serif;background: #29c5e6">Change</label>
                    </c:otherwise>
                </c:choose>
            </div>

        </div>
    </section>
</div>
<c:if test="${incorrect_input_data_error eq true}">
    <script>notifyAboutInvalidData('Incorrect exercise data was input')</script>
</c:if>
</body>
</html>

