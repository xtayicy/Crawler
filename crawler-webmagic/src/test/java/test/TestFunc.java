package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

/**
 * http://www.noobyard.com/article/p-ehbhpvsr-ra.html
 * @author Harry
 *
 */
public class TestFunc {
	@Test
	public void httpClient() throws ClientProtocolException, IOException{
		HttpClient client = new DefaultHttpClient();
		String url = "http://www.w3school.com.cn/b.asp";
		HttpGet getMethod = new HttpGet(url);
		HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1,
		        HttpStatus.SC_OK, "OK");
		response = client.execute(getMethod);
		String status = response.getStatusLine().toString();
		int statusCode = response.getStatusLine().getStatusCode();
		ProtocolVersion protocolVersion = response.getProtocolVersion();
		String phrase = response.getStatusLine().getReasonPhrase();
		if(statusCode == 200){                          //状态码200表示响应成功
		    String entity = EntityUtils.toString (response.getEntity(),"utf-8");
		    //System.out.println(entity);
		    EntityUtils.consume(response.getEntity());       
		}else {
		    EntityUtils.consume(response.getEntity());        
		}
	}
	
	@Test
	public void jsoup() throws IOException{
		/*Document doc = Jsoup.connect("http://www.w3school.com.cn/b.asp").post();
		System.out.println(doc);*/
		Document doc = Jsoup.connect("http://www.w3school.com.cn/b.asp").get();
		//获取Element,这里相当于div[id=w3school]
		Element element = doc.select("div#w3school").get(0);
		//System.out.println(element);
		String text = element.select("h1").text();
		String text1 = element.select("p").text();
		
		element = doc.select("div[id=course]").get(0);
		//System.out.println(element);
		Elements elements = doc.select("div[id=course]").select("li"); 
		for (Element ele : elements) {
		    String title = ele.select("a").text();
		    String course_url = ele.select("a").attr("href"); 
		    //System.out.println("课程的标题为:" + title + "\t对应的URL为" + course_url);
		}
		
		element = doc.select("[id=course]").get(0);
		//System.out.println(element);
		
		element = doc.select("#course").get(0);
		//System.out.println(element);
		
		//System.out.println(doc.select(".browserscripting").text());
		
		//利用匹配属性值开头、结尾或包含属性值来查找元素(很常用的方法)[attr^=value], [attr$=value], [attr*=value]
		//System.out.println(doc.select("#course").select("[href$=index.asp]").text());
		//[attr~=regex]: 利用属性值匹配正则表达式来查找元素,*指匹配所有元素
		//System.out.println(doc.select("#course").select("[href~=/*]").text());
	}
}
