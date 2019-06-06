<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="../js/jquery.js"></script>

<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson .header").click(function(){
		var $parent = $(this).parent();
		$(".menuson>li.active").not($parent).removeClass("active open").find('.sub-menus').hide();
		
		$parent.addClass("active");
		if(!!$(this).next('.sub-menus').size()){
			if($parent.hasClass("open")){
				$parent.removeClass("open").find('.sub-menus').hide();
			}else{
				$parent.addClass("open").find('.sub-menus').show();	
			}
			
			
		}
	});
	
	// 三级菜单点击
	$('.sub-menus li').click(function(e) {
        $(".sub-menus li.active").removeClass("active")
		$(this).addClass("active");
    });
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('.menuson').slideUp();
		if($ul.is(':visible')){
			$(this).next('.menuson').slideUp();
		}else{
			$(this).next('.menuson').slideDown();
		}
	});
})	
</script>


</head>

<body style="background:#fff3e1;">
	<div class="lefttop"><span></span>系统管理</div>
    
    <dl class="leftmenu">
        
    <dd>
    <div class="title">
    <span><img src="../images/leftico01.png" /></span>用户管理
    </div>
    	<ul class="menuson">
        
        <li>
            <div class="header">
            <cite></cite>
            <a href="/college_website/manager?oper=getalluser" target="rightFrame">查看所有用户</a>
            <i></i>
            </div>
        </li>
        <li>
            <div class="header">
            <cite></cite>
            <a href="../user/searchuser.jsp" target="rightFrame">搜索用户</a>
            <i></i>
            </div>                
        </li>    
        <li>
            <div class="header">
            <cite></cite>
            <a href="../user/deleteuser.jsp" target="rightFrame">删除用户</a>
            <i></i>
            </div>                
        </li>    
        </ul>    
    </dd>
    <dd>
    <div class="title">
    <span><img src="../images/leftico02.png" /></span>文章管理
    </div>
    <ul class="menuson">
        <li><div class="header"><cite></cite><a href="/college_website/manager?oper=getallart" target="rightFrame">浏览全部文章</a><i></i></div></li>
        <li><div class="header"><cite></cite><a href="../article2/searchart.jsp" target="rightFrame">搜索文章</a><i></i></div></li>
        <li><div class="header"><cite></cite><a href="../article2/addart.jsp" target="rightFrame">增加文章</a><i></i></div></li>
        <li><div class="header"><cite></cite><a href="../article2/changeart.jsp" target="rightFrame">修改文章内容</a><i></i></div></li>
        <li><div class="header"><cite></cite><a href="../article2/deleteart.jsp" target="rightFrame">删除文章</a><i></i></div></li>
        </ul>     
    </dd> 
    
    
    <dd><div class="title"><span><img src="../images/leftico03.png" /></span>评论管理</div>
    <ul class="menuson">
        <li><div class="header"><cite></cite><a href="/college_website/manager?oper=getallcom" target="rightFrame">浏览所有评论</a><i></i></li>
        <li><div class="header"><cite></cite><a href="../comment/searchcom.jsp" target="rightFrame">搜索评论</a><i></i></div></li>
        <li><div class="header"><cite></cite><a href="../comment/getcomofart.jsp" target="rightFrame">查看某文章的所有评论</a><i></i></div></li>
        <li><div class="header"><cite></cite><a href="../comment/getcomofuser.jsp" target="rightFrame">查看某用户的所有评论</a><i></i></div></li>
        <li><div class="header"><cite></cite><a href="../comment/deletecom.jsp" target="rightFrame">删除评论</a><i></i></div></li>
    </ul>    
    </dd>  
      
    
    </dl>
    
</body>
</html>