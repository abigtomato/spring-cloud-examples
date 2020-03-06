package com.abigtomato.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义负载均衡规则配置类
 * 不能放在@ComponentScan所扫描的当前包以及子包下
 * 否则自定义配置类会被所有ribbon客户端共享，达不到针对服务的特殊定制目的
 *
 * 默认轮询算法：
 * rest接口第几次请求 % 服务器集群总数量 = 服务器list的下标
 * （每次服务重启后rest接口计数从1开始）
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule() {
        // 使用随机算法
        return new RandomRule();
    }
}
