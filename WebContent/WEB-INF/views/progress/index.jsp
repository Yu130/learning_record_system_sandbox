<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/layout.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2><c:out value="${sessionScope.login_user.name}" />&nbsp;さんの学習進捗</h2>
                <table>
                    <tbody>
                        <tr>
                            <th>月間目標学習時間</th>
                            <td>
                            <c:out value="${monthly_target_time}" /> 時間
                            </td>
                        </tr>
                        <tr>
                            <th>年間目標学習時間</th>
                            <td>
                            <c:out value="${annual_target_time}" /> 時間
                            </td>
                        </tr>
                    </tbody>
                </table>
                    <p><a href="<c:url value="/progress/edit" />">　目標学習時間を編集する</a></p>
                    <br />
                    <br />
                <table>
                    <tbody>
                        <tr>
                            <th><c:out value="${ym_j}"/>　月間累計学習時間</th>
                            <td>
                            <c:out value="${monthly_sum_h}" /> 時間
                            </td>
                        </tr>
                        <tr>
                            <th>月間学習時間 進捗率</th>
                            <td>
                            <c:out value="${monthly_progress_rate}" /> %
                            </td>
                        </tr>
                    </tbody>
                </table>
                <br />
                                <table>
                    <tbody>
                        <tr>
                            <th><c:out value="${year}"/>年　年間累計学習時間</th>
                            <td>
                            <c:out value="${annual_sum_h}" /> 時間
                            </td>
                        </tr>
                        <tr>
                            <th>年間学習時間 進捗率</th>
                            <td>
                            <c:out value="${annual_progress_rate}" /> %
                            </td>
                        </tr>
                    </tbody>
                </table>
                <br />
    </c:param>
</c:import>