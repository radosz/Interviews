package logic;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Category implements WriteHTML {
	
	private List<String> newsType;
	private String folder;

	
	public Category(String folder){
		newsType = new ArrayList<String>();
		this.folder = folder;
	}
	
	public List<String> getCategory(){
		File folder = new File(this.folder);
		for(File fullname : folder.listFiles()){
			String name = fullname.getName();
			String[] parts = name.split("\\.");
			newsType.add(parts[0]);
		}
		return newsType;
	}
	
	@Override
	public String toHTML(String  servletContextPath){
		if(newsType.isEmpty()){
			getCategory();
		}
		StringBuilder  sb =  new StringBuilder();
		sb.append("<head><meta charset='UTF-8'></head>");
		sb.append("<div>");
		sb.append("<a href ='"+servletContextPath+"'>Начало"+"</a>");
		for(String category :  newsType){
			sb.append(" ");
			sb.append("<a href ='"+servletContextPath+"/"+category+"'>"+category+"</a>");
		}
		sb.append("<hr>");
		sb.append("</div>");
		return sb.toString();
	}
	
	public static void main(String[] args) {
		Category category =  new Category("/home/rado_sz/Interview/News/src/logic/");
		System.out.println(category.toHTML("/News"));
		
	}

}

