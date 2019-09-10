<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${user != null}">
                <h2><c:out value="${user.name}" />@<c:out value="${user.user_id}" /> さんのフォロー</h2>
                <c:choose>
                    <c:when test="${followees.size() > 0}">
                        <ul>
                            <c:forEach var="followee" items="${followees}">
                                <li>
                                    <c:out value="${followee.name}" />@<c:out value="${followee.user_id}" />
                                    <c:if test="${user.id != sessionScope.login_user.id && followee.id != sessionScope.login_user.id}">
                                        <form method="POST" action="<c:url value='follows/create' />">
                                            <input type="hidden" name="followee_id" value="${followee.user_id}" />
                                            <button type="submit">フォローする</button>
                                        </form>
                                    </c:if>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        フォローはありません
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <h2>お探しのユーザは見つかりませんでした</h2>
            </c:otherwise>
        </c:choose>
    </c:param>
</c:import>