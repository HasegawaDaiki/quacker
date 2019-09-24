<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${user != null}">
                <h2><c:out value="${user.name}" />@<c:out value="${user.user_id}" /> さんのフォロワー</h2>
                <c:choose>
                    <c:when test="${followers.size() > 0}">
                        <ul>
                            <c:forEach var="follower" items="${followers}">
                                <li>
                                    <c:out value="${follower.name}" />@<c:out value="${follower.user_id}" />
                                    <c:if test="${follower.id != sessionScope.login_user.id }">
                                        <form method="POST" action="<c:url value='follows/create' />">
                                            <input type="hidden" name="followee_id" value="${follower.user_id}" />
                                            <c:choose>
                                                <c:when test="${following_users.contains(user)}">
                                                    <button type="submit">フォロー解除</button>
                                                </c:when>
                                                <c:otherwise>
                                                    <button type="submit">フォローする</button>
                                                </c:otherwise>
                                            </c:choose>
                                        </form>
                                    </c:if>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:when>
                    <c:otherwise>
                       フォロワーはありません
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <h2>お探しのユーザは見つかりませんでした</h2>
            </c:otherwise>
        </c:choose>
    </c:param>
</c:import>