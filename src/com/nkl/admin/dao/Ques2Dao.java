package com.nkl.admin.dao;

import java.util.ArrayList;
import java.util.List;
import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.admin.domain.Ques2;

public class Ques2Dao extends BaseDao {

	public void addQues2(Ques2 ques2){
		super.add(ques2);
	}

	public void delQues2(Integer ques2_id){
		super.del(Ques2.class, ques2_id);
	}

	public void delQues2s(String[] ques2_ids){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <ques2_ids.length; i++) {
			sBuilder.append(ques2_ids[i]);
			if (i !=ques2_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String hql = "DELETE FROM Ques2 WHERE ques2_id IN(" +sBuilder.toString()+")";

		Object[] params = null;

		super.executeUpdateHql(hql, params);
	}

	public void updateQues2(Ques2 ques2){
		Ques2 _ques2 = (Ques2)super.get(Ques2.class, ques2.getQues2_id());
		if (!StringUtil.isEmptyString(ques2.getQues2_name())) {
			_ques2.setQues2_name(ques2.getQues2_name());
		}
		if (ques2.getQues2_single()!=null && ques2.getQues2_single()!=0) {
			_ques2.setQues2_single(ques2.getQues2_single());
		}
		if (ques2.getQues2_score()!=null && ques2.getQues2_score()!=0) {
			_ques2.setQues2_score(ques2.getQues2_score());
		}
		if (!StringUtil.isEmptyString(ques2.getQues2_answer())) {
			_ques2.setQues2_answer(ques2.getQues2_answer());
		}
		super.update(_ques2);
	}

	@SuppressWarnings("rawtypes")
	public Ques2 getQues2(Ques2 ques2){
		Ques2 _ques2=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Ques2 WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (ques2.getQues2_id()!=null && ques2.getQues2_id()!=0) {
			sBuilder.append(" and ques2_id = ? ");
			paramsList.add(ques2.getQues2_id());
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
			_ques2 = (Ques2)list.get(0);
		}

		return _ques2;
	}

	@SuppressWarnings("rawtypes")
	public List<Ques2>  listQues2s(Ques2 ques2){
		List<Ques2> ques2s = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Ques2 WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (ques2.getQues2_id()!=null && ques2.getQues2_id()!=0) {
			sBuilder.append(" and ques2_id = ? ");
			paramsList.add(ques2.getQues2_id());
		}
		if (!StringUtil.isEmptyString(ques2.getQues2_name())) {
			sBuilder.append(" and ques2_name like '%" + ques2.getQues2_name() +"%' ");
		}
		if (ques2.getQues2_single()!=null && ques2.getQues2_single()!=0) {
			sBuilder.append(" and ques2_single = " + ques2.getQues2_single() +" ");
		}

		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}

		if (!StringUtil.isEmptyString(ques2.getRandom())) {
			sBuilder.append(" order by rand() ");
		}else {
			sBuilder.append(" order by ques2_id asc ");
		}

		List list = null;
		if (ques2.getStart()!=-1) {
			list = super.findByPageHql(sBuilder.toString(), params, ques2.getStart(), ques2.getLimit());
		}else {
			list = super.executeQueryHql(sBuilder.toString(), params);
		}
		if (list != null && list.size() > 0) {
			ques2s = new ArrayList<Ques2>();
			for (Object object : list) {
				ques2s.add((Ques2)object);
			}
		}

		return ques2s;
	}

	public int  listQues2sCount(Ques2 ques2){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM Ques2 WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (ques2.getQues2_id()!=null && ques2.getQues2_id()!=0) {
			sBuilder.append(" and ques2_id = ? ");
			paramsList.add(ques2.getQues2_id());
		}
		if (!StringUtil.isEmptyString(ques2.getQues2_name())) {
			sBuilder.append(" and ques2_name like '%" + ques2.getQues2_name() +"%' ");
		}
		if (ques2.getQues2_single()!=null && ques2.getQues2_single()!=0) {
			sBuilder.append(" and ques2_single = " + ques2.getQues2_single() +" ");
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
		sBuilder.append("SELECT max(ques2_id) FROM Ques2 ");
		
		Integer count = (Integer)super.executeQueryHql(sBuilder.toString(), null).get(0);
		info_id = (int)count;
		return info_id;
	}

}
