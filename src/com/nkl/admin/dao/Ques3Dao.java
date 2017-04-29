package com.nkl.admin.dao;

import java.util.ArrayList;
import java.util.List;
import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.admin.domain.Ques3;

public class Ques3Dao extends BaseDao {

	public void addQues3(Ques3 ques3){
		super.add(ques3);
	}

	public void delQues3(Integer ques3_id){
		super.del(Ques3.class, ques3_id);
	}

	public void delQues3s(String[] ques3_ids){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <ques3_ids.length; i++) {
			sBuilder.append(ques3_ids[i]);
			if (i !=ques3_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String hql = "DELETE FROM Ques3 WHERE ques3_id IN(" +sBuilder.toString()+")";

		Object[] params = null;

		super.executeUpdateHql(hql, params);
	}

	public void updateQues3(Ques3 ques3){
		Ques3 _ques3 = (Ques3)super.get(Ques3.class, ques3.getQues3_id());
		if (!StringUtil.isEmptyString(ques3.getQues3_name())) {
			_ques3.setQues3_name(ques3.getQues3_name());
		}
		if (ques3.getQues3_score()!=null && ques3.getQues3_score()!=0) {
			_ques3.setQues3_score(ques3.getQues3_score());
		}
		if (ques3.getQues3_answer()!=null && ques3.getQues3_answer()!=0) {
			_ques3.setQues3_answer(ques3.getQues3_answer());
		}
		super.update(_ques3);
	}

	@SuppressWarnings("rawtypes")
	public Ques3 getQues3(Ques3 ques3){
		Ques3 _ques3=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Ques3 WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (ques3.getQues3_id()!=null && ques3.getQues3_id()!=0) {
			sBuilder.append(" and ques3_id = ? ");
			paramsList.add(ques3.getQues3_id());
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
			_ques3 = (Ques3)list.get(0);
		}

		return _ques3;
	}

	@SuppressWarnings("rawtypes")
	public List<Ques3>  listQues3s(Ques3 ques3){
		List<Ques3> ques3s = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Ques3 WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (ques3.getQues3_id()!=null && ques3.getQues3_id()!=0) {
			sBuilder.append(" and ques3_id = ? ");
			paramsList.add(ques3.getQues3_id());
		}
		if (!StringUtil.isEmptyString(ques3.getQues3_name())) {
			sBuilder.append(" and ques3_name like '%" + ques3.getQues3_name() +"%' ");
		}

		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}

		if (!StringUtil.isEmptyString(ques3.getRandom())) {
			sBuilder.append(" order by rand()  ");
		}else {
			sBuilder.append(" order by ques3_id asc ");
		}

		List list = null;
		if (ques3.getStart()!=-1) {
			list = super.findByPageHql(sBuilder.toString(), params, ques3.getStart(), ques3.getLimit());
		}else {
			list = super.executeQueryHql(sBuilder.toString(), params);
		}
		if (list != null && list.size() > 0) {
			ques3s = new ArrayList<Ques3>();
			for (Object object : list) {
				ques3s.add((Ques3)object);
			}
		}

		return ques3s;
	}

	public int  listQues3sCount(Ques3 ques3){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM Ques3 WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (ques3.getQues3_id()!=null && ques3.getQues3_id()!=0) {
			sBuilder.append(" and ques3_id = ? ");
			paramsList.add(ques3.getQues3_id());
		}
		if (!StringUtil.isEmptyString(ques3.getQues3_name())) {
			sBuilder.append(" and ques3_name like '%" + ques3.getQues3_name() +"%' ");
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
		sBuilder.append("SELECT max(ques3_id) FROM Ques3 ");
		
		Integer count = (Integer)super.executeQueryHql(sBuilder.toString(), null).get(0);
		info_id = (int)count;
		return info_id;
	}

}
