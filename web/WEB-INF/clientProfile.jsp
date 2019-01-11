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
    <link href="../styless/clientProfileStyle.css" rel="stylesheet">
    <script src="../scripts/button.js"></script>

</head>
<body>
<div id="wrapper">
    <jsp:include page="menu.jsp">
        <jsp:param name="pageTopic" value="profile"/>
        <jsp:param name="currentPage" value="profile"/>
    </jsp:include>
        <section>
            <div class = "container">
                <div class="rightcolumn">
                    <div class="card">

                        <jsp:useBean id="user" type="com.epam.fitness.model.Client" scope="request"/>
                        <div class="col-25">
                            <label for="Name">Name</label>
                        </div>
                        <div class="col-75">
                            <input type="text" id="Name" name="Name" value="${user.name}" readonly>
                        </div>


                        <div class="col-25">
                            <label for="SurName">Surname</label>
                        </div>
                        <div class="col-75">
                            <input type="text" id="SurName" name="SurName" value="${user.surname}" readonly>
                        </div>

                        <div class="col-25">
                            <label for="login">Login</label>
                        </div>
                        <div class="col-75">
                            <input type="text" id="login" name="login" value="${user.login}" readonly>
                        </div>

                        <div class="col-25">
                            <label for="coach_name">Coach</label>
                        </div>
                        <div class="col-75">
                            <c:choose>
                                <c:when test="${coach_name!=null}">
                                    <input type="text" id="coach_name" name="coach_name"  value="${coach_name} ${coach_surname}" readonly>
                                </c:when>
                                <c:otherwise>
                                    <input type="text" id="coach_name" name="coach_name"  value="You haven't coach already" readonly>
                                </c:otherwise>
                            </c:choose>
                        </div>

                        <div class="col-25">
                            <label for="visits_number">Visits Number</label>
                        </div>
                        <div class="col-75">
                            <input type="text" id="visits_number" name="visits_number"  value="${user.visitNumber}" readonly>
                        </div>

                        <div class="col-25">
                            <label for="sale">Private sale</label>
                        </div>
                        <div class="col-75">
                            <input type="text" id="sale" name="sale"  value="${user.personalSale}%" readonly>
                        </div>

                        <div class="col-25">
                            <label for="is_corporate">Corporate sale</label>
                        </div>
                        <div class="col-75">
                            <input type="text" id="is_corporate" name="is_corporate"  value="${user.corporateSale}%" readonly>
                        </div>

                        <div class="col-25">
                            <label for="end_date_of_trains">Membership is valid until</label>
                        </div>
                        <c:choose>

                            <c:when test="${end_date_of_trains != null}">
                                <div class="col-75">
                                    <input type="text" id="end_date_of_trains" name="end_date_of_trains"  value="${end_date_of_trains}" readonly>
                                </div>
                            </c:when>

                            <c:otherwise>
                                <div class="col-75">
                                    <input type="text" id="end_date_of_trains" name="end_date_of_trains"  value="You haven't membership yet" readonly>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </section>
</div>

</body>
</html>



