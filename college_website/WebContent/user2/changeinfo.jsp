<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">个人信息</a></li>
    <li><a href="#">修改个人简介</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>修改简介信息</span></div>
	    <form action="../user" method="post" target="rightFrame">
	    	<input  type="hidden" name="oper" value="changeinfo" />
		    <ul class="forminfo">
		    <li><label>新简介</label><input name="newInfo"  type="text" class="dfinput" /><i></i></li>
		    <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="确认修改"/></li>
		    </ul>
	    </form>
    
    </div>


</body>

</html>