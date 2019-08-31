<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>ユーザ 一覧</h2>
        <ul>
            <c:forEach var="user" items="${users}">
                <li>
                    <c:out value="${user.name}" />@<c:out value="${user.user_id}" />
                    <c:choose>
                        <c:when test="${user.delete_flag == 1}">
                            (削除済み)
                        </c:when>
                    </c:choose>
                </li>
            </c:forEach>
            <li>
        </ul>

        <div id="pagination">
            （全 ${users_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((users_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/users/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </c:param>
</c:import>