package com.hjp.shop.controller;

import java.util.List;

import com.hjp.shop.model.Product;
import com.jfinal.aop.ClearInterceptor;
import com.jfinal.aop.ClearLayer;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class IndexController extends Controller {
	public void index() {
		setAttr("products", Product.dao.getLaestProduct(10));
		render("index.html");
	}
	
	@ClearInterceptor(ClearLayer.ALL)
	@ActionKey("/log")
	public void log(){
		long pageSize = 10;
		long pageNo = 1;
		if( getPara()!=null){
			pageNo = getParaToLong();
		}
		long count  = Db.findFirst("select count(*) count from tbl_log").getLong("count");
		long pageCount = (count + pageSize -1) /pageSize;
		
		if(pageNo > pageCount){
			pageNo = pageCount;
		}
		List<Record> logPage = Db.paginate((int)pageNo, (int)pageSize, "select *", "from tbl_log order by id desc").getList(); 
		setAttr("pageNo", pageNo);
		setAttr("pageCount", pageCount);
		setAttr("logList", logPage);
		render("/log/list.html");
	}
}
