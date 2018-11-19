package com.iexiao.pnsp.config;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import com.iexiao.pnsp.constants.Constant;
import com.iexiao.pnsp.base.query.PnspTaskCronTQuery;
import com.iexiao.pnsp.base.service.PnspTaskCronTService;
import com.iexiao.pnsp.base.serviceImpl.TaskItem;
import com.iexiao.pnsp.utils.LocalCache;
import com.iexiao.pnsp.utils.SpecialUtil;

/**
 * 配置定时任务触发器
 * @author lizhiyong
 * @date 2018年10月18日
 */
@Configuration
@EnableScheduling
public class SpringTaskConfig implements SchedulingConfigurer{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringTaskConfig.class);
	
	public static final String DEFAULT_CRON = "0 0 8 * * *";
	
	private static String cron = Constant.NULL_STR;
	
	private LocalCache<String> taskCronCache = new LocalCache<>();
	
	@Value("${run.task.ip.address}")
	private String runTaskIpAddress;
	
	@Value("${task.scheduled.thread.pool.max.size}")
	private String taskScheduledThreadPoolMaxSize;
	
	@Autowired
	private TaskItem taskItem;
	
	@Autowired
	private PnspTaskCronTService pnspTaskCronTService;
	
	/**
	 * 定时任务触发器
	 * @author lizhiyong
	 * @date 2018年10月18日
	 * @param taskRegistrar
	 */
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		LOGGER.info("[SpringTask started]");
		//设置线程池
		taskRegistrar.setScheduler(this.taskScheduler());
		//校验运行定时任务的服务器地址
		String hostAddress = getHostAddress();
		LOGGER.info("runTaskIpAddress：" + this.runTaskIpAddress);
		LOGGER.info("hostAddress：" + hostAddress);
		if(!this.judgeRunTask(hostAddress)) {
			return;
		}
		//反射
		final Class<?> cl = this.taskItem.getClass();
		//获取所有定时任务业务接口
		Method[] taskMethods = cl.getDeclaredMethods();
		LOGGER.info("task methods size：" + taskMethods.length);
		if(taskMethods.length <= 0) {
			return;
		}
		//从缓存中获取task对象的key的集合，key即为定时任务业务接口名
		List<String> taskKeys = null;
		if(null != this.taskCronCache) {
			taskKeys = this.taskCronCache.getKeys();
		}
		for(final Method method : taskMethods) {
			//缓存中存在的才会定义触发器
			if(CollectionUtils.isNotEmpty(taskKeys) && taskKeys.contains(method.getName())) {
				LOGGER.info("task method name：" + method.getName());
				taskRegistrar.addTriggerTask(
					//定义触发器线程
					new Runnable() {

						@Override
						public void run() {
							try {
								//调用方法执行定时任务业务(if防止缓存过期读取为空)
								if(taskCronCache.containsKey(method.getName())) {
									cl.getMethod(method.getName(), new Class[]{}).invoke(taskItem);
								}
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							} catch (IllegalArgumentException e) {
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								e.printStackTrace();
							} catch (NoSuchMethodException e) {
								e.printStackTrace();
							} catch (SecurityException e) {
								e.printStackTrace();
							}
						}
						
					}, 
					//定义触发器执行周期
					new Trigger() {

						@Override
						public Date nextExecutionTime(TriggerContext triggerContext) {
							//获取task对象的cron(if防止缓存过期读取为空，为空则重新读取)
							if(taskCronCache.containsKey(method.getName())) {
								cron = taskCronCache.get(method.getName());
							}else {
								initTaskCache();
								cron = taskCronCache.get(method.getName());
							}
							LOGGER.info("task cron：" + cron);
							String regex = Constant.REG_SPRING_TASK_CRON;
							if(StringUtils.isBlank(cron) || !cron.matches(regex)) {
								cron = DEFAULT_CRON;
								LOGGER.info("new task cron：" + cron);
							}
							
							CronTrigger cronTrigger = new CronTrigger(cron);
							Date nextExec = cronTrigger.nextExecutionTime(triggerContext);
							return nextExec;
						}
						
					}
				);
			}
		}
	}
	
	/**
	 * 配置线程池,destroyMethod使spring上下文关闭时定时任务调度正确关闭，防止内存泄漏
	 * @author lizhiyong
	 * @date 2018年10月18日
	 * @return
	 */
	@Bean(destroyMethod="shutdown",name="taskScheduler")
	public Executor taskScheduler() {
		return Executors.newScheduledThreadPool(
				SpecialUtil.strToInteger(this.taskScheduledThreadPoolMaxSize));
	}
	
	/**
	 * 初始化SpringTask缓存容器
	 * @author lizhiyong
	 * @date 2018年10月18日
	 */
	@PostConstruct
	public void initTaskCache() {
		List<PnspTaskCronTQuery> allTaskList = this.pnspTaskCronTService.getAllTaskList();
		for(PnspTaskCronTQuery pnspTaskCronTQuery : allTaskList) {
			//将定时任务添加进缓存，设置缓存清除时间为24小时
			taskCronCache.add(pnspTaskCronTQuery.getTaskName(), 
					pnspTaskCronTQuery.getCron(), Constant.CACHE_SPRING_TASK_CYCLE);
		}
	}
	
	/**
	 * 获取主机地址
	 * @author lizhiyong
	 * @date 2018年10月18日
	 * @return
	 */
	private String getHostAddress() {
		//声明主机对象
		InetAddress inetAddress;
		String hostAddress = Constant.NULL_STR;
		try {
			//实例化，返回主机对象
			inetAddress = InetAddress.getLocalHost();
			//获取主机IP地址
			hostAddress = inetAddress.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return hostAddress;
	}
	
	/**
	 * 校验运行定时任务的主机地址
	 * @author lizhiyong
	 * @date 2018年10月18日
	 * @param ip
	 * @return
	 */
	private boolean judgeRunTask(String ip) {
		if(StringUtils.isNotBlank(ip) && ip.equals(this.runTaskIpAddress)) {
			return true;
		}
		return false;
	}

}
