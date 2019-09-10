<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${user != null}">
                <c:if test="${flush != null}">
                    <div class="flush_success">
                        ${flush}
                    </div>
                </c:if>

                <h2>${user.name}@${user.user_id}さんのページ</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>ユーザID</th>
                            <td><c:out value="${user.user_id}" /></td>
                        </tr>
                        <tr>
                            <th>ユーザ名</th>
                            <td><c:out value="${user.name}" /></td>
                        </tr>
                        <tr>
                            <th>登録日時</th>
                            <td>
                                <fmt:formatDate value="${user.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td>
                                <fmt:formatDate value="${user.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                    </tbody>
                </table>

                <table>
                    <tr>
                        <th>ユーザ</th>
                        <th>日時</th>
                        <th>クアック</th>
                        <th>表示</th>
                    </tr>
                <c:forEach var="quack" items="${quacks}" varStatus="status">
                    <c:if test="${quack.delete_flag == 0}">
                        <tr>
                            <td><c:out value="${quack.user.name}" />@<c:out value="${quack.user.user_id}" /></td>
                            <td><c:out value="${quack.created_at}" /></td>
                            <td><c:out value="${quack.content}" /></td>
                            <td><a href="<c:url value='/quacks/show?id=${quack.id}' />">詳細</a></td>
                        </tr>
                    </c:if>
                </c:forEach>
                </table>

                <c:if test="${sessionScope.login_user.id == quack.user.id}">
                    <p><a href="<c:url value='/quacks/delete' />">削除</a></p>
                </c:if>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

    </c:param>
</c:import>