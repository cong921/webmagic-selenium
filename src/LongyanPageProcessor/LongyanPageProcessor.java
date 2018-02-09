package LongyanPageProcessor;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import hello.BeiJingPipleline;
import hello.BeijingPageProcessor;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class LongyanPageProcessor implements PageProcessor {
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
		System.out.println(select);
		
		
		
	}
	public static void main(String[] args) {
		Spider.create(new LongyanPageProcessor()).addUrl("http://www.longyan.cn/ly_mo/public/mysound/myVoiceDetail.jsp?id=2081").addPipeline(new ConsolePipeline()).run();

	}
}
