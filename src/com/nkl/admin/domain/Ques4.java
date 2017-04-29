package com.nkl.admin.domain;

import com.nkl.common.domain.BaseDomain;
import com.nkl.common.util.StringUtil;
import com.nkl.common.util.Transcode;

public class Ques4 extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -1328167881087094433L;
	private Integer ques4_id; // 
	private String ques4_name; // 
	private Double ques4_score; // 
	private String ques4_answer; // 
	
	private String answer; // 测评答案
	private int result; // 1-正确 2-错误
	
	private String ids;
	private String random;

	public String getQues4_nameShow(){
		if (!StringUtil.isEmptyString(ques4_name)) {
			return Transcode.htmlDiscode(ques4_name);
		}
		return ques4_name;
	}
	
	public String getResultDesc(){
		switch (result) {
		case 1:
			return "正确";
		case 2:
			return "错误";
		default:
			return "";
		}
	}
	
	public String getQues4_answerShow(){
		if (!StringUtil.isEmptyString(ques4_answer)) {
			return Transcode.htmlDiscode(ques4_answer);
		}
		return ques4_name;
	}

	public Integer getQues4_id() {
		return ques4_id;
	}

	public String getQues4_name() {
		return ques4_name;
	}

	public Double getQues4_score() {
		return ques4_score;
	}

	public String getQues4_answer() {
		return ques4_answer;
	}

	public String getAnswer() {
		return answer;
	}

	public int getResult() {
		return result;
	}

	public String getIds() {
		return ids;
	}

	public String getRandom() {
		return random;
	}

	public void setQues4_id(Integer ques4_id) {
		this.ques4_id = ques4_id;
	}

	public void setQues4_name(String ques4_name) {
		this.ques4_name = ques4_name;
	}

	public void setQues4_score(Double ques4_score) {
		this.ques4_score = ques4_score;
	}

	public void setQues4_answer(String ques4_answer) {
		this.ques4_answer = ques4_answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setRandom(String random) {
		this.random = random;
	}
	
	 

	 
}
