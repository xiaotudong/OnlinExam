package com.nkl.admin.domain;

import java.util.List;

import com.nkl.common.domain.BaseDomain;
import com.nkl.common.util.StringUtil;
import com.nkl.common.util.Transcode;

public class Ques2 extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -1328167881087094433L;
	private Integer ques2_id; // 
	private String ques2_name; // 
	private Integer ques2_single; // 
	private Double ques2_score; // 
	private String ques2_answer; // 
	
	private List<Item> items;
	private String paper_name; // 
	private String A; // 
	private String B; // 
	private String C; // 
	private String D; // 
	private String[] answers; // 
	
	private String[] answers2; // 测评答案
	private int result; // 1-正确 2-错误
	
	private String ids;
	private String random;

	public String getQues2_nameShow(){
		if (!StringUtil.isEmptyString(ques2_name)) {
			return Transcode.htmlDiscode(ques2_name);
		}
		return ques2_name;
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

	public Integer getQues2_id() {
		return ques2_id;
	}

	public String getQues2_name() {
		return ques2_name;
	}

	public Integer getQues2_single() {
		return ques2_single;
	}

	public Double getQues2_score() {
		return ques2_score;
	}

	public String getQues2_answer() {
		return ques2_answer;
	}

	public List<Item> getItems() {
		return items;
	}

	public String getPaper_name() {
		return paper_name;
	}

	public String getA() {
		return A;
	}

	public String getB() {
		return B;
	}

	public String getC() {
		return C;
	}

	public String getD() {
		return D;
	}

	public String[] getAnswers() {
		return answers;
	}

	public String[] getAnswers2() {
		return answers2;
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

	public void setQues2_id(Integer ques2_id) {
		this.ques2_id = ques2_id;
	}

	public void setQues2_name(String ques2_name) {
		this.ques2_name = ques2_name;
	}

	public void setQues2_single(Integer ques2_single) {
		this.ques2_single = ques2_single;
	}

	public void setQues2_score(Double ques2_score) {
		this.ques2_score = ques2_score;
	}

	public void setQues2_answer(String ques2_answer) {
		this.ques2_answer = ques2_answer;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public void setPaper_name(String paper_name) {
		this.paper_name = paper_name;
	}

	public void setA(String a) {
		A = a;
	}

	public void setB(String b) {
		B = b;
	}

	public void setC(String c) {
		C = c;
	}

	public void setD(String d) {
		D = d;
	}

	public void setAnswers(String[] answers) {
		this.answers = answers;
	}

	public void setAnswers2(String[] answers2) {
		this.answers2 = answers2;
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
