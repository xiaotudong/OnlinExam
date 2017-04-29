package com.nkl.admin.dao;

import java.util.ArrayList;
import java.util.List;

import com.nkl.admin.domain.Score;
import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;

public class ScoreDao  extends BaseDao {

	public void addScore(Score score){
		super.add(score);
	}

	public void delScore(Integer score_id){
		super.del(Score.class, score_id);
	}

	public void delScores(String[] score_ids){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <score_ids.length; i++) {
			sBuilder.append(score_ids[i]);
			if (i !=score_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String hql = "DELETE FROM Score WHERE score_id IN(" +sBuilder.toString()+")";

		Object[] params = null;

		super.executeUpdateHql(hql, params);
	}
	
	public void delScoreByStuId(String user_id){
		String hql = "DELETE FROM Score s WHERE s.user.user_id=?";

		Object[] params = new Object[] { new Integer(user_id)};
		super.executeUpdateHql(hql, params);
	}

	public void updateScore(Score score){
		Score _score = (Score)super.get(Score.class, score.getScore_id());
		if (score.getQues4_score()!=0) {
			_score.setQues4_score(score.getQues4_score());
		}
		if (score.getSum_score()!=0) {
			_score.setSum_score(score.getSum_score());
		}
		if (score.getScore_flag()!=0) {
			_score.setScore_flag(score.getScore_flag());
		}
		super.update(_score);
	}
	
	@SuppressWarnings("rawtypes")
	public Score getScore(Score score){
		Score _score=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Score s");
		sBuilder.append("  join fetch s.user u WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (score.getScore_id()!=null && score.getScore_id()!=0) {
			sBuilder.append(" and score_id = ? ");
			paramsList.add(score.getScore_id());
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
			_score = (Score)list.get(0);
		}

		return _score;
	}

	@SuppressWarnings("rawtypes")
	public List<Score>  listScores(Score score){
		List<Score> scores = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Score s");
		sBuilder.append("  join fetch s.user u WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (score.getScore_id()!=null && score.getScore_id()!=0) {
			sBuilder.append(" and score_id = ? ");
			paramsList.add(score.getScore_id());
		}
		if (score.getUser()!=null && score.getUser().getUser_id()!=null && score.getUser().getUser_id()!=0) {
			sBuilder.append(" and u.user_id = " + score.getUser().getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(score.getReal_name())) {
			sBuilder.append(" and u.real_name like '%" + score.getReal_name() +"%' ");
		}
		if (score.getScore_valueMin()!=0) {
			sBuilder.append(" and s.sum_score >= " + score.getScore_valueMin() +" ");
		}
		if (score.getScore_valueMax()!=0) {
			sBuilder.append(" and s.sum_score <= " + score.getScore_valueMax() +" ");
		}
		if (score.getScore_flag()!=null && score.getScore_flag()!=0) {
			sBuilder.append(" and s.score_flag = " + score.getScore_flag() +" ");
		}

		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}

		sBuilder.append(" order by score_date desc,score_id asc ");

		List list = null;
		if (score.getStart()!=-1) {
			list = super.findByPageHql(sBuilder.toString(), params, score.getStart(), score.getLimit());
		}else {
			list = super.executeQueryHql(sBuilder.toString(), params);
		}
		if (list != null && list.size() > 0) {
			scores = new ArrayList<Score>();
			for (Object object : list) {
				scores.add((Score)object);
			}
		}

		return scores;
	}

	public int  listScoresCount(Score score){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM Score s join s.user u WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (score.getScore_id()!=null && score.getScore_id()!=0) {
			sBuilder.append(" and score_id = ? ");
			paramsList.add(score.getScore_id());
		}
		if (score.getUser()!=null && score.getUser().getUser_id()!=null && score.getUser().getUser_id()!=0) {
			sBuilder.append(" and u.user_id = " + score.getUser().getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(score.getReal_name())) {
			sBuilder.append(" and u.real_name like '%" + score.getReal_name() +"%' ");
		}
		if (score.getScore_valueMin()!=0) {
			sBuilder.append(" and s.sum_score >= " + score.getScore_valueMin() +" ");
		}
		if (score.getScore_valueMax()!=0) {
			sBuilder.append(" and s.sum_score <= " + score.getScore_valueMax() +" ");
		}
		if (score.getScore_flag()!=null && score.getScore_flag()!=0) {
			sBuilder.append(" and s.score_flag = " + score.getScore_flag() +" ");
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
 
	
	public int getScoreId(int user_id){
		int score_id = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT max(s.score_id) FROM Score s join s.user u ");
		sBuilder.append(" where u.user_id = " + user_id + "");

		Integer count = (Integer)super.executeQueryHql(sBuilder.toString(), null).get(0);
		score_id = (int)count;
		return score_id;
	}
	
}
