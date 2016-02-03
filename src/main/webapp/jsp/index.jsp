
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>PhotoParser</title>
</head>
<body>
<div class = "photo" align="center" style="color: black">
    <c:forEach var="image" items="${images}" varStatus="varCount" end="20">
        <img src="${image.url}" height="200" />
        <%--<c:if test="${varCount.count % 5 == 0}">--%>
            <%--<br/>--%>
        <%--</c:if>--%>
    </c:forEach>
</div>


</body>
</html>
