package com.nkl.admin.domain;

import com.nkl.common.domain.BaseDomain;
import com.nkl.common.util.StringUtil;
import com.nkl.common.util.Transcode;

public class Item extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1046248708400526535L;
	private Integer item_id; // 
	private Integer ques2_id; // 
	private String item_no; // 
	private String item_name; // 
	private Integer item_single; // 
	private Integer item_right; //1-是 2-否 

	private String ques2_name; // 
	
	private String ids;
	private String random;

	public String getItem_nameShow(){
		if (!StringUtil.isEmptyString(item_name)) {
			return Transcode.htmlDiscode(item_name);
		}
		return item_name;
	}

	public Integer getItem_id() {
		return item_id;
	}

	public Integer getQues2_id() {
		return ques2_id;
	}

	public String getItem_no() {
		return item_no;
	}

	public String getItem_name() {
		return item_name;
	}

	public Integer getItem_single() {
		return item_single;
	}

	public Integer getItem_right() {
		return item_right;
	}

	public String getQues2_name() {
		return ques2_name;
	}

	public String getIds() {
		return ids;
	}

	public String getRandom() {
		return random;
	}

	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}

	public void setQues2_id(Integer ques2_id) {
		this.ques2_id = ques2_id;
	}

	public void setItem_no(String item_no) {
		this.item_no = item_no;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public void setItem_single(Integer item_single) {
		this.item_single = item_single;
	}

	public void setItem_right(Integer item_right) {
		this.item_right = item_right;
	}

	public void setQues2_name(String ques2_name) {
		this.ques2_name = ques2_name;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setRandom(String random) {
		this.random = random;
	}
	
	 
}
