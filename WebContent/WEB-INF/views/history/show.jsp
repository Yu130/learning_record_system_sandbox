<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/layout.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${history != null}">
                <h2>学習履歴　詳細</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>日付</th>
                            <td><fmt:formatDate value="${history.learned_date}" pattern="yyyy-MM-dd" /></td>
                        </tr>
                        <tr>
                            <th>タイトル</th>
                            <td>
                                <pre><c:out value="${history.title}" /></pre>
                            </td>
                        </tr>
                        <tr>
                            <th>学習時間</th>
                            <td>
                                <c:out value="${history.learning_time}" /> 分
                            </td>
                        </tr>
                        <tr>
                            <th>開始時刻</th>
                            <td>
                                <fmt:formatDate value="${history.started_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                        <tr>
                            <th>終了時刻</th>
                            <td>
                                <fmt:formatDate value="${history.finished_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                        <tr>
                            <th>内容</th>
                            <td>
                                <pre><c:out value="${history.content}" /></pre>
                            </td>
                        </tr>
                    </tbody>
                </table>

                <c:if test="${sessionScope.login_user.user_id == history.user.user_id}">
                    <p><a href="<c:url value="/history/edit?id=${history.id}" />">この学習履歴を編集する</a></p>
                </c:if>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value="/history/index" />">一覧に戻る</a></p>
    </c:param>
</c:import>