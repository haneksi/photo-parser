
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>PhotoParser</title>
    <script src="https://code.jquery.com/jquery-1.12.1.min.js"></script>
    <script src="/js/left-menu.js" type="text/javascript"></script>
    <link href="/css/left-menu.css" rel="stylesheet"/>
</head>
<body>
    <div id="left-menu">
        <c:forEach items="${portfolioList}" var="portfolio">

            ${portfolio.url}

            <c:forEach items="${portfolio.albums}" var="album">
                <div id="item-left-menu">
                    ${album.title}
                </div>
            </c:forEach>

        </c:forEach>
    </div>
</body>
</html>
