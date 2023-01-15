<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/layout.jsp">
    <c:param name="content">
    <h2>学習中．．．</h2>
    <script type="text/javascript">
    var start = new Date();
    
    var hour = 0;
    var min = 0;
    var sec = 0;
    var now = 0;
    var datet = 0;
    
    function disp(){
    
        now = new Date();
    
        datet = parseInt((now.getTime() - start.getTime()) / 1000);
    
        hour = parseInt(datet / 3600);
        min = parseInt((datet / 60) % 60);
        sec = datet % 60;
    
        if(hour < 10) { hour = "0" + hour; }
        if(min < 10) { min = "0" + min; }
        if(sec < 10) { sec = "0" + sec; }
    
        var timer1 = hour + ':' + min + ':' + sec;
    
        document.form1.field1.value = timer1; 
    
        setTimeout("disp()", 1000);
    
    }
    </script>
    <body onLoad="disp()">
    <form action="#" name="form1">
    <div id="field1">
    <h2><b><input type="text" name="field1" size="4"></b></h2>
    </div>
    </form>
    <br>
    <form method="POST" action="<c:url value='/measuring/finish' />"autocomplete="off">
        <label for="learned_date">日付：</label>
        <div id="current_date">
         <script>
         date = new Date();
         year = date.getFullYear();
         month = date.getMonth() + 1;
         day = date.getDate();
         document.getElementById("current_date").innerHTML = year + "/" + month + "/" + day;
         </script>
         </div>
         <br /><br />

        <label for="started_at">開始時刻：</label>
        <fmt:formatDate value="${history.started_at}" pattern="HH:mm:ss" />
        <br /><br />
        
        <label for="title">タイトル：</label>
        <c:out value="${history.title}" />
        <br /><br />
        
        <label for="content">内容：</label>
        <c:out value="${history.content}" />
        <br /><br />
        
        <input type="hidden" name="_token" value="${_token}" />
        <button type="submit">終了</button>
    </form>
    </body>
    </c:param>
</c:import>