<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <h2>クアック 一覧</h2>
        <table>
            <tr>
                <th>ユーザ</th>
                <th>日時</th>
                <th>クアック</th>
            </tr>
            <c:forEach var="quack" items="${quacks}" varStatus="status">
                <tr>
                    <td><c:out value="${quack.user.name}" />@<c:out value="${quack.user.user_id}" /></td>
                    <td><c:out value="${quack.created_at}" /></td>
                    <td><c:out value="${quack.content}" /></td>
                </tr>
            </c:forEach>
        </table>
    </c:param>
</c:import>