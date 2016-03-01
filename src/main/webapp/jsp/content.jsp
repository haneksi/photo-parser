

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:if test="${not empty album}">
    <section id="gallary" >
            <c:forEach items="${album.images}" var="image">
                <img src="${image.url}" />
            </c:forEach>
    </section>
</c:if>

