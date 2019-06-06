<%@ page language="java" import ="entity.* , java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery.js"></script>

<script type="text/javascript">
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});

});
</script>


</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="/college_website/main/homepage.jsp" target="rightFrame">首页</a></li>
    <li><a href="#">评论管理</a></li>
    <li><a href="#">浏览全部评论</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
 
    <table class="tablelist">
    	<thead>
    	<tr>
        <th><input name="" type="checkbox" value="" checked="checked"/></th>
        <th>评论ID<i class="sort"><img src="images/px.gif" /></i></th>
        <th>用户ID</th>
        <th>文章ID</th>
        <th>评论内容</th>
        <th>评论时间</th>
        
        </tr>
        </thead>
        <tbody>
        <%
        List<Comment> list=(ArrayList<Comment>)request.getAttribute("list");
        for(Comment com : list){
        %>
        <tr>
        <td><input name="" type="checkbox" value="" /></td>
        <td><%=com.getCommentId() %></td>
        <td><%=com.getUserId() %></td>
        <td><%=com.getArtId() %></td>
        <td><%=com.getCommentValue() %></td>
        <td><%=(Date)com.getCommentDate() %></td>
        </tr> 
        <%} %>
        </tbody>
    </table>
    
   
    
    
    <div class="tip">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
      <div class="tipinfo">
        <span><img src="images/ticon.png" /></span>
        <div class="tipright">
        <p>是否确认对信息的修改 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        
        <div class="tipbtn">
        <input name="" type="button"  class="sure" value="确定" />&nbsp;
        <input name="" type="button"  class="cancel" value="取消" />
        </div>
    
    </div>
    
    
    
    
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>

</body>

</html>