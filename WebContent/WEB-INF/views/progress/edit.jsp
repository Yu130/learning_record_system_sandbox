<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/layout.jsp">
    <c:param name="content">
        <h2>目標学習時間　編集</h2>
        <form method="POST" action="<c:url value='/progress/update' />">
            <label for="monthly_target_time">月間目標学習時間</label><br />
            <input type="number" name="monthly_target_time" value="${user.monthly_target_time}" />
            <br />
            <br />

            <label for="annual_target_time">年間目標学習時間</label><br />
            <input type="number" name="annual_target_time" value="${user.annual_target_time}" />
            <br /><br />
            <button type="submit">更新</button>
        </form>
        <p><a href="<c:url value='/progress/index' />">学習進捗に戻る</a></p>
    </c:param>
</c:import>