
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

            <h3>${portfolio.url}</h3>

                <div id="items-left-menu">
                    <c:forEach items="${portfolio.albums}" var="album">
                        ${album.title}
                    </c:forEach>
                </div>

        </c:forEach>
    </div>
</body>
</html>
