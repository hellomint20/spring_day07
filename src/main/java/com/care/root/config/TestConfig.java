package com.care.root.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
	@Bean
	public TestClass tc() {
		TestClass test = new TestClass();
		test.setName("���̿���");
		return test;
	}
	//servelt-context.xml ���� �����ϴ� bean ����� �ڹ��ڵ�� ������
	//controller ������ autowired �̿��ؼ� �� ������ ����ϸ� ��
}
