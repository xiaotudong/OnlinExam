<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:if test="#attr.ques4!=null && #attr.ques4.ques4_id!=0">编辑</s:if><s:else>发布</s:else>编程题信息</title>
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
		if($("#paramsQues4\\.ques4_name").val()==''){
			alert("题目名称不能都为空！");
			return;
		}
		if($("#paramsQues4\\.ques4_answer").val()==''){
			alert("参考答案不能为空！");
			return;
		}
		$("#paramsQues4\\.ques4_id").val(0);
		$("#ques4").attr('action','Admin_addQues4.action').submit();
		 
	 });
	 
	 $("#editBtn").bind('click',function(){
		editor1.sync();
		editor2.sync();
		if($("#paramsQues4\\.ques4_name").val()==''){
			alert("题目名称不能都为空！");
			return;
		}
		if($("#paramsQues4\\.ques4_answer").val()==''){
			alert("参考答案不能为空！");
			return;
		}
		$("#ques4").attr('action','Admin_saveQues4.action').submit();
			 
	});
	
});

 
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">编程题信息管理&gt;&gt;<s:if test="#attr.ques4!=null && #attr.ques4.ques4_id!=0">编辑</s:if><s:else>发布</s:else>编程题信息</span>
</div>
<form id="ques4" name="ques4" action="Admin_saveQues4.action" method="post">    
<s:hidden id="paramsQues4.ques4_id" name="paramsQues4.ques4_id" value="%{#attr.ques4.ques4_id}" /> 
<table width="600" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle"><s:if test="#attr.ques4!=null && #attr.ques4.ques4_id!=0">编辑</s:if><s:else>发布</s:else>编程题信息</TD>
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
          <td width="150" align="right" style="padding-right:5px">题目名称：</td>
          <td width="*">
            <textarea Style="width:500px;height:100p;" name="paramsQues4.ques4_name" id="paramsQues4.ques4_name"><s:property value="#attr.ques4.ques4_name" escape="false"/>
            </textarea> 
          </td>  
        </tr>
        <tr>
          <td align="right" style="padding-right:5px">参考答案：</td>
          <td>
            <textarea Style="width:500px;height:300p;" name="paramsQues4.ques4_answer" id="paramsQues4.ques4_answer"><s:property value="#attr.ques4.ques4_answer" escape="false"/>
          	</textarea> 
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
            <s:if test="#attr.ques4!=null && #attr.ques4.ques4_id!=0">
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
	var ids = ['#paramsQues4\\.ques4_name','#paramsQues4\\.ques4_answer'];
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