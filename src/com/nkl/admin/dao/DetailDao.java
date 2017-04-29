package com.nkl.admin.dao;

import java.util.ArrayList;
import java.util.List;
import com.nkl.common.dao.BaseDao;
import com.nkl.admin.domain.Detail;

public class DetailDao extends BaseDao {

	public void addDetail(Detail detail){
		super.add(detail);
	}

	public void delDetail(Integer detail_id){
		super.del(Detail.class, detail_id);
	}

	public void delDetails(String[] detail_ids){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <detail_ids.length; i++) {
			sBuilder.append(detail_ids[i]);
			if (i !=detail_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String hql = "DELETE FROM Detail WHERE detail_id IN(" +sBuilder.toString()+")";

		Object[] params = null;

		super.executeUpdateHql(hql, params);
	}
	
	public void delDetailsByScoreIds(String[] score_ids){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <score_ids.length; i++) {
			sBuilder.append(score_ids[i]);
			if (i !=score_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String hql = "DELETE FROM Detail WHERE score_id IN(" +sBuilder.toString()+")";

		Object[] params = null;

		super.executeUpdateHql(hql, params);
	}

	public void updateDetail(Detail detail){
		Detail _detail = (Detail)super.get(Detail.class, detail.getDetail_id());
		if (detail.getUser_score()!=null && detail.getUser_score()!=0) {
			_detail.setUser_score(detail.getUser_score());
		}
		super.update(_detail);
	}

	@SuppressWarnings("rawtypes")
	public Detail getDetail(Detail detail){
		Detail _detail=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Detail WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (detail.getDetail_id()!=null && detail.getDetail_id()!=0) {
			sBuilder.append(" and detail_id = ? ");
			paramsList.add(detail.getDetail_id());
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
			_detail = (Detail)list.get(0);
		}

		return _detail;
	}

	@SuppressWarnings("rawtypes")
	public List<Detail>  listDetails(Detail detail){
		List<Detail> details = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Detail WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (detail.getDetail_id()!=null && detail.getDetail_id()!=0) {
			sBuilder.append(" and detail_id = ? ");
			paramsList.add(detail.getDetail_id());
		}
		if (detail.getScore_id()!=null && detail.getScore_id()!=0) {
			sBuilder.append(" and score_id = " + detail.getScore_id() +" ");
		}
		if (detail.getQues_type()!=null && detail.getQues_type()!=0) {
			sBuilder.append(" and ques_type = " + detail.getQues_type() +" ");
		}

		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}

		sBuilder.append(" order by ques_id asc,detail_id asc ");

		List list = null;
		if (detail.getStart()!=-1) {
			list = super.findByPageHql(sBuilder.toString(), params, detail.getStart(), detail.getLimit());
		}else {
			list = super.executeQueryHql(sBuilder.toString(), params);
		}
		if (list != null && list.size() > 0) {
			details = new ArrayList<Detail>();
			for (Object object : list) {
				details.add((Detail)object);
			}
		}

		return details;
	}

	public int  listDetailsCount(Detail detail){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM Detail WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (detail.getDetail_id()!=null && detail.getDetail_id()!=0) {
			sBuilder.append(" and detail_id = ? ");
			paramsList.add(detail.getDetail_id());
		}
		if (detail.getScore_id()!=null && detail.getScore_id()!=0) {
			sBuilder.append(" and score_id = " + detail.getScore_id() +" ");
		}
		if (detail.getQues_type()!=null && detail.getQues_type()!=0) {
			sBuilder.append(" and ques_type = " + detail.getQues_type() +" ");
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

}
