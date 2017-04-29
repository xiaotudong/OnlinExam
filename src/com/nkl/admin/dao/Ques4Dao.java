package com.nkl.admin.dao;

import java.util.ArrayList;
import java.util.List;
import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.admin.domain.Ques4;

public class Ques4Dao extends BaseDao {

	public void addQues4(Ques4 ques4){
		super.add(ques4);
	}

	public void delQues4(Integer ques4_id){
		super.del(Ques4.class, ques4_id);
	}

	public void delQues4s(String[] ques4_ids){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <ques4_ids.length; i++) {
			sBuilder.append(ques4_ids[i]);
			if (i !=ques4_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String hql = "DELETE FROM Ques4 WHERE ques4_id IN(" +sBuilder.toString()+")";

		Object[] params = null;

		super.executeUpdateHql(hql, params);
	}

	public void updateQues4(Ques4 ques4){
		Ques4 _ques4 = (Ques4)super.get(Ques4.class, ques4.getQues4_id());
		if (!StringUtil.isEmptyString(ques4.getQues4_name())) {
			_ques4.setQues4_name(ques4.getQues4_name());
		}
		if (ques4.getQues4_score()!=null && ques4.getQues4_score()!=0) {
			_ques4.setQues4_score(ques4.getQues4_score());
		}
		if (!StringUtil.isEmptyString(ques4.getQues4_answer())) {
			_ques4.setQues4_answer(ques4.getQues4_answer());
		}
		super.update(_ques4);
	}

	@SuppressWarnings("rawtypes")
	public Ques4 getQues4(Ques4 ques4){
		Ques4 _ques4=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Ques4 WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (ques4.getQues4_id()!=null && ques4.getQues4_id()!=0) {
			sBuilder.append(" and ques4_id = ? ");
			paramsList.add(ques4.getQues4_id());
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
			_ques4 = (Ques4)list.get(0);
		}

		return _ques4;
	}

	@SuppressWarnings("rawtypes")
	public List<Ques4>  listQues4s(Ques4 ques4){
		List<Ques4> ques4s = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Ques4 WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (ques4.getQues4_id()!=null && ques4.getQues4_id()!=0) {
			sBuilder.append(" and ques4_id = ? ");
			paramsList.add(ques4.getQues4_id());
		}
		if (!StringUtil.isEmptyString(ques4.getQues4_name())) {
			sBuilder.append(" and CONCAT(ques4_name,ques4_name2) like '%" + ques4.getQues4_name() +"%' ");
		}

		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}

		if (!StringUtil.isEmptyString(ques4.getRandom())) {
			sBuilder.append(" order by rand() ");
		}else {
			sBuilder.append(" order by ques4_id asc ");
		}

		List list = null;
		if (ques4.getStart()!=-1) {
			list = super.findByPageHql(sBuilder.toString(), params, ques4.getStart(), ques4.getLimit());
		}else {
			list = super.executeQueryHql(sBuilder.toString(), params);
		}
		if (list != null && list.size() > 0) {
			ques4s = new ArrayList<Ques4>();
			for (Object object : list) {
				ques4s.add((Ques4)object);
			}
		}

		return ques4s;
	}

	public int  listQues4sCount(Ques4 ques4){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM Ques4 WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (ques4.getQues4_id()!=null && ques4.getQues4_id()!=0) {
			sBuilder.append(" and ques4_id = ? ");
			paramsList.add(ques4.getQues4_id());
		}
		if (!StringUtil.isEmptyString(ques4.getQues4_name())) {
			sBuilder.append(" and CONCAT(ques4_name,ques4_name2) like '%" + ques4.getQues4_name() +"%' ");
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
		sBuilder.append("SELECT max(ques4_id) FROM Ques4 ");
		
		Integer count = (Integer)super.executeQueryHql(sBuilder.toString(), null).get(0);
		info_id = (int)count;
		return info_id;
	}

}
