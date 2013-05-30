package com.hjp.shop.controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.hjp.shop.model.Product;
import com.hjp.shop.model.Salesitem;
import com.jfinal.core.Controller;
import com.jfinal.kit.PathKit;

public class ReportController extends Controller {
		public void chart(){
			 //步骤1：创建CategoryDataset对象（准备数据）     
	        CategoryDataset dataset = createDataset();     
	        DefaultPieDataset pieDataset = createPieDataset();     
	        //步骤2：根据Dataset 生成JFreeChart对象，以及做相应的设置     
	        JFreeChart freeChart = createChart(dataset);     
	        JFreeChart freeChart2 = createPieChart(pieDataset);     
	        //步骤3：将JFreeChart对象输出到文件，Servlet输出流等  
	        String path  = PathKit.getWebRootPath();
			String path1 =path + "\\img\\report\\rep.png";
			String path2 =path + "\\img\\report\\pie.png";
	        saveAsFile(freeChart, path1, 600, 400);
	        saveAsFile(freeChart2, path2, 500, 400);
	        render("report.html");
		}
		
		 //保存为文件     
	    public static void saveAsFile(JFreeChart chart, String outputPath, int weight, int height) {     
	        FileOutputStream out = null;     
	        try {     
	            File outFile = new File(outputPath);     
	            if (!outFile.getParentFile().exists()) {     
	                outFile.getParentFile().mkdirs();     
	            }     
	            out = new FileOutputStream(outputPath);     
	            //保存为PNG文件     
	            ChartUtilities.writeChartAsPNG(out, chart, weight, height);     
	            //保存为JPEG文件     
	            //ChartUtilities.writeChartAsJPEG(out, chart, 500, 400);     
	            out.flush();     
	        } catch (FileNotFoundException e) {     
	            e.printStackTrace();     
	        } catch (IOException e) {     
	            e.printStackTrace();     
	        } finally {     
	            if (out != null) {     
	                try {     
	                    out.close();     
	                } catch (IOException e) {    
	                	e.printStackTrace();
	                }     
	            }     
	        }     
	    }     
	    
	    //根据CategoryDataset生成JFreeChart对象     
	    public static JFreeChart createChart(CategoryDataset categoryDataset) {    
	        //创建主题样式  
	        StandardChartTheme standardChartTheme=new StandardChartTheme("CN");  
	        //设置标题字体  
	        standardChartTheme.setExtraLargeFont(new Font("隶书",Font.BOLD,20));  
	        //设置图例的字体  
	        standardChartTheme.setRegularFont(new Font("宋书",Font.PLAIN,15));  
	        //设置轴向的字体  
	        standardChartTheme.setLargeFont(new Font("宋书",Font.PLAIN,15));  
	        //应用主题样式  
	        ChartFactory.setChartTheme(standardChartTheme); 
	        JFreeChart jfreechart = ChartFactory.createBarChart("产品销量图",    //标题     
	                "产品",    //categoryAxisLabel （category轴，横轴，X轴的标签）     
	                "数量",    //valueAxisLabel（value轴，纵轴，Y轴的标签）     
	                categoryDataset, // dataset     
	                PlotOrientation.VERTICAL,     
	                true, // legend     
	                false, // tooltips     
	                false); // URLs     
	             
	        //以下的设置可以由用户定制，也可以省略     
	        CategoryPlot  plot = (CategoryPlot) jfreechart.getPlot();     
	        //背景色　透明度     
	        plot.setBackgroundAlpha(0.5f);     
	        //前景色　透明度     
	        plot.setForegroundAlpha(0.5f);     
	        //其它设置可以参考 CategoryPlot     
	             
	        return jfreechart;     
	    }     
	    
	    /**   
	     * 创建CategoryDataset对象   
	     *    
	     */    
	    public static CategoryDataset createDataset() {     
	             
	    	List<Salesitem> items = Salesitem.dao.getAllItem(); 
	        DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();     
	        for (Salesitem salesitem : items) {
	        	String name = Product.dao.findById(salesitem.getInt("productid")).getStr("name");
	        	 categoryDataset.addValue(salesitem.getNumber("count"), "", name);
			}
	             
	        return categoryDataset;             
	    }   
	    public static DefaultPieDataset createPieDataset() {     
	    	
	    	List<Salesitem> items = Salesitem.dao.getAllItem(); 
	    	DefaultPieDataset dfp = new DefaultPieDataset();     
	    	for (Salesitem salesitem : items) {
	    		String name = Product.dao.findById(salesitem.getInt("productid")).getStr("name");
	    		dfp.setValue(name,salesitem.getNumber("count"));
	    	}
	    	
	    	return dfp;             
	    }   
	    
	    public static JFreeChart createPieChart(DefaultPieDataset dfp){
	    	//创建主题样式  
	        StandardChartTheme standardChartTheme=new StandardChartTheme("CN");  
	        //设置标题字体  
	        standardChartTheme.setExtraLargeFont(new Font("隶书",Font.BOLD,20));  
	        //设置图例的字体  
	        standardChartTheme.setRegularFont(new Font("宋书",Font.PLAIN,15));  
	        //设置轴向的字体  
	        standardChartTheme.setLargeFont(new Font("宋书",Font.PLAIN,15));  
	        //应用主题样式  
	        ChartFactory.setChartTheme(standardChartTheme); 
	    	JFreeChart chart = ChartFactory.createPieChart3D("产品销量饼图",dfp, true, true, true);
	    	PiePlot3D plot = (PiePlot3D) chart.getPlot();  
	        // 图形边框颜色  
	        plot.setBaseSectionOutlinePaint(Color.RED);  
	        // plot.setBaseSectionPaint(Color.WHITE);  
	        // 图形边框粗细  
	        plot.setBaseSectionOutlineStroke(new BasicStroke(1.0f));  
	 
	        // 指定图片的透明度(0.0-1.0)  
	        plot.setForegroundAlpha(0.65f);  
	        // 指定显示的饼图上圆形(false)还椭圆形(true)  
	        plot.setCircular(true);  
	 
	        // 设置第一个 饼块section 的开始位置，默认是12点钟方向  
	        plot.setStartAngle(360);  
	        // 设置鼠标悬停提示  
	        plot.setToolTipGenerator(new StandardPieToolTipGenerator()); 
	    	return chart;
	    }
}
