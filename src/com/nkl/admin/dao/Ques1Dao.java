package com.nkl.admin.dao;

import java.util.ArrayList;
import java.util.List;
import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.admin.domain.Ques1;

public class Ques1Dao extends BaseDao {

	public void addQues1(Ques1 ques1){
		super.add(ques1);
	}

	public void delQues1(Integer ques1_id){
		super.del(Ques1.class, ques1_id);
	}

	public void delQues1s(String[] ques1_ids){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <ques1_ids.length; i++) {
			sBuilder.append(ques1_ids[i]);
			if (i !=ques1_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String hql = "DELETE FROM Ques1 WHERE ques1_id IN(" +sBuilder.toString()+")";

		Object[] params = null;

		super.executeUpdateHql(hql, params);
	}

	public void updateQues1(Ques1 ques1){
		Ques1 _ques1 = (Ques1)super.get(Ques1.class, ques1.getQues1_id());
		if (!StringUtil.isEmptyString(_ques1.getQues1_name1())) {
			_ques1.setQues1_name1(_ques1.getQues1_name1());
		}
		if (!StringUtil.isEmptyString(_ques1.getQues1_name2())) {
			_ques1.setQues1_name2(_ques1.getQues1_name2());
		}
		if (_ques1.getQues1_score()!=null && _ques1.getQues1_score()!=0) {
			_ques1.setQues1_score(_ques1.getQues1_score());
		}
		if (!StringUtil.isEmptyString(_ques1.getQues1_answer())) {
			_ques1.setQues1_answer(_ques1.getQues1_answer());
		}
		super.update(_ques1);
	}

	@SuppressWarnings("rawtypes")
	public Ques1 getQues1(Ques1 ques1){
		Ques1 _ques1=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Ques1 WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (ques1.getQues1_id()!=null && ques1.getQues1_id()!=0) {
			sBuilder.append(" and ques1_id = ? ");
			paramsList.add(ques1.getQues1_id());
		}

		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}

		List list = super.executeQueryHql(sBuilder.toString(), params);
		if (list != null && list.size() > 0) {
			_ques1 = (Ques1)list.get(0);
		}

		return _ques1;
	}

	@SuppressWarnings("rawtypes")
	public List<Ques1>  listQues1s(Ques1 ques1){
		List<Ques1> ques1s = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Ques1 WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (ques1.getQues1_id()!=null && ques1.getQues1_id()!=0) {
			sBuilder.append(" and ques1_id = ? ");
			paramsList.add(ques1.getQues1_id());
		}
		if (!StringUtil.isEmptyString(ques1.getQues1_name1())) {
			sBuilder.append(" and CONCAT(ques1_name1,ques1_name2) like '%" + ques1.getQues1_name1() +"%' ");
		}
		

		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}

		if (!StringUtil.isEmptyString(ques1.getRandom())) {
			sBuilder.append(" order by rand() ");
		}else {
			sBuilder.append(" order by ques1_id asc ");
		}

		List list = null;
		if (ques1.getStart()!=-1) {
			list = super.findByPageHql(sBuilder.toString(), params, ques1.getStart(), ques1.getLimit());
		}else {
			list = super.executeQueryHql(sBuilder.toString(), params);
		}
		if (list != null && list.size() > 0) {
			ques1s = new ArrayList<Ques1>();
			for (Object object : list) {
				ques1s.add((Ques1)object);
			}
		}

		return ques1s;
	}

	public int  listQues1sCount(Ques1 ques1){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM Ques1 WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (ques1.getQues1_id()!=null && ques1.getQues1_id()!=0) {
			sBuilder.append(" and ques1_id = ? ");
			paramsList.add(ques1.getQues1_id());
		}

		if (!StringUtil.isEmptyString(ques1.getQues1_name1())) {
			sBuilder.append(" and CONCAT(ques1_name1,ques1_name2) like '%" + ques1.getQues1_name1() +"%' ");
		}

		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}

		long count = (Long)super.executeQueryCountHql(sBuilder.toString(), params);
		sum = (int)count;
		return sum;
	}

	public int  getQuesId(){
		int info_id = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT max(ques1_id) FROM Ques1 ");
		
		Integer count = (Integer)super.executeQueryHql(sBuilder.toString(), null).get(0);
		info_id = (int)count;
		return info_id;
	}
}
