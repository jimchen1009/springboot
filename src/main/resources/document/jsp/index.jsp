<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<body>
<h2>Hi, I'm Jim.</h2>
<%
    out.println("["+request.getRemoteAddr()+"]" + "<br/>session id:" + session.getId());
%> 
<br/>
</body>
</html>
