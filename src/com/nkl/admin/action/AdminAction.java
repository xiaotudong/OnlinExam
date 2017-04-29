package com.nkl.admin.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import com.nkl.admin.domain.Detail;
import com.nkl.admin.domain.Item;
import com.nkl.admin.domain.Ques1;
import com.nkl.admin.domain.Ques2;
import com.nkl.admin.domain.Ques3;
import com.nkl.admin.domain.Ques4;
import com.nkl.admin.domain.Score;
import com.nkl.admin.domain.User;
import com.nkl.admin.manager.AdminManager;
import com.nkl.common.action.BaseAction;
import com.nkl.common.util.BeanLocator;
import com.nkl.common.util.DateUtil;
import com.nkl.common.util.Md5;
import com.nkl.common.util.Param;

public class AdminAction  extends BaseAction {

	private static final long serialVersionUID = 1L;
	AdminManager adminManager = (AdminManager)BeanLocator.getInstance().getBean("adminManager");

	//抓取页面参数
	User paramsUser;
	Ques1 paramsQues1;
	Ques2 paramsQues2;
	Item paramsItem;
	Ques3 paramsQues3;
	Ques4 paramsQues4;
	Score paramsScore;
	Detail paramsDetail;
	
	String tip;
	List<Ques1> paramsQues1s = new ArrayList<Ques1>();
	List<Ques2> paramsQues2s = new ArrayList<Ques2>();
	List<Ques3> paramsQues3s = new ArrayList<Ques3>();
	List<Ques4> paramsQues4s = new ArrayList<Ques4>();
	List<Detail> details = new ArrayList<Detail>();
	
	/**
	 * @Title: saveAdmin
	 * @Description: 保存修改个人信息
	 * @return String
	 */
	public String saveAdmin(){
		try {
			//验证学生会话是否失效
			if (!validateAdmin()) {
				return "loginTip";
			}
			 //保存修改个人信息
			adminManager.updateUser(paramsUser);
			//更新session
			User admin = new User();
			admin.setUser_id(paramsUser.getUser_id());
			admin = adminManager.getUser(admin);
			Param.setSession("admin", admin);
			
			setSuccessTip("编辑成功", "modifyInfo.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			setErrorTip("编辑异常", "modifyInfo.jsp");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: saveAdminPass
	 * @Description: 保存修改个人密码
	 * @return String
	 */
	public String saveAdminPass(){
		try {
			//验证学生会话是否失效
			if (!validateAdmin()) {
				return "loginTip";
			}
			//验证原密码
			User admin = (User)Param.getSession("admin");
			String user_passOld1 = admin.getUser_pass();
			String user_passOld2 = Md5.makeMd5(paramsUser.getUser_passOld());
			if (!user_passOld1.equals(user_passOld2)) {
				setErrorTip("原密码不正确", "modifyPwd.jsp");
				return "infoTip";
			}
			
			//保存修改个人密码
			adminManager.updateUser(paramsUser);
			//更新session
			if (admin!=null) {
				admin.setUser_pass(paramsUser.getUser_pass());
				Param.setSession("admin", admin);
			}
			
			setSuccessTip("修改成功", "modifyPwd.jsp");
		} catch (Exception e) {
			setErrorTip("修改异常", "modifyPwd.jsp");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listTeachers
	 * @Description: 查询教师
	 * @return String
	 */
	public String listTeachers(){
		try {
			if (paramsUser==null) {
				paramsUser = new User();
			}
			//查询教师
			paramsUser.setUser_type(2);
			//设置分页信息
			setPagination(paramsUser);
			int[] sum={0};
			List<User> users = adminManager.listUsers(paramsUser,sum); 
			
			Param.setAttribute("users", users);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询教师异常", "main.jsp");
			return "infoTip";
		}
		
		return "teacherShow";
	}
	
	/**
	 * @Title: addTeacherShow
	 * @Description: 显示添加教师页面
	 * @return String
	 */
	public String addTeacherShow(){
		return "teacherEdit";
	}
	
	/**
	 * @Title: addTeacher
	 * @Description: 添加教师
	 * @return String
	 */
	public String addTeacher(){
		try {
			 //添加教师
			paramsUser.setUser_type(2);
			paramsUser.setReg_date(DateUtil.getDate(DateUtil.getCurDateTime()));
			adminManager.addUser(paramsUser);

			setSuccessTip("添加教师成功", "Admin_listTeachers.action");
		} catch (Exception e) {
			e.printStackTrace();
			setErrorTip("添加教师异常", "Admin_listTeachers.action");
		}
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editTeacher
	 * @Description: 编辑教师
	 * @return String
	 */
	public String editTeacher(){
		try {
			 //得到教师
			User user = adminManager.getUser(paramsUser);
			Param.setAttribute("user", user);
			
		} catch (Exception e) {
			setErrorTip("查询教师异常", "Admin_listTeachers.action");
			return "infoTip";
		}
		
		return "teacherEdit";
	}
	
	/**
	 * @Title: saveTeacher
	 * @Description: 保存编辑教师
	 * @return String
	 */
	public String saveTeacher(){
		try {
			 //保存编辑教师
			adminManager.updateUser(paramsUser);
			
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("user", paramsUser);
			return "userEdit";
		}
		setSuccessTip("编辑教师成功", "Admin_listTeachers.action");
		return "infoTip";
	}
	
	/**
	 * @Title: delTeachers
	 * @Description: 删除教师
	 * @return String
	 */
	public String delTeachers(){
		try {
			 //删除教师
			adminManager.delUsers(paramsUser);

			setSuccessTip("删除教师成功", "Admin_listTeachers.action");
		} catch (Exception e) {
			e.printStackTrace();
			setErrorTip("删除教师异常", "Admin_listTeachers.action");
		}
		return "infoTip";
	}
	
	/**
	 * @Title: listUsers
	 * @Description: 查询学生
	 * @return String
	 */
	public String listUsers(){
		try {
			if (paramsUser==null) {
				paramsUser = new User();
			}
			//查询学生
			paramsUser.setUser_type(1);
			//设置分页信息
			setPagination(paramsUser);
			int[] sum={0};
			List<User> users = adminManager.listUsers(paramsUser,sum); 
			
			Param.setAttribute("users", users);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询学生异常", "main.jsp");
			return "infoTip";
		}
		
		return "userShow";
	}
	
	/**
	 * @Title: addUserShow
	 * @Description: 显示添加学生页面
	 * @return String
	 */
	public String addUserShow(){
		return "userEdit";
	}
	
	/**
	 * @Title: addUser
	 * @Description: 添加学生
	 * @return String
	 */
	public String addUser(){
		try {
			 //添加学生
			paramsUser.setUser_type(1);
			paramsUser.setReg_date(DateUtil.getDate(DateUtil.getCurDateTime()));
			adminManager.addUser(paramsUser);

			setSuccessTip("添加学生成功", "Admin_listUsers.action");
		} catch (Exception e) {
			e.printStackTrace();
			setErrorTip("添加学生异常", "Admin_listUsers.action");
		}
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editUser
	 * @Description: 编辑学生
	 * @return String
	 */
	public String editUser(){
		try {
			 //得到学生
			User user = adminManager.getUser(paramsUser);
			Param.setAttribute("user", user);
			
		} catch (Exception e) {
			setErrorTip("查询学生异常", "Admin_listUsers.action");
			return "infoTip";
		}
		
		return "userEdit";
	}
	
	/**
	 * @Title: saveUser
	 * @Description: 保存编辑学生
	 * @return String
	 */
	public String saveUser(){
		try {
			 //保存编辑学生
			adminManager.updateUser(paramsUser);
			
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("user", paramsUser);
			return "userEdit";
		}
		setSuccessTip("编辑学生成功", "Admin_listUsers.action");
		return "infoTip";
	}
	
	/**
	 * @Title: delUsers
	 * @Description: 删除学生
	 * @return String
	 */
	public String delUsers(){
		try {
			 //删除学生
			adminManager.delUsers(paramsUser);

			setSuccessTip("删除学生成功", "Admin_listUsers.action");
		} catch (Exception e) {
			e.printStackTrace();
			setErrorTip("删除学生异常", "Admin_listUsers.action");
		}
		return "infoTip";
	}
	
	/**
	 * @Title: listQues1s
	 * @Description: 查询填空题
	 * @return String
	 */
	public String listQues1s(){
		try {
			if (paramsQues1==null) {
				paramsQues1 = new Ques1();
			}
			//设置分页信息
			setPagination(paramsQues1);
			//总的条数
			int[] sum={0};
			//查询填空题列表
			List<Ques1> ques1s = adminManager.listQues1s(paramsQues1,sum); 
			
			Param.setAttribute("ques1s", ques1s);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询填空题异常", "main.jsp");
			return "infoTip";
		}
		
		return "ques1Show";
	}
	
	/**
	 * @Title: addQues1Show
	 * @Description: 显示添加填空题页面
	 * @return String
	 */
	public String addQues1Show(){
		return "ques1Edit";
	}
	
	/**
	 * @Title: addQues1
	 * @Description: 添加填空题
	 * @return String
	 */
	public String addQues1(){
		try {
			 //添加填空题
			adminManager.addQues1(paramsQues1);
			
			setSuccessTip("添加成功", "Admin_listQues1s.action");
		} catch (Exception e) {
			setErrorTip("添加填空题异常", "Admin_listQues1s.action");
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editQues1
	 * @Description: 编辑填空题
	 * @return String
	 */
	public String editQues1(){
		try {
			 //得到填空题
			Ques1 ques1 = adminManager.queryQues1(paramsQues1);
			Param.setAttribute("ques1", ques1);
			
		} catch (Exception e) {
			setErrorTip("查询填空题异常", "Admin_listQues1s.action");
			return "infoTip";
		}
		
		return "ques1Edit";
	}
	
	/**
	 * @Title: saveQues1
	 * @Description: 保存编辑填空题
	 * @return String
	 */
	public String saveQues1(){
		try {
			 //保存编辑填空题
			adminManager.updateQues1(paramsQues1);
			
			setSuccessTip("编辑成功", "Admin_listQues1s.action");
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("ques1", paramsQues1);
			return "ques1Edit";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delQues1s
	 * @Description: 删除填空题
	 * @return String
	 */
	public String delQues1s(){
		try {
			 //删除填空题
			adminManager.delQues1s(paramsQues1);
			
			setSuccessTip("删除填空题成功", "Admin_listQues1s.action");
		} catch (Exception e) {
			setErrorTip("删除填空题异常", "Admin_listQues1s.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listQues2s
	 * @Description: 查询选择题
	 * @return String
	 */
	public String listQues2s(){
		try {
			if (paramsQues2==null) {
				paramsQues2 = new Ques2();
			}
			//设置分页信息
			setPagination(paramsQues2);
			//总的条数
			int[] sum={0};
			//查询试题列表
			List<Ques2> ques2s = adminManager.listQues2s(paramsQues2,sum); 
			
			Param.setAttribute("ques2s", ques2s);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			setErrorTip("查询选择题异常", "main.jsp");
			return "infoTip";
		}
		
		return "ques2Show";
	}
	
	/**
	 * @Title: addQues2Show
	 * @Description: 显示添加选择题页面
	 * @return String
	 */
	public String addQues2Show(){
		Param.setAttribute("ques2", paramsQues2);
		return "ques2Edit";
	}
	
	/**
	 * @Title: addQues2
	 * @Description: 添加选择题
	 * @return String
	 */
	public String addQues2(){
		try {
			 //添加选择题
			adminManager.addQues2(paramsQues2);
			
			setSuccessTip("添加选择题成功", "Admin_listQues2s.action");
		} catch (Exception e) {
			setErrorTip("添加选择题异常", "Admin_listQues2s.action");
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editQues2
	 * @Description: 编辑选择题
	 * @return String
	 */
	public String editQues2(){
		try {
			 //得到试题
			Ques2 ques2 = adminManager.queryQues2(paramsQues2);
			Param.setAttribute("ques2", ques2);
			
			//查询正确答案
			Param.setAttribute("answers", Arrays.asList(ques2.getQues2_answer().split(",")));
			
			//查询选项
			List<Item> items = ques2.getItems();
			for (Item item : items) {
				Param.setAttribute("item"+item.getItem_no(), item);
			}
			
		} catch (Exception e) {
			setErrorTip("查询选择题异常", "Admin_listQues2s.action");
			return "infoTip";
		}
		
		return "ques2Edit";
	}
	
	/**
	 * @Title: saveQues2
	 * @Description: 保存编辑选择题
	 * @return String
	 */
	public String saveQues2(){
		try {
			 //保存编辑选择题
			adminManager.updateQues2(paramsQues2);
			
			setSuccessTip("编辑选择题成功", "Admin_listQues2s.action");
		} catch (Exception e) {
			setErrorTip("编辑选择题失败", "Admin_listQues2s.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delQues2s
	 * @Description: 删除选择题
	 * @return String
	 */
	public String delQues2s(){
		try {
			 //删除试题
			adminManager.delQues2s(paramsQues2);
			
			setSuccessTip("删除试题成功", "Admin_listQues2s.action");
		} catch (Exception e) {
			setErrorTip("删除试题异常", "Admin_listQues2s.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listQues3s
	 * @Description: 查询判断题
	 * @return String
	 */
	public String listQues3s(){
		try {
			if (paramsQues3==null) {
				paramsQues3 = new Ques3();
			}
			//设置分页信息
			setPagination(paramsQues3);
			//总的条数
			int[] sum={0};
			//查询判断题列表
			List<Ques3> ques3s = adminManager.listQues3s(paramsQues3,sum); 
			
			Param.setAttribute("ques3s", ques3s);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询判断题异常", "main.jsp");
			return "infoTip";
		}
		
		return "ques3Show";
	}
	
	/**
	 * @Title: addQues3Show
	 * @Description: 显示添加判断题页面
	 * @return String
	 */
	public String addQues3Show(){
		return "ques3Edit";
	}
	
	/**
	 * @Title: addQues3
	 * @Description: 添加判断题
	 * @return String
	 */
	public String addQues3(){
		try {
			 //添加判断题
			adminManager.addQues3(paramsQues3);
			
			setSuccessTip("添加成功", "Admin_listQues3s.action");
		} catch (Exception e) {
			setErrorTip("添加判断题异常", "Admin_listQues3s.action");
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editQues3
	 * @Description: 编辑判断题
	 * @return String
	 */
	public String editQues3(){
		try {
			 //得到判断题
			Ques3 ques3 = adminManager.queryQues3(paramsQues3);
			Param.setAttribute("ques3", ques3);
			
		} catch (Exception e) {
			setErrorTip("查询判断题异常", "Admin_listQues3s.action");
			return "infoTip";
		}
		
		return "ques3Edit";
	}
	
	/**
	 * @Title: saveQues3
	 * @Description: 保存编辑判断题
	 * @return String
	 */
	public String saveQues3(){
		try {
			 //保存编辑判断题
			adminManager.updateQues3(paramsQues3);
			
			setSuccessTip("编辑成功", "Admin_listQues3s.action");
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("ques3", paramsQues3);
			return "ques3Edit";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delQues3s
	 * @Description: 删除判断题
	 * @return String
	 */
	public String delQues3s(){
		try {
			 //删除判断题
			adminManager.delQues3s(paramsQues3);
			
			setSuccessTip("删除判断题成功", "Admin_listQues3s.action");
		} catch (Exception e) {
			setErrorTip("删除判断题异常", "Admin_listQues3s.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listQues4s
	 * @Description: 查询编程题
	 * @return String
	 */
	public String listQues4s(){
		try {
			if (paramsQues4==null) {
				paramsQues4 = new Ques4();
			}
			//设置分页信息
			setPagination(paramsQues4);
			//总的条数
			int[] sum={0};
			//查询编程题列表
			List<Ques4> ques4s = adminManager.listQues4s(paramsQues4,sum); 
			
			Param.setAttribute("ques4s", ques4s);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询编程题异常", "main.jsp");
			return "infoTip";
		}
		
		return "ques4Show";
	}
	
	/**
	 * @Title: addQues4Show
	 * @Description: 显示添加编程题页面
	 * @return String
	 */
	public String addQues4Show(){
		return "ques4Edit";
	}
	
	/**
	 * @Title: addQues4
	 * @Description: 添加编程题
	 * @return String
	 */
	public String addQues4(){
		try {
			 //添加编程题
			adminManager.addQues4(paramsQues4);
			
			setSuccessTip("添加成功", "Admin_listQues4s.action");
		} catch (Exception e) {
			setErrorTip("添加编程题异常", "Admin_listQues4s.action");
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editQues4
	 * @Description: 编辑编程题
	 * @return String
	 */
	public String editQues4(){
		try {
			 //得到编程题
			Ques4 ques4 = adminManager.queryQues4(paramsQues4);
			Param.setAttribute("ques4", ques4);
			
		} catch (Exception e) {
			setErrorTip("查询编程题异常", "Admin_listQues4s.action");
			return "infoTip";
		}
		
		return "ques4Edit";
	}
	
	/**
	 * @Title: saveQues4
	 * @Description: 保存编辑编程题
	 * @return String
	 */
	public String saveQues4(){
		try {
			 //保存编辑编程题
			adminManager.updateQues4(paramsQues4);
			
			setSuccessTip("编辑成功", "Admin_listQues4s.action");
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("ques4", paramsQues4);
			return "ques4Edit";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delQues4s
	 * @Description: 删除编程题
	 * @return String
	 */
	public String delQues4s(){
		try {
			 //删除编程题
			adminManager.delQues4s(paramsQues4);
			
			setSuccessTip("删除编程题成功", "Admin_listQues4s.action");
		} catch (Exception e) {
			setErrorTip("删除编程题异常", "Admin_listQues4s.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: queryPaper
	 * @Description: 查询试卷
	 * @return String
	 */
	public String queryPaper(){
		try {
			//填空题10个
			Ques1 ques1 = new Ques1();
			ques1.setStart(0);
			ques1.setLimit(10);
			ques1.setRandom("1");
			List<Ques1> ques1s = adminManager.listQues1s(ques1,null);
			if (ques1s==null || ques1s.size()<10) {
				setErrorTip("填空题题库数量不足，无法生成试卷", "main.jsp");
				return "infoTip";
			}
			Param.setSession("user_ques1s", ques1s);
			
			//选择题15个
			Ques2 ques2 = new Ques2();
			ques2.setStart(0);
			ques2.setLimit(15);
			ques2.setRandom("1");
			List<Ques2> ques2s = adminManager.listQues2sShow(ques2,null);
			if (ques2s==null || ques2s.size()<15) {
				setErrorTip("选择题题库数量不足，无法生成试卷", "main.jsp");
				return "infoTip";
			}
			Param.setSession("user_ques2s", ques2s);
			
			//判断题15个
			Ques3 ques3 = new Ques3();
			ques3.setStart(0);
			ques3.setLimit(15);
			ques3.setRandom("1");
			List<Ques3> ques3s = adminManager.listQues3s(ques3,null);
			if (ques3s==null || ques3s.size()<15) {
				setErrorTip("判断题题库数量不足，无法生成试卷", "main.jsp");
				return "infoTip";
			}
			Param.setSession("user_ques3s", ques3s);
			
			//编程题2个
			Ques4 ques4 = new Ques4();
			ques4.setStart(0);
			ques4.setLimit(2);
			ques4.setRandom("1");
			List<Ques4> ques4s = adminManager.listQues4s(ques4,null);
			if (ques4s==null || ques4s.size()<2) {
				setErrorTip("编程题题库数量不足，无法生成试卷", "main.jsp");
				return "infoTip";
			}
			Param.setSession("user_ques4s", ques4s);
			
			//加载考试时间
			loadTimeMinute();
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "paperDetail";
	}
	
	/**
	 * @Title: finishPaper
	 * @Description: 提交试卷
	 * @return String
	 */
	public String finishPaper(){
		try {
			//提交试卷
			adminManager.finishPaper(paramsQues1s, paramsQues2s, paramsQues3s, paramsQues4s);
			
			setSuccessTip("提交试卷成功", "Admin_listScores.action");
			
		} catch (Exception e) {
			e.printStackTrace();
			setErrorTip("提交试卷异常", "main.jsp");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listScores
	 * @Description: 查询学生成绩
	 * @return String
	 */
	public String listScores(){
		try {
			if (paramsScore==null) {
				paramsScore = new Score();
			}
			//设置分页信息
			setPagination(paramsScore);
			//总的条数
			int[] sum={0};
			//用户身份限制
			User admin = (User)Param.getSession("admin");
			if (admin.getUser_type()==1) {
				paramsScore.setUser(admin);
			}
			//查询学生成绩列表
			List<Score> scores = adminManager.listScores(paramsScore,sum); 
			
			Param.setAttribute("scores", scores);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询学生成绩异常", "main.jsp");
			return "infoTip";
		}
		
		return "scoreShow";
	}
	
	/**
	 * @Title: delScores
	 * @Description: 删除学生成绩
	 * @return String
	 */
	public String delScores(){
		try {
			 //删除学生成绩
			adminManager.delScores(paramsScore);

			setSuccessTip("删除学生成绩成功", "Admin_listScores.action");
		} catch (Exception e) {
			setErrorTip("删除学生成绩异常", "Admin_listScores.action");
		}
		return "infoTip";
	}
	
	/**
	 * @Title: queryDetail
	 * @Description: 查询学生答题详情
	 * @return String
	 */
	public String queryDetail(){
		try {
			//查询学生答题详情-填空题
			Detail detail = new Detail();
			detail.setScore_id(paramsDetail.getScore_id());
			detail.setQues_type(1);
			detail.setStart(-1);
			List<Detail> details1 = adminManager.listDetails(detail, null);
			if (details1==null) {
				details1 = new ArrayList<Detail>();
			}
			Param.setAttribute("details1", details1);
			
			//查询学生答题详情-选择题
			detail.setQues_type(2);
			List<Detail> details2 = adminManager.listDetails(detail, null);
			if (details2==null) {
				details2 = new ArrayList<Detail>();
			}
			Param.setAttribute("details2", details2);
			
			//查询学生答题详情-判断题
			detail.setQues_type(3);
			List<Detail> details3 = adminManager.listDetails(detail, null);
			if (details3==null) {
				details3 = new ArrayList<Detail>();
			}
			Param.setAttribute("details3", details3);
			
			//查询学生答题详情-编程题
			detail.setQues_type(4);
			List<Detail> details4 = adminManager.listDetails(detail, null);
			if (details4==null) {
				details4 = new ArrayList<Detail>();
			}
			Param.setAttribute("details4", details4);
			
		} catch (Exception e) {
			setErrorTip("查询答题详情异常", "Admin_listScores.action");
			return "infoTip";
		}
		
		return "detailShow";
	}
	
	/**
	 * @Title: editDetail
	 * @Description: 查询学生答题详情
	 * @return String
	 */
	public String editDetail(){
		try {
			//查询学生答题详情-填空题
			Detail detail = new Detail();
			detail.setScore_id(paramsDetail.getScore_id());
			detail.setQues_type(1);
			detail.setStart(-1);
			List<Detail> details1 = adminManager.listDetails(detail, null);
			if (details1==null) {
				details1 = new ArrayList<Detail>();
			}
			Param.setAttribute("details1", details1);
			
			//查询学生答题详情-选择题
			detail.setQues_type(2);
			List<Detail> details2 = adminManager.listDetails(detail, null);
			if (details2==null) {
				details2 = new ArrayList<Detail>();
			}
			Param.setAttribute("details2", details2);
			
			//查询学生答题详情-判断题
			detail.setQues_type(3);
			List<Detail> details3 = adminManager.listDetails(detail, null);
			if (details3==null) {
				details3 = new ArrayList<Detail>();
			}
			Param.setAttribute("details3", details3);
			
			//查询学生答题详情-编程题
			detail.setQues_type(4);
			List<Detail> details4 = adminManager.listDetails(detail, null);
			if (details4==null) {
				details4 = new ArrayList<Detail>();
			}
			Param.setAttribute("details4", details4);
			
		} catch (Exception e) {
			setErrorTip("查询答题详情异常", "Admin_listScores.action");
			return "infoTip";
		}
		
		return "detailEdit";
	}
	
	/**
	 * @Title: saveDetail
	 * @Description: 批阅学生答题
	 * @return String
	 */
	public String saveDetail(){
		try {
			 //批阅学生答题
			adminManager.updateDetail(details);

			setSuccessTip("批阅学生答题成功", "Admin_listScores.action");
		} catch (Exception e) {
			setErrorTip("批阅学生答题异常", "Admin_listScores.action");
		}
		return "infoTip";
	}
	
	/**
	 * @Title: loadTimeMinute
	 * @Description: 记载考试时间
	 * @return void
	 */
	public void loadTimeMinute(){
		if (Param.getSession("time_minute")==null) {
			Properties props = new Properties();
			String path = "prop/database.properties";
			try {
				props.load(AdminAction.class.getClassLoader().getResourceAsStream(path));
				Param.setSession("time_minute", (String)props.get("time_minute"));
			} catch (Exception e) {
				e.printStackTrace();
				Param.setSession("time_minute", "60");
			}
		}
	}
	
	private boolean validateAdmin(){
		User admin = (User)Param.getSession("admin");
		if (admin!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	private void setErrorTip(String tip,String url){
		Param.setAttribute("tipType", "error");
		Param.setAttribute("tip", tip);
		Param.setAttribute("url1", url);
		Param.setAttribute("value1", "确 定");
	}
	
	private void setSuccessTip(String tip,String url){
		Param.setAttribute("tipType", "success");
		Param.setAttribute("tip", tip);
		Param.setAttribute("url1", url);
		Param.setAttribute("value1", "确 定");
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public User getParamsUser() {
		return paramsUser;
	}

	public void setParamsUser(User paramsUser) {
		this.paramsUser = paramsUser;
	}

	public Ques2 getParamsQues() {
		return paramsQues2;
	}

	public void setParamsQues(Ques2 paramsQues2) {
		this.paramsQues2 = paramsQues2;
	}

	public Item getParamsItem() {
		return paramsItem;
	}

	public void setParamsItem(Item paramsItem) {
		this.paramsItem = paramsItem;
	}

	public Ques1 getParamsQues1() {
		return paramsQues1;
	}

	public Ques2 getParamsQues2() {
		return paramsQues2;
	}

	public Ques3 getParamsQues3() {
		return paramsQues3;
	}

	public Score getParamsScore() {
		return paramsScore;
	}

	public void setParamsQues1(Ques1 paramsQues1) {
		this.paramsQues1 = paramsQues1;
	}

	public void setParamsQues2(Ques2 paramsQues2) {
		this.paramsQues2 = paramsQues2;
	}

	public void setParamsQues3(Ques3 paramsQues3) {
		this.paramsQues3 = paramsQues3;
	}

	public void setParamsScore(Score paramsScore) {
		this.paramsScore = paramsScore;
	}

	public List<Ques1> getParamsQues1s() {
		return paramsQues1s;
	}

	public List<Ques2> getParamsQues2s() {
		return paramsQues2s;
	}

	public List<Ques3> getParamsQues3s() {
		return paramsQues3s;
	}

	public void setParamsQues1s(List<Ques1> paramsQues1s) {
		this.paramsQues1s = paramsQues1s;
	}

	public void setParamsQues2s(List<Ques2> paramsQues2s) {
		this.paramsQues2s = paramsQues2s;
	}

	public void setParamsQues3s(List<Ques3> paramsQues3s) {
		this.paramsQues3s = paramsQues3s;
	}

	public Ques4 getParamsQues4() {
		return paramsQues4;
	}

	public Detail getParamsDetail() {
		return paramsDetail;
	}

	public List<Ques4> getParamsQues4s() {
		return paramsQues4s;
	}

	public List<Detail> getDetails() {
		return details;
	}

	public void setParamsQues4(Ques4 paramsQues4) {
		this.paramsQues4 = paramsQues4;
	}

	public void setParamsDetail(Detail paramsDetail) {
		this.paramsDetail = paramsDetail;
	}

	public void setParamsQues4s(List<Ques4> paramsQues4s) {
		this.paramsQues4s = paramsQues4s;
	}

	public void setDetails(List<Detail> details) {
		this.details = details;
	}


}
