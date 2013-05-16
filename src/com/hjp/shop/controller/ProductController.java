package com.hjp.shop.controller;

import java.util.Date;

import com.hjp.shop.model.Category;
import com.hjp.shop.model.Product;
import com.jfinal.core.Controller;

public class ProductController extends Controller {
		public void add(){
			this.setAttr("categories", Category.dao.getAllLeafCategory());
			if(getPara("add")!=null && getPara("add").equals("ok")){
				String name = getPara("name");
				String descr = getPara("descr");
				int normalprice = getParaToInt("normalprice");
				int memberprice = getParaToInt("memberprice");
				int categoryId = getParaToInt("categoryId");
				Product p = new Product();
     			p.set("name", name).set("descr", descr)
     					.set("normalprice", normalprice)
     					.set("memberprice", memberprice)
     					.set("categoryid",categoryId)
     					.set("pdate",new Date());
				if(p.save()){
					setAttr("info", "添加成功");
				}else{
					setAttr("info", "添加失败");
				}
			}
			render("add.html");
		}
		public void search(){
			
		}
		public void list(){
			int pageNo;
			if (getPara()!=null) {
				try {
					pageNo = getParaToInt();
					if (pageNo <1 ) {
						pageNo = 1;
					}
				} catch (Exception e) {
					pageNo = 1;
					e.printStackTrace();
				}
			}else {
				pageNo = 1;
			}
			this.setAttr("products", Product.dao.getAllProduct(pageNo).getList());
			setAttr("pageNo",pageNo);
			render("list.html");
		}
		public void update(){
			
		}
}
