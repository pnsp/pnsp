package com.iexiao.pnsp.config;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.iexiao.pnsp.utils.SpecialUtil;

@Configuration
@Import({ShiroConfig.class,DruidConfig.class,FileUploadConfig.class})
@EnableAspectJAutoProxy //启用AspectJ自动代理
@EnableAsync
public class SystemConfig {
	
	@Value("${log.core.pool.size}")
	private String logCorePoolSize;
	
	@Value("${log.max.pool.size}")
	private String logMaxPoolSize;
	
	@Value("${log.queue.capacity}")
	private String logQueueCapacity;
	
	@Value("${log.keep.alive.seconds}")
	private String logKeepAliveSeconds;
	
	/*@Value("${log.thread.name}")
	private String logThreadName;*/
	
	/**
	 * 配置Spring提供的线程池
	 * @author lizhiyong
	 * @date 2018年10月18日
	 * @return
	 */
	@Bean(destroyMethod="shutdown",name="logExecutor")
	public ThreadPoolTaskExecutor logExecutor() {
    	//newFixedThreadPool
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数,线程池维护线程的最少数量
        executor.setCorePoolSize(SpecialUtil.strToInteger(logCorePoolSize));
        // 设置最大线程数
        executor.setMaxPoolSize(SpecialUtil.strToInteger(logMaxPoolSize));
        // 设置队列容量
        executor.setQueueCapacity(SpecialUtil.strToInteger(logQueueCapacity));
        // 设置线程活跃时间（秒）,允许的空闲时间
        executor.setKeepAliveSeconds(SpecialUtil.strToInteger(logKeepAliveSeconds));
        // 设置默认线程名称
        //executor.setThreadNamePrefix(logThreadName);
        // 设置拒绝策略
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务  
        // CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行 
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.initialize();
        return executor;
    }

}
