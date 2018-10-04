package com.iexiao.pnsp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ShiroConfig.class,DruidConfig.class})
public class SystemConfig {

}
