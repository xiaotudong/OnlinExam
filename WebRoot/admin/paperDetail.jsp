<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>开始答题</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
	var show_time;
	$(document).ready(function(){
		var time_end = new Date().getTime();
		show_time = function(){ 
			var time_start = new Date().getTime(); //设定当前时间	
			// 计算时间差 	
			var time_distance = time_end - time_start; 	
			// 时	
			var int_hour = Math.floor(time_distance/3600000); 	
			time_distance -= int_hour * 3600000; 	
			// 分	
			var int_minute = Math.floor(time_distance/60000); 	
			time_distance -= int_minute * 60000; 	
			// 秒 	
			var int_second = Math.floor(time_distance/1000); 	
			// 时分秒为单数时、前面加零 	
			if(int_hour < 10){ 		int_hour = "0" + int_hour; 	} 	
			if(int_minute < 10){ 		int_minute = "0" + int_minute; 	} 	
			if(int_second < 10){		int_second = "0" + int_second; 	} 	
			// 显示时间 	
			$("#time_h").val(int_hour); 	
			$("#time_m").val(int_minute); 	
			$("#time_s").val(int_second); 
			
			//判断是否到时间了
			if(int_hour=="00" && int_minute=="00" && int_second=="00"){
				alert("时间到了，系统将自动终止答题并交卷！")
				$("#info").submit();
			}
			
			// 设置定时器	
			setTimeout("show_time()",1000); 
		}
		
		var ques_num = "";
		$("#start_paper").bind("click",function(){
			//$("#ques1_paper").removeAttr("disabled"); 
			$("#start_paper").attr("disabled",true); 
			$("#ques2_paper").removeAttr("disabled"); 
			$("#ques3_paper").removeAttr("disabled"); 
			$("#finish_paper").removeAttr("disabled"); 
			$("#part1").prependTo("#partShow"); 
			ques_num = "#part1";
			
			//开始倒计时
			var time_minute = Number("${time_minute}")*60*1000;
			time_end = new Date(Date.parse(new Date()) + time_minute);
			show_time();
		});
		
		$("#ques1_paper").bind("click",function(){
			$("#ques1_paper").attr("disabled",true); 
			$("#ques2_paper").removeAttr("disabled"); 
			$("#ques3_paper").removeAttr("disabled"); 
			$("#ques4_paper").removeAttr("disabled"); 
			$(ques_num).prependTo("#partHidden"); 
			$("#part1").prependTo("#partShow"); 
			ques_num = "#part1";
		});
		$("#ques2_paper").bind("click",function(){
			$("#ques1_paper").removeAttr("disabled"); 
			$("#ques2_paper").attr("disabled",true); 
			$("#ques3_paper").removeAttr("disabled"); 
			$("#ques4_paper").removeAttr("disabled"); 
			$(ques_num).prependTo("#partHidden"); 
			$("#part2").prependTo("#partShow"); 
			ques_num = "#part2";
		});
		$("#ques3_paper").bind("click",function(){
			$("#ques1_paper").removeAttr("disabled"); 
			$("#ques2_paper").removeAttr("disabled"); 
			$("#ques3_paper").attr("disabled",true); 
			$("#ques4_paper").removeAttr("disabled"); 
			$(ques_num).prependTo("#partHidden"); 
			$("#part3").prependTo("#partShow"); 
			ques_num = "#part3";
		});
		$("#ques4_paper").bind("click",function(){
			$("#ques1_paper").removeAttr("disabled"); 
			$("#ques2_paper").removeAttr("disabled"); 
			$("#ques3_paper").removeAttr("disabled"); 
			$("#ques4_paper").attr("disabled",true); 
			$(ques_num).prependTo("#partHidden"); 
			$("#part4").prependTo("#partShow"); 
			ques_num = "#part4";
		});
		
		$("#finish_paper").bind("click",function(){
			$("#info").submit();
		});
		
	});
</script>
<style type="text/css">
 body,td,div
 {
   font-size:12px;
 }
 .article_title{
 	margin-top:10px;
	text-align:center;
	height:40px;
	line-height:40px;
	font-size: 18px; 
	font-weight:bold;
	color:#8a1e1e;
 }
 .article_time{
	text-align:center;
	height:30px;
	line-height:30px;
	color:#8a1e1e;
	font-size: 12px; 
}
.article_note{
	margin-top:5px;
	padding:10px;
	border:1px dashed black;
	font-size: 12px; 
}
.article_con{
	margin-top:5px;
	text-align:left;
	line-height:25px;
	font-size: 14px; 
	padding:10px;
}
.item_result1{
	font-size: 14px; 
	text-align:left;
	min-height:35px;
}
.item_result2{
	text-align:left;
	min-height:30px;
	max-height:none;
	padding-left:20px;
}
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">在线考试模块&gt;&gt;开始答题</span>
</div>
<div id="middleBg">
	 <form name="info" id="info" action="Admin_finishPaper.action" method="post">
	 <div id="op" class="article_title">
	 	<input type="button" class="btnstyle" id="start_paper" value="开始答题"/>&nbsp;&nbsp;&nbsp;&nbsp;
	 	<input type="button" class="btnstyle" id="ques1_paper" disabled="disabled" value="填空题"/>&nbsp;
	 	<input type="button" class="btnstyle" id="ques2_paper" disabled="disabled" value="选择题"/>&nbsp;
	 	<input type="button" class="btnstyle" id="ques3_paper" disabled="disabled" value="判断题"/>&nbsp;&nbsp;
	 	<input type="button" class="btnstyle" id="ques4_paper" disabled="disabled" value="编程题"/>&nbsp;&nbsp;&nbsp;&nbsp;
	 	<input type="button" class="btnstyle" id="finish_paper" disabled="disabled" value="交 卷"/>&nbsp;&nbsp;&nbsp;&nbsp;
	 	倒计时：<input type="text" id="time_h" readOnly="readOnly" style="width:40px;text-align:center" value="01"/>时
	 	<input type="text" id="time_m" readOnly="readOnly" style="width:40px;text-align:center" value="00"/>分
	 	<input type="text" id="time_s" readOnly="readOnly" style="width:40px;text-align:center" value="00"/>秒
	 </div>
	 
	 <div id="partShow" style="width:100%">
	 </div>
	 
	 <div id="partHidden" style="visibility: hidden;">
	 <div id="part1">
	 <div class="article_title">第一部分（填空题）</div>
	 <!--  
	 <div class="article_time">问题数量：<s:property value="#attr.paper.ques_count" /> 道　发布时间：<s:property value="#attr.paper.paper_date.substring(0,19)" /></div>
	 -->
	 <div class="article_note">第一部分（填空题）的题目数量为10道，2分/道，满分20分</div>
	 <div class="article_con" style="padding-left:50px">
		 <s:if test="#attr.user_ques1s!=null && #attr.user_ques1s.size()>0">
		 <s:iterator value="#attr.user_ques1s" id="ques1" status="status">
		 <s:hidden name="paramsQues1s[<s:property value='#status.index' />].id" value="%{#ques1.ques1_id}" />
		 <div class="item_result1">
		 	<strong>
		 		<s:property value="#status.index+1" />、<s:property value="#attr.ques1.ques1_name1Show" escape="false"/>
		 		<input type="text" name="paramsQues1s[<s:property value="#status.index" />].answer" style="border-style:none;border-bottom:1px solid black;background-color:white;width:100px"/>
		 		<s:property value="#attr.ques1.ques1_name2Show" escape="false"/>
		 	</strong>
		 </div>
		 </s:iterator>	
		 </s:if>
	 </div>
	 </div>
	 
	 <div id="part2">
	 <div class="article_title">第二部分（选择题）</div>
	 <div class="article_note">第二部分（选择题）的题目数量为15道，2分/道，满分30分</div>
	 <div class="article_con" style="padding-left:50px">
		 <s:if test="#attr.user_ques2s!=null && #attr.user_ques2s.size()>0">
		 <s:iterator value="#attr.user_ques2s" id="ques2" status="status">
		 <s:hidden name="paramsQues2s[<s:property value='#status.index' />].id" value="%{#ques2.ques2_id}" />
		 <div class="item_result1">
		 	<strong><s:property value="#status.index+1" />、<s:property value="#attr.ques2.ques2_nameShow" escape="false"/>（）</strong>
		 </div>
		 <s:iterator value="#ques2.items" id="item">
		 <div class="item_result2">
		 	<input type="radio" value="<s:property value='#item.item_no'/>" name="paramsQues2s[<s:property value="#status.index" />].answers"/> 
		 	<s:property value="#item.item_no" />：<s:property value="#item.item_nameShow" escape="false"/>
		 </div>
		 </s:iterator>	
		 </s:iterator>	
		 </s:if>
	 </div>
	 </div>
	 
	 <div id="part3">
	 <div class="article_title">第三部分（判断题）</div>
	 <div class="article_note">第三部分（判断题）的题目数量为15道，2分/道，满分30分</div>
	 <div class="article_con" style="padding-left:50px">
		 <s:if test="#attr.user_ques3s!=null && #attr.user_ques3s.size()>0">
		 <s:iterator value="#attr.user_ques3s" id="ques3" status="status">
		 <s:hidden name="paramsQues3s[<s:property value='#status.index' />].id" value="%{#ques3.ques3_id}" />
		 <div class="item_result1">
		 	<strong><s:property value="#status.index+1" />、<s:property value="#attr.ques3.ques3_nameShow" escape="false"/></strong>
		 </div>
		 <div class="item_result2">
		 	<input type="radio" value="1" name="paramsQues3s[<s:property value='#status.index' />].answer"/> A：正确
		 </div>
		 <div class="item_result2">
		 	<input type="radio" value="2" name="paramsQues3s[<s:property value='#status.index' />].answer"/> B：错误
		 </div>
		 </s:iterator>	
		 </s:if>
	 </div>
	 </div>
	 
	 <div id="part4">
	 <div class="article_title">第四部分（编程题）</div>
	 <div class="article_note">第四部分（编程题）的题目数量为2道，10分/道，满分20分</div>
	 <div class="article_con" style="padding-left:50px">
		 <s:if test="#attr.user_ques4s!=null && #attr.user_ques4s.size()>0">
		 <s:iterator value="#attr.user_ques4s" id="ques4" status="status">
		 <s:hidden name="paramsQues4s[<s:property value='#status.index' />].id" value="%{#ques4.ques4_id}" />
		 <div class="item_result1">
		 	<strong><s:property value="#status.index+1" />、<s:property value="#attr.ques4.ques4_nameShow" escape="false"/></strong>
		 </div>
		 <div class="item_result2">
		 	<textarea name="paramsQues4s[<s:property value='#status.index' />].answer" style="width:500px;height:300px"></textarea>
		 </div>
		 </s:iterator>	
		 </s:if>
	 </div>
	 </div>
	 
	 </div>
	 </form>
</div>
</body>
</html>