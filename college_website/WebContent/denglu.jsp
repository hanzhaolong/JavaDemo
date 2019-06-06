<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title></title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link href="Static/layui/css/layui.css" rel="stylesheet" />
<link href="Content/login.css" rel="stylesheet" />
<script src="Scripts/jquery-1.12.4.min.js"></script>
<script src="Scripts/app/jparticle.jquery.js"></script>
<script src="Scripts/app/compatible.js"></script>
</head>
<body>

	<div class="bg"></div>

	<div class="login">
		<div class="login-tit">
			<strong>软件学院教育网</strong> <em>Log In </em>
		</div>
		<% //登录校验
			Object obj = request.getAttribute("flag");
			if(obj!=null){
				%>	
		<div style="text-align: center">
			<span style="color: red; font-weight: bold">用户名或密码错误</span>
		</div>
		<%} %>
		<div class="login-frm">
			<form action="DengluServlet" class="layui-form" method="post">
				<input type="hidden" name="oper" value="login" />
				<ul>
					<li class="login-ico "><input class="login-txt"
						name="userName" type="text" placeholder="登录名"></li>
					<li class="login-ico "><input class="login-txt"
						name="password" type="password" placeholder="密码"></li>
					<li><input type="checkbox" lay-skin="primary" title="记住密码"
						checked></li>
					<li><a href="zhuce.jsp" style="color:white">注册</a>&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="main3/main.jsp" style="text-align:right;color:white">游客登录</a></li>
					
					<li><input class="login-btn" type="submit" name="denglu"
						value="立即登录"></li>
				</ul>

			</form>
		</div>
		<div class="login-bg"></div>
	</div>

	<script src="Static/layui/layui.all.js"></script>
	<script src="Scripts/app/login.js"></script>
</body>
</html>





