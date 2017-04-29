package com.nkl.admin.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.nkl.admin.dao.DetailDao;
import com.nkl.admin.dao.ItemDao;
import com.nkl.admin.dao.Ques1Dao;
import com.nkl.admin.dao.Ques2Dao;
import com.nkl.admin.dao.Ques3Dao;
import com.nkl.admin.dao.Ques4Dao;
import com.nkl.admin.dao.ScoreDao;
import com.nkl.admin.dao.UserDao;
import com.nkl.admin.domain.Detail;
import com.nkl.admin.domain.Item;
import com.nkl.admin.domain.Ques1;
import com.nkl.admin.domain.Ques2;
import com.nkl.admin.domain.Ques3;
import com.nkl.admin.domain.Ques4;
import com.nkl.admin.domain.Score;
import com.nkl.admin.domain.User;
import com.nkl.common.util.Md5;
import com.nkl.common.util.Param;
import com.nkl.common.util.StringUtil;
import com.nkl.common.util.Transcode;

public class AdminManager {

	UserDao userDao;
	Ques1Dao ques1Dao;
	Ques2Dao ques2Dao;
	Ques3Dao ques3Dao;
	Ques4Dao ques4Dao;
	ItemDao itemDao;
	ScoreDao scoreDao;
	DetailDao detailDao;
	
	/**
	 * @Title: listUsers
	 * @Description: 用户查询
	 * @param user
	 * @return List<User>
	 */
	public List<User>  listUsers(User user,int[] sum){
		
		if (sum!=null) {
			sum[0] = userDao.listUsersCount(user);
		}
		List<User> users = userDao.listUsers(user);
		
		
		return users;
	}
	
	/**
	 * @Title: getUser
	 * @Description: 用户查询
	 * @param user
	 * @return User
	 */
	public User  getUser(User user){
		
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		User _user = userDao.getUser(user);
		
		return _user;
	}
	 
	/**
	 * @Title: addUser
	 * @Description: 添加用户
	 * @param user
	 * @return void
	 */
	public void  addUser(User user){
		
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		userDao.addUser(user);
		
	}
	
	/**
	 * @Title: updateUser
	 * @Description: 更新用户信息
	 * @param user
	 * @return void
	 */
	public void updateUser(User user){
		
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		userDao.updateUser(user);
		
	}
	
	/**
	 * @Title: delUsers
	 * @Description: 删除用户信息
	 * @param user
	 * @return void
	 */
	public void  delUsers(User user){
		
		userDao.delUsers(user.getIds().split(","));
		
	}
	
	/**
	 * @Title: listQues1s
	 * @Description: 填空题查询
	 * @param ques1
	 * @return List<Ques1>
	 */
	public List<Ques1>  listQues1s(Ques1 ques1,int[] sum){
		
		if (sum!=null) {
			sum[0] = ques1Dao.listQues1sCount(ques1);
		}
		List<Ques1> ques1s = ques1Dao.listQues1s(ques1);
		
		
		return ques1s;
	}
	
	/**
	 * @Title: queryQues1
	 * @Description: 填空题查询
	 * @param ques1
	 * @return Ques1
	 */
	public Ques1  queryQues1(Ques1 ques1){
		
		Ques1 _ques1 = ques1Dao.getQues1(ques1);
		
		return _ques1;
	}
	 
	/**
	 * @Title: addQues1
	 * @Description: 添加填空题
	 * @param ques1
	 * @return void
	 */
	public void  addQues1(Ques1 ques1){
		
		ques1.setQues1_score(2.0);
		ques1.setQues1_name1(Transcode.htmlEncode(ques1.getQues1_name1()));
		ques1.setQues1_name2(Transcode.htmlEncode(ques1.getQues1_name2()));
		ques1Dao.addQues1(ques1);
		
	}
	
	/**
	 * @Title: updateQues1
	 * @Description: 更新填空题信息
	 * @param ques1
	 * @return void
	 */
	public void  updateQues1(Ques1 ques1){
		
		ques1.setQues1_name1(Transcode.htmlEncode(ques1.getQues1_name1()));
		ques1.setQues1_name2(Transcode.htmlEncode(ques1.getQues1_name2()));
		ques1Dao.updateQues1(ques1);
		
	}
	
	/**
	 * @Title: delQues1s
	 * @Description: 删除填空题信息
	 * @param ques1
	 * @return void
	 */
	public void  delQues1s(Ques1 ques1){
		
		ques1Dao.delQues1s(ques1.getIds().split(","));
		
	}
	
	/**
	 * @Title: listQues2s
	 * @Description: 选择题查询
	 * @param ques
	 * @return List<Ques>
	 */
	public List<Ques2> listQues2s(Ques2 ques, int[] sum) {
		
		if (sum!=null) {
			sum[0] = ques2Dao.listQues2sCount(ques);
		}
		List<Ques2> quess = ques2Dao.listQues2s(ques);
		
		return quess;
	}
	
	/**
	 * @Title: listQues2sShow
	 * @Description: 选择题查询
	 * @param ques
	 * @return List<Ques>
	 */
	public List<Ques2> listQues2sShow(Ques2 ques, int[] sum) {
		
		if (sum!=null) {
			sum[0] = ques2Dao.listQues2sCount(ques);
		}
		List<Ques2> quess = ques2Dao.listQues2s(ques);
		if (quess!=null) {
			for (int i = 0; i < quess.size(); i++) {
				Ques2 _ques = quess.get(i);
				Item item = new Item();
				item.setStart(-1);
				item.setQues2_id(_ques.getQues2_id());
				List<Item> items = itemDao.listItems(item);
				_ques.setItems(items);
			}
		}
		
		return quess;
	}
	
	/**
	 * @Title: queryQues2
	 * @Description: 选择题明细
	 * @param ques
	 * @return Ques
	 */
	public Ques2 queryQues2(Ques2 ques) {
		
		Ques2 _ques = ques2Dao.getQues2(ques);
		if (_ques!=null) {//查询选项
			Item item = new Item();
			item.setStart(-1);
			item.setQues2_id(_ques.getQues2_id());
			List<Item> items = itemDao.listItems(item);
			_ques.setItems(items);
		}
		
		return _ques;
	}
	
	/**
	 * @Title: addQues2
	 * @Description: 添加选择题
	 * @param ques
	 * @return Ques
	 */
	public void addQues2(Ques2 ques) {
		
		//添加选择题
		String[] answers = ques.getAnswers();
		StringBuilder ques_answer = new StringBuilder();
		for (String answer : answers) {
			ques_answer.append(",");
			ques_answer.append(answer);
		}
		ques.setQues2_answer(ques_answer.toString().substring(1));
		ques.setQues2_single(1);
		ques.setQues2_score(2.0);
		ques.setQues2_name(Transcode.htmlEncode(ques.getQues2_name()));
		ques2Dao.addQues2(ques);
		int ques_id = ques2Dao.getQuesId();
		//添加选项
		Item item = new Item();
		if (!StringUtil.isEmptyString(ques.getA())) {
			item.setQues2_id(ques_id);
			item.setItem_no("A");
			item.setItem_name(ques.getA());
			//item.setItem_single(ques.getQues2_single());
			item.setItem_single(1);
			if (ques.getQues2_answer().indexOf("A")!=-1) {
				item.setItem_right(1);
			}else{
				item.setItem_right(2);
			}
			item.setItem_name(Transcode.htmlEncode(item.getItem_name()));
			itemDao.addItem(item);
		}
		if (!StringUtil.isEmptyString(ques.getB())) {
			item.setQues2_id(ques_id);
			item.setItem_no("B");
			item.setItem_name(ques.getB());
			//item.setItem_single(ques.getQues2_single());
			item.setItem_single(1);
			if (ques.getQues2_answer().indexOf("B")!=-1) {
				item.setItem_right(1);
			}else{
				item.setItem_right(2);
			}
			item.setItem_name(Transcode.htmlEncode(item.getItem_name()));
			itemDao.addItem(item);
		}
		if (!StringUtil.isEmptyString(ques.getC())) {
			item.setQues2_id(ques_id);
			item.setItem_no("C");
			item.setItem_name(ques.getC());
			//item.setItem_single(ques.getQues2_single());
			item.setItem_single(1);
			if (ques.getQues2_answer().indexOf("C")!=-1) {
				item.setItem_right(1);
			}else{
				item.setItem_right(2);
			}
			item.setItem_name(Transcode.htmlEncode(item.getItem_name()));
			itemDao.addItem(item);
		}
		if (!StringUtil.isEmptyString(ques.getD())) {
			item.setQues2_id(ques_id);
			item.setItem_no("D");
			item.setItem_name(ques.getD());
			//item.setItem_single(ques.getQues2_single());
			item.setItem_single(1);
			if (ques.getQues2_answer().indexOf("D")!=-1) {
				item.setItem_right(1);
			}else{
				item.setItem_right(2);
			}
			item.setItem_name(Transcode.htmlEncode(item.getItem_name()));
			itemDao.addItem(item);
		}
		
		
	}
	
	/**
	 * @Title: updateQues2
	 * @Description: 更新选择题
	 * @param ques
	 * @return Ques
	 */
	public void updateQues2(Ques2 ques) {
		
		//更新选择题
		String[] answers = ques.getAnswers();
		StringBuilder ques_answer = new StringBuilder();
		for (String answer : answers) {
			ques_answer.append(",");
			ques_answer.append(answer);
		}
		ques.setQues2_answer(ques_answer.toString().substring(1));
		ques.setQues2_name(Transcode.htmlEncode(ques.getQues2_name()));
		ques2Dao.updateQues2(ques);
		int ques2_id = ques.getQues2_id();
		//更新选项
		itemDao.delItemByQuesId(ques2_id+"");
		Item item = new Item();
		if (!StringUtil.isEmptyString(ques.getA())) {
			item.setQues2_id(ques2_id);
			item.setItem_no("A");
			item.setItem_name(ques.getA());
			//item.setItem_single(ques.getQues2_single());
			item.setItem_single(1);
			if (ques.getQues2_answer().indexOf("A")!=-1) {
				item.setItem_right(1);
			}else{
				item.setItem_right(2);
			}
			item.setItem_name(Transcode.htmlEncode(item.getItem_name()));
			itemDao.addItem(item);
		}
		if (!StringUtil.isEmptyString(ques.getB())) {
			item.setQues2_id(ques2_id);
			item.setItem_no("B");
			item.setItem_name(ques.getB());
			//item.setItem_single(ques.getQues2_single());
			item.setItem_single(1);
			if (ques.getQues2_answer().indexOf("B")!=-1) {
				item.setItem_right(1);
			}else{
				item.setItem_right(2);
			}
			item.setItem_name(Transcode.htmlEncode(item.getItem_name()));
			itemDao.addItem(item);
		}
		if (!StringUtil.isEmptyString(ques.getC())) {
			item.setQues2_id(ques2_id);
			item.setItem_no("C");
			item.setItem_name(ques.getC());
			//item.setItem_single(ques.getQues2_single());
			item.setItem_single(1);
			if (ques.getQues2_answer().indexOf("C")!=-1) {
				item.setItem_right(1);
			}else{
				item.setItem_right(2);
			}
			item.setItem_name(Transcode.htmlEncode(item.getItem_name()));
			itemDao.addItem(item);
		}
		if (!StringUtil.isEmptyString(ques.getD())) {
			item.setQues2_id(ques2_id);
			item.setItem_no("D");
			item.setItem_name(ques.getD());
			//item.setItem_single(ques.getQues2_single());
			item.setItem_single(1);
			if (ques.getQues2_answer().indexOf("D")!=-1) {
				item.setItem_right(1);
			}else{
				item.setItem_right(2);
			}
			item.setItem_name(Transcode.htmlEncode(item.getItem_name()));
			itemDao.addItem(item);
		}
		
		
	}
	
	/**
	 * @Title: delQues2s
	 * @Description: 删除选择题
	 * @param ques
	 * @return Ques
	 */
	public void delQues2s(Ques2 ques) {
		
		//删除选项
		itemDao.delItemByQuesIds(ques.getIds().split(","));
		//删除选择题
		ques2Dao.delQues2s(ques.getIds().split(","));
		
		
	}
	
	/**
	 * @Title: listItems
	 * @Description: 试题查询
	 * @param item
	 * @return List<Item>
	 */
	public List<Item> listItems(Item item) {
		
		List<Item> items = itemDao.listItems(item);
		
		return items;
	}
	
	/**
	 * @Title: listQues3s
	 * @Description: 判断题查询
	 * @param ques3
	 * @return List<Ques3>
	 */
	public List<Ques3>  listQues3s(Ques3 ques3,int[] sum){
		
		if (sum!=null) {
			sum[0] = ques3Dao.listQues3sCount(ques3);
		}
		List<Ques3> ques3s = ques3Dao.listQues3s(ques3);
		
		
		return ques3s;
	}
	
	/**
	 * @Title: queryQues3
	 * @Description: 判断题查询
	 * @param ques3
	 * @return Ques3
	 */
	public Ques3  queryQues3(Ques3 ques3){
		
		Ques3 _ques3 = ques3Dao.getQues3(ques3);
		
		return _ques3;
	}
	 
	/**
	 * @Title: addQues3
	 * @Description: 添加判断题
	 * @param ques3
	 * @return void
	 */
	public void  addQues3(Ques3 ques3){
		
		ques3.setQues3_score(2.0);
		ques3.setQues3_name(Transcode.htmlEncode(ques3.getQues3_name()));
		ques3Dao.addQues3(ques3);
		
	}
	
	/**
	 * @Title: updateQues3
	 * @Description: 更新判断题信息
	 * @param ques3
	 * @return void
	 */
	public void  updateQues3(Ques3 ques3){
		
		ques3.setQues3_name(Transcode.htmlEncode(ques3.getQues3_name()));
		ques3Dao.updateQues3(ques3);
		
	}
	
	/**
	 * @Title: delQues3s
	 * @Description: 删除判断题信息
	 * @param ques3
	 * @return void
	 */
	public void  delQues3s(Ques3 ques3){
		
		ques3Dao.delQues3s(ques3.getIds().split(","));
		
	}
	
	/**
	 * @Title: listQues4s
	 * @Description: 编程题查询
	 * @param ques4
	 * @return List<Ques4>
	 */
	public List<Ques4>  listQues4s(Ques4 ques4,int[] sum){
		
		if (sum!=null) {
			sum[0] = ques4Dao.listQues4sCount(ques4);
		}
		List<Ques4> ques4s = ques4Dao.listQues4s(ques4);
		
		
		return ques4s;
	}
	
	/**
	 * @Title: queryQues4
	 * @Description: 编程题查询
	 * @param ques4
	 * @return Ques4
	 */
	public Ques4  queryQues4(Ques4 ques4){
		
		Ques4 _ques4 = ques4Dao.getQues4(ques4);
		
		return _ques4;
	}
	 
	/**
	 * @Title: addQues4
	 * @Description: 添加编程题
	 * @param ques4
	 * @return void
	 */
	public void  addQues4(Ques4 ques4){
		
		ques4.setQues4_score(10.0);
		ques4.setQues4_name(Transcode.htmlEncode(ques4.getQues4_name()));
		ques4.setQues4_answer(Transcode.htmlEncode(ques4.getQues4_answer()));
		ques4Dao.addQues4(ques4);
		
	}
	
	/**
	 * @Title: updateQues4
	 * @Description: 更新编程题信息
	 * @param ques4
	 * @return void
	 */
	public void  updateQues4(Ques4 ques4){
		
		ques4.setQues4_name(Transcode.htmlEncode(ques4.getQues4_name()));
		ques4.setQues4_answer(Transcode.htmlEncode(ques4.getQues4_answer()));
		ques4Dao.updateQues4(ques4);
		
	}
	
	/**
	 * @Title: delQues4s
	 * @Description: 删除编程题信息
	 * @param ques4
	 * @return void
	 */
	public void  delQues4s(Ques4 ques4){
		
		ques4Dao.delQues4s(ques4.getIds().split(","));
		
	}
	
	//提交试卷
	@SuppressWarnings("unchecked")
	public void finishPaper(List<Ques1> quess1,List<Ques2> quess2,List<Ques3> quess3,List<Ques4> quess4) {
		//四种题目总分
		double ques1_score=0;
		double ques2_score=0;
		double ques3_score=0;
		double ques4_score=0;
		//四种题目试题
		List<Ques1> user_quess1 = (List<Ques1>)Param.getSession("user_ques1s");
		List<Ques2> user_quess2 = (List<Ques2>)Param.getSession("user_ques2s");
		List<Ques3> user_quess3 = (List<Ques3>)Param.getSession("user_ques3s");
		List<Ques4> user_quess4 = (List<Ques4>)Param.getSession("user_ques4s");
		//答题详情集合
		List<Detail> details = new ArrayList<Detail>();
		//计算得分
		for (int i = 0; i < quess1.size(); i++) {
			Ques1 ques1 = quess1.get(i);
			Ques1 user_ques1 = user_quess1.get(i);
			String answer = ques1.getAnswer();
			String ques1_answer = user_ques1.getQues1_answer();
			
			//保存详情
			Detail detail = new Detail();
			detail.setQues_id(user_ques1.getQues1_id());
			detail.setQues_name(user_ques1.getQues1_name1()+"（）"+user_ques1.getQues1_name2());
			detail.setQues_score(user_ques1.getQues1_score());
			detail.setQues_answer(user_ques1.getQues1_answer());
			detail.setQues_type(1);
			detail.setUser_answer(answer);
			detail.setUser_score(0.0);
			
			if (answer!=null && answer.equals(ques1_answer)) {
				ques1_score+=user_ques1.getQues1_score();
				detail.setUser_score(user_ques1.getQues1_score());
			}
			details.add(detail);
		}
		for (int i = 0; i < quess2.size(); i++) {
			Ques2 ques2 = quess2.get(i);
			if (ques2!=null) {
				Ques2 user_ques2 = user_quess2.get(i);
				String[] answers = ques2.getAnswers();
				String[] ques2_answers = user_ques2.getQues2_answer().split(",");
				
				//保存详情
				Detail detail = new Detail();
				detail.setQues_id(user_ques2.getQues2_id());
				detail.setQues_name(user_ques2.getQues2_name());
				detail.setQues_score(user_ques2.getQues2_score());
				detail.setQues_answer(user_ques2.getQues2_answer());
				detail.setQues_type(2);
				detail.setUser_answer(answers!=null?Arrays.asList(answers).toString():"");
				detail.setUser_score(0.0);
				
				if (answers!=null && Arrays.asList(answers).containsAll(Arrays.asList(ques2_answers)) && Arrays.asList(ques2_answers).containsAll(Arrays.asList(answers))) {
					ques2_score+=user_ques2.getQues2_score();
					detail.setUser_score(user_ques2.getQues2_score());
				}
				details.add(detail);
			}
			
		}
		for (int i = 0; i < quess3.size(); i++) {
			Ques3 ques3 = quess3.get(i);
			if (ques3!=null) {
				Ques3 user_ques3 = user_quess3.get(i);
				int answer = ques3.getAnswer();
				int ques3_answer = user_ques3.getQues3_answer();
				
				//保存详情
				Detail detail = new Detail();
				detail.setQues_id(user_ques3.getQues3_id());
				detail.setQues_name(user_ques3.getQues3_name());
				detail.setQues_score(user_ques3.getQues3_score());
				detail.setQues_answer(user_ques3.getQues3_answerDesc());
				detail.setQues_type(3);
				detail.setUser_answer(answer==1?"正确":"错误");
				detail.setUser_score(0.0);
				
				if (answer==ques3_answer) {
					ques3_score+=user_ques3.getQues3_score();
					detail.setUser_score(user_ques3.getQues3_score());
				}
				details.add(detail);
			}
		}
		
		for (int i = 0; i < quess4.size(); i++) {
			Ques4 ques4 = quess4.get(i);
			if (ques4!=null) {
				Ques4 user_ques4 = user_quess4.get(i);
				String answer = ques4.getAnswer();
				String ques4_answer = user_ques4.getQues4_answer();
				
				//保存详情
				Detail detail = new Detail();
				detail.setQues_id(user_ques4.getQues4_id());
				detail.setQues_name(user_ques4.getQues4_name());
				detail.setQues_score(user_ques4.getQues4_score());
				detail.setQues_answer(ques4_answer);
				detail.setQues_type(4);
				detail.setUser_answer(answer);
				detail.setUser_score(0.0);
				details.add(detail);
			}
		}
		
		//保存成绩
		User admin = (User)Param.getSession("admin");
		Score score =new Score();
		score.setUser(admin);
		score.setQues1_score(ques1_score);
		score.setQues2_score(ques2_score);
		score.setQues3_score(ques3_score);
		score.setQues4_score(ques4_score);
		score.setSum_score(ques1_score+ques2_score+ques3_score+ques4_score);
		score.setScore_date(new Date());
		score.setScore_flag(1);//待批阅
		addScore(score);
		
		//保存明细
		addDetails(details);
	}
	
	/**
	 * @Title: listScores
	 * @Description: 成绩查询
	 * @param score
	 * @return List<Score>
	 */
	public List<Score>  listScores(Score score,int[] sum){
		
		if (sum!=null) {
			sum[0] = scoreDao.listScoresCount(score);
		}
		List<Score> scores = scoreDao.listScores(score);
		
		
		return scores;
	}
	
	/**
	 * @Title: addScore
	 * @Description: 添加成绩
	 * @param score
	 * @return void
	 */
	public void  addScore(Score score){
		
		//删除旧成绩
		//scoreDao.delScoreByStuId(score.getUser_id()+"");
		//添加新成绩
		scoreDao.addScore(score);
		
	}
	
	/**
	 * @Title: addDetails
	 * @Description: 添加成绩明细
	 * @param score
	 * @return void
	 */
	public void  addDetails(List<Detail> details){
		
		//获取成绩ID
		User admin = (User)Param.getSession("admin");
		int score_id = scoreDao.getScoreId(admin.getUser_id());
		//添加明细
		for (Detail detail : details) {
			detail.setScore_id(score_id);
			detail.setQues_name(Transcode.htmlEncode(detail.getQues_name()));
			detail.setQues_answer(Transcode.htmlEncode(detail.getQues_answer()));
			if (!StringUtil.isEmptyString(detail.getUser_answer())) {
				detail.setUser_answer(Transcode.htmlEncode(detail.getUser_answer()));
			}
			detailDao.addDetail(detail);
		}
		
	}
	
	/**
	 * @Title: delScores
	 * @Description: 删除成绩信息
	 * @param score
	 * @return void
	 */
	public void  delScores(Score score){
		
		scoreDao.delScores(score.getIds().split(","));
		detailDao.delDetailsByScoreIds(score.getIds().split(","));
		
	}
	
	/**
	 * @Title: listDetails
	 * @Description: 答题详情查询
	 * @param detail
	 * @return List<Detail>
	 */
	public List<Detail> listDetails(Detail detail, int[] sum) {
		
		if (sum != null) {
			sum[0] = detailDao.listDetailsCount(detail);
		}
		List<Detail> details = detailDao.listDetails(detail);
		if (detail.getQues_type()==2 && details!=null && details.size()>0) {
			for (Detail detail2 : details) {
				//查询选项
				Item item = new Item();
				item.setStart(-1);
				item.setQues2_id(detail2.getQues_id());
				List<Item> items = itemDao.listItems(item);
				detail2.setItems(items);
			}
		}
		
		return details;
	}

	/**
	 * @Title: updateDetail
	 * @Description: 更新答题详情
	 * @param detail
	 * @return Detail
	 */
	public void updateDetail(List<Detail> details) {
		
		double ques4_score = 0;
		int score_id = 0;
		if (details!=null && details.size()>0) {
			for (Detail detail2 : details) {
				score_id = detail2.getScore_id();
				ques4_score +=detail2.getUser_score();
				detailDao.updateDetail(detail2);
			}
			//更新答题信息
			Score score = new Score();
			score.setScore_id(score_id);
			score = scoreDao.getScore(score);
			score.setQues4_score(ques4_score);
			score.setSum_score(score.getSum_score()+ques4_score);
			score.setScore_flag(2);//已批阅
			scoreDao.updateScore(score);
		}

		
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public Ques1Dao getQues1Dao() {
		return ques1Dao;
	}

	public Ques2Dao getQues2Dao() {
		return ques2Dao;
	}

	public Ques3Dao getQues3Dao() {
		return ques3Dao;
	}

	public Ques4Dao getQues4Dao() {
		return ques4Dao;
	}

	public ItemDao getItemDao() {
		return itemDao;
	}

	public ScoreDao getScoreDao() {
		return scoreDao;
	}

	public DetailDao getDetailDao() {
		return detailDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setQues1Dao(Ques1Dao ques1Dao) {
		this.ques1Dao = ques1Dao;
	}

	public void setQues2Dao(Ques2Dao ques2Dao) {
		this.ques2Dao = ques2Dao;
	}

	public void setQues3Dao(Ques3Dao ques3Dao) {
		this.ques3Dao = ques3Dao;
	}

	public void setQues4Dao(Ques4Dao ques4Dao) {
		this.ques4Dao = ques4Dao;
	}

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	public void setScoreDao(ScoreDao scoreDao) {
		this.scoreDao = scoreDao;
	}

	public void setDetailDao(DetailDao detailDao) {
		this.detailDao = detailDao;
	}
}
