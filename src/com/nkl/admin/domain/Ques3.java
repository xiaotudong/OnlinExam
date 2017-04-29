package com.nkl.admin.domain;

import com.nkl.common.domain.BaseDomain;
import com.nkl.common.util.StringUtil;
import com.nkl.common.util.Transcode;

public class Ques3 extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -1328167881087094433L;
	private Integer ques3_id; // 
	private String ques3_name; // 
	private Double ques3_score; // 
	private Integer ques3_answer; // 
	
	private int answer; // 测评答案
	private int result; // 1-正确 2-错误
	
	private String ids;
	private String random;

	public String getQues3_nameShow(){
		if (!StringUtil.isEmptyString(ques3_name)) {
			return Transcode.htmlDiscode(ques3_name);
		}
		return ques3_name;
	}
	
	public String getQues3_answerDesc(){
		switch (ques3_answer) {
		case 1:
			return "正确";
		case 2:
			return "错误";
		default:
			return "";
		}
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

	public Integer getQues3_id() {
		return ques3_id;
	}

	public String getQues3_name() {
		return ques3_name;
	}

	public Double getQues3_score() {
		return ques3_score;
	}

	public Integer getQues3_answer() {
		return ques3_answer;
	}

	public int getAnswer() {
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

	public void setQues3_id(Integer ques3_id) {
		this.ques3_id = ques3_id;
	}

	public void setQues3_name(String ques3_name) {
		this.ques3_name = ques3_name;
	}

	public void setQues3_score(Double ques3_score) {
		this.ques3_score = ques3_score;
	}

	public void setQues3_answer(Integer ques3_answer) {
		this.ques3_answer = ques3_answer;
	}

	public void setAnswer(int answer) {
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
