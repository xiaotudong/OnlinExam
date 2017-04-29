<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生成绩信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
var tempClassName="";
function tr_mouseover(obj) 
{ 
	tempClassName=obj.className;
	obj.className="list_mouseover";
}
function tr_mouseout(obj)      
{ 
	obj.className=tempClassName;
}      
function CheckAll(obj) 
{
	var checks=document.getElementsByName("chkid");
    for (var i=0;i<checks.length;i++)
	{
	    var e = checks[i];
	    e.checked = obj.checked;
	}
    
}

function del()
{
	var checks=document.getElementsByName("chkid");
    var ids="";
	for (var i=0;i<checks.length;i++)
    {
        var e = checks[i];
		if(e.checked==true)
		{
		  if(ids=="")
		  {
		    ids=ids+e.value;
		  }
		  else
		  {
		    ids=ids+","+e.value;
		  }
		}
    }
    if(ids=="")
    {
       alert("请至少选择一个要删除的成绩！");
       return false;
    }
    if(confirm('确认删除吗!?'))
    {
       document.score.action="Admin_delScores.action?paramsScore.ids="+ids;
       document.score.submit();
    }
    
}

function serch()
{
   document.score.action="Admin_listScores.action";
   document.score.submit();
}
 
function GoPage()
{
  var pagenum=document.getElementById("goPage").value;
  var patten=/^\d+$/;
  if(!patten.exec(pagenum))
  {
    alert("页码必须为大于0的数字");
    return false;
  }
  document.getElementById("pageNo").value=pagenum;
  document.score.action="Admin_listScores.action";
  document.score.submit();
}
function ChangePage(pagenum)
{
  document.getElementById("pageNo").value=pagenum;
  document.score.action="Admin_listScores.action";
  document.score.submit();
}
function exportScores()
{
   document.score.action="exportScores.action";
   document.score.submit();
}
</script>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">在线考试模块&gt;&gt;学生成绩查询</span>
</div>
<form name="score" id="score" action="Admin_listScores.action" method="post">
<input type="hidden" name="pageNo" id="pageNo" value="${pageNo}"/>
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">学生成绩列表</td>
    <td width="" align="right">
      <s:if test="#attr.admin.user_type!=1">
            学生姓名：
      <input type="text" id="paramsScore.user.real_name" name="paramsScore.user.real_name" value="${paramsScore.user.real_name}" class="inputstyle" Style="width:100px"/>&nbsp;
      </s:if>
            批阅状态：
      <s:select list="#{'1':'待批阅','2':'已批阅'}" id="paramsScore.score_flag" name="paramsScore.score_flag" value="%{#attr.paramsScore.score_flag}" 
      		listKey="key" listValue="value" headerKey="0" headerValue="请选择"
      		cssClass="selectstyle" cssStyle="width:100px" >
      </s:select>&nbsp;
      <input type="button" value="搜索" onclick="serch();" class="btnstyle"/>&nbsp;
      <s:if test="#attr.admin.user_type!=1">
      <input type="button" value="导出" onclick="exportScores();" class="btnstyle"/>&nbsp;
      <input type="button" value="删除" onclick="del();" class="btnstyle"/>
      </s:if>
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
<table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
     <s:if test="#attr.admin.user_type!=1">
     <td width="40" align="center"><input type="checkbox" onclick="CheckAll(this);" style="vertical-align:text-bottom;" title="全选/取消全选"/></td>
     </s:if>
     <td width="40" align="center">序号</td>
     <td width="" align="center">学生姓名</td>
     <td width="" align="center">填空题</td>
     <td width="" align="center">选择题</td>
     <td width="" align="center">判断题</td>
     <td width="" align="center">编程题</td>
     <td width="" align="center">总得分</td>
     <td width="" align="center">答题时间</td>
     <td width="" align="center">状态</td>
     <td width="" align="center">操作</td>
   </tr>
   <s:if test="#attr.scores!=null && #attr.scores.size()>0">
   <s:iterator value="#attr.scores" id="score" status="status">
   <tr class="<s:if test='(#status.index + 1)%2==0'>list1</s:if><s:else>list0</s:else>" onmouseover="tr_mouseover(this)" onmouseout="tr_mouseout(this)"> 
     <s:if test="#attr.admin.user_type!=1">
     <td width="" align="center"><s:checkbox name="chkid" fieldValue="%{#score.score_id}" cssStyle="vertical-align:text-bottom;"/></td>
     </s:if>
     <td width="" align="center"><s:property value="#status.index+1+#attr.paramsScore.start"/></td>
     <td width="" align="center"><s:property value="#score.user.real_name"/></td>
     <td width="" align="center"><s:property value="#score.ques1_score"/></td>
     <td width="" align="center"><s:property value="#score.ques2_score"/></td>
     <td width="" align="center"><s:property value="#score.ques3_score"/></td>
     <td width="" align="center"><s:property value="#score.ques4_score"/></td>
     <td width="" align="center"><s:property value="#score.sum_score"/></td>
     <td width="" align="center"><s:property value="#score.score_dateDesc"/></td>
     <td width="" align="center"><s:property value="#score.score_flagDesc"/></td>
     <td width="" align="center">
       <s:a href="Admin_queryDetail.action?paramsDetail.score_id=%{#score.score_id}">答题详情</s:a>&nbsp;
       <s:if test="#score.score_flag==1 && #attr.admin.user_type!=1">
       <s:a href="Admin_editDetail.action?paramsDetail.score_id=%{#score.score_id}">批阅答题</s:a>
       </s:if>
     </td>
   </tr> 
   </s:iterator>
   </s:if>
   <s:else>
   <tr>
     <td height="60" colspan="11" align="center"><b>&lt;不存在学生成绩信息&gt;</b></td>
   </tr>
   </s:else>
   
</table>
<jsp:include page="page.jsp"></jsp:include>
</form> 
</body>
</html>