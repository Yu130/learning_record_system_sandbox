<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>
<label for="learned_date">日付</label><br />
<input type="date" name="learned_date" value="<fmt:formatDate value='${history.learned_date}' pattern='yyyy-MM-dd' />" />
<br /><br />

<label for="title">タイトル</label><br />
<input type="text" name="title" value="${history.title}" />
<br /><br />

<label for="title">学習時間</label><br />
<input type="text" name="learning_time" value="${history.learning_time}" />
<br /><br />

<label for="started_at">開始時刻</label><br />
<input type="datetime" name="started_at" value="<fmt:formatDate value='${history.started_at}' pattern='yyyy-MM-dd hh:mm' />" />
<br /><br />

<label for="finished_date">終了時刻</label><br />
<input type="datetime" name="finished_at" value="<fmt:formatDate value='${history.started_at}' pattern='yyyy-MM-dd hh:mm' />" />
<br /><br />

<label for="content">内容</label><br />
<textarea name="content" rows="10" cols="50">${history.content}</textarea>
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">登録</button>