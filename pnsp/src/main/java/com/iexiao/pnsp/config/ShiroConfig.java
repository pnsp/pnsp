package com.iexiao.pnsp.config;

import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.google.common.collect.Maps;
import com.iexiao.pnsp.filter.ResponseHeaderFilter;
import com.iexiao.pnsp.filter.ShiroAuthLoginFilter;
import com.iexiao.pnsp.filter.ShiroFuncFilter;
import com.iexiao.pnsp.utils.shiro.CustomRealm;

@Configuration
public class ShiroConfig {
	
	@Bean
	@Order(Integer.MAX_VALUE - 2)
	public FilterRegistrationBean headerFilter(ResponseHeaderFilter responseHeaderFilter) {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(responseHeaderFilter);
		registration.addUrlPatterns("/*");
		return registration;
	}
	
	@Bean
	@Order(Integer.MAX_VALUE - 1)
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager defaultSecurityManager,
    		ShiroAuthLoginFilter shiroAuthLoginFilter,
    		ShiroFuncFilter shiroFuncFilter) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(defaultSecurityManager);
        //自定义filter，key是自定义filter命名，value是拦截器如new ShiroAuthLoginFilter()
        Map<String,Filter> filters = Maps.newLinkedHashMap();
        filters.put("authLogin", shiroAuthLoginFilter);
        filters.put("func", shiroFuncFilter);
        shiroFilterFactoryBean.setFilters(filters);
		//按顺序执行拦截，key是路径，value是自定义filter命名
        Map<String, String> map = Maps.newLinkedHashMap();
        map.put("/static/**", "anon");
        map.put("/**/*.do", "authLogin");
        //map.put("/**/*.do", "func");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }
	
	@Bean
    public DefaultWebSecurityManager securityManager(CustomRealm customRealm,
    										DefaultWebSessionManager defaultWebSessionManager) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //安全验证管理器
        defaultWebSecurityManager.setRealm(customRealm);
        //session管理器
        defaultWebSecurityManager.setSessionManager(defaultWebSessionManager);
        //defaultWebSecurityManager.setCacheManager(ehCacheManager);
        return defaultWebSecurityManager;
    }
	
	@Bean
	public CustomRealm customRealm() {
		CustomRealm customRealm = new CustomRealm();
		customRealm.setAuthenticationTokenClass(UsernamePasswordToken.class);
		return customRealm;
	}
	
	@Bean
	public DefaultWebSessionManager sessionManager(SimpleCookie simpleCookie) {
		DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
		//删除失效的session
		defaultWebSessionManager.setDeleteInvalidSessions(true);
        //避免本系统sessionid与tomcat冲突,默认为JSESSIONID
		defaultWebSessionManager.setSessionIdCookie(simpleCookie);
        return defaultWebSessionManager;
	}
	
	@Bean
	public SimpleCookie simpleCookie() {
		SimpleCookie simpleCookie = new SimpleCookie();
		simpleCookie.setName("PNSP.SHIROID");
		return simpleCookie;
	}
	
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
	
}
