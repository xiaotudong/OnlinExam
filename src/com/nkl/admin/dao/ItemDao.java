package com.nkl.admin.dao;

import java.util.ArrayList;
import java.util.List;
import com.nkl.common.dao.BaseDao;
import com.nkl.admin.domain.Item;

public class ItemDao extends BaseDao {

	public void addItem(Item item){
		super.add(item);
	}

	public void delItem(Integer item_id){
		super.del(Item.class, item_id);
	}

	public void delItems(String[] item_ids){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <item_ids.length; i++) {
			sBuilder.append(item_ids[i]);
			if (i !=item_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String hql = "DELETE FROM Item WHERE item_id IN(" +sBuilder.toString()+")";

		Object[] params = null;

		super.executeUpdateHql(hql, params);
	}

	public void delItemByQuesId(String ques2_id){
		String hql = "DELETE FROM Item WHERE ques2_id=?";

		Object[] params = new Object[] { new Integer(ques2_id)};
		super.executeUpdateHql(hql, params);
	}
	
	public void delItemByQuesIds(String[] ques2_ids){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <ques2_ids.length; i++) {
			sBuilder.append(ques2_ids[i]);
			if (i !=ques2_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String hql = "DELETE FROM Item WHERE ques2_id IN(" +sBuilder.toString()+")";

		Object[] params = null;

		super.executeUpdateHql(hql, params);
	}

	public void updateItem(Item item){
		Item _item = (Item)super.get(Item.class, item.getItem_id());
		super.update(_item);
	}

	@SuppressWarnings("rawtypes")
	public Item getItem(Item item){
		Item _item=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Item WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (item.getItem_id()!=null && item.getItem_id()!=0) {
			sBuilder.append(" and item_id = ? ");
			paramsList.add(item.getItem_id());
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
			_item = (Item)list.get(0);
		}

		return _item;
	}

	@SuppressWarnings("rawtypes")
	public List<Item>  listItems(Item item){
		List<Item> items = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Item WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (item.getItem_id()!=null && item.getItem_id()!=0) {
			sBuilder.append(" and item_id = ? ");
			paramsList.add(item.getItem_id());
		}
		if (item.getQues2_id()!=null && item.getQues2_id()!=0) {
			sBuilder.append(" and ques2_id = " + item.getQues2_id() +" ");
		}

		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}

		sBuilder.append(" order by item_id asc ");

		List list = null;
		if (item.getStart()!=-1) {
			list = super.findByPageHql(sBuilder.toString(), params, item.getStart(), item.getLimit());
		}else {
			list = super.executeQueryHql(sBuilder.toString(), params);
		}
		if (list != null && list.size() > 0) {
			items = new ArrayList<Item>();
			for (Object object : list) {
				items.add((Item)object);
			}
		}

		return items;
	}

	public int  listItemsCount(Item item){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM Item WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (item.getItem_id()!=null && item.getItem_id()!=0) {
			sBuilder.append(" and item_id = ? ");
			paramsList.add(item.getItem_id());
		}
		if (item.getQues2_id()!=null && item.getQues2_id()!=0) {
			sBuilder.append(" and ques2_id = " + item.getQues2_id() +" ");
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
