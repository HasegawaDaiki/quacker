<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>Insert title here</title>
        <link rel="stylesheet" href="<c:url value='/css/style.css' />">
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h1>Quacker</h1>
                <c:if test="${sessionScope.login_user != null}">
                    <a href="<c:url value='/quacks/new' />">クアック！</a>&nbsp;
                </c:if>
            </div>
            <c:if test="${sessionScope.login_user != null}">
                <div id="user_name">
                    <c:out value="${sessionScope.login_user.name}" />@<c:out value="${sessionScope.login_user.user_id}" />
                </div>
            </c:if>
            <div id="content">
                ${param.content}
            </div>
            <div id="footer">
                by Daiki Hasegawa.
            </div>
        </div>
    </body>
</html>