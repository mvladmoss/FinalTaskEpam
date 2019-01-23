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
    <link href="https://fonts.googleapis.com/css?family=Oswald:300" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet">
    <script src="../../scripts/buttonStyleChanger.js"></script>
    <script src="../../scripts/gymPeriodSetters.js"></script>
    <script src="../../scripts/ajax/ajaxConnections.js"></script>
    <script src="../../scripts/ajax/ajaxRequest.js"></script>
    <script src="../../scripts/validation/exerciseValidation.js"></script>
</head>
<body>
<div id="wrapper">
    <jsp:include page="../menu.jsp">
        <jsp:param name="pageTopic" value="exercises"/>
        <jsp:param name="currentPage" value="show_client_exercises"/>
    </jsp:include>
    <section>
        <div class="container">
            <div class="rightcolumn">
                <c:choose>
                    <c:when test="${is_membership_valid == false}">
                        <div class="flex-container">
                            <div class="flex-item" style="color:#516b9e;width:500px;">
                                <h3>You can not choose exercises until you don't buy membership</h3>
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
                        <jsp:useBean id="program" type="com.epam.fitness.model.Program" scope="request"/>
                        <input id="programIdBlock" value="${program.id}" style="display: none;">
                        <div class="tabs">
                            <c:forEach var = "i" begin = "1" end = "${program.trainsPerWeek}">
                                <input class="tabs__tab" type="radio" id="tabs__tab${i}" onclick="setCurrentTrainDayInForm('${i}')" name="tabstab" style="display: none;" checked="checked"/>
                                <label class="tabs__title" for="tabs__tab${i}">Day <c:out value="${i}"/></label>
                                <div class="tabs__text" style="overflow-y: scroll;max-height: 340px;">
                                    <ul>
                                        <c:forEach items="${exercises}" var="exerciseDto">
                                            <c:if test="${exerciseDto.numberTrainDay==i}">
                                                <div class="flex-container">
                                                    <div class="flex-item flex-text">
                                                        <c:out value="${exerciseDto.exercise.name}(${exerciseDto.setNumber},${exerciseDto.repeatNumber})"/>
                                                    </div>
                                                    <div class="flex-item ">
                                                        <input class="modal__check" type="checkbox" id="modal${exerciseDto.id}"/>
                                                        <div class="modal">
                                                            <label class="modal__closetwo" for="modal${exerciseDto.id}"></label>
                                                            <div class="modal__info">
                                                                <label class="modal__close" for="modal${exerciseDto.id}">&times;</label>
                                                                <h2 class="edit" >Edit</h2>
                                                                <form action="${pageContext.request.contextPath}/controller?command=update_exercise" method="post">
                                                                    <div class="col-25" style="margin-left: -11px;">
                                                                        <label for="setNumber">Number of set</label>
                                                                    </div>
                                                                    <div class="col-75">
                                                                        <input type="text" id="setNumber" style="width: 101%;" name="setNumber">
                                                                    </div>
                                                                    <div class="col-25" style="margin-left: -31px;">
                                                                        <label for="repeats" style="margin-left: 10px;margin-top: 5px;">Repeats</label>
                                                                    </div>
                                                                    <div class="col-75">
                                                                        <input type="text" style="width: 101%; margin-left: 20px;;" id="repeats" name="repeats">
                                                                    </div>
                                                                    <input id="exerciseDtoId" name="exerciseDtoId" value="${exerciseDto.id}" style="display: none">
                                                                    <input type="submit" class="buttonSub" value="Update" id="update" style="margin-top: 10px;">
                                                                </form>
                                                            </div>
                                                        </div>
                                                        <label for="modal${exerciseDto.id}"  style="width: 60px;
                                                    height: 22px;padding-top: 10px;"><img class="update" src="../../images/update.png" height="40px" width="40px" border="0"></label>
                                                    </div>
                                                    <div class="flex-item delete">
                                                        <p>
                                                            <form action="${pageContext.request.contextPath}/controller?command=delete_exercise" method="post">
                                                                <input id="exerciseDtoId" name="exerciseDtoId" value="${exerciseDto.id}" style="display: none;">
                                                                <button type="submit" style="display: contents;"><img src="../../images/delete.jpg" width="40" height="40" alt="Удалить" style="display: inline"></button>
                                                            </form>
                                                        </p>
                                                    </div>
                                                </div>
                                            </c:if>
                                        </c:forEach>
                                    </ul>
                                </div>

                            </c:forEach>
                        </div>
                        <input class="modal__check" type="checkbox" id="modal"/>
                        <div class="modal">
                            <label class="modal__closetwo" for="modal"></label>
                            <div class="modal__info" style="width: 80%;max-width: 900px;max-height: 1500px;min-height: 500px">
                                <label class="modal__close" for="modal">&times;</label>
                                <input type="search"  oninput="makeRequest()" placeholder="Enter the name of exercise" name="searchType" id="searchType" style="width: 40%;border-radius: 4px 4px 4px 4px;height: 30px;font: 20px 'Oswald', sans-serif;">
                                <hr style="margin-left: -20px;width: 110%;">
                                <form name="form" class="beatForm" action="${pageContext.request.contextPath}/controller?command=add_exercise" method="post">
                                    <input id="trainDay" name="trainDay" value="${program.trainsPerWeek}" style="display: none;">
                                    <div id="reg" >
                                        <div class="flex-container-iter" id="flex-container">
                                            <div class="flex-item" id="flex-item" >
                                                <h2 id="exerciseName" style="background: #29c5e6;font: 13px 'Oswald', sans-serif;color: #fff;padding: 10px;margin: 20px 0 0 0;border-radius: 15px 15px 0 0;width: 190px;min-height: 40px;"></h2>
                                                <p>
                                                    <img id="img" width="250" height="181" style="margin-top:-20px;width: 210px;height: 150px;" alt="Our offices">
                                                </p>
                                                <div class="flex-container" style="margin-top: -30px;">
                                                    <div class="flex-item">
                                                        <input class="modal__check" type="checkbox" id="modal{exercise.id}"/>
                                                        <div class="modal">
                                                            <label class="modal__closetwo" for="modal{exercise.id}"></label>
                                                            <div class="modal__info">
                                                                <label class="modal__close" for="modal{exercise.id}">&times;</label>
                                                                <h2 class="edit">Description</h2>
                                                                <h3 id="description" style="color: black;text-align: left;font-weight: normal;"></h3>
                                                            </div>
                                                        </div>
                                                        <label for="modal{exercise.id}"  style="width: 60px;
                                                    height: 22px;padding-top: 10px;"><img class="update" src="../../images/description.jpg" height="40px" width="40px" style="margin-left: 50px;height: 50px;width: 40px;" border="0"></label>

                                                    </div>
                                                    <div class="flex-item" style="margin-left: 20px;">
                                                        <input class="modal__check" type="checkbox" id="modalChoose{exercise.id}"/>
                                                        <div class="modal">
                                                            <label class="modal__closetwo" for="modalChoose{exercise.id}"></label>
                                                            <div class="modal__info" style="width: 1000px;height: 140px;padding-top: 40px;">
                                                                <label class="modal__close" for="modalChoose{exercise.id}">&times;</label>
                                                                <div class="exercise">
                                                                    <h2 style="text-align: center; margin-top: -35px;color: black;"> Add Exercise</h2>
                                                                    <div class="col-25">
                                                                        <label for="setNumber" style="margin-top: 5px;margin-left: -140px;">Sets</label>
                                                                    </div>
                                                                    <div class="col-75">
                                                                        <input  id="setNumber{exercise.id}" style="margin-left: -120px;width: 618px;" title="1-2 number"   type="text">
                                                                    </div>

                                                                    <div class="col-25">
                                                                        <label for="setNumber" style="margin-top: 5px;margin-left: -110px;">Repeats</label>
                                                                    </div>
                                                                    <div class="col-75">
                                                                        <input  id="repeats{exercise.id}"  style="margin-left: -120px;width: 618px;"   type="text">
                                                                    </div>
                                                                </div>
                                                                <input type="submit" class="buttonSub" onclick="setExerciseProgram('{exercise.id}',${program.id})" title="1-2 number" style="margin-top: 10px;" value="Add">
                                                            </div>
                                                        </div>
                                                        <label for="modalChoose{exercise.id}"  style="width: 60px;
                                                    height: 22px;padding-top: 10px;"><img class="update" src="../../images/choose.jpg" height="40px" width="40px" style="margin-left: 8px;height: 50px;width: 40px;" border="0"></label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <br/>
                                </form>
                            </div>
                        </div>
                        <label for="modal" class="buttonSub" onclick="makeRequest()" style="color: white;text-align: center;margin-top: 10px;padding-top: 15px;width: 80px;height: 30px;font: 14px Tahoma, sans-serif;background: #29c5e6">Add exercise</label>

                    </c:otherwise>
                </c:choose>
                </div>

        </div>
    </section>
</div>

</body>
</html>



