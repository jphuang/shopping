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
		render("list.html");
	}

	public void ajax() {
		List<Category> lists = Category.dao.getCategories();
		if (getPara("ajax") != null && getPara("ajax").equals("ok")) {
			List<Map> arr = new ArrayList<Map>();
			for (Category c : lists) {
				Map map = new HashMap();
				map.put("id", c.get("id"));
				map.put("pid", c.get("pid"));
				map.put("name", c.get("name"));
				map.put("descr", c.get("descr"));
				arr.add(map);
			}
			JSONArray jsonArr = JSONArray.fromObject(arr);
			renderJson(arr);
		}
	}

	public void add() {
		String name = getPara("name");
		String descr = getPara("descr");
		int pid = 0;
		if (name != null && descr != null) {
			if (getPara("pid") != null)
				pid = getParaToInt("pid");
			Category c = new Category();
			if (pid > 0) {
				Category parent = Category.dao.findById(pid);
				parent.set("isleaf", 0);
				c.set("name", name).set("descr", descr).set("pid", pid)
						.set("isleaf", 1)
						.set("grade", parent.getInt("grade") + 1);
				if (c.save() && parent.update()) {
					int id = Category.dao.getIdByNameAndPid(name, pid);
					renderText("" + id);
				} else {
					renderText("no");
				}
			} else {
				c.set("name", name).set("descr", descr).set("pid", 0)
						.set("isleaf", 1).set("grade", 1);
				if (c.save()) {
					setAttr("info", "添加成功");
				} else {
					setAttr("info", "添加失败");
				}

			}
		}
		render("add.html");
	}

	public void delete() {
		int id = 0;
		if (getPara("id") != null) {
			id = getParaToInt("id");
		}
		if (id > 0){
			if(Category.dao.deleteById(id)){
				renderText("yes");
			}
		}else{
			renderNull();
		}
	}

	public void update() {
		int id = 0;
		if (getPara("id") != null) {
			id = getParaToInt("id");
		}
		String name = getPara("name");
		if (id > 0) {
			Category c = Category.dao.findById(id);
			c.set("name", name);
			if (c.update()) {
				renderText("yes");
			}
		}else{
			renderNull();
		}
	}
}
