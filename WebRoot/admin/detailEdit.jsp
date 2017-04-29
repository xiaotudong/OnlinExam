<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>批阅答题</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	function trimStr(str)     
	{     
	    if ((typeof(str) != "string") || !str)   
	    {    
	        return "";    
	    }   
	    return str.replace(/(^\s*)|(\s*$)/g, "");    
	}
	
	 var num=/^\d+(\.\d+)?$/;
	 $("#editBtn").bind('click',function(){
		if(checkValue()){
			$("#ques").attr('action','Admin_saveDetail.action').submit();
		}
	});
	 
	 function checkValue(){
		 var flag = true;
		 $("input[id^='user_score']").each(function(){
			var user_score = $(this).val();
			if(!num.exec(user_score)){
				alert("试题得分必须为数字！");
				flag = false;
				return false;
			}
		 });
		 return flag;
	 }
	
});

 
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">学生答题管理&gt;&gt;学生答题批阅</span>
</div>
<form id="ques" name="ques" action="Admin_saveDetail.action" method="post">    
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">学生答题批阅</TD>
              <TD class="edittitleright">&nbsp;</TD>
            </TR>
        </TABLE>
     </td>
   </tr>
   <tr>
     <td height="1" bgcolor="#8f8f8f"></td>
   </tr>
   <tr>
     <td >
     <table width="100%" align="center" cellpadding="1" cellspacing="1" class="editbody">
        <tr>
          <td align="left" style="padding-left:5px;font-weight:bold">
          	一、填空题部分（共10道，2分/道，满分20分）
          </td>
        </tr>
        <s:if test="#attr.details1!=null && #attr.details1.size()>0">
        <s:iterator value="#attr.details1" id="detail" status="status">
        <tr>
          <td align="left" style="padding-left:25px;">
          	<s:property value="#status.index+1"/>、<s:property value="#detail.ques_nameShow" escape="false"/>
          </td>
        </tr>
        <tr>
          <td align="left" style="padding-left:25px;">
          	正确答案、<s:property value="#detail.ques_answerShow" escape="false"/>&nbsp;&nbsp;
          	学生答案、<s:property value="#detail.user_answerShow" escape="false"/>&nbsp;&nbsp;
          	试题得分、<s:property value="#detail.user_score"/>分
          </td>
        </tr>	
        </s:iterator>
        </s:if>
        
        <tr>
          <td align="left" style="padding-left:5px;font-weight:bold">
          	二、选择题部分（共15道，2分/道，满分30分）
          </td>
        </tr>
        <s:if test="#attr.details2!=null && #attr.details2.size()>0">
        <s:iterator value="#attr.details2" id="detail" status="status">
        <tr>
          <td align="left" style="padding-left:25px;">
          	<s:property value="#status.index+1"/>、<s:property value="#detail.ques_nameShow" escape="false"/>
          </td>
        </tr>
        <tr>
          <td align="left" style="padding-left:35px;">
          	<s:iterator value="#detail.items" id="item" status="status">
          	<s:property value="#item.item_no"/>、<s:property value="#item.item_nameShow" escape="false"/>
          	<br/>
          	</s:iterator>
          </td>
        </tr>	
        <tr>
          <td align="left" style="padding-left:25px;">
          	正确答案、<s:property value="#detail.ques_answerShow" escape="false"/>&nbsp;&nbsp;
          	学生答案、<s:property value="#detail.user_answerShow" escape="false"/>&nbsp;&nbsp;
          	试题得分、<s:property value="#detail.user_score"/>分
          </td>
        </tr>	
        </s:iterator>
        </s:if>
        
        <tr>
          <td align="left" style="padding-left:5px;font-weight:bold">
          	三、判断题部分（共15道，2分/道，满分30分）
          </td>
        </tr>
        <s:if test="#attr.details3!=null && #attr.details3.size()>0">
        <s:iterator value="#attr.details3" id="detail" status="status">
        <tr>
          <td align="left" style="padding-left:25px;">
          	<s:property value="#status.index+1"/>、<s:property value="#detail.ques_nameShow" escape="false"/>
          </td>
        </tr>
        <tr>
          <td align="left" style="padding-left:25px;">
          	正确答案、<s:property value="#detail.ques_answer"/>&nbsp;&nbsp;
          	学生答案、<s:property value="#detail.user_answer"/>&nbsp;&nbsp;
          	试题得分、<s:property value="#detail.user_score"/>分
          </td>
        </tr>	
        </s:iterator>
        </s:if>
       
       <tr>
          <td align="left" style="padding-left:5px;font-weight:bold">
          	四、编程题部分（共2道，10分/道，满分20分）
          </td>
        </tr>
        <s:if test="#attr.details4!=null && #attr.details4.size()>0">
        <s:iterator value="#attr.details4" id="detail" status="status">
        <tr>
          <td align="left" style="padding-left:25px;">
          	<s:property value="#status.index+1"/>、<s:property value="#detail.ques_nameShow" escape="false"/>
			<s:hidden name="details[%{#status.index}].detail_id" value="%{#detail.detail_id}" /> 
			<s:hidden name="details[%{#status.index}].score_id" value="%{#detail.score_id}" /> 
			<s:hidden name="details[%{#status.index}].ques_id" value="%{#detail.ques_id}" />
          </td>
        </tr>
        <tr>
          <td align="left" style="padding-left:25px;">
          	参考答案、<s:property value="#detail.ques_answerShow" escape="false"/>
          </td>
        </tr>
        <tr>
          <td align="left" style="padding-left:25px;">
          	学生答案、<s:property value="#detail.user_answerShow" escape="false"/>
          </td>
        </tr>	
        <tr> 
          <td align="left" style="padding-left:25px;">
           	 试题得分：
            <s:textfield cssStyle="width:100px;" name="details[%{#status.index}].user_score" id="user_score%{#status.index}" value=""/> 
          </td>
        </tr> 
        </s:iterator>
        </s:if>
     </table>  
     </td> 
   </tr>   
   <tr>
     <td> 
       <table width="100%" align="center" cellpadding="0" cellspacing="0" class="editbody">
        <tr class="editbody">
          <td align="center" height="30"> 
            <input type="button" onclick="window.history.back();" Class="btnstyle" value="返 回" />&nbsp;&nbsp;
          	<input type="button" id="editBtn" Class="btnstyle" value="批 阅"/> 
            &nbsp;<label style="color:red">${tip}</label> 
          </td>
        </tr>
      </table>
     </td> 
   </tr>
</table>
</form>
</body>
</html>