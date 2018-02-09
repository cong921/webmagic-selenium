package hello;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.annotation.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lcw.dao.mapper.CrawlerXqMapper;
import lcw.po.CrawlerXq;
import lcw.po.CrawlerXqExample;
import lcw.po.CrawlerXqWithBLOBs;
import lcw.po.CrawlerXqExample.Criteria;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.utils.FilePersistentBase;
import util.MyBatisUtil;

/**
 * Store results in files.<br>
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.1.0
 */
@ThreadSafe
public class BeiJingPipleline extends FilePersistentBase implements Pipeline {

	private Logger logger = LoggerFactory.getLogger(getClass());

	/*
	 * private static ConnectionPool connectionPool = new
	 * ConnectionPool("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/spider",
	 * "root", "root");
	 * 
	 *//**
		 * create a FilePipeline with default path"/data/webmagic/"
		 *//*
			 * public JubiPipleline() { setPath("/data/webmagic/"); }
			 * 
			 * public JubiPipleline(String path) { setPath(path); }
			 */

	@Override
	public void process(ResultItems resultItems, Task task) {
		List<List<Object>> list = resultItems.get("list");
		SqlsessionInsert taxesInsert = new BeiJingInsert();
		List<CrawlerXqWithBLOBs> list1 = new ArrayList<>();
		for (List<Object> listStr : list) {
			CrawlerXqWithBLOBs exchange = new CrawlerXqWithBLOBs();
			exchange.setXqUrl("http://www.longyan.cn/ly_mo/public/mysound/myVoiceDetail.jsp?id=" + listStr.get(1).toString());
			exchange.setXqUrlMd5(MD5Utils.createMD5(exchange.getXqUrl()));
			exchange.setXqPudate(null);
			exchange.setXqTitle(listStr.get(2).toString());
			exchange.setZdName("龙岩");
			exchange.setLmName("我的声音");
			exchange.setSjfl("公共服务");
			exchange.setXqContent(null);
			exchange.setLoadTime(new Date());
			exchange.setIsSimtitle(0);;
			exchange.setIsExteurl(0);
//			System.out.println("++++++++++++++=============");
//			System.out.println(listStr);
//			System.out.println(exchange);
//			System.out.println("++++++++++++++=============");
			CrawlerXqExample example = new CrawlerXqExample();
			Criteria createCriteria = example.createCriteria();
			createCriteria.andXqUrlMd5EqualTo(exchange.getXqUrlMd5());
			List<CrawlerXq> selectByExample=new ArrayList<>();
			try {
				selectByExample= MyBatisUtil.getSqlSession().getMapper(CrawlerXqMapper.class)
						.selectByExample(example);
			} finally {
				if(MyBatisUtil.getSqlSession()!=null)
				MyBatisUtil.getSqlSession().close();
			}
			if (selectByExample.size() < 1)
				list1.add(exchange);
		}
		taxesInsert.setList(list1);
		try {
			taxesInsert.insert();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
