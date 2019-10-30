<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<body>
<h2>Hi, I'm Jim. the image is from nginx.</h2>
<%
    out.println("["+request.getRemoteAddr()+"]" + "<br/>session id:" + session.getId());
%> 
<br/>
<img alt="图片" src="images/book.jpg" style="width:300px;height:300px">
</body>
</html>
