package com.nkl.admin.domain;

import java.util.Date;

import com.nkl.common.domain.BaseDomain;
import com.nkl.common.util.DateUtil;

public class Score extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 5376026488216840230L;
	private Integer score_id; // 
	private User user; // 
	private Double ques1_score; // 
	private Double ques2_score; // 
	private Double ques3_score; // 
	private Double ques4_score; // 
	private Double sum_score; // 
	private Date score_date; // 
	private Integer score_flag; //1:待批阅 2:已批阅

	private String real_name; // 
	private double score_valueMin; 
	private double score_valueMax; 
	
	private String ids;
	private String random;

	public String getScore_dateDesc(){
		if (score_date!=null) {
			return DateUtil.dateToDateString(score_date);
		}else {
			return null;
		}
	}
	
	public String getScore_flagDesc(){
		switch (score_flag) {
		case 1:
			return "待批阅";
		case 2:
			return "已批阅";
		default:
			return "";
		}
	}

	public Integer getScore_id() {
		return score_id;
	}

	public Double getQues1_score() {
		return ques1_score;
	}

	public Double getQues2_score() {
		return ques2_score;
	}

	public Double getQues3_score() {
		return ques3_score;
	}

	public Double getQues4_score() {
		return ques4_score;
	}

	public Double getSum_score() {
		return sum_score;
	}

	public Date getScore_date() {
		return score_date;
	}

	public Integer getScore_flag() {
		return score_flag;
	}

	public String getReal_name() {
		return real_name;
	}

	public double getScore_valueMin() {
		return score_valueMin;
	}

	public double getScore_valueMax() {
		return score_valueMax;
	}

	public String getIds() {
		return ids;
	}

	public String getRandom() {
		return random;
	}

	public void setScore_id(Integer score_id) {
		this.score_id = score_id;
	}

	public void setQues1_score(Double ques1_score) {
		this.ques1_score = ques1_score;
	}

	public void setQues2_score(Double ques2_score) {
		this.ques2_score = ques2_score;
	}

	public void setQues3_score(Double ques3_score) {
		this.ques3_score = ques3_score;
	}

	public void setQues4_score(Double ques4_score) {
		this.ques4_score = ques4_score;
	}

	public void setSum_score(Double sum_score) {
		this.sum_score = sum_score;
	}

	public void setScore_date(Date score_date) {
		this.score_date = score_date;
	}

	public void setScore_flag(Integer score_flag) {
		this.score_flag = score_flag;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public void setScore_valueMin(double score_valueMin) {
		this.score_valueMin = score_valueMin;
	}

	public void setScore_valueMax(double score_valueMax) {
		this.score_valueMax = score_valueMax;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	 
}
