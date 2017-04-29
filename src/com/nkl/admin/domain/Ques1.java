package com.nkl.admin.domain;

import com.nkl.common.domain.BaseDomain;
import com.nkl.common.util.StringUtil;
import com.nkl.common.util.Transcode;

public class Ques1 extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -1328167881087094433L;
	private Integer ques1_id; // 
	private String ques1_name1; // 
	private String ques1_name2; // 
	private Double ques1_score; // 
	private String ques1_answer; // 
	
	private String answer; // 测评答案
	private int result; // 1-正确 2-错误
	
	private String ids;
	private String random;

	public String getQues1_name1Show(){
		if (!StringUtil.isEmptyString(ques1_name1)) {
			return Transcode.htmlDiscode(ques1_name1);
		}
		return ques1_name1;
	}
	
	public String getQues1_name2Show(){
		if (!StringUtil.isEmptyString(ques1_name2)) {
			return Transcode.htmlDiscode(ques1_name2);
		}
		return ques1_name2;
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

	public Integer getQues1_id() {
		return ques1_id;
	}

	public String getQues1_name1() {
		return ques1_name1;
	}

	public String getQues1_name2() {
		return ques1_name2;
	}

	public Double getQues1_score() {
		return ques1_score;
	}

	public String getQues1_answer() {
		return ques1_answer;
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

	public void setQues1_id(Integer ques1_id) {
		this.ques1_id = ques1_id;
	}

	public void setQues1_name1(String ques1_name1) {
		this.ques1_name1 = ques1_name1;
	}

	public void setQues1_name2(String ques1_name2) {
		this.ques1_name2 = ques1_name2;
	}

	public void setQues1_score(Double ques1_score) {
		this.ques1_score = ques1_score;
	}

	public void setQues1_answer(String ques1_answer) {
		this.ques1_answer = ques1_answer;
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
