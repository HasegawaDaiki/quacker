<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
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
        <h2>ホーム</h2>
        <ul>
            <c:forEach var="quack" items="${quacks}" varStatus="status">
                <li><c:out value="${quack.user.user_id}" />@<c:out value="${quack.user.name}" /> : <c:out value="${quack.created_at}" /> : <c:out value="${quack.content}" /></li>
            </c:forEach>
        </ul>

    </c:param>
</c:import>