package com.paat.oa.woqu;


import cn.binarywang.tools.generator.ChineseMobileNumberGenerator;
import cn.binarywang.tools.generator.ChineseNameGenerator;
import cn.binarywang.tools.generator.EmailAddressGenerator;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.paat.oa.woqu.service.OAService;
import com.paat.oa.woqu.service.YgyService;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WoquApplication.class)
public class WoquApplicationTests {

	//引入 ContiPerf 进行性能测试
	@Rule
	public ContiPerfRule rule = new ContiPerfRule();

	@Resource
	OAService oaService;

	@Resource
	RedisTemplate redisTemplate;

	@Resource
	YgyService ygyService;

	@Test
	public void contextLoads() {
		oaService.pullOA(2);
	}

	//10个线程 执行100次
	@Test
	@PerfTest(invocations = 1000,threads = 10)
	public void checkJyb() {
		oaService.checkJyb();
	}

	@Test
	public void msg2() {

		String result = HttpRequest.get("https://i.weread.qq.com/video/catalogue?bookId=MP_WXS_3549978476&synckey=1624614068&count=20")
				.header(Header.USER_AGENT, "WeRead/5.4.4 WRBrand/other Dalvik/2.1.0 (Linux; U; Android 7.1.2; SM-G977N Build/LMY48Z)")//头信息，多个头信息多次调用此方法即可
//                .cookie(cookie,cookie2)
                .header("accessToken","MkZeTJon")
				.header("vid","6000744")
				.header("basever","5.4.4.10150180")
				.header("accessToken","kY0ULQU7")
				.header("Host","i.weread.qq.com")
				.timeout(20000)//超时，毫秒
				.execute().body();
		System.out.println(result);

		for (Object obj : JSONUtil.parseArray(JSONUtil.parseObj(result).getStr("reviews"))){
			JSONObject ob = (JSONObject) obj;
			String id = ob.getStr("reviewId");
			JSONObject review =JSONUtil.parseObj(ob.getStr("review"));
			JSONObject mpInfo =JSONUtil.parseObj(review.getStr("mpInfo"));
			String doc_url = mpInfo.getStr("doc_url");
			String pic_url = mpInfo.getStr("pic_url");
			String title = mpInfo.getStr("title");

			System.out.println(String.format("%s,%s,%s,%s,",id,title,doc_url,pic_url));
		}




//		oaService.checkCaiWu();
//		oaService.sendMsg(2);

//		BoundHashOperations boundHashOperations = redisTemplate.boundHashOps("oa:liuyonggang");
//		boundHashOperations.put("xILqjpmyvXwb/Z7uh+14EW80QhWfznJ0","nBVxa3mFdyizVI2mrgmQf0PaKacsQMeXVbmbPpWcmzs3epeg/6ImBbl4UqzWzSkqdiDKAKUsmG12Fz1xCD/Ke9ysapjkXq0B5uoFEVfU5sxBnDv63DgeqEYAkmDdnmg18QDes5uzummBvXINb3Eba280QhWfznJ0");

//		System.out.println(boundHashOperations.getKey());
//		System.out.println(boundHashOperations.get(boundHashOperations.getKey()));
//		System.out.println(boundHashOperations.);

	}

	@Test
	@PerfTest(invocations = 1000,threads = 10)
	public void check(){
		String appId ="b3a940dd294b46a1b433642d18299634";
		String appSecret ="7bd9f5150059476493141753260bb579754";

		//新增创客
		Map map = MapUtil.newHashMap();
		map.put("mobile", ChineseMobileNumberGenerator.getInstance().generate());
		map.put("realName", ChineseNameGenerator.getInstance().generate());
		map.put("email", EmailAddressGenerator.getInstance().generate());
		map.put("companyId",1305781645000966144L);

		String signature = ygyService.changeMap(appId,appSecret,map);
		System.out.println(signature);

		//新增创客
		ygyService.saveEmployed(signature,map);



	}

	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());

//		String [] students = FileUtil.readUtf8String("/opt/student.txt").split(",");
		List<String> students = FileUtil.readUtf8Lines("/opt/student.txt");
		System.out.println(JSONUtil.toJsonStr(students));

	}

}
