

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>${album.title}</title>
</head>
<body>
<%--images--%>
<c:if test="${album != null}">
    <div id="content" >
        <ul>
            <c:forEach items="${album.images}" var="image">
                <li><img src="${image.url}"/></li>
            </c:forEach>
        </ul>>
    </div>
</c:if>

</body>
</html>
