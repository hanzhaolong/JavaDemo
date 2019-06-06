<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>

</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    </ul>
    </div>
    
    <div class="mainindex">
    
    
    <div class="welinfo">
    <span><img src="images/sun.png" alt="天气" /></span>
    <b>您好，欢迎使用软件学院教育网后台系统</b>
    <a href="/college_website/manager?oper=getalluser" target="rightFrame">用户管理</a>
    </div>
    
    <div class="xline"></div>
    
    <ul class="iconlist">
    
    <li><img src="images/ico01.png" /><p><a href="/college_website/manager?oper=getalluser" target="rightFrame">用户管理</a></p></li>
    <li><img src="images/ico02.png" /><p><a href="../article/addart.jsp" target="rightFrame">发布文章</a></p></li>
    <li><img src="images/ico04.png" /><p><a href="/college_website/manager?oper=getallart" target="rightFrame">文章管理</a></p></li>
    <li><img src="images/ico05.png" /><p><a href="/college_website/manager?oper=getallcom" target="rightFrame">评论管理</a></p></li>
    <li><img src="images/ico06.png" /><p><a href="/college_website/article/searchart.jsp" target="rightFrame">查询文章</a></p></li> 
            
    </ul>
  
    <div class="xline"></div>

   
</body>

</html>
