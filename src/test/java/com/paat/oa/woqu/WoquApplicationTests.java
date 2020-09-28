package com.paat.oa.woqu;


import cn.binarywang.tools.generator.ChineseMobileNumberGenerator;
import cn.binarywang.tools.generator.ChineseNameGenerator;
import cn.binarywang.tools.generator.EmailAddressGenerator;
import cn.hutool.core.map.MapUtil;
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
		oaService.pullOA(1);
	}

	//10个线程 执行100次
	@Test
	@PerfTest(invocations = 1000,threads = 10)
	public void checkJyb() {
		oaService.checkJyb();
	}

	@Test
	public void msg2() {

		oaService.checkCaiWu();
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

}
