package com.hjp.shop.controller;

import com.hjp.shop.model.Product;
import com.jfinal.core.Controller;

public class IndexController extends Controller {
	public void index() {
		setAttr("products", Product.dao.getLaestProduct(10));
		render("index.html");
	}
}
