package com.foxmo.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Ribbon负载均衡规则替换
 * 注意这个自定意类不能放在@ComponnentScan所扫描的当前包下以及子包下
 * 否则自定义的这个配置类就会被所有的Ribbon客户端所共享，达不到特殊化定制的目的
 */
@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule(){
        return new RandomRule();    //定义随机规则
    }
}
