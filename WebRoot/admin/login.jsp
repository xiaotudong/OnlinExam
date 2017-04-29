<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>在线考试系统登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/index.css">
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
	
</script>
<style type="text/css">
 body,td,div
 {
   font-size:12px;
 }
</style>
</head>
<BODY>
<div class="top_div"></div>
<FORM id="info" name="info"  method="post" action="LoginInSystem.action">
<DIV id=UpdatePanel1>
<div
			style="background: rgb(255, 255, 255); 
			margin:  auto auto; 
			border: 1px solid rgb(231, 231, 231); 
			border-image: none; 
			width: 400px; 
			height: 250px; 
			text-align: center;">
			<P style="padding: 30px 0px 10px; position: relative;">
				<span class="u_logo"></span> 
				<input class="ipt" id="params.user_name" type="text"  name="params.user_name" placeholder="请输入用户名" value="">
			</P>
			<P style="position: relative;">
				<span class="p_logo"></span> 
				<input class="ipt" id="params.user_pass" type="password" name="params.user_pass" placeholder="请输入密码" value="">
			</P>
			<ul>
				<li>
				        身&nbsp;&nbsp;份：
				    <input type="radio" name="params.user_type" value="1" class="role" />学生 
				    <input type="radio" name="params.user_type value="2" class="role" />老师
				    <input type="radio" name="params.user_type" value="3" class="role" />管理员
				</li> 
			</ul>
			<TR>
							<TD></TD>
							<TD colspan="2" id="loginTip" style="HEIGHT:22px;color:orange">${tip}</TD>
						</TR>
						<TR>
							<TD></TD>
							<TD colspan="2">
								<img id="loginInBtn"  style="border:0px;cursor:pointer" src="images/login_button.gif" />&nbsp;&nbsp;
								<img id="regInBtn"  style="border:0px;cursor:pointer" src="images/reg_button.gif" /> 
							</TD>
						</TR>
		</div>
</DIV>
</FORM>
<script language="javascript" type="text/javascript">

	$(document).ready(function(){
		var loginInBtn = $("#loginInBtn");
		var user_name = $("#params\\.user_name");
		var user_pass = $("#params\\.user_pass");
		var loginTip = $("#loginTip");
		
		loginInBtn.bind('click',function(){
			if(user_name.val()==''||user_pass.val()==''){
				loginTip.html("用户名、密码不能为空！")
				return;
			}
			$("#info").submit();
			 
		});
		
		var regInBtn = $("#regInBtn");
		regInBtn.bind('click',function(){
		window.location.href="reg.jsp";
			 
		});
	});
</script>
</BODY>
</HTML>
