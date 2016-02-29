
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<div class="accordion-container">
    <h2>Portfolio Collection</h2>

    <c:forEach items="${portfolioList}" var="portfolio">
        <div class="set">
            <a href="#">
                <b class="header-portfolio">${portfolio.url} </b>
                <i class="fa fa-plus"></i>
            </a>
            <div class="content">
                <c:forEach items="${portfolio.albums}" var="album">
                    <p id ="album-title-accordion">
                        <c:url var="albumUrl" value="/album">
                            <c:param name="id" value="${album.id}"/>
                        </c:url>
                        <a href="${albumUrl}">${album.title}</a>
                    </p>

                </c:forEach>
            </div>
        </div>
    </c:forEach>

</div>
</body>
