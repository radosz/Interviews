package servlets;

import javax.servlet.ServletContext;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.annotation.JacksonFeatures;

import logic.Category;
import logic.JsonAdapter;
import logic.News;

@Path("/")
public class ReadNews {
	@Context
	private ServletContext sc;
	
	@GET
	@Path("/{param}")
	@Produces("text/html; charset=UTF-8")
	public Response getNews(@PathParam("param") String category){
		String folder = sc.getRealPath("/WEB-INF")+"/url/";
		String path = folder+category+".txt";
		StringBuilder webBuilder = new StringBuilder();
		try{
			webBuilder.append(new Category(folder).toHTML(sc.getContextPath()));
			webBuilder.append(new News().toHTML(path));
			return Response.status(200).entity(webBuilder.toString()).build();
		}catch(NullPointerException e){
			return Response.status(400).build();
		}
		
	}
	
	@GET
	@Path("/")
	@Produces("text/html; charset=UTF-8")
	public Response index(){
		String folder = sc.getRealPath("/WEB-INF")+"/url/";
		Category menu =  new Category(folder);
		return Response.status(200).entity(menu.toHTML(sc.getContextPath())).build();
		
	}
	
	@GET
	@Path("/rest/{param}")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
	@JacksonFeatures(serializationEnable =  { SerializationFeature.INDENT_OUTPUT })
	public JsonAdapter getTrackInJSON(@PathParam("param") String category) {
		JsonAdapter news = new JsonAdapter();
		String folder = sc.getRealPath("/WEB-INF")+"/url/";
		String path = folder+category+".txt";
		news.getJson(path);
		return news;
 
	}
}
