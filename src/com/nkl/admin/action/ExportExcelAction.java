
package com.nkl.admin.action;

import java.util.HashMap;
import java.util.List;

import com.nkl.admin.domain.Score;
import com.nkl.admin.domain.User;
import com.nkl.admin.manager.AdminManager;
import com.nkl.common.action.BaseAction;
import com.nkl.common.util.BeanLocator;
import com.nkl.common.util.Param;

public class ExportExcelAction extends BaseAction{

	private static final long serialVersionUID = 8143993190677252109L;
	AdminManager adminManager = (AdminManager)BeanLocator.getInstance().getBean("adminManager");
	
	//导出参数
	Score paramsScore;
	HashMap<String, Object> report = new HashMap<String, Object>();

	/**
	 * @Title: exportScores
	 * @Description: 导出成绩信息
	 * @return String
	 */
	public String exportScores(){
		try{
			if (paramsScore==null) {
				paramsScore = new Score();
			}
			//得到用户信息 作为限制条件
			User admin = (User)Param.getSession("admin");
			if (admin.getUser_type()==1) {
				paramsScore.setUser(admin);
			}
			//设置分页信息
			paramsScore.setStart(-1);
			//查询用户列表
			List<Score> scores = adminManager.listScores(paramsScore,null); 
			report.put("scores", scores);
			
			//设置导出文件名
			setExportExcelName("学生成绩信息.xls");
		}
        catch(Exception e){
            setErrorReason("导出异常，请稍后再试", e);
            return ERROR;
        }
        return SUCCESS; 
	}

	public HashMap<String, Object> getReport() {
		return report;
	}

	public void setReport(HashMap<String, Object> report) {
		this.report = report;
	}



	public Score getParamsScore() {
		return paramsScore;
	}



	public void setParamsScore(Score paramsScore) {
		this.paramsScore = paramsScore;
	}
	
	
}
