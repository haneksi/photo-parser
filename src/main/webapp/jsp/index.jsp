
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>PhotoParser</title>
    <script src="https://code.jquery.com/jquery-1.12.1.min.js"></script>
    <script src=<c:url value="/js/left-menu.js"/> type="text/javascript"></script>
    <link href=<c:url value="/css/left-menu.css"/> rel="stylesheet"/>
</head>
<body>

    <div class="accordion-container">
        <h2>Portfolio Collection</h2>

        <c:forEach items="${portfolioList}" var="portfolio">
            <div class="set">
                <a href="#">
                    <b id="header-portfolio">${portfolio.url} </b>
                    <i class="fa fa-plus"></i>
                </a>
                <div class="content">
                    <c:forEach items="${portfolio.albums}" var="album">
                        <p>${album.title}</p>
                    </c:forEach>
                </div>
            </div>
        </c:forEach>

    </div>

</body>
</html>
