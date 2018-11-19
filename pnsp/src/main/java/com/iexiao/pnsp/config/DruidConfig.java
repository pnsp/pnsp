package com.iexiao.pnsp.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@PropertySource("classpath:/application.properties")
public class DruidConfig {
	
	//Environment四种获取属性的形式
	//env.getProperty("key")
	//env.getProperty("key","key不存在时的默认值")
	//env.getProperty("key",Class<T>) - 指定返回的类型 - 如：Integer.class
	//env.getProperty("key",Class<T>,"不存在时的默认值")
	@Autowired
	private Environment env; 
	
	//将外部配置文件与返回对象绑定
	@ConfigurationProperties(prefix="spring.druid")
	//初始化时指向init方法启动连接池,销毁时指向close方法关闭连接池
	@Bean(initMethod="init",destroyMethod="close")
	public DruidDataSource dataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		//连接池添加慢日志
		//dataSource.setProxyFilters(Lists.newArrayList(statFilter()));
		return dataSource;
	}
	
	/**
	 * 打印慢sql日志
	 * @author lizhiyong
	 * @date 2018年4月25日
	 * @return
	 */
	/*@Bean
	public Filter statFilter() {
		StatFilter filter = new StatFilter();
		//慢sql日志最少时间
		filter.setSlowSqlMillis(1000);
		//是否打印日志
		filter.setLogSlowSql(true);
		//是否合并日志
		filter.setMergeSql(true);
		return filter;
	}*/
	
	/**
	 * 包装
	 * @author lizhiyong
	 * @date 2018年4月25日
	 * @return
	 */
	/*@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		return new ServletRegistrationBean(new StatViewServlet(),"/druid/*"); //指定拦截的url
	}*/
	
}
