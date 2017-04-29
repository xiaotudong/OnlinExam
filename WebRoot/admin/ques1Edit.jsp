<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:if test="#attr.ques1!=null && #attr.ques1.ques1_id!=0">编辑</s:if><s:else>发布</s:else>填空题信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script charset="utf-8" src="editor2/kindeditor-all-min.js"></script>
<script charset="utf-8" src="editor2/lang/zh-CN.js"></script>
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
	
	 
	$("#addBtn").bind('click',function(){
		editor1.sync();
		editor2.sync();
		if($("#paramsQues1\\.ques1_name1").val()=='' && $("#paramsQues1\\.ques1_name2").val()==''){
			alert("空前文字和空后文字不能都为空！");
			return;
		}
		if($("#paramsQues1\\.ques1_answer").val()==''){
			alert("答案不能为空！");
			return;
		}
		$("#paramsQues1\\.ques1_id").val(0);
		$("#ques1").attr('action','Admin_addQues1.action').submit();
		 
	 });
	 
	 $("#editBtn").bind('click',function(){
		editor1.sync();
		editor2.sync();
		if($("#paramsQues1\\.ques1_name1").val()=='' && $("#paramsQues1\\.ques1_name2").val()==''){
			alert("空前文字和空后文字不能都为空！");
			return;
		}
		if($("#paramsQues1\\.ques1_answer").val()==''){
			alert("答案不能为空！");
			return;
		}
		$("#ques1").attr('action','Admin_saveQues1.action').submit();
			 
	});
	
});

 
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">填空题信息管理&gt;&gt;<s:if test="#attr.ques1!=null && #attr.ques1.ques1_id!=0">编辑</s:if><s:else>发布</s:else>填空题信息</span>
</div>
<form id="ques1" name="ques1" action="Admin_saveQues1.action" method="post">    
<s:hidden id="paramsQues1.ques1_id" name="paramsQues1.ques1_id" value="%{#attr.ques1.ques1_id}" /> 
<table width="600" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle"><s:if test="#attr.ques1!=null && #attr.ques1.ques1_id!=0">编辑</s:if><s:else>发布</s:else>填空题信息</TD>
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
          <td width="30%" align="right" style="padding-right:5px">空前文字：</td>
          <td width="70%">
            <textarea Style="width:345px;height:100px" name="paramsQues1.ques1_name1" id="paramsQues1.ques1_name1"><s:property value="#attr.ques1.ques1_name1" escape="false"/></textarea> 
          </td>  
        </tr>
        <tr>
          <td width="30%" align="right" style="padding-right:5px">题目答案：</td>
          <td width="70%">
            <s:textfield cssStyle="width:345px;" name="paramsQues1.ques1_answer" id="paramsQues1.ques1_answer" value="%{#attr.ques1.ques1_answer}"/> 
          </td>  
        </tr>  
        <tr>
          <td width="30%" align="right" style="padding-right:5px">空后文字：</td>
          <td width="70%">
            <textarea Style="width:345px;height:100px" name="paramsQues1.ques1_name2" id="paramsQues1.ques1_name2"><s:property value="#attr.ques1.ques1_name2" escape="false"/></textarea> 
          </td>   
        </tr> 
     </table>  
     </td> 
   </tr>   
   <tr>
     <td> 
       <table width="100%" align="center" cellpadding="0" cellspacing="0" class="editbody">
        <tr class="editbody">
          <td align="center" height="30"> 
            <s:if test="#attr.ques1!=null && #attr.ques1.ques1_id!=0">
          	<input type="button" id="editBtn" Class="btnstyle" value="编 辑"/> 
          	</s:if>
          	<s:else>
          	<input type="button" id="addBtn" Class="btnstyle" value="添 加" />
          	</s:else>
            &nbsp;<label style="color:red">${tip}</label> 
          </td>
        </tr>
      </table>
     </td> 
   </tr>
</table>
</form>
<script>        
KindEditor.ready(function(K) {
	var ids = ['#paramsQues1\\.ques1_name1','#paramsQues1\\.ques1_name2'];
	for(var i=0;i<ids.length;i++){
		window['editor'+(i+1)]=K.create(ids[i],{
			width : '95%',
			items:[
				'fullscreen','bold','italic', 'image'
			],
			uploadJson : 'editor2/jsp/upload_json.jsp',
	        fileManagerJson : 'editor2/jsp/file_manager_json.jsp',
	        allowFileManager : true
		});
	}
});
</script>
</body>
</html>