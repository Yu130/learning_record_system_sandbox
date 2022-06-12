<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/layout.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>学習履歴 一覧</h2>
        <table id="history_list">
            <tbody>
                <tr>
                    <th>日付</th>
                    <th>タイトル</th>
                    <th>学習時間</th>
                    <th>詳細</th>
                </tr>
                <c:forEach var="history" items="${history}" varStatus="status">
                    <tr class="row${status.count % 4}">
                        <td><c:out value="${history.learned_date}" /></td>
                        <td><c:out value="${history.title}" /></td>
                        <td><c:out value="${history.learning_time}" /></td>
                        <td><a href="<c:url value='/history/show?id=${user_id}' />">詳細を表示</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${history_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((history_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/history/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='/history/new' />">手動登録</a></p>

    </c:param>
</c:import>