<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        <c:out value="${error}" /><br />
    </div>
</c:if>
<label for="content">内容</label><br />
<textarea name="content" rows="10" cols="50">${quack.content}</textarea>
<br /><br />

<button type="submit">投稿</button>