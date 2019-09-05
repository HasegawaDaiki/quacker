<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>
<label for="user_id">ユーザID</label><br />
<input type="text" name="user_id" value="${user.user_id}" />
<br /><br />

<label for="name">ユーザ名</label><br />
<input type="text" name="name" value="${user.name}" />
<br /><br />

<label for="email">メールアドレス</label><br />
<input type="email" name="email" value="${user.email}">
<br /><br />

<label for="password">パスワード</label><br />
<input type="password" name="password" />
<br /><br />

<input type="hidden" name="previous_user_id" value="${user.user_id}" />
<button type="submit">完了</button>