package com.care.root.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
	@Bean
	public TestClass tc() {
		TestClass test = new TestClass();
		test.setName("에이에이");
		return test;
	}
	//servelt-context.xml 에서 설정하던 bean 등록을 자바코드로 생성함
	//controller 에서는 autowired 이용해서 값 꺼내서 사용하면 됨
}
