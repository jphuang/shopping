package com.hjp.shop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.hjp.shop.model.Category;
import com.jfinal.core.Controller;

public class CategoryController extends Controller {
	public void list() {
		return;
		/*
		 * List<Category> lists = Category.dao.getCategories();
		 * if(getPara("ajax")!=null && getPara("ajax").equals("ok")){ List<Map>
		 * arr = new ArrayList<Map>(); for (Category c : lists) { Map map = new
		 * HashMap(); map.put("id", c.get("id")); map.put("pid", c.get("pdi"));
		 * map.put("name", c.get("name")); map.put("descr", c.get("descr"));
		 * arr.add(map); System.out.println(map); } return; //JSONArray jsonArr
		 * = JSONArray.fromObject(arr); //renderJson(jsonArr); }else{
		 */
		// render("list.html");
		// }

	}

	public void ajax(){
		List<Category> lists = Category.dao.getCategories();
		if(getPara("ajax")!=null && getPara("ajax").equals("ok")){
			List<Map> arr = new ArrayList<Map>();
			for (Category c : lists) {
					Map map = new HashMap();
					map.put("id", c.get("id"));
					map.put("pid", c.get("pdi"));
					map.put("name", c.get("name"));
					map.put("descr", c.get("descr"));
					arr.add(map);
					System.out.println(map);
				}
			JSONArray jsonArr = JSONArray.fromObject(arr);
			renderJson(jsonArr);
			}
		}

	public void add() {
		String name = getPara("name");
		String descr = getPara("descr");
		if (name != null && descr != null) {
			Category c = new Category();
			c.set("name", name);
			c.set("descr", descr);
			c.set("pid", 0);
			c.set("isleaf", 1);
			c.set("grade", 1);
			if (c.save()) {
				setAttr("info", "添加成功");
			} else {
				setAttr("info", "添加失败");
			}
		}
		render("add.html");
	}

	public void delete() {

	}

	public void update() {

	}
}
