package com.hjp.shop.controller;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.hjp.shop.model.Category;
import com.hjp.shop.model.Product;
import com.jfinal.core.Controller;
import com.jfinal.kit.PathKit;
import com.jfinal.upload.UploadFile;

public class ProductController extends Controller {
	public void add() {
		this.setAttr("categories", Category.dao.getAllLeafCategory());
		if (getPara("add") != null && getPara("add").equals("ok")) {
			String name = getPara("name");
			String descr = getPara("descr");
			int normalprice = getParaToInt("normalprice");
			int memberprice = getParaToInt("memberprice");
			int categoryId = getParaToInt("categoryId");
			Product p = new Product();
			p.set("name", name).set("descr", descr)
					.set("normalprice", normalprice)
					.set("memberprice", memberprice)
					.set("categoryid", categoryId).set("pdate", new Date());
			if (p.save()) {
				setAttr("info", "添加成功");
			} else {
				setAttr("info", "添加失败");
			}
		}
		render("add.html");
	}

	public void search() {
		this.setAttr("categories", Category.dao.getAllLeafCategory());
		if (getPara("search") != null && getPara("search").equals("ok")) {
			int categoryid = getParaToInt("categoryId");
			String keyword = getPara("keyword");
			int minNormalprice = -1 ;
			int maxNormalprice = -1;
			int minMemberprice = -1;
			int maxMembelprice = -1;
			if(getPara("minNormalprice") != null && !getPara("minNormalprice").equals("")){
				minNormalprice = getParaToInt("minNormalprice");
			}
			if(getPara("maxNormalprice") != null && !getPara("maxNormalprice").equals("")){
				maxNormalprice = getParaToInt("maxNormalprice");
			}
			if(getPara("minMemberprice") != null && !getPara("minMemberprice").equals("")){
				minMemberprice = getParaToInt("minMemberprice");
			}
			if(getPara("maxMembelprice") != null && !getPara("maxMembelprice").equals("")){
				maxMembelprice = getParaToInt("maxMembelprice");
			}
			Date minpdate=null;
			Date maxpdate= null;
			try {
				if(getPara("minpdate")!=null && !getPara("minpdate").equals(""))
				minpdate = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE).parse( getPara("minpdate"));
				
				if(getPara("maxpdate")!=null && !getPara("maxpdate").equals(""))
					maxpdate = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE).parse( getPara("maxpdate"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			List<Product> products = Product.dao.search(categoryid, keyword,minNormalprice, maxNormalprice, minMemberprice,maxMembelprice,minpdate,maxpdate);
			setAttr("products", products);
			render("searchOK.html");
		}else{
			render("search.html");
		}
	}

	public void list() {
		int pageNo;
		if (getPara() != null) {
			try {
				pageNo = getParaToInt();
				if (pageNo < 1) {
					pageNo = 1;
				}
			} catch (Exception e) {
				pageNo = 1;
				e.printStackTrace();
			}
		} else {
			pageNo = 1;
		}
		this.setAttr("products", Product.dao.getAllProduct(pageNo).getList());
		setAttr("pageNo", pageNo);
		render("list.html");
	}

	public void update() {
		int id = 0;
		if(getPara()!=null)
			id = getParaToInt();
		if(id>0)
		setAttr("product", Product.dao.findById(id));
		if (getPara("update") != null && getPara("update").equals("ok")) {
			String name = getPara("name");
			int pid = getParaToInt("id");
			String descr = getPara("descr");
			int normalprice = getParaToInt("normalprice");
			int memberprice = getParaToInt("memberprice");
			int categoryId = getParaToInt("categoryId");
			Product p = Product.dao.findById(pid);
			p.set("name", name)
			 .set("descr", descr)
			.set("normalprice", normalprice)
			.set("memberprice", memberprice)
			.set("categoryid", categoryId);
			if (p.update()) {
				setAttr("info", "更新成功");
			} else {
				setAttr("info", "更新失败");
			}
		}
		this.setAttr("categories", Category.dao.getAllLeafCategory());
		render("update.html");
	}
	public void delete() {
		int id;
		if(getPara("id")!=null){
			id = getParaToInt("id");
			System.out.println(id);
			if (Product.dao.deleteById(id)) {
				renderText("yes");
			}
		}else{
			renderNull();
		}
	}
	
	public void detail(){
		int id = 0;
		if(getPara()!=null){
			id = getParaToInt();
			setAttr("product",Product.dao.findById(id));
		}
		render("detail.html");
	}
	public void uploadfile(){
		int id = 0;
		if(getPara()!=null){
			id = getParaToInt();
			setAttr("id", id);
			render("uploadfile.html");
			return;
		}
		renderNull();
	}
	
	public void upload(){
		int maxPostSize = 10 * 1024 * 1024;
		String path  = PathKit.getWebRootPath();
		path +="\\img\\product";
		UploadFile upfile = getFile("FILE", path, maxPostSize, "utf-8");
		String pid = getPara("id");
		if( upfile.getFile().renameTo(new File(path+"\\"+pid + ".jpg"))){
			renderHtml("<h2>上传成功</h2>");
			return;
		}else{
			renderHtml("<h2>上传失败</h2>");
			return;
		}
	}
}
