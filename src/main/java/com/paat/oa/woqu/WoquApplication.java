package com.paat.oa.woqu;

import com.paat.oa.woqu.service.OAService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class WoquApplication {

	@Resource
	OAService oAService;

	public static void main(String[] args) {
		SpringApplication.run(WoquApplication.class, args);

//		oAService.pullOA();
	}

}
