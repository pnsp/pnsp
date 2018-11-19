package com.iexiao.pnsp.interceptor;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iexiao.pnsp.exception.MybatisException;
import com.iexiao.pnsp.utils.shiro.ShiroUtil;

/**
 * mybatis结果数量统计拦截器(在mybatis的xml文件中开启)
 * @author lizhiyong
 * @date 2018年10月17日
 */
//四大对象Executor(执行处理), StatementHandler(会话处理),ParameterHandler(参数处理)，ResultHandler(结论处理)
//StatementHandler - prepare(预编译SQL-用于修改sql),parameterize(设置参数),query(查询),update(更新),batch(批处理)
@Intercepts({@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = { Statement.class })})
public class ResultCountLimitInterceptor implements Interceptor {
	
	public static final int DEFAULT_LIMIT_COUNT = 1000; 
	
	public static final String RESULT_COUNT_MORE = "result count more than limit value";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShiroUtil.class);
	
	private int limitCount = DEFAULT_LIMIT_COUNT;
 
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Statement statement = (Statement) invocation.getArgs()[0];
		ResultSet resultSet = statement.getResultSet();
		int rowCount = 0;
		while(resultSet.next()) {
			rowCount++;
			if(rowCount > limitCount) {
				throw new MybatisException(MybatisException.Type.RESULT_COUNT_MORE, RESULT_COUNT_MORE);
			}
		}
		resultSet.beforeFirst();
		return invocation.proceed();
	}
	 
	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}
	 
	@Override
	public void setProperties(Properties properties) {
	   if(!properties.containsKey("limitCount")) {
		   return;
	   }
	   String limitCountStr = properties.getProperty("limitCount");
	   if(!StringUtils.isNumeric(limitCountStr)) {
		   return;
	   }
	   this.limitCount = Integer.valueOf(limitCountStr);
	}
  
}
