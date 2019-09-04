<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${quack != null && quack.delete_flag == 0}">
                <h2>クアック詳細</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>ユーザ</th>
                            <td><c:out value="${quack.user.name}" />@<c:out value="${quack.user.user_id}" /></td>
                        </tr>
                        <tr>
                            <th>登録日時</th>
                            <td>
                                <fmt:formatDate value="${quack.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                        <tr>
                            <th>クアック</th>
                            <td>
                                <pre><c:out value="${quack.content}" /></pre>
                            </td>
                        </tr>
                    </tbody>
                </table>

                <c:if test="${sessionScope.login_user.id == quack.user.id}">
                    <form method="POST" action="<c:url value='/quacks/destroy' />">
                        <input type="hidden" name="quack_id" value="${quack.id}" />
                        <button type="submit">クアックを削除</button>
                    </form>
                </c:if>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>
    </c:param>
</c:import>