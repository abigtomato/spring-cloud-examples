# 动态刷新配置
* 配置更新后，通过发送post请求来动态刷新配置：
    * 广播通知：`curl -X POST "http://localhost:3344/actuator/bus-refresh"`
    * 定点通知：`curl -X POST "http://localhost:3344/actuator/bus-refresh/cloud-config-client:3355"`
* 通过rabbitmq+bus消息总线来全局推送配置的更新，cloud-config-server就做为消息的代理，配置变更后由该服务推送消息给所有订阅者
* 每一个cloud-config-client实例都订阅mq中的同一个topic（默认springCloudBus），当一个服务刷新数据时，信息会存入topic，这样所有监听topic的服务都能得到通知，更新自己的配置