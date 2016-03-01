<%@tag description="Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@attribute name="leftmenu" fragment="true" %>
<%@attribute name="content" fragment="true" %>

<html>
    <head>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
        <title>PhotoParser</title>
        <table>
            <tr>
                <td id="table-header">
                    <jsp:invoke fragment="header"/>
                </td>
            </tr>
        </table>
    </head>
    <body>
        <table id="table-body">
        <tr>
            <td id="table-body-leftmenu">
                <jsp:invoke fragment="leftmenu"/>
            </td>
            <td id="table-body-content">
                <jsp:invoke fragment="content"/>
            </td>
        </tr>
        <tr>
            <td id="table-body-footer">
                <jsp:invoke fragment="footer"/>
            </td>
        </tr>
        </table>
    </body>
</html>