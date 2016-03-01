<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<t:page-template>
  <jsp:attribute name="header">
      <script src=<c:url value="/js/left-menu.js"/> type="text/javascript"></script>
      <link href=<c:url value="/css/style.css"/> rel="stylesheet"/>

  </jsp:attribute>

    <jsp:attribute name="leftmenu">
        <c:import url="left-menu.jsp"/>
    </jsp:attribute>

  <jsp:attribute name="content">
      <c:import url="content.jsp"/>
  </jsp:attribute>

    <jsp:attribute name="footer">
        <script src=<c:url value="/js/scripts.js"/> type="text/javascript"></script>
    </jsp:attribute>
</t:page-template>


