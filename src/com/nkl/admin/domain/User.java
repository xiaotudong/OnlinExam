package com.nkl.admin.domain;

import java.util.Date;

import com.nkl.common.domain.BaseDomain;
import com.nkl.common.util.DateUtil;

public class User extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 935450826788987376L;
	private Integer user_id; // 
	private String user_name; // 
	private String user_pass; // 
	private String real_name; // 
	private Integer user_sex; // 1：男 0：女
	private Integer user_age; // 
	private String user_mail; // 
	private Date reg_date; // 
	private Integer user_type; // 1：学生 2：教师 3：管理员 
	
	private String ids; //  
	private String random;
	private String user_types;
	private String user_passOld; // 
	
	public String getReg_dateDesc(){
		if (reg_date!=null) {
			return DateUtil.dateToDateString(reg_date);
		}else {
			return null;
		}
	}
	
	public String getUser_typeDesc() {
		switch (user_type) {
		case 1:
			return "学生";
		case 2:
			return "教师";
		case 3:
			return "管理员";
		default:
			return "";
		}
	}
	
	public String getUser_sexDesc(){
		switch (user_sex) {
		case 1:
			return "男";
		case 2:
			return "女";
		default:
			return "男";
		}
	}

	public Integer getUser_id() {
		return user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public String getUser_pass() {
		return user_pass;
	}

	public String getReal_name() {
		return real_name;
	}

	public Integer getUser_sex() {
		return user_sex;
	}

	public Integer getUser_age() {
		return user_age;
	}

	public String getUser_mail() {
		return user_mail;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public Integer getUser_type() {
		return user_type;
	}

	public String getIds() {
		return ids;
	}

	public String getRandom() {
		return random;
	}

	public String getUser_types() {
		return user_types;
	}

	public String getUser_passOld() {
		return user_passOld;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public void setUser_sex(Integer user_sex) {
		this.user_sex = user_sex;
	}

	public void setUser_age(Integer user_age) {
		this.user_age = user_age;
	}

	public void setUser_mail(String user_mail) {
		this.user_mail = user_mail;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public void setUser_type(Integer user_type) {
		this.user_type = user_type;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public void setUser_types(String user_types) {
		this.user_types = user_types;
	}

	public void setUser_passOld(String user_passOld) {
		this.user_passOld = user_passOld;
	}
	
	 

}
