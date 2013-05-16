package com.hjp.shop.controller;

import com.hjp.shop.model.Category;
import com.jfinal.core.Controller;

public class ProductController extends Controller {
		public void add(){
			this.setAttr("categories", Category.dao.getAllLeafCategory());
			render("add.html");
		}
		public void search(){
			
		}
		public void list(){
			renderText("fuck");
		}
		public void update(){
			
		}
}
