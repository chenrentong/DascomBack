package com.dascom.product.test;



import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSSH {
	private ApplicationContext ctx=null;
	@Test
	public void test() throws SQLException {
		ctx=new ClassPathXmlApplicationContext("applicationContext-public.xml");
		System.out.println("数据源"+ctx);
		DataSource source=ctx.getBean(DataSource.class);
		System.out.println("数据库"+source.getConnection().toString());
	}

}
