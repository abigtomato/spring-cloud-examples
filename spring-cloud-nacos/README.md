# nacos切换cp模式
* `curl -X PUT '$NACOS_SERVER:8848/nacos/v1/ns/operator/switches?entry=serverMode&value=CP'`
# nacos集群搭建
* 将nacos自带的derby切换为mysql存储
    1. 执行`nacos/conf`路径下的sql脚本`nacos-mysql.sql`
    2. 修改`nacos/conf`路径下的配置文件`application.properties`
```properties
spring.datasource.platform=mysql
db.num=1
db.url.0=jdbc:mysql://192.168.121.100:3306/nacos_config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true
db.user=root
dn.password=123456
```
* 修改nacos配置文件（nacos/conf/cluster.conf）
```shell script
192.168.121.100:8848
192.168.121.101:8848
192.168.121.102:8848
```
* 启动所有节点上的nacos
```shell script
./startup.sh
ps -ef | grep nacos
```
* 修改nginx配置文件（nginx/conf/nginx.conf）
```shell script
upstream cluster {
  server 192.168.121.100:8848;
  server 192.168.121.101:8848;
  server 192.168.121.102:8848;
}

server {
  listen      1111;
  server_name localhost;
  
  location / {
    proxy_pass http://cluster;
  }
}
```
* 启动nginx
```shell script
./nginx -c ../conf/nginx.conf
ps -ef | grep nginx
```