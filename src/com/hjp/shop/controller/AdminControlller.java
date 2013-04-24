package com.hjp.shop.controller;

import java.io.StringWriter;
import java.util.Date;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import com.jfinal.core.Controller;

public class AdminControlller extends Controller {
	
	public void index() {
		
		//取得引擎
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, "WebRoot/admin");
		try {
			
			
			//初始化引擎
			ve.init();
			
			//取得velocity的模板
			Template template = ve.getTemplate("index.vm");
			
			//取得上下文
			VelocityContext content = new VelocityContext();
			
			//数据填入上下文
			content.put("name", "huangjiangping");
			content.put("date", new Date().toString());
			
			//输出流
			StringWriter writer = new StringWriter();
			
			//转换输出
			template.merge(content, writer);
			renderVelocity(writer.toString());
			//renderHtml(writer.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
