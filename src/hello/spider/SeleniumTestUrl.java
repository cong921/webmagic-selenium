package hello.spider;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import hello.BeiJingInsert;
import hello.MD5Utils;
import hello.SqlsessionInsert;
import lcw.dao.mapper.CrawlerXqMapper;
import lcw.po.CrawlerXq;
import lcw.po.CrawlerXqExample;
import lcw.po.CrawlerXqExample.Criteria;
import lcw.po.CrawlerXqWithBLOBs;
import util.MyBatisUtil;

/**
 * @author 邮箱 921894@gmail.com</n>
 */
public class SeleniumTestUrl implements Runnable {
	static {
		File file=new File("D:/url1.txt");
		if(file.exists())
			file.delete();
	}
	private String url;
	{
//		System.setProperty("webdriver.chrome.driver",
//				"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
		 System.setProperty("webdriver.ie.driver",
		 "C:\\Program Files\\Internet Explorer\\IEDriverServer.exe");
	}

	public SeleniumTestUrl() {
	}

	public SeleniumTestUrl(String url) {
		this.url = url;
	}

//	public static void main(String[] args) throws IOException, InterruptedException {
//		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
//		Queue<SeleniumTestUrl> synchronousQueue = new LinkedBlockingQueue<>();
//		BufferedReader br = new BufferedReader(
//				new InputStreamReader(new FileInputStream(new File("D:/urllist.txt")), "utf-8"));
//		String data = null;
//		while ((data = br.readLine()) != null)
//			synchronousQueue.offer(new SeleniumTestUrl(data));
//		while (!synchronousQueue.isEmpty()) {
//			fixedThreadPool.execute(synchronousQueue.poll());
//		}
//	}
	public static void main(String[] args) {
		test1();
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub

		// 第一步： 设置chromedriver地址。一定要指定驱动的位置。
		// System.setProperty("webdriver.ie.driver",
		// "C:\\Program Files\\Internet Explorer\\IEDriverServer.exe");
		// System.setProperty("webdriver.chrome.driver",
		// "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
//		ChromeOptions options = new internet();
		// 设置user agent为iphone5
		options.addArguments("--user-agent=Apple Iphone os X");
		// 第二步：初始化驱动
		WebDriver driver = new ChromeDriver(options);
		// 第三步：获取目标网页
		// driver.get("https://www.baidu.com");
		try {
//			if(hasUrl(url)) {
//				driver.quit();
//				return;
//			}
			try {
				
				driver.get("http://shipin.beijing.gov.cn/index.php?option=spbj,view&id=6133");
				Thread.sleep(1000);
				while(!isElementPresent(By.xpath("//h1"), driver));
				WebElement findElement = driver.findElement(By.xpath("//h1"));
				String text = findElement.getText();
				System.out.println(text);
				String video = driver.findElement(By.xpath("//*[@id=\"vodPlayer\"]/video")).getAttribute("src");
				video="<video width=\"100%\" height=\"auto\" controls=\"controls\" src="+video+" autoplay=\"\"></video>";
				insert(url,text,video);
				// System.out.println(driver.getPageSource());
			} catch (InterruptedException e) {
				e.printStackTrace();

			} finally {
				driver.quit();
				Thread.currentThread();
				Thread.yield();
			}
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	  private boolean isElementPresent(By by,WebDriver driver) { 
		    try { 
		      driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS); 
		      driver.findElement(by); 
		      return true; 
		    } catch (NoSuchElementException e) { 
		      return false; 
		    } 
	  }
	public boolean hasUrl(String url) {
		 CrawlerXqWithBLOBs exchange = new CrawlerXqWithBLOBs();
		 exchange.setXqUrlMd5(MD5Utils.createMD5(url));
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
         if(selectByExample.size()>0)
        	 return true;
         return false;
	}
	//将url video title插入到数据库
	public void insert(String url1,String title1,String video1) {
		
//      System.out.println(resultItems);
        List<Map<String,String>> list = new ArrayList<>();
        Map<String,String> map1=new HashMap<>();
        map1.put("url", url1);
        map1.put("title", title1);
        map1.put("video", video1);
        
        list.add(map1);
        if(list==null) return;
        SqlsessionInsert taxesInsert=null;
        List<CrawlerXqWithBLOBs> list1 = new ArrayList<>();
        for (Map<String, String> map : list) {
            String title = map.get("title");
            String url = map.get("url");
            if(title!=null) {
                 taxesInsert = new BeiJingInsert();
                 


                CrawlerXqWithBLOBs exchange = new CrawlerXqWithBLOBs();
                exchange.setXqUrl(url);
                exchange.setXqUrlMd5(MD5Utils.createMD5(url));
//                System.out.println("++++++++++++++++++++");
//                System.out.println(url);
//                System.out.println(MD5Utils.createMD5(url));
//                System.out.println("++++++++++++++++++++");
                exchange.setXqPudate(null);
                exchange.setXqTitle(title);
                exchange.setZdName("首都之窗");
                exchange.setLmName("政风行风系列");
                exchange.setSjfl("信息公开");
                exchange.setXqContent(video1);
                exchange.setLoadTime(new Date());
                exchange.setIsSimtitle(0);;
                exchange.setIsExteurl(0);
                exchange.setLmTop("视频北京");
                exchange.setSta(200);
//              System.out.println("++++++++++++++=============");
//              System.out.println(exchange);
//              System.out.println("++++++++++++++=============");
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
        }
        taxesInsert.setList(list1);
        try {
            taxesInsert.insert();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	@Test
	public static void test1() {// 第一步： 设置chromedriver地址。一定要指定驱动的位置。
		// System.setProperty("webdriver.ie.driver",
		// "C:\\Program Files\\Internet Explorer\\IEDriverServer.exe");
		System.setProperty("webdriver.chrome.driver",
				"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		// 设置user agent为iphone5
//		options.addArguments("--user-agent=Apple Iphone X");
		// 第二步：初始化驱动
		WebDriver driver = new ChromeDriver(options);
		// 第三步：获取目标网页
		// driver.get("https://www.baidu.com");
		driver.get("http://shipin.beijing.gov.cn/Portal/Html/option/com_content/ItemId/27/page/1/");
		 
		try {
			while(true) {
				Thread.sleep(100);
				List<WebElement> findElement = driver.findElements(By.xpath("//div[contains(@class, 'sdzcl_l')]/div[contains(@class, 'list')]/ul/li/a[contains(@class, 'leaidx')]"));
				BufferedWriter bw=null;
				try {
					
					 bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("D:/url1.txt"), true)));
					for (WebElement webElement : findElement) {
						String text = webElement.getAttribute("href");
						bw.write(text+"\r\n");
//					System.out.println(text);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					if(bw!=null)
						try {
							bw.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
				List<WebElement> next = driver.findElements(By.xpath("//*[@id=\"page\"]/a"));
				WebElement nextPage=null;
				for (WebElement webElement : next) {
//					System.out.println(webElement);
//					System.out.println(webElement.getText());
					if("下一页".equals(webElement.getText()))
						nextPage=webElement;
//					System.out.println();;
				}
				String old = driver.getCurrentUrl();
				if(nextPage!=null)
					nextPage.click();
				String news = driver.getCurrentUrl();
				System.out.println(old.equals(news));
				if(old.equals(news))
					break;
				Thread.sleep(500);

			}
			
			// System.out.println(driver.getPageSource());
		} catch (InterruptedException e) {
			e.printStackTrace();

		} finally {
			driver.quit();
		}
		// 第四步：解析。以下就可以进行解了。使用webMagic、jsoup等进行必要的解析。

	}

}

/*
 * // 第一步： 设置chromedriver地址。一定要指定驱动的位置。
 * //System.setProperty("webdriver.ie.driver", //
 * "C:\\Program Files\\Internet Explorer\\IEDriverServer.exe");
 * System.setProperty("webdriver.chrome.driver",
 * "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
 * 
 * ChromeOptions options = new ChromeOptions(); //设置user agent为iphone5
 * options.addArguments("--user-agent=Apple Iphone X"); // 第二步：初始化驱动 WebDriver
 * driver = new ChromeDriver(options); // 第三步：获取目标网页
 * //driver.get("https://www.baidu.com");
 * driver.get("http://shipin.beijing.gov.cn/index.php?option=spbj,view&id=9260")
 * ; try{ Thread.sleep(2000); WebElement findElement =
 * driver.findElement(By.xpath("/html/body/div[3]/h1")); String text =
 * findElement.getText(); System.out.println(text);
 * System.out.println(driver.findElement(By.xpath("//*[@id=\"vodPlayer\"]/video"
 * )).getAttribute("src")); // System.out.println(driver.getPageSource());
 * }catch(InterruptedException e) { e.printStackTrace();
 * 
 * }finally { driver.quit(); } // 第四步：解析。以下就可以进行解了。使用webMagic、jsoup等进行必要的解析。
 * 
 * 
 * 
 * // // 第一步： 设置chromedriver地址。一定要指定驱动的位置。 //
 * System.setProperty("webdriver.ie.driver", //
 * "C:\\Program Files\\Internet Explorer\\IEDriverServer.exe");
 * ////System.setProperty("webdriver.chrome.driver", ////
 * "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe"); //
 * // // 第二步：初始化驱动 // WebDriver driver = new InternetExplorerDriver(); // //
 * 第三步：获取目标网页 //
 * driver.get("http://shipin.beijing.gov.cn/Portal/Html/ItemId/109/9259.html");
 * // // 第四步：解析。以下就可以进行解了。使用webMagic、jsoup等进行必要的解析。 //
 * System.out.println(driver.getPageSource());
 * 
 * // System.setProperty("webdriver,chrome.driver",
 * "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe"); //
 * //声明chromeoptions,主要是给chrome设置参数 // ChromeOptions options = new
 * ChromeOptions(); // //设置user agent为iphone5 //
 * options.addArguments("--user-agent=Apple Iphone 5"); // //实例化chrome对象，并加入选项
 * // WebDriver driver = new ChromeDriver(options); // //打开百度 //
 * driver.get("https://www.baidu.com"); // try{ // Thread.sleep(5000); //
 * }catch(InterruptedException e) { // e.printStackTrace(); // } //
 * driver.quit();
 * 
 */
