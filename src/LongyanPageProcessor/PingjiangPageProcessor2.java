package LongyanPageProcessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class PingjiangPageProcessor2 implements PageProcessor {
	private Site site=Site
		    .me()
		    .setDomain("http://banshi.beijing.gov.cn")
		    .setSleepTime(100)
		    .setUserAgent(
		            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}

	@Override
	public void process(Page page) {
		Document parse = Jsoup.parse(page.getHtml().toString());
		Elements select = parse.select("body > table:nth-child(2) > tbody > tr > td:nth-child(3) > table.td_color7 > tbody > tr > td:nth-child(2)");
		List<Map<String,String>> listMap=new ArrayList<Map<String,String>>();
		for (Element element : select) {
			String title = element.attr("title");
			Elements select2 = element.select("a");
			String href=select2.attr("href");
			Map<String,String> map=new HashMap<String,String>();
			map.put("title", title);
			map.put("href", href);
			listMap.add(map);
		}
		if(listMap!=null) page.putField("listMap", listMap);
	}
	public static void main(String[] args) {
		Spider spider=null;
		int i=0;
//		for(int i=0;i<2257;i++)
			spider=Spider.create(new PingjiangPageProcessor2()).addUrl("http://wsbs.hunan.gov.cn/virtualhall/instance/baseinfolist.jsp?queryName=0&queryValue=&pager.offset="+String.valueOf(i*16)+"&pager.sortKey=null&pager.desc=true").addPipeline(new ConsolePipeline());
		spider.thread(100).run();
	}
}
