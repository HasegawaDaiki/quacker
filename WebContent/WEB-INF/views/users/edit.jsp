<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${user != null}">
                <h2>${user.name}@${user.user_id} さんのユーザ情報　編集ページ</h2>
                <p>(パスワードは変更する場合のみ入力してください)</p>
                <form method="POST" action="<c:url value='/users/update' />">
                    <c:import url="_form.jsp" />
                </form>
                <br /><br />

                <form method="POST" action="<c:url value='/users/destroy' />">
                    <input type="hidden" name="user_id" value="${user.user_id}">
                    <button type="submit">削除</button>
                </form>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした</h2>
            </c:otherwise>
        </c:choose>
    </c:param>
</c:import>