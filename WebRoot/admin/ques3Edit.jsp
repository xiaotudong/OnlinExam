<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:if test="#attr.ques3!=null && #attr.ques3.ques3_id!=0">编辑</s:if><s:else>发布</s:else>判断题信息</title>
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
	
	 var ques3_answer = "<s:property value='#attr.ques3.ques3_answer' />";
	 if(ques3_answer!='' && ques3_answer!='0'){
		 $("#ques3_answer"+ques3_answer).attr('checked','checked');
	 }else{
		 $("#ques3_answer1").attr('checked','checked');
	 }
	
	$("#addBtn").bind('click',function(){
		editor1.sync();
		if($("#paramsQues3\\.ques3_name").val()==''){
			alert("题目标题不能都为空！");
			return;
		}
		$("#paramsQues3\\.ques3_id").val(0);
		$("#ques3").attr('action','Admin_addQues3.action').submit();
		 
	 });
	 
	 $("#editBtn").bind('click',function(){
		editor1.sync();
		if($("#paramsQues3\\.ques3_name").val()==''){
			alert("题目标题不能都为空！");
			return;
		}
		$("#ques3").attr('action','Admin_saveQues3.action').submit();
			 
	});
	
});

 
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">判断题信息管理&gt;&gt;<s:if test="#attr.ques3!=null && #attr.ques3.ques3_id!=0">编辑</s:if><s:else>发布</s:else>判断题信息</span>
</div>
<form id="ques3" name="ques3" action="Admin_saveQues3.action" method="post">    
<s:hidden id="paramsQues3.ques3_id" name="paramsQues3.ques3_id" value="%{#attr.ques3.ques3_id}" /> 
<table width="600" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle"><s:if test="#attr.ques3s!=null && #attr.ques3.ques3_id!=0">编辑</s:if><s:else>发布</s:else>判断题信息</TD>
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
          <td width="30%" align="right" style="padding-right:5px">题目标题：</td>
          <td width="70%">
            <textarea Style="width:345px;height:100px" name="paramsQues3.ques3_name" id="paramsQues3.ques3_name"><s:property value="#attr.ques3.ques3_name" escape="false"/></textarea> 
          </td>  
        </tr>
        <tr>
          <td width="30%" align="right" style="padding-right:5px">题目答案：</td>
          <td width="70%">
            <input type="radio" id="ques3_answer1" name="paramsQues3.ques3_answer" value="1"/>正确&nbsp;&nbsp;
            <input type="radio" id="ques3_answer2" name="paramsQues3.ques3_answer" value="2"/>错误 
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
            <s:if test="#attr.ques3!=null && #attr.ques3.ques3_id!=0">
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
	var ids = ['#paramsQues3\\.ques3_name'];
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