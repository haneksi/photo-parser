

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Album</title>
</head>
<body>

    <c:if test="${!album.images}">
        <div id="content">
            <c:forEach items="${album.images}" var="image">
                <p>${image.url}</p>
            </c:forEach>
        </div>
    </c:if>

</body>
</html>
