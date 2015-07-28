package logic;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JsonAdapter {
	
	private List<Map<String,String>> news;

	public JsonAdapter(){
		news = new ArrayList<Map<String,String>>();
	}
	
	public List<Map<String, String>> getJson(String path){
		News newsObj = new News();
		newsObj.loadUrlsFromFile(path);
		Map<String,String> map = new LinkedHashMap<String,String>();
			for (int i = 0; i < newsObj.getDescriptions().size(); i++) {
				map = new LinkedHashMap<String,String>();
				map.put("title",removeHTML(newsObj.getTitles().get(i)));
				map.put("link",removeHTML(newsObj.getLinks().get(i)));
				map.put("enclosure",removeHTML(newsObj.getEnclosures().get(i)));
				map.put("description",removeHTML(newsObj.getDescriptions().get(i)));
				news.add(map);
			}
		return news;
	}
	
	private String removeHTML(String noHtml){
		String removeTags = "\\<.*?>";
		noHtml = noHtml.replaceAll(removeTags, "");
		return noHtml;
	}

	public List<Map<String, String>> getNews() {
		return news;
	}
	
	

}
