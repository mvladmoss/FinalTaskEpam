<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"  language = "java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<h1>Fitness-centre</h1>
<br/>
<c:if test="${sessionScope.user.role eq 'admin'}">
    <a href="${pageContext.servletContext.contextPath}/controller?command=showPrograms">Programs</a>
</c:if>
<a href="${pageContext.servletContext.contextPath}/controller?command=paymentHistory">Payment History</a>