package hello;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant;

public class TestProcessor2 implements PageProcessor, Job {
	// http://rexian.beijing.gov.cn/default/com.web.index.index.moreEveryoneAnswerQuery.biz.ext?PageCond/begin=10&PageCond/length=6&PageCond/isCount=true
	public static final String URL_LIST = "http://rexian\\.beijing\\.gov\\.cn/default/com\\.web\\.index\\.index\\.moreEveryoneAnswerQuery\\.biz\\.ext\\?PageCond/begin=(\\d)+&pageCond/length=6&PageCond/isCount=true";
	public static List<String> failUrl = new ArrayList<String>();
	public static final String URL_POST = "http://rexian\\.beijing\\.gov\\.cn/default/com\\.web\\.consult\\.consultDetail\\.flow\\?originalId=[a-zA-z0-9]+";
	private static Logger logger = Logger.getLogger(TestProcessor2.class);
	private Site site = Site.me().setDomain("http://banshi.beijing.gov.cn").setSleepTime(100).setUserAgent(
			"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

	public void setSite(Site site) {
		this.site = site;
	}

	public static void main(String[] args) {
		Spider spider = Spider.create(new TestProcessor2());
		List< String> list=new ArrayList<String>();
		list.add("10032444");
		list.add("10032064");
		list.add("10031731");
		list.add("10032819");
		list.add("10032801");
		list.add("10032240");
		list.add("10032919");
		list.add("10032098");
		list.add("10031702");
		list.add("10032260");
		list.add("10026216");
		list.add("10031578");
		list.add("10028260");
		list.add("10032766");
		list.add("10032277");
		list.add("10031789");
		list.add("10031800");
		list.add("10031804");
		list.add("10031123");
		list.add("10030864");
		list.add("10030674");
		list.add("10030647");
		list.add("10030769");
		list.add("10027546");
		list.add("10030035");
		list.add("10030189");
		list.add("10029908");
		list.add("10030725");
		list.add("10030753");
		list.add("10027733");
		list.add("10027497");
		list.add("10029022");
		list.add("10030635");
		list.add("10030742");
		list.add("10030611");
		list.add("10030612");
		list.add("10030712");
		list.add("10030359");
		list.add("20030761");
		list.add("10030648");
		list.add("10030757");
		list.add("10030054");
		list.add("10029505");
		list.add("10029511");
		list.add("10029563");
		list.add("10028468");
		list.add("10029283");
		list.add("10030790");
		list.add("10029883");
		list.add("10028376");
		list.add("10028670");
		list.add("10030015");
		list.add("10029967");
		list.add("10030014");
		list.add("10029874");
		list.add("10029817");
		list.add("10028458");
		list.add("20028676");
		list.add("10028702");
		list.add("10025065");
		list.add("10026171");
		list.add("10027694");
		list.add("10025617");
		list.add("10027922");
		list.add("10027156");
		list.add("10027117");
		list.add("10027039");
		list.add("10027598");
		list.add("10027418");
		list.add("10028338");
		list.add("10027359");
		list.add("10026387");
		list.add("10026260");
		list.add("10027048");
		list.add("10027029");
		list.add("10023209");
		list.add("10022846");
		list.add("10025988");
		list.add("10026763");
		list.add("10025880");
		list.add("10025491");
		list.add("10026359");
		list.add("10025298");
		list.add("10025479");
		list.add("10023954");
		list.add("20023517");
		list.add("10024568");
		list.add("10023669");
		list.add("10023275");
		list.add("20022788");
		list.add("10023314");
		list.add("10023915");
		list.add("10022268");
		list.add("10020720");
		list.add("10020501");
		list.add("10023924");
		list.add("10023195");
		list.add("10023185");
		list.add("10020171");
		list.add("10023224");
		list.add("10018421");
		list.add("40017838");
		list.add("10022917");
		list.add("10021933");
		list.add("10022605");
		list.add("10022246");
		list.add("10022398");
		list.add("10022379");
		list.add("10022557");
		list.add("10022543");
		list.add("10021990");
		list.add("10022515");
		list.add("10022867");
		list.add("10021874");
		list.add("10022336");
		list.add("10022126");
		list.add("40021005");
		list.add("10019167");
		list.add("20020862");
		list.add("10020573");
		list.add("10021030");
		list.add("10020712");
		list.add("10021379");
		list.add("10020533");
		list.add("10020529");
		list.add("10020430");
		list.add("10020459");
		list.add("10020463");
		list.add("10020796");
		list.add("10020874");
		list.add("10020285");
		list.add("10019111");
		list.add("10019575");
		list.add("10021049");
		list.add("10021036");
		list.add("10020551");
		list.add("10018990");
		list.add("10019711");
		list.add("10020353");
		list.add("10020580");
		list.add("10019605");
		list.add("10020281");
		list.add("10020174");
		list.add("10020703");
		list.add("10020736");
		list.add("10019771");
		list.add("10018790");
		list.add("10019496");
		list.add("10019096");
		list.add("10017963");
		list.add("10018608");
		list.add("10019281");
		list.add("10019389");
		list.add("10019403");
		list.add("10018616");
		list.add("10017703");
		list.add("10018771");
		list.add("10016421");
		list.add("10017187");
		list.add("10017625");
		list.add("10017101");
		list.add("20018034");
		list.add("10017511");
		list.add("10015233");
		list.add("10016358");
		list.add("10015389");
		list.add("20014594");
		list.add("10015589");
		list.add("10014763");
		list.add("10014558");
		list.add("10015167");
		list.add("10014586");
		list.add("10014943");
		list.add("10014734");
		list.add("40014853");
		list.add("10005358");
		list.add("10014890");
		list.add("10014941");
		list.add("10015165");
		list.add("10015164");
		list.add("10029759");
		list.add("10026308");
		list.add("10029297");
		list.add("10029163");
		list.add("10027595");
		list.add("10027515");
		list.add("10030334");
		list.add("10031613");
		list.add("10031698");
		list.add("10030157");
		list.add("10030142");
		list.add("10030147");
		list.add("10031181");
		list.add("10031269");
		list.add("10030106");
		list.add("10013637");
		list.add("10026050");
		list.add("10030107");
		list.add("10026498");
		list.add("10030719");
		list.add("10026859");
		list.add("10030390");
		list.add("10030044");
		list.add("10032859");
		list.add("10027810");
		list.add("10031280");
		list.add("10025250");
		list.add("10032378");
		list.add("10025215");
		list.add("10022430");
		list.add("10033587");
		list.add("10033241");
		list.add("10033302");
		list.add("10033735");
		list.add("10031083");
		list.add("10025122");
		list.add("10032618");
		list.add("10031744");
		list.add("10032534");
		list.add("20032167");
		list.add("10031371");
		list.add("10031444");
		list.add("10031992");
		list.add("10031661");
		list.add("10031472");
		list.add("10030869");
		list.add("10028548");
		list.add("10030902");
		list.add("10032297");
		list.add("10029368");
		list.add("10030535");
		list.add("10031339");
		list.add("10030654");
		list.add("10030763");
		list.add("10030770");
		list.add("10024190");
		list.add("10027873");
		list.add("10031384");
		list.add("10030247");
		list.add("10030826");
		list.add("10028006");
		list.add("10030824");
		list.add("10030807");
		list.add("10031520");
		list.add("10031587");
		list.add("10031100");
		list.add("10030517");
		list.add("10030960");
		list.add("10030478");
		list.add("10030167");
		list.add("10030396");
		list.add("10030322");
		list.add("10031137");
		list.add("10030133");
		list.add("10030731");
		list.add("10030839");
		list.add("10030840");
		list.add("10030846");
		list.add("10030275");
		list.add("10030113");
		list.add("10028609");
		list.add("10030462");
		list.add("10030474");
		list.add("10029732");
		list.add("10029954");
		list.add("10029048");
		list.add("10025766");
		list.add("10029807");
		list.add("10030108");
		list.add("10028628");
		list.add("10028142");
		list.add("40030242");
		list.add("10029485");
		list.add("10029509");
		list.add("10029549");
		list.add("10030011");
		list.add("10029512");
		list.add("10029412");
		list.add("10029226");
		list.add("10029293");
		list.add("10028669");
		list.add("10027498");
		list.add("10027867");
		list.add("10028750");
		list.add("10026793");
		list.add("10028320");
		list.add("10027704");
		list.add("10027761");
		list.add("10027881");
		list.add("10027883");
		list.add("10022901");
		list.add("10027196");
		list.add("10027738");
		list.add("10027579");
		list.add("10028265");
		list.add("10023750");
		list.add("10028014");
		list.add("10028152");
		list.add("10026401");
		list.add("10027162");
		list.add("10027467");
		list.add("10026490");
		list.add("10027274");
		list.add("10026887");
		list.add("10026655");
		list.add("10026624");
		list.add("10026587");
		list.add("10025801");
		list.add("10026423");
		list.add("10026266");
		list.add("10026917");
		list.add("10026176");
		list.add("10026072");
		list.add("10025739");
		list.add("10025976");
		list.add("20026589");
		list.add("10025434");
		list.add("10025687");
		list.add("10024712");
		list.add("10024517");
		list.add("10025742");
		list.add("10025956");
		list.add("10024593");
		list.add("10024594");
		list.add("10024303");
		list.add("10024570");
		list.add("40024852");
		list.add("10025181");
		list.add("10024083");
		list.add("10024330");
		list.add("10025317");
		list.add("10025527");
		list.add("10024755");
		list.add("40022874");
		list.add("10023200");
		list.add("10024651");
		list.add("10024095");
		list.add("10024215");
		list.add("10024352");
		list.add("10024354");
		list.add("10024168");
		list.add("10024373");
		list.add("10023243");
		list.add("10023155");
		list.add("10024048");
		list.add("10022923");
		list.add("10025340");
		list.add("10023007");
		list.add("40023856");
		list.add("10022984");
		list.add("10024097");
		list.add("10024017");
		list.add("10023760");
		list.add("10024151");
		list.add("40017231");
		list.add("10023183");
		list.add("10023708");
		list.add("10023697");
		list.add("10023518");
		list.add("10022897");
		list.add("10022464");
		list.add("10022841");
		list.add("10022905");
		list.add("10022267");
		list.add("10020422");
		list.add("20021166");
		list.add("10022014");
		list.add("10021044");
		list.add("10021274");
		list.add("10022179");
		list.add("10021542");
		list.add("10021496");
		list.add("10022222");
		list.add("10022202");
		list.add("10021767");
		list.add("10020508");
		list.add("10021326");
		list.add("10021105");
		list.add("10021298");
		list.add("20021273");
		list.add("10021191");
		list.add("10020878");
		list.add("10021800");
		list.add("10020925");
		list.add("20021121");
		list.add("10021135");
		list.add("10021033");
		list.add("10021038");
		list.add("10020888");
		list.add("10020437");
		list.add("10020435");
		list.add("10021584");
		list.add("10021161");
		list.add("10021740");
		list.add("10020952");
		list.add("10020678");
		list.add("10020981");
		list.add("40021177");
		list.add("10020562");
		list.add("10020675");
		list.add("10020972");
		list.add("10020684");
		list.add("10020034");
		list.add("10020035");
		list.add("10019825");
		list.add("20020126");
		list.add("10020166");
		list.add("10019570");
		list.add("10019723");
		list.add("10006842");
		list.add("10020627");
		list.add("10020188");
		list.add("10020287");
		list.add("10019567");
		list.add("10017878");
		list.add("10020025");
		list.add("10020122");
		list.add("10019440");
		list.add("10017063");
		list.add("10019608");
		list.add("10019541");
		list.add("10019644");
		list.add("20019085");
		list.add("10020036");
		list.add("10019190");
		list.add("10019046");
		list.add("10018828");
		list.add("10019686");
		list.add("10016674");
		list.add("10015477");
		list.add("10017136");
		list.add("10017056");
		list.add("10017245");
		list.add("40017023");
		list.add("10017117");
		list.add("10017167");
		list.add("10016193");
		list.add("10016761");
		list.add("10016683");
		list.add("10016439");
		list.add("10016159");
		list.add("10017433");
		list.add("10016438");
		list.add("10017164");
		list.add("10017115");
		list.add("10015519");
		list.add("10015820");
		list.add("10015255");
		list.add("30016703");
		list.add("40015696");
		list.add("10015548");
		list.add("10016412");
		list.add("10026715");
		list.add("10026672");
		list.add("10027574");
		list.add("10026190");
		list.add("10013685");
		list.add("10026261");
		list.add("10025678");
		list.add("10028235");
		list.add("10027638");
		list.add("10029591");
		list.add("10025877");
		list.add("10028114");
		list.add("10025271");
		list.add("10027589");
		list.add("10027692");
		list.add("10027646");
		list.add("10027451");
		list.add("10025962");
		list.add("10027313");
		list.add("10028390");
		list.add("10027407");
		list.add("10027416");
		list.add("10010801");
		list.add("10024538");
		list.add("10025288");
		list.add("10028263");
		list.add("10028140");
		list.add("10024758");
		list.add("10022927");
		list.add("10022770");
		list.add("10024127");
		list.add("10023047");
		list.add("10021473");
		list.add("10009496");
		list.add("10019264");
		list.add("10022050");
		list.add("10020857");
		list.add("10007576");
		list.add("10020015");
		list.add("10018884");
		list.add("10033206");
		list.add("10033910");
		list.add("10033319");
		list.add("10006720");
		list.add("10005683");
		list.add("10018497");
		list.add("10033296");
		list.add("10033920");
		list.add("20031746");
		list.add("10033933");
		list.add("20004594");
		list.add("10004698");
		list.add("10016617");
		list.add("10016657");
		list.add("10030325");
		list.add("10026965");
		list.add("10029347");
		list.add("10030316");
		list.add("10027151");
		list.add("10029358");
		list.add("20031008");
		list.add("10033965");
		list.add("10034034");
		list.add("10033251");
		list.add("10032675");
		list.add("10031128");
		list.add("10032130");
		list.add("10033099");
		list.add("10031987");
		list.add("10022938");
		Spider addPipeline = spider.addPipeline(new AskAndAnswerPipleline2());
//		 for (int m = 0; m <= 100; m++) {
		Spider addUrl = null ;
		for (String string : list) {
			addUrl= addPipeline.addUrl("http://rexian.beijing.gov.cn/default/com.web.consult.consultDetail.flow?originalId="+string);
		}
		addUrl.run();

	}

	@Override
	public Site getSite() {

		return site;
	}

	@Override
	public void process(Page page) {
		if (page.getUrl().regex(URL_LIST).match()) {

			JSONObject json = (JSONObject) JSONObject.parse(page.getRawText());
			JSONArray jsonArray = json.getJSONArray("everyoneAnswer");
			for (Object object2 : jsonArray) {
				JSONObject andAnswer = (JSONObject) object2;
				// System.out.println(andAnswer.get("letterTitle"));
				// System.out.println(andAnswer.get("originalId"));
//				page.addTargetRequest(
//						"http://rexian.beijing.gov.cn/default/com.web.consult.consultDetail.flow?originalId="
//								+ andAnswer.get("originalId"));
				page.addTargetRequest("http://rexian.beijing.gov.cn/default/com.web.consult.consultDetail.flow?originalId=10032444");
				
			}

		} else {
			String url = page.getResultItems().getRequest().getUrl();
//			System.out.println(url);
			page.putField("title", page.getHtml().xpath("//h2/text()").get());
//			page.putField("title", page.getHtml().xpath("//h2/text()").get());
			List<String> all = page.getHtml().xpath("//p[contains(@class, 'offic_p font14')]/text()").all();
			StringBuilder sb=new StringBuilder();
			for (String string : all) {
				sb.append(string+"\r\n");
			}
			sb.setLength(sb.length()-2);
			page.putField("answer", sb.toString());
			page.putField("url", url);
			// System.out.println("==================");
			// System.out.println(page.getResultItems());
			logger.info(page.getResultItems());
		}
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		Spider spider = Spider.create(new TestProcessor2());

		// for (int m = 0; m <= 0; m++) {
		for (int m = 0; m <= 0; m++) {
			// System.out.println(TotalPageUtilProcessor.getTotalPage());
			String begin = m == 0 ? "0" : m + "0";
			Request request = new Request(
					"http://rexian.beijing.gov.cn/default/com.web.index.index.moreEveryoneAnswerQuery.biz.ext?PageCond/begin="
							+ begin + "&PageCond/length=6&PageCond/isCount=true");
			HttpRequestBody requestBody = new HttpRequestBody();
			requestBody
					.setBody(("{\"PageCond/begin\":" + begin + ",\"PageCond/length\":6,\"PageCond/isCount\":\"true\"}")
							.getBytes());
			request.setRequestBody(requestBody);
			request.setMethod(HttpConstant.Method.POST);
			spider.addRequest(request);
		}
		spider.addPipeline(new AskAndAnswerPipleline()).run();

	}
}