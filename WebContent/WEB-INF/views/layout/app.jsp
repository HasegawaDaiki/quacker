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
                <h1><a href="<c:url value='/home' />">Quacker</a></h1>&nbsp;&nbsp;&nbsp;
                <c:if test="${sessionScope.login_user != null}">
                    <div id="quack">
                        <a href="<c:url value='/quacks/new' />">クアック!</a>&nbsp;&nbsp;
                    </div>
                    <div id="user_name">
                        <c:out value="${sessionScope.login_user.name}" />@<c:out value="${sessionScope.login_user.user_id}" />
                    </div>
                    <div id="logout">
                        <a href="<c:url value='/logout' />">ログアウト</a>
                    </div>
                </c:if>
            </div>
            <div id="content">
                ${param.content}
            </div>
            <div id="footer">
                by Daiki Hasegawa.
            </div>
        </div>
    </body>
</html>