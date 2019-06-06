<%@ page language="java" import="entity.*, java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>

<!-- html 5 shim + modernizer -->
<script src="./js/modernizr.js"></script>

<!-- all stylesheets -->
<link rel="stylesheet" href="css/reset.css" />
<link rel="stylesheet" href="css/960.css" />
<link rel="stylesheet" href="css/style1.css" />
<link rel="stylesheet" href="css/tipsy.css" />
<link rel="stylesheet" href="css/fancy.css" />
<link href="css/nivo-slider.css" rel="stylesheet" />
<link href="css/skins/tango/skin.css" rel="stylesheet" />

<!-- Fonts -->
<link href='http://fonts.googleapis.com/css?family=Droid+Sans'
	rel='stylesheet' type='text/css' />
<link
	href='http://fonts.googleapis.com/css?family=Droid+Serif:400,400italic'
	rel='stylesheet' type='text/css' />
<link href='http://fonts.googleapis.com/css?family=Share'
	rel='stylesheet' type='text/css' />
<link rel="shortcut icon"
	href="http://www.transforms.web2treat.com/favicon.ico" />

<title>Insert title here</title>
</head>
<body>
	
	<section class="container_12 blog_articles clearfix">

	<section class="grid_12">
	<%
				Article art = (Article) request.getAttribute("Article");

				if (art != null) {
			%>
	<article class="red_post">
	
		<figure><img src="<%=art.getImageUrl() %>" alt="" /></figure>
		<div class="post_content">
		<h4><a><%=art.getTitle()%></a></h4>
		<span class="postinfo"><%=art.getCreateDate()%></span>
		<p><%=art.getContent()%></p>
		<p></p><p></p><p></p><p></p><p></p><p></p><p></p><p></p>
		
	
		<%
		List<Comment> list= (ArrayList<Comment>) request.getAttribute("Comment");
		for(Comment com : list){
		%>
		
		<p>来自用户<%=com.getUserId()%>的评论：<%=com.getCommentValue()%></p>
		
		<%
				}}
			%>
			<p></p><p></p><p></p><p></p>
			<%
			User user = (User)session.getAttribute("user");
			if(user!=null){
			%>
	<form action="/college_website/user" class="" method="post">
    <input type="hidden" name="oper" value="addcomment" />
    <input type="hidden" name="artId" value="<%=art.getArtId() %>" />
    <ul class="forminfo">
    <li><label></label><input name="commentValue" type="text" class="dfinput" placeholder="欢迎添加评论"/><i></i>
    
    <label>&nbsp;</label><input name="" type="submit" class="btn" value="评论"/></li>
    </ul>
		</div>
		
	</article>
			<%
				}
			%>
	</section>
</section><!--end of blog section-->


		<!-- all Javascripts -->
		<script src="./js/jquery.js"></script>
		<script src="./js/nivo.js"></script>
		<script src="./js/easing.js"></script>
		<script src="./js/menu.js"></script>
		<script src="./js/carusol.js"></script>
		<script src="./js/tipsy.js"></script>
		<script src="./js/fancy.js"></script>
		<script src="./js/custom.js"></script>
		
<script>
$(window).load(function() {

		/* Nivo slider */
        $('#slider').nivoSlider({
		directionNavHide: false, // Only show on hover});
		controlNav: false,
		effect: 'sliceDown',
		 animSpeed: 1000
		 
		});

});
</script>
</body>
</html>