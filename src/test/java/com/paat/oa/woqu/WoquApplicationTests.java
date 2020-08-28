package com.paat.oa.woqu;


import com.paat.oa.woqu.service.OAService;
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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WoquApplication.class)
public class WoquApplicationTests {

	//引入 ContiPerf 进行性能测试
//	@Rule
	public ContiPerfRule rule = new ContiPerfRule();

	@Resource
	OAService oaService;

	@Resource
	RedisTemplate redisTemplate;

	@Test
	public void contextLoads() {
		oaService.pullOA(1);
	}

	//10个线程 执行100次
	@Test
//	@PerfTest(invocations = 1000,threads = 10)
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

}
