

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Album</title>
</head>
<body>

        <div id="content">
            <ul>
            <c:forEach items="${album.images}" var="image">
                <li><p>${image.url}</p></li>
            </c:forEach>
            </ul>>
        </div>

</body>
</html>
