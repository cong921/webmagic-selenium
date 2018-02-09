package hello;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lcw.dao.mapper.CrawlerXqMapper;
import lcw.po.CrawlerXq;
import lcw.po.CrawlerXqExample;
import lcw.po.CrawlerXqExample.Criteria;
import lcw.po.CrawlerXqWithBLOBs;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;
import util.MyBatisUtil;

public class BeijingPageProcessor implements PageProcessor,Job {
	private static Logger logger= Logger.getLogger(BeijingPageProcessor.class) ;
	private Site site=Site
    .me()
    .setDomain("http://banshi.beijing.gov.cn")
    .setSleepTime(300)
    .setUserAgent(
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");;
	
	public void setSite(Site site) {
		this.site = site;
	}

	public static void main(String[] args) {
		Spider.create(new BeijingPageProcessor()).addUrl("http://www.longyan.cn/ly_mo/mo/service/Mo.getConditionQueryEventList.json?STATUS=8&PAGE_INDEX=1&PAGE_COUNT="+TotalPageUtilProcessor.getTotalPage()).addPipeline(new BeiJingPipleline()).run();
//		CrawlerXqWithBLOBs selectOne = MyBatisUtil.getSqlSession().selectOne("lcw.dao.CrawlerXqMapper.selectByXqUrlMd5", "0cb76ba53c9bdb6d4140b301f2c83e65");
//		CrawlerXqWithBLOBs crawlerXqWithBLOBs=new CrawlerXqWithBLOBs();
//		crawlerXqWithBLOBs.setXqUrlMd5("0cb76ba53c9bdb6d4140b301f2c83e65");
//		crawlerXqWithBLOBs.setXqId(12829l);
//		CrawlerXqExample example=new CrawlerXqExample();
//		Criteria createCriteria = example.createCriteria();
//		createCriteria.andXqUrlMd5EqualTo("0cb76ba53c9bdb6d4140b301f2c83e65");
//		List<CrawlerXq> selectByExample = MyBatisUtil.getSqlSession().getMapper(CrawlerXqMapper.class).selectByExample(example);
//		System.out.println(selectByExample.size());
		
	}
	
	@Override
	public Site getSite() {
		
		return site;
	}

	@Override
	public void process(Page page) {
		Json json = page.getJson();
		
		 JsonParser parser=new JsonParser();  //创建JSON解析器
		 System.out.println(json.toString());
         JsonElement object=parser.parse(json.toString());  //创建JsonObject对象
         JsonArray jsonList = object.getAsJsonObject().get("responseData").getAsJsonObject().get("eventList").getAsJsonArray();
//         String s= jsonList.get(1).getAsJsonObject().get("content").getAsString();
//         System.out.println("+++++++"+s+"++++++");
		List<List<Object>> list=new ArrayList<>();
    	for (JsonElement json2 : jsonList) {
    		List<Object> list1=new ArrayList<Object>();
    		int i=json2.getAsJsonObject().get("content").getAsString().length();
    		if(i>900) {
    			
    			list1.add(json2.getAsJsonObject().get("content").getAsString().substring(0, 900));
    		}else {
    			list1.add(json2.getAsJsonObject().get("content").getAsString());
    		}
    		list1.add(json2.getAsJsonObject().get("id").getAsString());
    		list1.add(list1.get(0));
    		list.add(list1);
    		logger.warn("+++++"+list1);
		}
    	page.putField("list", list);
		
	}
/*	@Override
	public void process(Page page) {
		Json json = page.getJson();
		System.out.println("+++++");
		System.out.println(json);
		System.out.println("+++++");
		JSONObject jsonObject=json.toObject(JSONObject.class);
		System.out.println(jsonObject.get("responseData"));
		Object object = jsonObject.get("responseData");
		List<JSONObject> jsonList = object.toList(JSONObject.class);
		List<List<Object>> list=new ArrayList<>();
		for (JSONObject json2 : jsonList) {
			List<Object> list1=new ArrayList<Object>();
			System.out.println(list1);
			list1.add(json2.getString("zn_url"));
			list1.add(json2.getString("xz_type"));
			list1.add(json2.getString("title"));
			list1.add(json2.getString("server_object"));
			list1.add(json2.getString("zt_type"));
			list1.add(json2.getString("dx_type"));
			list1.add(json2.getString("zq_type"));
			list1.add(json2.getString("bm_type"));
			list.add(list1);
			logger.info("+++++"+list1);
		}
		page.putField("list", list);
		
	}
*/
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		Spider.create(new BeijingPageProcessor()).addUrl("http://banshi.beijing.gov.cn/jsonData/201710/t20171007_50750.json?_=1507532502499").addPipeline(new BeiJingPipleline()).run();
		Spider.create(new BeijingPageProcessor()).addUrl("http://banshi.beijing.gov.cn/jsonData/201710/t20171007_50748.json?_=1507532502499").addPipeline(new BeiJingPipleline()).run();
		Spider.create(new BeijingPageProcessor()).addUrl("http://banshi.beijing.gov.cn/jsonData/201710/t20171007_50746.json?_=1507532502499").addPipeline(new BeiJingPipleline()).run();
		
	}
}
