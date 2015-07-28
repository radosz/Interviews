package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.sun.syndication.feed.synd.SyndEnclosureImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class News implements WriteHTML {

	private List<URL> urls = new ArrayList<URL>();
	private List<SyndEntry> news = new ArrayList<SyndEntry>();
	private SyndFeedInput rssInput = new SyndFeedInput();
	private List<String> titles = new ArrayList<String>();
	private List<String> descriptions = new ArrayList<String>();
	private List<String> links = new ArrayList<String>();
	private List<String> enclosures = new ArrayList<String>();
	private List<String> authors = new ArrayList<String>();

	@SuppressWarnings("unchecked")
	private List<SyndEntry> getNews() {
		Iterator<URL> rss = urls.iterator();
		HttpURLConnection httpcon = null;
		SyndFeed feed;
		while (rss.hasNext()) {
			try {
				httpcon = (HttpURLConnection) rss.next().openConnection();
				feed = rssInput.build(new XmlReader(httpcon));
				news.addAll(feed.getEntries());
			} catch (IllegalArgumentException | FeedException | IOException e) {
				// TODO Auto-generated catch block
			}
		}
		httpcon.disconnect();
		return news;
	}

	public List<URL> getUrls() {
		return urls;
	}

	private void setUrl(String url) throws MalformedURLException {
		urls.add(new URL(url));
	}

	public void loadUrlsFromFile(String path) {
		File file = new File(path);
		try {
			Scanner reader = new Scanner(file);
			while (reader.hasNext()) {
				setUrl(reader.nextLine());
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getNews();
		read();
	}

	private void read() {
		Iterator<SyndEntry> news = this.news.iterator();
		while (news.hasNext()) {
			SyndEntry item = news.next();
			titles.add(item.getTitle());
			try {
				authors.add(new URL(item.getLink()).getHost());
			} catch (MalformedURLException e1) {
				// pass
			}
			links.add(item.getLink());
			descriptions.add(item.getDescription().getValue());
			try {
				SyndEnclosureImpl enc = (SyndEnclosureImpl) item
						.getEnclosures().get(0);
				enclosures.add(enc.getUrl());
			} catch (IndexOutOfBoundsException e) {
				enclosures.add("NULL");
			}
		}
	}
	
	@Override
	public String toHTML(String path) {
		StringBuilder sb = new StringBuilder();
		loadUrlsFromFile(path);
		String author = "";
		sb.append("<head><meta charset='UTF-8'></head>");
		sb.append(("<h1>" + "Последни новини :" + "</h1>"));
		for (int i = 0; i < getDescriptions().size(); i++) {
			if (!author.equals(getAuthors().get(i))) {
				sb.append("<hr>");
				sb.append("<h4>Източник : " + getAuthors().get(i) + "</h4>");
			}
			sb.append("<a href=" + getLinks().get(i) + ">" + "<h3>"
					+ getTitles().get(i) + "</h3></a>");
			String img = getEnclosures().get(i);
			if (!img.equals("NULL")) {
				sb.append("<img src=" + getEnclosures().get(i)
						+ " align='center' >");
			}
			sb.append("<p>" + getDescriptions().get(i) + "</p>");
			sb.append("<br></br>");
			author = getAuthors().get(i);
		}
		System.out.println(sb.toString());
		return sb.toString();
	}

	public List<String> getTitles() {
		return titles;
	}

	public List<String> getDescriptions() {
		return descriptions;
	}

	public List<String> getLinks() {
		return links;
	}

	public List<String> getEnclosures() {
		return enclosures;
	}

	public List<String> getAuthors() {
		return authors;
	}

}
