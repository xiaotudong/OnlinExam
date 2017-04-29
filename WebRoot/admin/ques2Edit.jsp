<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:if test="#attr.ques2!=null && #attr.ques2.ques2_id!=0">编辑</s:if><s:else>添加</s:else>选择题信息</title>
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
	
	var num=/^\d+(\.\d+)?$/;
	$("#addBtn").bind('click',function(){
		initItemInfo();
		if($("#paramsQues2\\.ques2_name").val()==''){
			alert("题目标题不能为空！");
			return;
		}
		if(itemNos_count==0){
			alert("至少有一个选项不能为空！");
			return;
		}
		if(!validItemCheck()){
			alert("空选项不能设置为答案！");
			return;
		}
		if(itemAnswers_count==0){
			alert("至少勾选一个答案！");
			return;
		}
		if(itemAnswers_count>1){
			alert("单选只能勾选一个答案！");
			return;
		}
		$("#paramsQues2\\.ques2_id").val(0);
		$("#ques2_info").attr('action','Admin_addQues2.action').submit();
		 
	 });
	 
	 $("#editBtn").bind('click',function(){
		initItemInfo();
	    if($("#paramsQues2\\.ques2_name").val()==''){
			alert("题目标题不能为空！");
			return;
		}
	    if(itemNos_count==0){
			alert("至少有一个选项不能为空！");
			return;
		}
	    if(!validItemCheck()){
			alert("空选项不能设置为答案！");
			return;
		}
		if(itemAnswers_count==0){
			alert("至少勾选一个答案！");
			return;
		}
		if(itemAnswers_count>1){
			alert("单选只能勾选一个答案！");
			return;
		}
		$("#ques2_info").attr('action','Admin_saveQues2.action').submit();
			 
	});
	
	//验证选择结果
	var itemNos = [];
	var itemNos_count = 0
	var itemAnswers = [];
	var itemAnswers_count = 0
	function initItemInfo(){
		editor1.sync();
		editor2.sync();
		editor3.sync();
		editor4.sync();
		editor5.sync();
		
		itemNos = [];
		itemNos_count = 0
		itemAnswers = [];
		itemAnswers_count = 0
		 for(var i=1;i<=4;i++){
			 if(trimStr($("#Ques"+i).val())!=''){
				 itemNos[i-1]=trimStr($("#Ques"+i).val());
				 itemNos_count++;
			 }else{
				 itemNos[i-1]="";
			 }
		 }
		 $("input[name='paramsQues2.answers']").each(function(index){
			 if($(this).attr("checked")){
				 itemAnswers[index]=$(this).val();
				 itemAnswers_count++;
			 }else{
				 itemAnswers[index]="";
			 }
		 });
	}
	
	function validItemCheck(){
		for(var i=0;i<4;i++){
			if(itemNos[i]=='' && itemAnswers[i]!=""){
				return false;
			}
		}
		return true;
	}
	 
	
});

 
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">选择题信息管理&gt;&gt;<s:if test="#attr.ques2!=null && #attr.ques2.ques2_id!=0">编辑</s:if><s:else>添加</s:else>选择题信息</span>
</div>
<form id="ques2_info" name="ques2_info" action="Admin_saveQues.action" method="post">    
<s:hidden id="paramsQues2.ques2_id" name="paramsQues2.ques2_id" value="%{#attr.ques2.ques2_id}" /> 
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle"><s:if test="#attr.ques2!=null && #attr.ques2.ques2_id!=0">编辑</s:if><s:else>添加</s:else>选择题信息</TD>
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
          <td width="20%" align="right" style="padding-right:5px">题目标题：</td>
          <td width="80%">
            <textarea cssStyle="width:345px;height:100px" name="paramsQues2.ques2_name" id="paramsQues2.ques2_name"><s:property value="#attr.ques2.ques2_name" escape="false"/></textarea> 
          </td>  
        </tr> 
        <!--  
        <tr> 
          <td align="right" style="padding-right:5px">选择题分数：</td>
          <td>
            <s:textfield cssStyle="width:100px;" name="paramsQues2.ques2_score" id="paramsQues2.ques2_score" value="%{#attr.ques2.ques2_score}"/> 
          </td>
        </tr>  
        <tr> 
          <td align="right" style="padding-right:5px">是否单选：</td>
          <td>
            <s:select list="#{'1':'是','2':'否' }" id="ques2_single" name="paramsQues2.ques2_single" value="%{#attr.ques2.ques2_single}"
            		listKey="key" listValue="value" headerKey="0" headerValue="请选择" cssStyle="width:100px">
            </s:select>
          </td>
        </tr>  
        -->
        <tr> 
          <td align="right" style="padding-right:5px">选择题选项：</td>
          <s:if test="#attr.ques2!=null && #attr.ques2.ques2_id!=0">
          <td>
          	  <input type="checkbox" name="paramsQues2.answers" id="answers1" value="A" <s:if test="#attr.itemA.item_right==1">checked='checked'</s:if> /> A：
          	  <textarea Style="width:200px;height:80px" name="paramsQues2.A" id="Ques1"><s:property value="#attr.itemA.item_name" escape="false"/></textarea>
          	  <br/>
          	  <input type="checkbox" name="paramsQues2.answers" id="answers2" value="B" <s:if test="#attr.itemB.item_right==1">checked='checked'</s:if> /> B：
          	  <textarea Style="width:200px;height:80px" name="paramsQues2.B" id="Ques2"><s:property value="#attr.itemB.item_name" escape="false"/></textarea>
          	  <br/>
          	  <input type="checkbox" name="paramsQues2.answers" id="answers3" value="C" <s:if test="#attr.itemC.item_right==1">checked='checked'</s:if> /> C：
          	  <textarea Style="width:200px;height:80px" name="paramsQues2.C" id="Ques3"><s:property value="#attr.itemC.item_name" escape="false"/></textarea>
          	  <br/>
          	  <input type="checkbox" name="paramsQues2.answers" id="answers4" value="D" <s:if test="#attr.itemD.item_right==1">checked='checked'</s:if> /> D：
          	  <textarea Style="width:200px;height:80px" name="paramsQues2.D" id="Ques4"><s:property value="#attr.itemD.item_name" escape="false"/></textarea>
          </td>
          </s:if>
          <s:else>
          <td>
          	  <input type="checkbox" name="paramsQues2.answers" id="answers1" value="A"/> A：
          	  <textarea Style="width:200px;height:80px" name="paramsQues2.A" id="Ques1"></textarea>
          	  <br/>
          	  <input type="checkbox" name="paramsQues2.answers" id="answers2" value="B"/> B：
          	  <textarea Style="width:200px;height:80px" name="paramsQues2.B" id="Ques2"></textarea>
          	  <br/>
          	  <input type="checkbox" name="paramsQues2.answers" id="answers3" value="C"/> C：
          	  <textarea Style="width:200px;height:80px" name="paramsQues2.C" id="Ques3"></textarea>
          	  <br/>
          	  <input type="checkbox" name="paramsQues2.answers" id="answers4" value="D"/> D：
          	  <textarea Style="width:200px;height:80px" name="paramsQues2.D" id="Ques4"></textarea>
          </td>
          </s:else>
        </tr> 
        <tr> 
          <td align="right" style="padding-right:5px">选项说明：</td>
          <td style="color:red">勾选复选框表示正确答案，单选只能勾选一个</td>
        </tr>    
     </table>  
     </td> 
   </tr>   
   <tr>
     <td> 
       <table width="100%" align="center" cellpadding="0" cellspacing="0" class="editbody">
        <tr class="editbody">
          <td align="center" height="30"> 
            <s:if test="#attr.ques2!=null && #attr.ques2.ques2_id!=0">
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
	var ids = ['#paramsQues2\\.ques2_name','#Ques1','#Ques2','#Ques3','#Ques4'];
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