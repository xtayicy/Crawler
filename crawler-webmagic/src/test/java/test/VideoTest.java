package test;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.junit.Test;

/**
 * 
 * @author Harry
 *
 */
public class VideoTest {
	@Test
	public void test() throws IOException{
		int size = 1;
		
		Document doc = Jsoup.connect("https://www.flpdown.com/").get();
		Elements moreEelements = doc.select(".more");
		for (Element moreElement : moreEelements) {
			String url = moreElement.select("a").attr("href");
			if(size == 1){
				doc = Jsoup.connect(url).get();
				Element postElement = doc.select("#posts").get(0);
				Elements postEelements = postElement.select(".post");
				for (Element e1 : postEelements) {
					url = e1.select("a").attr("href");
					//System.out.println(url);
				}
				
				Element paginationElement = doc.select(".pagination").get(0);
				System.out.println(paginationElement);
				
				Element nextPageElement = paginationElement.select(".next-page > a").get(0);
				String nextPageUrl = nextPageElement.select("a").attr("href");
				System.out.println(nextPageUrl);
				
				size++;
			}else{
				//System.out.println(url);
			}
		}
	}
}
