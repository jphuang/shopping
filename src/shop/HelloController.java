package shop;

import com.jfinal.core.Controller;
public class HelloController extends Controller{
	public void index(){
		this.renderText("Hello World,Jfianl");
		
	}
}
