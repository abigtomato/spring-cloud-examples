package com.abigtomato.example.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 编码形式配置gateway的路由网关
 */
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("baidu_news_gouji_route",
                r -> r.path("/gounei").uri("http://news.baidu.com/guonei")).build();
        routes.route("baidu_news_guonei_route",
                r -> r.path("/gouji").uri("http://news.baidu.com/guoji")).build();
        return routes.build();
    }
}
