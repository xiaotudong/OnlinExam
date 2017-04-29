package com.nkl.admin.domain;

import java.util.List;

import com.nkl.common.domain.BaseDomain;
import com.nkl.common.util.StringUtil;
import com.nkl.common.util.Transcode;

public class Detail extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 6501016372178746571L;
	private Integer detail_id; // 
	private Integer score_id; // 
	private Integer ques_id; // 
	private String ques_name; // 
	private Double ques_score; // 
	private String ques_answer; // 
	private Integer ques_type; // 1-填空题 2-选择题 3-判断题 4-编程题
	private String user_answer; // 
	private Double user_score; // 


	private List<Item> items;
	private String ids;
	private String random;
	
	public String getsQues_typeDesc(){
		switch (ques_type) {
		case 1:
			return "填空题";
		case 2:
			return "选择题";
		case 3:
			return "判断题";
		case 4:
			return "编程题";
		default:
			return "";
		}
	}

	 
	public String getQues_nameShow(){
		if (!StringUtil.isEmptyString(ques_name)) {
			return Transcode.htmlDiscode(ques_name);
		}
		return ques_name;
	}

	
	public String getQues_answerShow(){
		if (!StringUtil.isEmptyString(ques_answer)) {
			return Transcode.htmlDiscode(ques_answer);
		}
		return ques_answer;
	}

	public String getUser_answerShow(){
		if (!StringUtil.isEmptyString(user_answer)) {
			return Transcode.htmlDiscode(user_answer);
		}
		return user_answer;
	}


	public Integer getDetail_id() {
		return detail_id;
	}


	public Integer getScore_id() {
		return score_id;
	}


	public Integer getQues_id() {
		return ques_id;
	}


	public String getQues_name() {
		return ques_name;
	}


	public Double getQues_score() {
		return ques_score;
	}


	public String getQues_answer() {
		return ques_answer;
	}


	public Integer getQues_type() {
		return ques_type;
	}


	public String getUser_answer() {
		return user_answer;
	}


	public Double getUser_score() {
		return user_score;
	}


	public List<Item> getItems() {
		return items;
	}


	public String getIds() {
		return ids;
	}


	public String getRandom() {
		return random;
	}


	public void setDetail_id(Integer detail_id) {
		this.detail_id = detail_id;
	}


	public void setScore_id(Integer score_id) {
		this.score_id = score_id;
	}


	public void setQues_id(Integer ques_id) {
		this.ques_id = ques_id;
	}


	public void setQues_name(String ques_name) {
		this.ques_name = ques_name;
	}


	public void setQues_score(Double ques_score) {
		this.ques_score = ques_score;
	}


	public void setQues_answer(String ques_answer) {
		this.ques_answer = ques_answer;
	}


	public void setQues_type(Integer ques_type) {
		this.ques_type = ques_type;
	}


	public void setUser_answer(String user_answer) {
		this.user_answer = user_answer;
	}


	public void setUser_score(Double user_score) {
		this.user_score = user_score;
	}


	public void setItems(List<Item> items) {
		this.items = items;
	}


	public void setIds(String ids) {
		this.ids = ids;
	}


	public void setRandom(String random) {
		this.random = random;
	}
	

}
