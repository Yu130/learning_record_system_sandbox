<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/layout.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
        <div id="flush_success">
            <c:out value="${flush}"></c:out>
        </div>
        </c:if>
        <h2>学習時間計測　開始</h2>
        <c:if test="${errors != null}">
            <div id="flush_error">
            入力内容にエラーがあります。<br />
            <c:forEach var="error" items="${errors}">
                ・<c:out value="${error}" /><br />
            </c:forEach>
            </div>
        </c:if>
        
        <form method="POST" action="<c:url value='/measuring/start' />"autocomplete="off">
            <label for="title">タイトル</label><br />
            <input type="text" name="title" autocomplete="off" value="${history.title}" />
            <br /><br />
            
            <label for="content">内容</label><br />
            <textarea name="content" autocomplete="off" rows="10" cols="50">${history.content}</textarea>
            <br /><br />
            
            <input type="hidden" name="_token" value="${_token}" />
            <button type="submit">学習を開始する！</button>
        </form>
       
        <p><a href="<c:url value='/history/index' />">一覧に戻る</a></p>
    </c:param>
</c:import>