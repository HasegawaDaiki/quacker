<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}" />
            </div>
        </c:if>
        <c:if test="${error != null}">
            <div id="flush_error">
                <c:out value="${error}" />
            </div>
        </c:if>

        <h2>ログイン</h2>
        <form method="POST" action="<c:url value='/login' />">
            <label for="user_id">ユーザID</label><br />
            <input type="text" name="user_id" value="${user_id}" />
            <br /><br />

            <label for="password">パスワード</label><br />
            <input type="password" name="password" />
            <br /><br />

            <button type="submit">ログイン</button>
        </form>

        <p><a href="<c:url value='/users/new' />">新規ユーザ登録</a></p>
    </c:param>
</c:import>