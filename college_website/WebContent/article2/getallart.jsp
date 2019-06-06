<%@ page language="java" import="entity.*, java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script language="javascript">
$(function(){	
	//导航切换
	$(".imglist li").click(function(){
		$(".imglist li.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})	
</script>
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
	<%
	User user = (User)session.getAttribute("user");
	if(user!=null){
	%>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">查看文章</a></li>
    <li><a href="#">查看全部文章</a></li>
    </ul>
    </div>
    <%
	}
	%>
    
    <div class="rightinfo">
 
    <table class="imgtable">
    
    <thead>
    <tr>
    <th width="100px;">缩略图</th>
    <th>文章ID</th>
    <th>文章标题</th>
    <th>作者</th>
    <th>文章内容</th>
    <th>发布时间</th>
    </tr>
    </thead>

    <tbody>
    <%
        List<Article> list=(ArrayList<Article>)request.getAttribute("list");
    	
        for(Article art : list){
        %>
    <tr>
    <td class="imgtd"><img src="<%=art.getImageUrl() %>" /></td>
    <td><%=art.getArtId() %></td>
    <td><a href="/college_website/user?oper=showart&&artId=<%=art.getArtId() %>"><%=art.getTitle() %></a></td>
    <td>管理员<p>韩先生与李先生</p></td>
    <td><span style="display: inline-block; width: 666px; height: 36px; overflow: hidden; white-space: nowrap; word-break: keep-all; text-overflow: ellipsis;" id="title0">
    <%=art.getContent() %></span></td>
    <td><%=art.getCreateDate() %></td>
    </tr>
	<%} %>
    </tbody>
    
    </table>
    </div>

<script type="text/javascript">
	$('.imgtable tbody tr:odd').addClass('odd');
	</script>
    
</body>

</html>