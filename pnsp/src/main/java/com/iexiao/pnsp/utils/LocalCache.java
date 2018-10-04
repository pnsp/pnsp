package com.iexiao.pnsp.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentHashMap实现的本地缓存类
 * @author lizhiyong
 * @date 2018年3月29日
 * @Param <T>
 */
public class LocalCache<T> {
	//换算毫秒
	private static final long SECOND_TIME = 1000;
	//默认过期时间
	private final int DEFUALT_VALIDITY_TIME = 20;
	private static final Timer timer;
	private static final ConcurrentHashMap<String, Object> map;
	
	static {
		timer = new Timer();
		map = new ConcurrentHashMap<String, Object>();
	}
	
	/**
	 * 添加缓存（默认过期时间为20秒）
	 * @author lizhiyong
	 * @date 2018年3月29日
	 * @param key
	 * @param value
	 */
	public void add(String key,T value) {
		add(key,value,DEFUALT_VALIDITY_TIME);
	}
	
	/**
	 * 添加缓存（自定义过期时间）
	 * @author lizhiyong
	 * @date 2018年3月29日
	 * @param key
	 * @param value
	 * @param validityTime
	 */
	public void add(String key,T value,int validityTime) {
		map.put(key, value);
		//添加过期定时
		timer.schedule(new TimeoutTimerTask(key), validityTime * SECOND_TIME);
	}
	
	/**
	 * 获取缓存集中所有key值
	 * @author lizhiyong
	 * @date 2018年3月29日
	 * @return
	 */
	public synchronized List<String> getKeys(){
		List<String> keys = new ArrayList<String>();
		for(Map.Entry<String, Object> e : map.entrySet()) {
			keys.add(e.getKey());
		}
		return keys;
	}
	
	/**
	 * 根据key获取缓存
	 * @author lizhiyong
	 * @date 2018年3月29日
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T get(String key) {
		return (T) map.get(key);
	}
	
	/**
	 * 判断是否包含某key值的缓存
	 * @author lizhiyong
	 * @date 2018年3月29日
	 * @param key
	 * @return
	 */
	public boolean contains(String key) {
		return map.containsKey(key);
	}
	
	/**
	 * 移除缓存
	 * @author lizhiyong
	 * @date 2018年3月29日
	 * @param key
	 */
	public static void remove(String key) {
		map.remove(key);
	}
	
	/**
	 * 获取缓存集中的缓存数量
	 * @author lizhiyong
	 * @date 2018年3月29日
	 * @return
	 */
	public int size() {
		return map.size();
	}
	
	/**
	 * 清空缓存集合
	 * @author lizhiyong
	 * @date 2018年3月29日
	 */
	public void clear() {
		if(null != timer) {
			timer.cancel();
		}
		map.clear();
	}
	
	/**
	 * 清除超时缓存定时服务类
	 * @author lizhiyong
	 * @date 2018年3月29日
	 */
	class TimeoutTimerTask extends TimerTask {
		private String valueKey;
		public TimeoutTimerTask(String key) {
			this.valueKey = key;
		}
		@Override
		public void run() {
			LocalCache.remove(valueKey);
		}
	}
}
