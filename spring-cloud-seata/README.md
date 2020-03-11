# seata术语
* Transaction ID XID：全局唯一的事务ID
* Transaction Coordinator (TC)：
    * 事务协调器，维护全局事务的运行状态，负责协调并驱动全局事务的提交或回滚
    * 可以理解成seata服务器
* Transaction Manager (TM)：
    * 控制全局事务的边界，负责开启一个全局事务，并最终发起全局提交或全局回滚的决议
    * 可以理解成加上@GlobalTransactional注解发起事务的service
* Resource Manager (RM)：
    * 控制分支事务，负责分支注册，状态汇报，并接收事务协调器的指令，驱动分支（本地）事务的提交和回滚
    * 可以理解成参与事务的各个数据库

# seata处理分布式事务的过程
* TM 向 TC 申请一个全局事务，全局事务创建成功并生成一个全局唯一的 XID
* XID 在微服务调用链路的上下文中传播
* RM 向 TC 注册分支事务，将其纳入 XID 对应全局事务的管辖
* TM 向 TC 发起针对 XID 的全局提交或回滚决议
* TC 调度 XID 下管辖的全部分支事务完成提交或回滚
![seata](./images/seata.png)

# 分布式事务的执行流程
* TM 开启分布式事务（TM 向 TC 注册全局事务记录）
* 按业务场景，编排数据库和服务等事务内资源（RM 向 TC 汇报资源准备状态）
* TM 结束分布式事务，事务一阶段结束（TM 通过 TC 提交/回滚分布式事务）
* TC 汇总事务信息，决定分布式事务是提交还是回滚
* TC 通知所有 RM 提交/回滚资源，事务二阶段结束

# 修改seata配置
* 自定义事务组名称
```text
修改conf/file.conf
service {
    #vgroup->rgroup
    vgroup_mapping.fsp_tx_group = "default"
    #only support single node
    default.grouplist = "127.0.0.1:8091"
    #degrade current not support
    enableDegrade = false
    #disable
    disable = false
    #unit ms,s,m,h,d represents milliseconds, seconds, minutes, hours, days, default permanent
    max.commit.retry.timeout = "-1"
    max.rollback.retry.timeout = "-1"
}
```
* 事务日志存储模式设置为db
```text
执行conf/db_store.sql脚本创建数据库
修改conf/file.conf
```text
修改conf/file.conf
store {
    ## store mode: file、db
    mode = "db"
    
    ## database store
    db {
        ## the implement of javax.sql.DataSource, such as DruidDataSource(druid)/BasicDataSource(dbcp) etc.
        datasource = "dbcp"
        ## mysql/oracle/h2/oceanbase etc.
        db-type = "mysql"
        driver-class-name = "com.mysql.jdbc.Driver"
        url = "jdbc:mysql://192.168.121.100:3306/seata"
        user = "root"
        password = "123456"
        min-conn = 1
        max-conn = 3
        global.table = "global_table"
        branch.table = "branch_table"
        lock-table = "lock_table"
        query-limit = 100
    }
}
```
* 注册中心连接信息
```text
修改conf/registry.conf
registry {
    # file 、nacos 、eureka、redis、zk、consul、etcd3、sofa
    type = "nacos"

    nacos {
        serverAddr = "192.168.121.100:8848"
        namespace = ""
        cluster = "default"
    }
}
```