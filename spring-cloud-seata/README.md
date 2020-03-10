# seata术语
* Transaction ID XID：全局唯一的事务ID
* Transaction Coordinator (TC)：事务协调器，维护全局事务的运行状态，负责协调并驱动全局事务的提交或回滚
* Transaction Manager (TM)：控制全局事务的边界，负责开启一个全局事务，并最终发起全局提交或全局回滚的决议
* Resource Manager (RM)：控制分支事务，负责分支注册，状态汇报，并接收事务协调器的指令，驱动分支（本地）事务的提交和回滚

# seata处理分布式事务的过程
* TM 向 TC 申请一个全局事务，全局事务创建成功并生成一个全局唯一的 XID
* XID 在微服务调用链路的上下文中传播
* RM 向 TC 注册分支事务，将其纳入 XID 对应全局事务的管辖
* TM 向 TC 发起针对 XID 的全局提交或回滚决议
* TC 调度 XID 下管辖的全部分支事务完成提交或回滚
![seata](./images/seata.png)

# 修改seata配置
* 自定义事务组名称
```text
修改conf/file.conf
service {
    vgroup_mapping.my_test_tx_group = "fsp_tx_group"
    
    default.grouplist = "127.0.0.1:8848"
    enableDegrade = false
    disable = false
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
    ## store mode: file, db
    mode = "db"
    
    db {
        url = "jdbc:mysql://192.168.121.100:3306/seata"
        user = "root"
        password = "123456"
    }
}
```
* 注册中心连接信息
```text
修改conf/registry.conf
registry {
    ## file, nacos, eureka, redis, zk, consul, etcd3, sofa
    type = "nacos"

    nacos {
        serverAddr = "192.168.121.100:8848"
        namespace = ""
        cluster = "default"
    }
}
```