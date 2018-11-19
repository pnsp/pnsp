package com.iexiao.pnsp.interceptor;

import java.sql.Statement;
import java.util.Properties;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iexiao.pnsp.utils.shiro.ShiroUtil;

/**
 * mybatis-Sql执行时间记录拦截器(在mybatis的xml文件中开启)
 * @author lizhiyong
 * @date 2018年10月17日
 */
//四大对象Executor(执行处理), StatementHandler(会话处理),ParameterHandler(参数处理)，ResultHandler(结论处理)
//StatementHandler - prepare(预编译SQL-用于修改sql),parameterize(设置参数),query(查询),update(更新),batch(批处理)
@Intercepts({@Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
			 @Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),
			 @Signature(type = StatementHandler.class, method = "batch", args = { Statement.class })
			})
public class SqlCostInterceptor implements Interceptor {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShiroUtil.class);
 
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		long startTime = System.currentTimeMillis();
		try {
			return invocation.proceed();
		} finally {
			long endTime = System.currentTimeMillis();
			long sqlCost = endTime - startTime;
			LOGGER.info("数据库执行总耗时：[" + sqlCost +"ms]");
			if(sqlCost > 1000) {
				LOGGER.info("[数据库执行总耗时太长，请优化！]");
			}
		}
	}
	 
	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}
	 
	@Override
	public void setProperties(Properties properties) {
	   
	}
  
}
